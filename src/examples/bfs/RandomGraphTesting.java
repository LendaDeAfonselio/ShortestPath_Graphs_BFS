package examples.bfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import algorithm.BreadthFirstSearch;
import domain.GraphBFS;
import domain.Vertex;
import inputgenerator.RandomGraphCreator;

public class RandomGraphTesting {
	public static void main(String[] args) throws Exception {
		BreadthFirstSearch bfs = new BreadthFirstSearch();
		GraphBFS random_graph = RandomGraphCreator.createRandomGraph(15);
		System.out.println("Generated Graph:");
		System.out.println(random_graph);
		Vertex init = random_graph.getVertexes().get(0);
		System.out.println("Results:");

		List<Set<Vertex>> path = new ArrayList<>();
		Vertex[] pred = new Vertex[random_graph.vertexNumber()];
		double[] distances = new double[random_graph.vertexNumber()];
		bfs.BreadFirstSearch(random_graph, init, random_graph.vertexNumber(), pred, path, distances);
		for (int j = 0; j < distances.length; j++) {

			System.out.println(String.format("The optimal path from %s to %s with path %s and has cost %.2f", init,
					random_graph.getVertexes().get(j).toString(),
					path.get(j).stream().sorted((v1, v2) -> v1.getPosition() < v2.getPosition() ? -1 : 0)
							.map(s -> s.toString()).collect(Collectors.joining("-->")),
					distances[j]));
		}

	}
}
