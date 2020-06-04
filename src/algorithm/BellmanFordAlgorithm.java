package algorithm;

import java.util.List;

import domain.Edge;
import domain.Graph;
import domain.Vertex;

public class BellmanFordAlgorithm {
	public double[] getShortestPath(Vertex init, Graph graph) throws Exception {
		
		// setup		
		int graphLength = graph.vertexNumber();
		double infinity = Double.POSITIVE_INFINITY;
		double[] distances = new double[graphLength];
		List<Edge> edges = graph.getEdges();
		List<Vertex> vertexList = graph.getIndexedVertexes();
		
		// debug e testes
		int iterated_times = 0;

		// Initialize the map with distances to each vertex being infinite - O(V)
		int i = 0;
		for (Vertex vertex : vertexList) {
			if (!vertex.equals(init)) {
				distances[i] = infinity;
			}
			i++;
		}

		// Percorrer todos os vertices
		// Percorrer todas as arestas
		// atualizar o no array das distancias sse:
		// o valor atual - currentdistance - nao for infinito
		// currentdistance + o peso da aresta atual sao menores do que as que estao
		// guardadas na posicao do destino
		boolean changesFromPreviousIteration = true;
	
		for (Vertex v : vertexList) {
			// a ordem eh a mesma de uma iteracao para a outra, portanto se nao houver
			// mudancas na tabela de distancias entao nao eh necessahrio continuar a iterar
			// uma vez que o resultado das iteracoes seguintes serah sempre o mesmo.
			if (!changesFromPreviousIteration) {
				break;
			}
			if (!v.equals(init)) { // V - 1 vezes que eh preciso iterar
				changesFromPreviousIteration = false;
				for (Edge edge : edges) {
					// setup
					double weight = edge.getWeight();
					Vertex source = edge.getSource();
					Vertex destiny = edge.getDestiny();

					// se o noh de destino eh igual ao no onde comecamos a distancia sera 0 e na
					// realidade nao interssa para este problema, portanto poupamos assim trabalho
					if (!destiny.equals(init)) {
						double currentDistance = distances[source.getPosition()];
						if (currentDistance != infinity
								&& currentDistance + weight < distances[destiny.getPosition()]) {
							distances[destiny.getPosition()] = currentDistance + weight;
							changesFromPreviousIteration = true;
						}
					}
					iterated_times++;
				}
			}
			
			// iterated_times++;
		}
		System.out.println(iterated_times);

		// Verificar ciclos infinitos que permitem decrescer o valor para "sempre".
		for (Edge e : edges) {
			Vertex origin = e.getSource();
			Vertex dest = e.getDestiny();

			double weight = e.getWeight();
			double currentDistance = distances[origin.getPosition()];

			// Se houver um caminho "ainda" mais curto entao ha um ciclo negativo que eh
			// infinito
			// uma vez que reduz a cada passagem
			if (currentDistance != infinity && currentDistance + weight < distances[dest.getPosition()]) {
				throw new Exception("Infinite path discovered");
			}
		}
		return distances;
	}

}
