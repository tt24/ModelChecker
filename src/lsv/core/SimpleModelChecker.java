package lsv.core;

import lsv.model.Model;
import lsv.model.State;
import lsv.grammar.Formula;

public class SimpleModelChecker implements ModelChecker {

	public boolean check(Model model, Formula constraint, Formula formula) {
		// TO IMPLEMENT
		return false;
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
		if(formula.getApNeg()[0]) {
			return true;
		}
		return false;
	}
	
	public boolean checkTautology(Formula formula) {
		if(formula.getTautology()[0].equals("True")) {
			return true;
		}
		else {
			return false;
		}
	}

	public String[] getTrace() {
		// TO IMPLEMENT
		return null;
	}
}