package domain;

import java.util.ArrayList;
import java.util.List;

public class Graph {
	private List<Vertex> vertexes = new ArrayList<>();
	private List<Edge> edges = new ArrayList<>();

	public Graph(List<Vertex> vertexes, List<Edge> edges) {
		this.vertexes = vertexes;
		this.edges = edges;
	}

	public int vertexNumber() {
		return vertexes.size();
	}
	
	public void addVertexes(Vertex toBeAdded) {
		this.vertexes.add(toBeAdded);
	}

	public void addUniDirectionalEdge(Vertex source, Vertex destiny, double weight) {
		Edge toBeAdded = new Edge(source,destiny,weight);
		edges.add(toBeAdded);
	}

	public void addBiDirectionalEdge(Vertex x, Vertex y, double weight) {
		Edge toBeAdded = new Edge(x,y,weight);
		edges.add(toBeAdded);
		
		Edge toBeAddedSecond = new Edge(y,x,weight);
		edges.add(toBeAddedSecond);
	}

	public List<Vertex> getVertexes() {
		return vertexes;
	}
	
	public List<Vertex> getIndexedVertexes() {
		int count = 0;
		for( Vertex v : vertexes) {
			v.setPosition(count++);
		}
		return vertexes;
	}

	public List<Edge> getEdges() {
		return edges;
	}
}
