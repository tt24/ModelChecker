package lsv.core;

import java.util.ArrayList;
import lsv.model.*;

public class GraphNode {
	
	private State state;
	private ArrayList<GraphNode> neighbours;
	
	public GraphNode(State state) {
		this.state = state;
		neighbours = new ArrayList<>();
	}
	
	public State getState() {
		return state;
	}
	
	public ArrayList<GraphNode> getTransitions() {
		return neighbours;
	}
	
	public void addNeighbour(GraphNode neighbour) {
		neighbours.add(neighbour);
	}
}
