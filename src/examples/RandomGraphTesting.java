package examples;

import java.util.Locale;

import algorithm.BellmanFordAlgorithm;
import domain.Graph;
import domain.Vertex;
import inputgenerator.RandomGraphCreator;

public class RandomGraphTesting {
	public static void main(String[] args) throws Exception {
		BellmanFordAlgorithm bfa = new BellmanFordAlgorithm();
		Graph random_graph = RandomGraphCreator.createRandomGraph(5);
		System.out.println("Generated Graph:");
		System.out.println(random_graph);
		Vertex init = random_graph.getVertexes().get(0);
		System.out.println("Results:");
		double [] result = bfa.getShortestPath(init, random_graph);
		int i = 0;
		for (Vertex v : random_graph.getVertexes()) {
			if (!v.equals(init)) {
				System.out.println(String.format(Locale.ENGLISH, "Shortest path to object %s has cost %.3f", v.getLabel(), result[i]));
			}
			i++;
		}
		
	}
}
