/**
 * 
 */
package domain;

/**
 * @author Afonso
 *
 */
public class Edge {
	private Vertex source;
	private Vertex destiny;
	private double weight;

	public Edge(Vertex source, Vertex destiny, double weight) {
		this.source = source;
		this.destiny = destiny;
		this.weight = weight;
	}

	public Vertex getSource() {
		return source;
	}

	public Vertex getDestiny() {
		return destiny;
	}

	public double getWeight() {
		return weight;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(source.toString());
		
		sb.append(String.format("---- %.3f ---->",weight ));
		sb.append(destiny.toString());
		
		return sb.toString();
	}

}
