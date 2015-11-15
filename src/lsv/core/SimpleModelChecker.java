package lsv.core;

import lsv.model.*;

import lsv.grammar.Formula;
import java.util.*;

public class SimpleModelChecker implements ModelChecker {

	ExecutionGraph graph;

	public boolean check(Model model, Formula constraint, Formula formula) {
		if (formula.isSingleTt()) {
			return checkTautology(formula);
		}
		if (formula.isSingleAp()) {

		}
		// TO IMPLEMENT
		return false;
	}

	public boolean checkIfExists() {
		return false;
	}

	public Hashtable<String, ArrayList<Transition>> getAllTransitions(Model model, String[] actionsA,
			String[] actionsB) {
		Hashtable<String, ArrayList<Transition>> table = new Hashtable<>();
		for (Transition transition : model.getTransitions()) {
			if (actionsA.length == 0 || actionsB.length == 0 || contains(actionsB, transition.getActions()[0])
					|| contains(actionsA, transition.getActions()[0])) {
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

	public boolean checkUntil(Formula formula, State state, String transitionName,
			Hashtable<String, ArrayList<Transition>> transitionsToCheck, Model model) {
		Formula[] contents = getNestedContents(formula);
		if (transitionName.equals("first")) {
			if (checkStateFormula(contents[1], state, model)) {
				return true;
			} else {
				if (!checkStateFormula(contents[0], state, model)) {
					return false;
				}
			}
		}
		String[] actionsA = formula.getActions()[0];
		String[] actionsB = formula.getActions()[1];
		if (actionsB.length == 0 || contains(actionsB, transitionName)) {
			if (checkStateFormula(contents[1], state, model)) {
				return true;
			}
		} else {
			if (actionsA.length == 0 || contains(actionsA, transitionName) || transitionName.equals("first")) {
				for (Transition transition: transitionsToCheck.get(state.getName())) {
					String nextStateStr = transition.getTarget();
					State nextState = graph.getStateNameTable().get(nextStateStr).getState();
					ArrayList<Transition> possibleTran = transitionsToCheck.get(state.getName());
					possibleTran.remove(0);
					transitionsToCheck.put(state.getName(), possibleTran);
					if (checkUntil(formula, nextState, transition.getActions()[0], transitionsToCheck, model)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean contains(String[] array, String element) {
		for (String s : array) {
			if (s!=null && s.equals(element)) {
				return true;
			}
		}
		return false;
	}

	public Formula[] getNestedContents(Formula formula) {
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

	public boolean checkStateFormula(Formula formula, State state, Model model) {
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
			return !negation && checkStateFormula(contents[0], state, model) && checkStateFormula(contents[1], state, model);
		case "||":
			return !negation && (checkStateFormula(contents[0], state, model) || checkStateFormula(contents[1], state, model));
		case "=>":
			return !negation && (!checkStateFormula(contents[0], state, model) || checkStateFormula(contents[1], state, model));
		case "<=>":
			return !negation && (checkStateFormula(contents[0], state, model) == checkStateFormula(contents[1], state, model));
		case "U":
			return !negation && checkUntil(formula, state, "first", getAllTransitions(model, formula.getActions()[0], formula.getActions()[1]), model);
		}
		return false;
	}
	
	public boolean checkPathFormula(Formula formula, State state, Model model) {
		boolean negation = formula.isNegation();
		
		switch (formula.getOperator()) {
		case "X":
			Formula trueTautology = new Formula(true);
			//TODO
			//Formula s = new For
			//return !negation && checkStateFormula(contents[0], state, model) && checkStateFormula(contents[1], state, model);
		case "G":
			//TODO
		case "F":
			//TODO
		}
		
		if (formula.getQuantifier().equals("A")) {
			return negation && !checkUntil(formula, state, "first", getAllTransitions(model, formula.getActions()[0], formula.getActions()[1]), model);
		}
		else {
			return !negation && checkUntil(formula, state, "first", getAllTransitions(model, formula.getActions()[0], formula.getActions()[1]), model);		
		}
	}

	public boolean checkAP(Formula formula, State state) {
		String ap = formula.getAp()[0];
		for (String label : state.getLabel()) {
			if (label.equals(ap)) {
				if (formula.getApNeg()[0]) {
					return false;
				} else {
					return true;
				}
			}
		}
		if (formula.getApNeg()[0]) {
			return true;
		}
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

	public static void main(String[] args) {
		SimpleModelChecker smc = new SimpleModelChecker();
		Builder builder = new Builder();
		Model model = builder.buildModel("test/resources/model.json");
		Formula formula = builder.buildFormula("test/resources/ctl2.json");
		smc.graph = new ExecutionGraph();
		smc.graph.createGraph(model);
		State state = smc.graph.getInits().get(1).getState();
		System.out.println(smc.checkPathFormula(formula, state, model));
	}
}