package examples.bfs;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import algorithm.BreadthFirstSearchForWeightedGraphs;
import domain.Graph;
import domain.Vertex;
import inputgenerator.RandomGraphCreator;

/**
 * Random Graph testing It is recommended if possible to run parallel random
 * graph for bigger inputs and the sync version for smaller ones
 * 
 * @author Afonso
 *
 */
public class RandomGraphTesting {
	public static void main(String[] args) throws Exception {
		BreadthFirstSearchForWeightedGraphs bfs = new BreadthFirstSearchForWeightedGraphs();
		int vertex_number = 1000;
		Graph random_graph = RandomGraphCreator.createRandomGraphParallel(vertex_number);

		String toWrite = "";
		// System.out.println("Generated Graph:");
		// System.out.println(random_graph);
		toWrite += "Generated Graph:\n";
		toWrite += random_graph.toString() + "\n";

		Vertex init = random_graph.getVertexes().get(0);
		toWrite += "Edge Number:" + random_graph.getEdgeNumber() + "\n";
		toWrite += "Results:\n";

		// Get the Java runtime
		Runtime runtime = Runtime.getRuntime();
		runtime.gc();
		long startTime = System.nanoTime();
		List<Set<Vertex>> path = new ArrayList<>();
		Vertex[] pred = new Vertex[random_graph.vertexNumber()];
		double[] distances = new double[random_graph.vertexNumber()];

		bfs.BreadFirstSearchForGraphs(random_graph, init, random_graph.vertexNumber(), pred, path, distances);
		long endTime = System.nanoTime();
		long memory = runtime.totalMemory() - runtime.freeMemory();
		for (int j = 0; j < distances.length; j++) {
			String res = String.format("The optimal path from %s to %s with path %s and has cost %.2f", init,
					random_graph.getVertexes().get(j).toString(),
					path.get(j).stream().sorted((v1, v2) -> v1.getPosition() < v2.getPosition() ? -1 : 0)
							.map(s -> s.toString()).collect(Collectors.joining("-->")),
					distances[j]);
			toWrite += res + "\n";
		}

		long duration = (endTime - startTime);
		System.out.println(String.format("Search execution time: %d nanosecond", duration));
		toWrite += String.format("Search execution time: %d nanosecond\n", duration);

		System.out.println(String.format("Memory used in search: %d\n", memory));
		toWrite += String.format("Memory used in search: %dbytes\n", memory);

		File resultFile = new File("Results" + vertex_number + ".log");
		if (resultFile.createNewFile()) {
			System.out.println("File created: " + resultFile.getName());
		} else {
			System.out.println("File already exists.");
		}
		FileWriter myWriter = new FileWriter(resultFile);
		myWriter.write(toWrite);
		myWriter.close();
	}
}
