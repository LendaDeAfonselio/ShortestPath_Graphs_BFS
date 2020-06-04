package algorithm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import domain.Edge;
import domain.Graph;
import domain.Vertex;
import util.Pair;

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
							System.out.println(edge);
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

	@SuppressWarnings("unchecked")
	public Pair<Double, Set<Edge>>[] getShortestPathWithCycleDetection(Vertex init, Graph graph,
			Set<Set<Edge>> forbiddenList, int max_retries) throws Exception {

		// setup
		int graphLength = graph.vertexNumber();
		double infinity = Double.POSITIVE_INFINITY;
		Pair<Double, Set<Edge>>[] distances = (Pair<Double, Set<Edge>>[]) new Pair[graphLength];
		List<Edge> edges = graph.getEdges();
		List<Vertex> vertexList = graph.getIndexedVertexes();
		boolean foundpath = false;

		// debug e testes
		int iterated_times = 0;
		int retries = 0;

		while (!foundpath && retries < max_retries) {

			// Initialize the map with distances to each vertex being infinite - O(V)
			int i = 0;
			for (Vertex vertex : vertexList) {
				if (!vertex.equals(init)) {
					distances[i] = new Pair<Double, Set<Edge>>(infinity, new HashSet<>());
				} else {
					distances[i] = new Pair<Double, Set<Edge>>(0.0, new HashSet<>());
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
							Pair<Double, Set<Edge>> pos = distances[source.getPosition()];
							double currentDistance = pos.x;
							Set<Edge> temporaryList = new HashSet<>(pos.y);
							
							if (currentDistance != infinity
									&& currentDistance + weight < distances[destiny.getPosition()].x
									&& forbiddenList.stream().noneMatch(t -> t.equals(temporaryList))) {
								temporaryList.add(edge);
								Pair<Double, Set<Edge>> destiny_pos = distances[destiny.getPosition()];
								destiny_pos.x = currentDistance + weight;
								destiny_pos.y = temporaryList;
								changesFromPreviousIteration = true;
							}
						}
						iterated_times++;
					}
				}

				// iterated_times++;
			}

			// Verificar ciclos infinitos que permitem decrescer o valor para "sempre".
			foundpath = true;
			for (Edge e : edges) {
				Vertex origin = e.getSource();
				Vertex dest = e.getDestiny();

				double weight = e.getWeight();
				Pair<Double, Set<Edge>> pos = distances[origin.getPosition()];

				double currentDistance = pos.x;

				// Se houver um caminho "ainda" mais curto entao ha um ciclo negativo que eh
				// infinito
				// uma vez que reduz a cada passagem
				if (currentDistance != infinity && currentDistance + weight < distances[dest.getPosition()].x
						&& forbiddenList.stream().noneMatch(t -> t.equals(pos.y))) {
					System.out.println(pos.y);
					forbiddenList.add(pos.y);
					retries++;
					foundpath = false;
				}
			}
		}

		if (!foundpath) {
			throw new Exception("Infinite cycle always detected on at least the following paths:" + forbiddenList
					+ "\nNumber of tries: " + retries);
		}
		
		System.out.println(iterated_times);

		return distances;
	}

}
