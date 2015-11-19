package lsv.core;

import java.util.ArrayList;
import java.util.HashMap;

import lsv.model.*;

public class ExecutionGraph {

	private HashMap<String, Transition> transitionTable = new HashMap<>();
	private HashMap<String, GraphNode> stateNameTable = new HashMap<>();
	private ArrayList<GraphNode> inits = new ArrayList<>();

	public void setTransitions(Model model) {
		for (Transition transition : model.getTransitions()) {
			transitionTable.put(transition.getSource() + ":" + transition.getTarget(), transition);
			GraphNode source = stateNameTable.get(transition.getSource());
			source.addNeighbour(stateNameTable.get(transition.getTarget()));
		}
	}

	public void setStates(Model model) {
		for (State state : model.getStates()) {
			stateNameTable.put(state.getName(), new GraphNode(state));
			if (state.isInit()) {
				inits.add(stateNameTable.get(state.getName()));
			}
		}
	}

	public void createGraph(Model model) {
		setStates(model);
		setTransitions(model);
	}

	public HashMap<String, Transition> getTransitionTable() {
		return transitionTable;
	}

	public HashMap<String, GraphNode> getStateNameTable() {
		return stateNameTable;
	}

	public ArrayList<GraphNode> getInits() {
		return inits;
	}

	public void printTransitionStateDetails() {
		for (String transitionName : transitionTable.keySet()) {
			State source = stateNameTable.get(transitionTable.get(transitionName).getSource()).getState();
			State target = stateNameTable.get(transitionTable.get(transitionName).getTarget()).getState();
			System.out.println("Source " + source.getName() + ", label " + getStringArray(source.getLabel()) + ", target "
					+ target.getName() + ", label " + getStringArray(target.getLabel()) + ", actions "
					+ getStringArray(transitionTable.get(transitionName).getActions()));
		}
	}
	
	public String getStringArray(String[] array) {
		String result = "";
		for(String s: array) {
			result=result+" "+ s+" ";
		}
		return result;
	}

}
