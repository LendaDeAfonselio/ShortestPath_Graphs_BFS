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

	// getters, setters, etc

	public Vertex getSource() {
		return source;
	}

	public Vertex getDestiny() {
		return destiny;
	}

	public double getWeight() {
		return weight;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((destiny == null) ? 0 : destiny.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		long temp;
		temp = Double.doubleToLongBits(weight);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Edge))
			return false;
		Edge other = (Edge) obj;
		if (destiny == null) {
			if (other.destiny != null)
				return false;
		} else if (!destiny.equals(other.destiny))
			return false;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		else if (other.weight != weight)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(source.toString());
		
		sb.append(String.format("---- %.3f ---->",weight ));
		sb.append(destiny.toString());
		
		return sb.toString();
	}

}
