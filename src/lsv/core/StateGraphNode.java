package lsv.core;

import java.util.ArrayList;
import lsv.model.*;

public class StateGraphNode {
	
	private State state;
	private ArrayList<Transition> transitions;
	
	public StateGraphNode(State state) {
		this.state = state;
		transitions = new ArrayList<>();
	}
	
	public State getState() {
		return state;
	}
	
	public ArrayList<Transition> getTransitions() {
		return transitions;
	}
	
	public void addTransition(Transition transition) {
		transitions.add(transition);
	}
}
