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

	//TODO we need to use this method
	public boolean check(Model model, Formula constraint, Formula formula) {
		if (formula.isSingleTt()) {
			return checkTautology(formula);
		}
		if (formula.isSingleAp()) {

		}
		// TO IMPLEMENT
		return false;
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
			if (actionsA.length == 0 || actionsB.length == 0 || contains(actionsB, transition.getActions()[0])
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
			HashMap<String, ArrayList<Transition>> transitionsToCheck, Model model) {
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
		
		// If both parts hold, we explore all of the states we can reach from this formula via relevant transitions (if there are any)
		String[] actionsA = formula.getActions()[0];
		String[] actionsB = formula.getActions()[1];
		
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
							if (checkUntil(formula, nextState, transition.getActions()[0], transitionsToCheck, model)) {
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
				contents[i] = ctls[i];
			}
		}
		return contents;
	}

	/**
	 * Checks a validity of a 
	 * @param formula
	 * @param state
	 * @param model
	 * @return
	 */
	public boolean checkStateFormula(Formula formula, State state, Model model) {
		System.out.println("Check state formula " + getStringFormula(formula) + " state " + state.getName()
		+ " labels: " + getStringArray(state.getLabel()));
		boolean negation = formula.isNegation();
		if (formula.isSingleTt()) {
			return checkTautology(formula) && !negation;
		}
		if (formula.isSingleAp()) {
			return checkAP(formula, state) && !negation;
		}
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
		case "U":
			return !negation && checkUntil(formula, state, "first",
					getAllTransitions(model, formula.getActions()[0], formula.getActions()[1]), model);
		}
		return false;
	}

	public boolean checkPathFormula(Formula formula, State state, Model model) {
		System.out.println("Check path formula" + getStringFormula(formula));
		boolean negation = formula.isNegation();

		switch (formula.getOperator()) {
		case "X":
			Formula trueTautology = new Formula(true);
			// TODO
			// Formula s = new For
			// return !negation && checkStateFormula(contents[0], state, model)
			// && checkStateFormula(contents[1], state, model);
		case "G":
			// TODO
		case "F":
			// TODO
		}

		if (formula.getQuantifier().equals("A")) {
			return negation && !checkUntil(formula, state, "first",
					getAllTransitions(model, formula.getActions()[0], formula.getActions()[1]), model);
		} else {
			return !negation && checkUntil(formula, state, "first",
					getAllTransitions(model, formula.getActions()[0], formula.getActions()[1]), model);
		}
	}

	public boolean checkAP(Formula formula, State state) {
		System.out.print("Check AP " + formula.getAp()[0]);
		String ap = formula.getAp()[0];
		for (String label : state.getLabel()) {
			if (label.equals(ap)) {
				if (formula.getApNeg()[0]) {
					System.out.println(" false");
					return false;
				} else {
					System.out.println(" true");
					return true;
				}
			}
		}
		if (formula.getApNeg()[0]) {
			System.out.println(" true");
			return true;
		}
		System.out.println(" false");
		return false;
	}

	public boolean checkTautology(Formula formula) {
		if (formula.getTautology()[0].equals("True")) {
			return true;
		} else {
			return false;
		}
	}

	public String[] getTrace() {
		// TO IMPLEMENT
		return null;
	}

	public String getStringFormula(Formula formula) {
		String actions = "";
		if (formula.getActions() != null) {
			actions += getStringArray(formula.getActions()[0]) + " " + getStringArray(formula.getActions()[1]);
		}
		String result = "Negation " + formula.isNegation() + ", quantifier " + formula.getQuantifier() + ", operator "
				+ formula.getOperator() + ", actions " + actions + ", ap " + getStringArray(formula.getAp());
		return result;
	}

	public String getStringArray(String[] array) {
		String result = "";
		for (String s : array) {
			result = result + " " + s + " ";
		}
		return result;
	}

	public static void main(String[] args) {
		SimpleModelChecker smc = new SimpleModelChecker();
		Builder builder = new Builder();
		Model model = builder.buildModel("test/resources/ourModel.json");
		Formula formula = builder.buildFormula("test/resources/ctl2.json");
		smc.graph = new ExecutionGraph();
		smc.graph.createGraph(model);
		smc.graph.printTransitionStateDetails();
		State state = smc.graph.getInits().get(0).getState();
		System.out.println(smc.checkPathFormula(formula, state, model));
	}
}