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

	public Vertex(String label) {
		this.label = label;
	}
	
	public Vertex(String label, int position) {
		this.label = label;
		this.position = position;
	}
	// getters, setters, equals...

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

	@Override
	public String toString() {
		return label;
	}
}
