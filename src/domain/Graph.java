package domain;

import java.util.ArrayList;
import java.util.List;

public class Graph {
	public List <Vertex> graph = new ArrayList<>();

	public Graph(List<Vertex> graph) {
		this.graph = graph;
	}

	public void addOneDirectionEdge(Vertex origin, Vertex destiny, double weight) {
		Vertex v1 = findVertex(origin);
		v1.addConnection(destiny, weight);
	}

	public int vertexNumber() {
		return graph.size();
	}
	
	public void addBiDirectionalEdge(Vertex x, Vertex y, double weight) {
		Vertex v1 = findVertex(x);
		v1.addConnection(y, weight);
		
		Vertex v2 = findVertex(y);
		v2.addConnection(x, weight);
	}
	
	public Vertex findVertex(Vertex v) {
		for (Vertex ver : graph) {
			if (v.equals(ver)) {
				return ver;
			}
		}
		return null;
	}
}
