package lsv.core;

import lsv.model.*;

import lsv.grammar.Formula;

import java.util.*;

/**
 * Class implementing a model checker for asCTL formulae.
 *
 */
public class SimpleModelChecker implements ModelChecker {

	private ExecutionGraph graph = new ExecutionGraph();
	private ArrayList<String> trace;

	/**
	 * Determines whether a formula holds for the given model by looping through
	 * all of the initial states and calling the appropriate methods for
	 * state/path formula checking.
	 */
	public boolean check(Model model, Formula constraint, Formula formula) {
		ArrayList<GraphNode> initialSt = graph.getInits();
		boolean formulaHolds = true;
		// Go through all of the initial states and check if the formula holds
		// for all of them
		for (int index = 0; index < initialSt.size(); index++) {
			trace = new ArrayList<>();
			State initSt = initialSt.get(index).getState();
			// If formula has quantifiers in front of it, it is going to be a
			// path formula
			if (formula.getQuantifier() != null && formula.getQuantifier().length() != 0) {
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

	/**
	 * Checks the validity of a formula with "until" as the main operator, and
	 * "exists" as the quantifier
	 * 
	 * @param formula
	 *            - formula to check
	 * @param state
	 *            - will start checking from this state
	 * @param transitionName
	 *            - transition that lead to this state. If this is the first
	 *            state we check the formula for, pass "first" as the argument.
	 * @param transitionsToCheck
	 *            - a list of transitions (branching paths) that we still have
	 *            to check in the case the current path doesn't succeed
	 * @param model
	 * @return - boolean value true if the formulae holds and false otherwise
	 */
	public boolean checkUntil(Formula formula, State state, String transitionName,
			HashMap<String, ArrayList<Transition>> transitionsToCheck, Model model, String[] actionsA,
			String[] actionsB, boolean forAll, boolean isNext) {
		System.out.println("Check until " + getStringFormula(formula) + " state " + state.getName());
		Formula[] contents = getNestedContents(formula);
		boolean reached = false;
		// If we got to the current state via an action of the second set and
		// part two holds, then formula holds
		if (((actionsB == null || contains(actionsB, transitionName)) && !(transitionName.equals("first") && isNext))
				|| (transitionName.equals("first") && !isNext)) {
			// for(int i = trace.size()-1; i>=0; i--) {
			// if(!trace.get(i).equals("\\")) {
			// trace.remove(i);
			// }
			// else {
			// break;
			// }
			// }
			if (!transitionName.equals("first")) {
				trace.add(transitionName);
			}
			if (checkStateFormula(contents[1], state, model)) {
				// if (!forAll) {
				// return true;
				// }
				// else {
				// reached = true;
				// }
				return true;
			}
		}
		// else {
		// if(forAll&&(transitionsToCheck.get(state.getName())==null
		// ||transitionsToCheck.get(state.getName()).size()==0)) {
		// if(!trace.get(trace.size()-1).equals(transitionName)) {
		// trace.add(transitionName);
		// }
		// return false;
		// }
		// }
		// Otherwise check if we got here via an action from the first action
		// set
		if (actionsA == null || contains(actionsA, transitionName) || transitionName.equals("first")) {
			if (!transitionName.equals("first")) {
				trace.add(transitionName);
			}
			// If so, check whether the first part of the formula holds
			if (checkStateFormula(contents[0], state, model)) {
				// If there are any transitions we can take from here, do
				// that
				if (transitionsToCheck.get(state.getName()) != null) {
					// Iterate over all possible transitions
					Iterator<Transition> iterator = transitionsToCheck.get(state.getName()).iterator();
					if (transitionsToCheck.get(state.getName()) != null
							&& transitionsToCheck.get(state.getName()).size() > 1) {
						trace.add("\\");
					}
					boolean first = true;
					while (iterator.hasNext()) {
						// for(int i = trace.size()-1; i>=0; i--) {
						// if(!trace.get(i).equals("")) {
						// trace.remove(i);
						// }
						// else {
						// break;
						// }
						// }
						if (!first) {
							for (int i = trace.size() - 1; i >= 0; i--) {
								if (!trace.get(i).equals("\\")) {
									trace.remove(i);
								} else {
									break;
								}
							}
						}
						first = false;
						Transition transition = iterator.next();
						String nextStateStr = transition.getTarget();
						State nextState = graph.getStateNameTable().get(nextStateStr).getState();
						iterator.remove();
						// Check the same path formula for the destination
						// state
						if (reached = checkUntil(formula, nextState, transition.getActions()[0], transitionsToCheck,
								model, actionsA, actionsB, forAll, isNext)) {
							if (!forAll) {
								return true;
							}
							// else {
							// if (forAll || transitionsToCheck.size()==0) {
							// return false;
							// }
							// }
						} else {
							if (forAll) {
								return false;
							}
						}
					}
				}
			}
		} else {
			if (forAll && !contains(actionsB, transitionName)) {
				trace.add(transitionName);
				return false;
			}
		}
		return reached;
	}

	public boolean contains(String[] array, String element) {
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
			result = checkStateFormula(contents[0], state, model) && checkStateFormula(contents[1], state, model);
			break;
		case "||":
			System.out.println("Case ||");
			result = (checkStateFormula(contents[0], state, model) || checkStateFormula(contents[1], state, model));
			break;
		case "=>":
			System.out.println("Case =>");
			result = (!checkStateFormula(contents[0], state, model) || checkStateFormula(contents[1], state, model));
			break;
		case "<=>":
			System.out.println("Case <=>");
			result = (checkStateFormula(contents[0], state, model) == checkStateFormula(contents[1], state, model));
			break;
		}
		return negation ? !result : result;
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
		while(trace.contains("\\")) {
			trace.remove("\\");
		}
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

	public void setExecutionGraph(Model model) {
		graph.createGraph(model);
	}

	public ExecutionGraph getExecutionGraph() {
		return graph;
	}

	public static void main(String[] args) {

		SimpleModelChecker smc = new SimpleModelChecker();

		// Determine model and formula
		// TODO pass these as command line arguments
		Model model = Builder.buildModel("test/resources/ourTests/alwaysModel.json");
		Formula formula = Builder.buildFormula("test/resources/ourTests/conjunctionTestFormula.json");

		// System.out.println(formula.getOperator().length());

		// Create execution graph
		smc.graph = new ExecutionGraph();
		smc.graph.createGraph(model);
		smc.graph.printTransitionStateDetails();

		// Check for the result
		boolean result = smc.check(model, null, formula);
		System.out.println("Obtained: " + result);
		System.out.println(smc.getStringArray(smc.getTrace()));
	}
}