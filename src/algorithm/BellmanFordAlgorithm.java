package algorithm;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import domain.Edge;
import domain.Graph;
import domain.Vertex;

public class BellmanFordAlgorithm {
	public Map<String, Double> getShortestPath(Vertex init, Graph graph) {
		// setup
		int graphLength = graph.vertexNumber();
		double infinity = Double.POSITIVE_INFINITY;
		double[] distances = new double[graphLength];
		List<Edge> edges = graph.getEdges();
		List<Vertex> vertexList = graph.getVertexes();

		// Initialize the map with distances to each vertex being infinite - O(V)
		int i = 0;
		for (Vertex vertex : vertexList) {
			if (!vertex.equals(init)) {
				distances[i++] = infinity;
			}
		}

		// Isto nao estah certo xD
		// Percorrer todos os vertices
		// Percorrer todas as arestas
		// atualizar o no array das distancias sse:
		// o valor atual - currentdistance - nao for infinito
		// currentdistance + o peso da aresta atual sao menores do que as que estao
		// guardadas na posicao do destino
		i = 0;
		for (Vertex v : vertexList) {
			if (!v.equals(init)) {
				for (Edge edge : edges) {
					double weight = edge.getWeight();
					Vertex source = edge.getSource();
					Vertex destiny = edge.getDestiny();
					double currentDistance = distances[source.getPosition()];
					//if 
				}
			}
			i++;
		}
		// for (Vertex v : graph.graph) {
			// if (!v.equals(init)) {
				// for (Map.Entry<Vertex, Double> edge : v.getOutgoingEdges().entrySet()) {
					// double weight = edge.getValue();
					// Vertex source = v;
					// Vertex destiny = edge.getKey();
					// double currentDistance = distances.get(source.getLabel());
					// if (currentDistance != infinity && currentDistance + weight <
						// distances.get(destiny.getLabel())) {
						// distances.replace(destiny.getLabel(), currentDistance + weight);
						// }
				// }
			// }
		// }

		return distances;
	}

}
