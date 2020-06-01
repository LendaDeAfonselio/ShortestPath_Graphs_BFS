package algorithm;

import java.util.HashMap;
import java.util.Map;

import domain.Graph;
import domain.Vertex;

public class BellmanFordAlgorithm {
	public Map<String, Double> getShortestPath(Vertex init, Graph graph) {
		int graphLength = graph.vertexNumber();

		double infinity = Double.POSITIVE_INFINITY;
		HashMap<String, Double> distances = new HashMap<>();

		// Initialize the map with distances to each vertex being infinite
		for (Vertex v : graph.graph) {
			distances.put(v.getLabel(), infinity);
		}
		distances.replace(init.getLabel(), 0.0);

		// Isto nao estah certo xD
		for (Vertex v : graph.graph) {
			if (!v.equals(init)) {
				for (Map.Entry<Vertex, Double> edge : v.getOutgoingEdges().entrySet()) {
					double weight = edge.getValue();
					Vertex source = v;
					Vertex destiny = edge.getKey();
					double currentDistance = distances.get(source.getLabel());
					if (currentDistance != infinity && currentDistance + weight < distances.get(destiny.getLabel())) {
						distances.replace(destiny.getLabel(), currentDistance + weight);
					}
				}
			}
		}

		return distances;
	}

}
