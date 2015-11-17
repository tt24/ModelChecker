package lsv.core;

import java.util.ArrayList;
import java.util.Hashtable;

import lsv.model.*;

public class ExecutionGraph {

	private Hashtable<String, Transition> transitionTable = new Hashtable<>();
	private Hashtable<String, GraphNode> stateNameTable = new Hashtable<>();
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

	public ArrayList<GraphNode> createGraph(Model model) {
		setStates(model);
		setTransitions(model);
		return null;
	}
	
	public Hashtable<String, Transition> getTransitionTable() {
		return transitionTable;
	}

	public Hashtable<String, GraphNode> getStateNameTable() {
		return stateNameTable;
	}

	public ArrayList<GraphNode> getInits() {
		return inits;
	}

}
