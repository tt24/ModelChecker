package lsv.core;

import java.util.ArrayList;

import lsv.model.State;
import lsv.model.Transition;

public class TreeNode {
	
	private State state;
	private ArrayList<State> children;
	private Transition transition;
	
	public TreeNode(State state) {
		this.state = state;
		this.transition = null;
		this.children = new ArrayList<>();
	}
	
	public TreeNode(State state, Transition transition) {
		this.state = state;
		this.transition = transition;
		this.children = new ArrayList<>();
	}
	
	public Transition getTransition() {
		return transition;
	}
	
	public State getState() {
		return state;
	}
	
	public ArrayList<State> getChildren() {
		return children;
	}
	
	public void addChild(State child) {
		children.add(child);
	}

}
