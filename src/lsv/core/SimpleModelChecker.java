package lsv.core;

import lsv.model.*;
import lsv.grammar.Formula;
import java.util.*;

/**
 * Class implementing a model checker for asCTL formulae.
 *
 */
public class SimpleModelChecker implements ModelChecker {

//	private ExecutionGraph graph = new ExecutionGraph();
	private ArrayList<String> trace;
	private Formula constraint = null;
	private HashMap<String, State> stateNameTable = new HashMap<>();
	private ArrayList<State> inits = new ArrayList<>();
	private final String SEPARATOR = "\\";
	private final String JUMP = "jump back";

	/**
	 * Determines whether a formula holds for the given model by looping through
	 * all of the initial states and calling the appropriate methods for
	 * state/path formula checking.
	 */
	public boolean check(Model model, Formula constraint, Formula formula) {
		if (constraint != null) {
			this.constraint = constraint;
			if (constraint.getQuantifier() != null) {
				boolean constraintHolds = false;
				for (int index = 0; index < inits.size(); index++) {
					trace = new ArrayList<>();
					State initSt = inits.get(index);
					if (!checkFormulaKind(constraint, initSt, model)) {
						if(constraint.getQuantifier().contains("A")) {
							return false;
						}
					}
					else {
						constraintHolds = true;
						if(constraint.getQuantifier().contains("E")) {
							break;
						}
					}
				}
				if(!constraintHolds) {
					trace.clear();
					trace.add("No paths were found that satisfy the constraint");
					return false;
				}
				if(constraint.getQuantifier().contains("A")) {
					constraint = null;
				}
				else {
					
				}
			} else {
				for (int index = 0; index < inits.size(); index++) {
					trace = new ArrayList<>();
					State initSt = inits.get(index);
					if (!checkStateFormula(constraint, initSt, model)) {
						return false;
					}
				}
			}
		}
		boolean formulaHolds = true;
		// Go through all of the initial states and check if the formula holds
		// for all of them
		for (int index = 0; index < inits.size(); index++) {
			trace = new ArrayList<>();
			State initSt = inits.get(index);
			// If formula has quantifier E in front of it, formula has to hold
			// for at least one initial state
			// if A - for all initial states
			if (formula.getQuantifier() != null && formula.getQuantifier().contains("E")) {
				if (formulaHolds = checkFormulaKind(formula, initSt, model)) {
					return true;
				}
			} else {
				if (!(formulaHolds = checkFormulaKind(formula, initSt, model))) {
					return false;
				}
			}
		}
		trace.clear();
		if(formula.getQuantifier().contains("E")) {
			trace.add("No paths that satisfy the formula found");
		}
		else {
			trace.add("All paths satisfy the formula");
		}
		return formulaHolds;
	}

	/**
	 * Creates a map between all states in the model and the relevant
	 * transitions (the ones that are in either action set A or set B)
	 * 
	 * @param model
	 * @param actionsA
	 *            - set of actions
	 * @param actionsB
	 *            - set of actions
	 * @return the hashmap between state names and all relevant transitions from
	 *         these states
	 */
	public HashMap<String, ArrayList<Transition>> getAllTransitions(Model model, String[] actionsA, String[] actionsB,
			boolean forAll) {
		HashMap<String, ArrayList<Transition>> table = new HashMap<>();
		// Loop through all the transitions listed for the model
		for (Transition transition : model.getTransitions()) {
			// Transitions is relevant if it is contained by either of the
			// action sets
			// If an action set is not specified, we assume that all transitions
			// are relevant
			if (actionsA == null || actionsB == null || contains(actionsB, transition.getActions()[0])
					|| contains(actionsA, transition.getActions()[0]) || forAll) {
				if (table.containsKey(transition.getSource())) {
					ArrayList<Transition> transitionList = table.get(transition.getSource());
					transitionList.add(transition);
					table.put(transition.getSource(), transitionList);
				} else {
					ArrayList<Transition> transitionList = new ArrayList<>();
					transitionList.add(transition);
					table.put(transition.getSource(), transitionList);
				}
			}
		}
		return table;
	}
	
	public boolean checkConstraint(Formula constraint, State state, String transitionName) {
		
		return false;
	}

	/**
	 * Checks the validity of a formula with "until" as the main operator, and
	 * "exists" as the quantifier
	 * 
	 * @param formula
	 *            - formula to check
	 * @param state
	 *            - will start checking from this state
	 * @param actionName
	 *            - transition that lead to this state. If this is the first
	 *            state we check the formula for, pass "first" as the argument.
	 * @param transitionsToCheck
	 *            - a list of transitions (branching paths) that we still have
	 *            to check in the case the current path doesn't succeed
	 * @param model
	 * @return - boolean value true if the formulae holds and false otherwise
	 */
	public boolean checkUntil(Formula formula, State state, String actionName,
			HashMap<String, ArrayList<Transition>> transitionsToCheck, Model model, String[] actionsA,
			String[] actionsB, boolean forAll, boolean isNext) {
		System.out.println("Check until " + getStringFormula(formula) + " state " + state.getName());
		Formula[] contents = getNestedContents(formula);
		boolean reached = false;
		// If we got to the current state via an action of the second set and
		// part two holds, then formula holds
		if (((actionsB == null || contains(actionsB, actionName)) && !(actionName.equals("first") && isNext))
				|| (actionName.equals("first") && !isNext)) {
			if (!actionName.equals("first")) {
				trace.add(actionName);
			}
			if (checkFormulaKind(contents[1], state, model)) {
				return true;
			}
			if(!actionName.equals("first")) {
				trace.remove(trace.size()-1);
				trace.remove(trace.size()-1);
			}
		}
		// Otherwise check if we got here via an action from the first action
		// set
		if (actionsA == null || contains(actionsA, actionName) || actionName.equals("first")) {
			if (!actionName.equals("first")) {
				trace.add(actionName);
			}
			// If so, check whether the first part of the formula holds
			if (checkFormulaKind(contents[0], state, model)) {
				// If there are any transitions we can take from here, do
				// that
				if (transitionsToCheck.get(state.getName()) != null) {
					// Iterate over all possible transitions
					Iterator<Transition> iterator = transitionsToCheck.get(state.getName()).iterator();
					if (transitionsToCheck.get(state.getName()) != null
							&& transitionsToCheck.get(state.getName()).size() > 1) {
						trace.add(SEPARATOR);
					}
					boolean first = true;
					while (iterator.hasNext()) {
//						if (!first) {
//							boolean here = trace.contains("here")||trace.contains("here1");
//							for (int i = trace.size() - 1; i >= 0; i--) {
//								System.out.println(getStringArray(getTrace()));
//								if (!trace.get(i).equals(SEPARATOR)) {
//									trace.remove(i);
//								} else {
//									if(here) {
//										trace.remove(i);
//										here = false;
//									}
//									else {
//										break;
//									}
//								}
//							}
//						}
						first = false;
						Transition transition = iterator.next();
						String nextStateStr = transition.getTarget();
						State nextState = stateNameTable.get(nextStateStr);
						iterator.remove();
						// Check the same path formula for the destination
						// state
						if (reached = checkUntil(formula, nextState, transition.getActions()[0], transitionsToCheck,
								model, actionsA, actionsB, forAll, isNext)) {
							if (!forAll) {
								return true;
							}
							if(!trace.get(trace.size()-1).equals(JUMP)) {
								trace.add(JUMP);
							}
						} else {
							if (forAll) {
								return false;
							}
							if(!trace.get(trace.size()-1).equals(JUMP)) {
								trace.add(JUMP);
							}
						}
					}
				}
			}
		} else {
			if (forAll && !contains(actionsB, actionName)) {
				trace.add(actionName);
				return false;
			}
		}
		return reached;
	}

	public boolean contains(String[] array, String element) {
		if (array == null) {
			return true;
		}
		if (array.length == 0) {
			return false;
		}
		for (String s : array) {
			if (s != null && s.equals(element)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns two formulas that are the nested contents of a formula containing
	 * a binary operator
	 * 
	 * @param formula
	 * @return - array of formulas containing the first and the second component
	 *         of the given formula
	 */
	public Formula[] getNestedContents(Formula formula) {
		System.out.println("Get nested contents");
		// Results are either APs or CTLs or a mix of both
		String[] aps = formula.getAp();
		Formula[] ctls = formula.getNestedCTL();
		String[] tautologies = formula.getTautology();
		Formula[] contents = new Formula[2];
		for (int i = 0; i < aps.length; i++) {
			if (aps[i] != null) {
				String apToPass = aps[i];
				// If the ap is negated, we need to add the negation to the
				// beginning of the ap string for it to be parsed correctly
				if (formula.getApNeg() != null && formula.getApNeg()[i]) {
					apToPass = "¬" + apToPass;
				}
				contents[i] = new Formula(apToPass);
			}
			if (ctls != null) {
				if (ctls[i] != null) {
					contents[i] = ctls[i];
				}
			}
			if (tautologies != null && tautologies[i] != null && tautologies[i].equalsIgnoreCase("True")) {
				contents[i] = new Formula(true);
			}
			if (tautologies != null && tautologies[i] != null && tautologies[i].equalsIgnoreCase("False")) {
				contents[i] = new Formula(false);
			}
		}
		return contents;
	}

	/**
	 * Checks validity of a formula for the given state
	 * 
	 * @param formula
	 * @param state
	 * @param model
	 * @return
	 */
	public boolean checkStateFormula(Formula formula, State state, Model model) {
		System.out.println("Checking the state formula " + getStringFormula(formula) + " for state " + state.getName()
				+ " with labels: " + getStringArray(state.getLabel()));
		if (!trace.isEmpty()) {
			if (!trace.get(trace.size() - 1).equals(state.getName())) {
				trace.add(state.getName());
			}
		} else {
			trace.add(state.getName());
		}
		// If formula is a negation, it will have to be treated differently
		boolean negation = formula.isNegation();
		boolean result = false;

		if (formula.isSingleTt()) {
			return negation ? !checkTautology(formula) : checkTautology(formula);
		}

		if (formula.isSingleAp()) {
			return negation ? !checkAP(formula, state) : checkAP(formula, state);
		}

		// In case formula contains binary operator, split it up and evaluate
		// according to the operator
		Formula[] contents = getNestedContents(formula);
		switch (formula.getOperator()) {
		case "&&":
			System.out.println("Case &&");
			result = checkFormulaKind(contents[0], state, model) && checkFormulaKind(contents[1], state, model);
			break;
		case "||":
			System.out.println("Case ||");
			result = (checkFormulaKind(contents[0], state, model) || checkFormulaKind(contents[1], state, model));
			break;
		case "=>":
			System.out.println("Case =>");
			result = (!checkFormulaKind(contents[0], state, model) || checkFormulaKind(contents[1], state, model));
			break;
		case "<=>":
			System.out.println("Case <=>");
			result = (checkFormulaKind(contents[0], state, model) == checkFormulaKind(contents[1], state, model));
			break;
		}
		return negation ? !result : result;
	}

	public boolean checkFormulaKind(Formula formula, State state, Model model) {
		if (formula.getQuantifier() == null) {
			return checkStateFormula(formula, state, model);
		}
		return checkPathFormula(formula, state, model);
	}

	public boolean checkPathFormula(Formula formula, State state, Model model) {
		System.out.println("Check path formula" + getStringFormula(formula));
		boolean negation = formula.isNegation();

		boolean allQuantifier = false;

		// If the quantifier is the universal one
		if (formula.getQuantifier().substring(0, 1).equals("A")) {
			allQuantifier = true;
		}

		String operator = "";
		if (formula.getOperator() != null && formula.getOperator().equals("U")) {
			operator = formula.getOperator();
		} else {
			operator = formula.getQuantifier().substring(1);
		}

		boolean result = false;

		// These are going to be used later in case of X, F and G
		Formula trueTautology = new Formula(true);
		Formula[] secondPart = getNestedContents(formula);
		Formula nested = secondPart[0];
		if (secondPart[1] != null) {
			nested = new Formula(secondPart[0], secondPart[1], formula.getOperator());
		}
		Formula transformedToU;

		switch (operator) {
		case "U":
			result = checkUntil(formula, state, "first",
					getAllTransitions(model, formula.getActions()[0], formula.getActions()[1], allQuantifier), model,
					formula.getActions()[0], formula.getActions()[1], allQuantifier, false);
			return negation ? !result : result;

		case "X":
			transformedToU = new Formula(trueTautology, nested, "U");
			result = checkUntil(transformedToU, state, "first",
					getAllTransitions(model, formula.getActions()[0], new String[0], allQuantifier), model,
					new String[0], formula.getActions()[0], allQuantifier, true);
			return negation ? !result : result;
		case "F":
			transformedToU = new Formula(trueTautology, nested, "U");
			result = checkUntil(transformedToU, state, "first",
					getAllTransitions(model, formula.getActions()[0], formula.getActions()[1], allQuantifier), model,
					formula.getActions()[0], formula.getActions()[1], allQuantifier, false);
			return negation ? !result : result;
		case "G":
			transformedToU = new Formula(trueTautology, new Formula(true, nested), "U");
			result = checkUntil(transformedToU, state, "first",
					getAllTransitions(model, formula.getActions()[0], formula.getActions()[1], allQuantifier), model,
					formula.getActions()[0], formula.getActions()[1], allQuantifier, false);
			return negation ? result : !result;
		}

		return false;
	}

	/**
	 * Checks the validity of an atomic proposition at the given state.
	 * 
	 * @param formula
	 *            - AP to check
	 * @param state
	 * @return
	 */
	public boolean checkAP(Formula formula, State state) {
		System.out.print("Check AP " + formula.getAp()[0]);
		// Single APs are stored as the first components of the AP array
		String ap = formula.getAp()[0];
		// Go through all of the labels of the given state
		for (String label : state.getLabel()) {
			if (label.equals(ap)) {
				// If a label matches the AP, yet AP is negated, it doesn't hold
				if (formula.getApNeg() != null && formula.getApNeg()[0]) {
					System.out.println(" false");
					return false;
				} else {
					System.out.println(" true");
					return true;
				}
			}
		}
		// If we haven't found the AP amongst the labels, and AP is negated, it
		// holds.
		if (formula.getApNeg() != null && formula.getApNeg()[0]) {
			System.out.println(" true");
			return true;
		}
		System.out.println(" false");
		return false;
	}

	/**
	 * Returns true for "True" tautology and false for a "False" one.
	 * 
	 * @param formula
	 * @return
	 */
	public boolean checkTautology(Formula formula) {
		if (formula.getTautology()[0].equalsIgnoreCase("True")) {
			return true;
		} else {
			return false;
		}
	}

	public String[] getTrace() {
//		while (trace.contains(SEPARATOR)) {
//			trace.remove(SEPARATOR);
//		}
		String[] array = new String[trace.size()];
		return trace.toArray(array);
	}

	/**
	 * Returns a string interpretation of a given formula
	 * 
	 * @param formula
	 * @return
	 */
	public String getStringFormula(Formula formula) {
		String actions = "";
		if (formula.getActions() != null && formula.getActions()[0] != null) {
			actions += getStringArray(formula.getActions()[0]);
		}
		if (formula.getActions() != null && formula.getActions()[1] != null) {
			actions += " " + getStringArray(formula.getActions()[1]);
		}
		if (actions.length() == 0) {
			actions = "no actions ";
		}
		String result = "Negation " + formula.isNegation() + ", quantifier " + formula.getQuantifier() + ", operator "
				+ formula.getOperator() + ", actions " + actions + ", ap " + getStringArray(formula.getAp());
		return result;
	}

	/**
	 * Returns a concatenation of the string representations of the array
	 * elements.
	 * 
	 * @param array
	 * @return
	 */
	public String getStringArray(String[] array) {
		String result = "";
		if (array != null) {
			for (String s : array) {
				result = result + " " + s + " ";
			}
		}
		return result;
	}
	
	public void setStates(Model model) {
		for (State state : model.getStates()) {
			stateNameTable.put(state.getName(), state);
			if (state.isInit()) {
				inits.add(stateNameTable.get(state.getName()));
			}
		}
	}

	public static void main(String[] args) {

		SimpleModelChecker smc = new SimpleModelChecker();

		// Determine model and formula
		// TODO pass these as command line arguments
		Model model = Builder.buildModel("test/resources/model2.json");
		Formula formula = Builder.buildFormula("test/resources/ctl.json");
		
		smc.setStates(model);

		// Check for the result
		boolean result = smc.check(model, null, formula);
		System.out.println("Obtained: " + result);
		System.out.println(smc.getStringArray(smc.getTrace()));
	}
}