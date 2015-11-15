package lsv.core;

import java.util.ArrayList;
import java.util.Hashtable;

import lsv.model.*;

public class ExecutionTree {
	
	Hashtable<String, ArrayList<Transition>> transitionTable = new Hashtable<>();
	
	public ArrayList<TreeNode> findRoots(Model model) {
		ArrayList<TreeNode> roots = new ArrayList<>();
		State[] states = model.getStates();
		for(State state: states) {
			if(state.isInit()) {
				roots.add(new TreeNode(state));
			}
		}
		return roots;
	}
	
	public void setTransitions(Model model) {
		Transition[] transitions = model.getTransitions();
		for(Transition transition: transitions) {
			if(transitionTable.containsKey(transition.getSource())) {
				ArrayList<Transition> fromSourceTransitions = transitionTable.get(transition.getSource());
				fromSourceTransitions.add(transition);
				transitionTable.put(transition.getSource(), fromSourceTransitions);
			}
			else {
				ArrayList<Transition> fromSourceTransitions = new ArrayList<>();
				fromSourceTransitions.add(transition);
				transitionTable.put(transition.getSource(), fromSourceTransitions);
			}
		}
	}
	
	public ArrayList<TreeNode> createTree(Model model) {
		ArrayList<TreeNode> trees =  findRoots(model);
		
		return null;
	}
	
	
	
	

}
