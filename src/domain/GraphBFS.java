package domain;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GraphBFS {
	private List<Vertex> vertexes = new ArrayList<>();
	private List<Edge> edges[];

	@SuppressWarnings("unchecked")
	public GraphBFS(List<Vertex> vertexes) {
		this.vertexes = vertexes;
		edges = (List<Edge>[]) new LinkedList[vertexes.size()];
		for (int i = 0; i < edges.length; ++i) {
			edges[i] = new LinkedList<>();
		}
	}

	public GraphBFS(List<Vertex> vertexes, List<Edge>[] edges) {
		this.vertexes = vertexes;
		this.edges = edges;
	}

	public int vertexNumber() {
		return vertexes.size();
	}

	public void addVertex(Vertex toBeAdded) {
		this.vertexes.add(toBeAdded);
	}

	public void addUniDirectionalEdge(Vertex source, Vertex destiny, double weight) {
		Edge toBeAdded = new Edge(source, destiny, weight);
		edges[source.getPosition()].add(toBeAdded);
	}

	public void addBiDirectionalEdge(Vertex x, Vertex y, double weight) {
		Edge toBeAdded = new Edge(x, y, weight);
		edges[x.getPosition()].add(toBeAdded);

		Edge toBeAddedSecond = new Edge(y, x, weight);
		edges[y.getPosition()].add(toBeAddedSecond);
	}

	public List<Vertex> getVertexes() {
		return vertexes;
	}

	public List<Vertex> getIndexedVertexes() {
		int count = 0;
		for (Vertex v : vertexes) {
			v.setPosition(count++);
		}
		return vertexes;
	}

	public List<Edge> getEdgesForVertex(Vertex x) {
		return edges[x.getPosition()];
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Vertexes:" + vertexes + "\n");
		sb.append("Edges:\n");
		for(int i = 0; i < edges.length; i++) {			
			for (Edge e : edges[i]) {
				sb.append(e + "\n");
			}
		}
		return sb.toString();
	}

}
