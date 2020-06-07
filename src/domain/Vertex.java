/**
 * 
 */
package domain;

/**
 * Class that represents a vertex of a graph
 *
 */
public class Vertex {
	private String label;
	private int position;
	
	/**
	 * Constructor for vertex
	 * @param label - the label of the vetex
	 * @param position - the relative position in the graph
	 */
	public Vertex(String label, int position) {
		this.label = label;
		this.position = position;
	}

	/**
	 * Constructor for vertex
	 * @param label- the label of the vertex
	 */
	public Vertex(String label) {
		this.label = label;
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

	@Override
	public boolean equals(Object o) {
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
