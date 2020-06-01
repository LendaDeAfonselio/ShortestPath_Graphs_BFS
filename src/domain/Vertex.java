/**
 * 
 */
package domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that represents a vertex of a graph
 *
 */
public class Vertex {
	private String label;
	private Map<Vertex, Double> connections = new HashMap<>();

	public Vertex(String label) {
		this.label = label;
	}
	
	public Vertex(String label, Map<Vertex, Double> connections) {
		this.label = label;
		this.connections = connections;
	}

	public Vertex addConnection(Vertex y, double weight) {
		connections.put(y, weight);
		return this;
	}
	
	public Map<Vertex, Double> getOutgoingEdges(){
		return connections;
	}
	
	public String getLabel() {
		return label;
	}
	
	public boolean equals(Vertex x, Object o) {
		if (!(o instanceof Vertex)) {
			return false;
		}
		Vertex comp = (Vertex) o;
		return comp.label.equals(label);
	}
}
