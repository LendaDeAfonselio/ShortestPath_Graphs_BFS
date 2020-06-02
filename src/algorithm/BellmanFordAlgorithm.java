package algorithm;

import java.util.List;

import domain.Edge;
import domain.Graph;
import domain.Vertex;


public class BellmanFordAlgorithm {
	public double[] getShortestPath(Vertex init, Graph graph) {
		// setup
		int graphLength = graph.vertexNumber();
		double infinity = Double.POSITIVE_INFINITY;
		double[] distances = new double[graphLength];
		List<Edge> edges = graph.getEdges();
		List<Vertex> vertexList = graph.getIndexedVertexes();

		// Initialize the map with distances to each vertex being infinite - O(V)
		int i = 0;
		for (Vertex vertex : vertexList) {
			if (!vertex.equals(init)) {
				distances[i++] = infinity;
			}
		}

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
					if (currentDistance != infinity && currentDistance + weight < distances[destiny.getPosition()]) {
						distances[destiny.getPosition()] = currentDistance + weight;
					}
				}
			}
			i++;
		}

		return distances;
	}

}
