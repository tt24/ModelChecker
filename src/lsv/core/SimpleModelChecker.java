package lsv.core;

import lsv.model.*;

import lsv.grammar.Formula;

import java.util.*;

/**
 * Class implementing a model checker for asCTL formulae.
 *
 */
public class SimpleModelChecker implements ModelChecker {

	ExecutionGraph graph;

	/**
	 * Determines whether a formula holds for the given model by looping through all of the initial states 
	 * and calling the appropriate methods for state/path formula checking.
	 */
	public boolean check(Model model, Formula constraint, Formula formula) {

		ArrayList<GraphNode> initialSt = graph.getInits();
		boolean formulaHolds = true;
		// Go through all of the initial states and check if the formula holds for all of them
		for (int index = 0; index < initialSt.size(); index++) {
			State initSt = initialSt.get(index).getState();
			// If formula has quantifiers in front of it, it is going to be a path formula
			if (formula.getQuantifier()!=null && formula.getQuantifier().length()!=0) {
				formulaHolds = formulaHolds && checkPathFormula(formula, initSt, model);
			}
			// Otherwise it is a state formula
			else {
				formulaHolds = formulaHolds && checkStateFormula(formula, initSt, model);
			}
		}
		return formulaHolds;
	}


	/**
	 * Creates a map between all states in the model and the relevant transitions (the ones that are in either action set A or set B)
	 * @param model
	 * @param actionsA - set of actions
	 * @param actionsB - set of actions
	 * @return the hasmap between state names and all relevant transitions from these states
	 */
	public HashMap<String, ArrayList<Transition>> getAllTransitions(Model model, String[] actionsA, String[] actionsB) {
		HashMap<String, ArrayList<Transition>> table = new HashMap<>();
		// Loop through all the transitions listed for the model
		for (Transition transition : model.getTransitions()) {
			// Transitions is relevant if it is contained by either of the action sets
			// If an action set is not specified, we assume that all transitions are relevant
			if ((actionsA!=null && actionsA.length == 0) || (actionsB!=null && actionsB.length == 0) || contains(actionsB, transition.getActions()[0])
					|| contains(actionsA, transition.getActions()[0])) {
				if (table.containsKey(transition.getSource())) {
					ArrayList<Transition> transitionList = table.get(transition.getSource());
					transitionList.add(transition);
					table.put(transition.getSource(), transitionList);
				} 
				else {
					ArrayList<Transition> transitionList = new ArrayList<>();
					transitionList.add(transition);
					table.put(transition.getSource(), transitionList);
				}
			}
		}
		return table;
	}

	/**
	 * Checks the validity of a formula with "until" as the main operator, and "exists" as the quantifier
	 * @param formula - formula to check
	 * @param state - will start checking from this state
	 * @param transitionName - transition that lead to this state. If this is the first state we check the formula for, pass "first" as the argument.
	 * @param transitionsToCheck  - a list of transitions (branshing paths) that we still have to check in the case the current path doesn't succeed
	 * @param model
	 * @return - boolean value true if the formulae holds and false otherwise
	 */
	public boolean checkUntil(Formula formula, State state, String transitionName,
			HashMap<String, ArrayList<Transition>> transitionsToCheck, Model model, String[] actionsA, String[] actionsB) {
		System.out.println("Check until " + getStringFormula(formula) + " state " + state.getName());
		Formula[] contents = getNestedContents(formula);
		// If this is the first state we check the formula for, and the second part holds, formula holds
		if (transitionName.equals("first")) {
			if (checkStateFormula(contents[1], state, model)) {
				return true;
			}
			// Otherwise if the first part of the formula doesn't hold, it is invalid
			else {
				if (!checkStateFormula(contents[0], state, model)) {
					return false;
				}
			}
		}

		//		// If both parts hold, we explore all of the states we can reach from this formula via relevant transitions (if there are any)
		//		String[] actionsA = formula.getActions()[0];
		//		String[] actionsB = formula.getActions()[1];

		// If we got to the current state via an action of the second set and part two holds, then formula holds
		if (actionsB.length == 0 || contains(actionsB, transitionName)) {
			if (checkStateFormula(contents[1], state, model)) {
				return true;
			}
		} 
		// Otherwise check if we got here via an action from the first action set
		else {
			if (actionsA.length == 0 || contains(actionsA, transitionName) || transitionName.equals("first")) {
				// If so, check whether the first part of the formula holds
				if (checkStateFormula(contents[0], state, model)) {
					// If there are any transitions we can take from here, do that
					if (transitionsToCheck.get(state.getName()) != null) {
						// Iterate over all possible transitions
						Iterator<Transition> iterator = transitionsToCheck.get(state.getName()).iterator();
						while (iterator.hasNext()) {
							Transition transition = iterator.next();
							String nextStateStr = transition.getTarget();
							State nextState = graph.getStateNameTable().get(nextStateStr).getState();
							iterator.remove();
							// Check the same path formula for the destination state
							if (checkUntil(formula, nextState, transition.getActions()[0], transitionsToCheck, model, actionsA, actionsB)) {
								return true;
							}
						}
					}
				}

			}
		}
		return false;
	}


	public boolean contains(String[] array, String element) {
		if (array == null) {
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
	 * Returns two formulas that are the nested contents of a formula containing a binary operator
	 * @param formula
	 * @return - array of formulas containing the first and the second component of the given formula
	 */
	public Formula[] getNestedContents(Formula formula) {
		System.out.println("Get nested contents");
		// Results are either APs or CTLs or a mix of both
		String[] aps = formula.getAp();
		Formula[] ctls = formula.getNestedCTL();
		Formula[] contents = new Formula[2];
		for (int i = 0; i < aps.length; i++) {
			if (aps[i] != null) {
				contents[i] = new Formula(aps[i]);
			} else {
				if (ctls != null) {
					contents[i] = ctls[i];
				}
			}
		}
		return contents;
	}

	/**
	 * Checks validity of a formula for the given state
	 * @param formula
	 * @param state
	 * @param model
	 * @return
	 */
	public boolean checkStateFormula(Formula formula, State state, Model model) {
		System.out.println("Checking the state formula " + getStringFormula(formula) + " for state " + state.getName()
		+ " with labels: " + getStringArray(state.getLabel()));
		// If formula is a negation, it will have to be treated differently
		boolean negation = formula.isNegation();

		if (formula.isSingleTt()) {
			return checkTautology(formula) && !negation;
		}

		if (formula.isSingleAp()) {
			return checkAP(formula, state) && !negation;
		}

		// In case formula contains binary operator, split it up and evaluate according to the operator
		Formula[] contents = getNestedContents(formula);
		switch (formula.getOperator()) {
		case "&&":
			System.out.println("Case &&");
			return !negation && checkStateFormula(contents[0], state, model)
					&& checkStateFormula(contents[1], state, model);
		case "||":
			System.out.println("Case ||");
			return !negation
					&& (checkStateFormula(contents[0], state, model) || checkStateFormula(contents[1], state, model));
		case "=>":
			System.out.println("Case =>");
			return !negation
					&& (!checkStateFormula(contents[0], state, model) || checkStateFormula(contents[1], state, model));
		case "<=>":
			System.out.println("Case <=>");
			return !negation
					&& (checkStateFormula(contents[0], state, model) == checkStateFormula(contents[1], state, model));
		}
		return false;
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
		if (formula.getOperator()!=null && formula.getOperator().length()!=0) {
			operator = formula.getOperator();
		}
		else {
			operator = formula.getQuantifier().substring(1);
		}

		switch (operator) {
		case "U":
			if (allQuantifier) {
				return negation && !checkUntil(formula, state, "first",
						getAllTransitions(model, formula.getActions()[0], formula.getActions()[1]), model, formula.getActions()[0], formula.getActions()[1]);
			}
			return !negation && checkUntil(formula, state, "first",
					getAllTransitions(model, formula.getActions()[0], formula.getActions()[1]), model, formula.getActions()[0], formula.getActions()[1]);
		case "X":
			Formula trueTautology = new Formula(true);
			Formula innerFormula = getInnerFormula(formula);
			Formula transformedToU = new Formula(trueTautology, innerFormula, "U");
			if (allQuantifier) {
				return negation && !checkUntil(formula, state, "first",
						getAllTransitions(model, formula.getActions()[0], formula.getActions()[1]), model, formula.getActions()[0], formula.getActions()[1]);
			}
			return !negation && checkUntil(formula, state, "first",
					getAllTransitions(model, formula.getActions()[0], formula.getActions()[1]), model, formula.getActions()[0], formula.getActions()[1]);
		case "G":
			// TODO
		case "F":
			// TODO
		}

		return false;
	}


	public Formula getInnerFormula(Formula formula) {

		String[] aps = formula.getAp();
		Formula[] ctls = formula.getNestedCTL();
		Formula innerFormula;
		if (formula.getOperator() != null) {
			if (ctls != null && (ctls[0] != null || ctls[1] != null)) {
				if (ctls[0] != null && ctls[1] != null) {
					innerFormula = new Formula(ctls[0], ctls[1], formula.getOperator());
				}
				else {
					if (aps[0] != null) {
						innerFormula = new Formula(aps[0], ctls[1], formula.getOperator());
					}
					else {
						innerFormula = new Formula(ctls[0], aps[1], formula.getOperator());
					}
				}
			}
			else {
				innerFormula = new Formula(aps[0], aps[1], formula.getOperator());
			}
		}
		else {
			if (ctls != null) {
				innerFormula = ctls[0];
			}
			else {
				innerFormula = new Formula(aps[0]);
			}
		}
		
		return innerFormula;
	}

	/**
	 * Checks the validity of an atomic proposition at the given state.
	 * @param formula - AP to check
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
				if (formula.getApNeg()[0]) {
					System.out.println(" false");
					return false;
				}
				else {
					System.out.println(" true");
					return true;
				}
			}
		}
		// If we haven't found the AP amongst the labels, and AP is negated, it holds.
		if (formula.getApNeg()[0]) {
			System.out.println(" true");
			return true;
		}
		System.out.println(" false");
		return false;
	}

	/**
	 * Returns true for "True" tautology and false for a "False" one.
	 * @param formula
	 * @return
	 */
	public boolean checkTautology(Formula formula) {
		if (formula.getTautology()[0].equals("True")) {
			return true;
		} else {
			return false;
		}
	}

	
	public String[] getTrace() {
		// TODO
		// TO IMPLEMENT
		return null;
	}

	/**
	 * Returns a string interpretation of a given formula
	 * @param formula
	 * @return
	 */
	public String getStringFormula(Formula formula) {
		String actions = "";
		if (formula.getActions() != null) {
			actions += getStringArray(formula.getActions()[0]) + " " + getStringArray(formula.getActions()[1]);
		}
		String result = "Negation " + formula.isNegation() + ", quantifier " + formula.getQuantifier() + ", operator "
				+ formula.getOperator() + ", actions " + actions + ", ap " + getStringArray(formula.getAp());
		return result;
	}

	/**
	 * Returns a concatenation of the string representations of the array elements.
	 * @param array
	 * @return
	 */
	public String getStringArray(String[] array) {
		String result = "";
		for (String s : array) {
			result = result + " " + s + " ";
		}
		return result;
	}

	
	public static void main(String[] args) {

		SimpleModelChecker smc = new SimpleModelChecker();

		// Determine model and formula
		//TODO pass these as command line arguments
		Model model = Builder.buildModel("test/resources/ourModel.json");
		Formula formula = Builder.buildFormula("test/resources/ctlNestedTest.json");
		Formula fs = smc.getInnerFormula(formula);
		//System.out.println(formula.getOperator().length());

		//		// Create execution graph
		//		smc.graph = new ExecutionGraph();
		//		smc.graph.createGraph(model);
		//		smc.graph.printTransitionStateDetails();
		//
		//		// Check for the result
		//		boolean result = smc.check(model, null, formula);
		//		System.out.println("Obtained: " + result);
	}
}