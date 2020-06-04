/**
 * 
 */
package domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents a vertex of a graph
 *
 */
public class Vertex {
	private String label;
	private int position;
	private List<Edge> outgoing = new ArrayList<>();

	public Vertex(String label) {
		this.label = label;
	}
	
	public Vertex(String label, int position) {
		this.label = label;
		this.position = position;
	}
	
	

	public Vertex(String label, int position, List<Edge> outgoing) {
		this.label = label;
		this.position = position;
		this.outgoing = outgoing;
	}

	public void setPosition(int position) {
		this.position = position;
	}
	
	public int getPosition() {
		return position;
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
	
	public List<Edge> getOutgoing() {
		return outgoing;
	}

	public void addOutgoingEdge(Edge e) {
		this.outgoing.add(e);
	}

	@Override
	public String toString() {
		return label;
	}
}
