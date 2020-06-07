package examples.bfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import algorithm.BreadthFirstSearchForWeightedGraphs;
import domain.Graph;
import domain.Vertex;

/**
 * Example for the 2nd of the better cases
 * @author Afonso
 *
 */
public class TUExampleBetter2 {
	public static void main(String[] args) {
		Vertex a = new Vertex("A", 0);
		Vertex b = new Vertex("B", 1);
		Vertex c = new Vertex("C", 2);
		Vertex d = new Vertex("D", 3);
		Vertex e = new Vertex("E", 4);

		ArrayList<Vertex> verts = new ArrayList<>();
		verts.add(a);
		verts.add(b);
		verts.add(c);
		verts.add(d);
		verts.add(e);

		Graph graph = new Graph(verts);
		graph.addUniDirectionalEdge(a, c, 2);
		graph.addUniDirectionalEdge(a, b, 1);
		graph.addUniDirectionalEdge(c, d, -1);
		graph.addUniDirectionalEdge(d, b, 2);
		graph.addUniDirectionalEdge(b, e, -2);

		System.out.println("Graph:");
		System.out.println(graph);
		Runtime runtime = Runtime.getRuntime();
		runtime.gc();
		long startTime = System.nanoTime();
		BreadthFirstSearchForWeightedGraphs bfs = new BreadthFirstSearchForWeightedGraphs();
		List<Set<Vertex>> path = new ArrayList<>();
		Vertex[] pred = new Vertex[graph.vertexNumber()];
		double[] distances = new double[graph.vertexNumber()];

		bfs.BreadFirstSearchForGraphs(graph, a, graph.vertexNumber(), pred, path, distances);
		long endTime = System.nanoTime();
		long memory = runtime.totalMemory() - runtime.freeMemory();
		for (int j = 0; j < distances.length; j++) {
			System.out.println(String.format("The optimal path from %s to %s with path %s and has cost %.2f", a,
					graph.getVertexes().get(j).toString(),
					path.get(j).stream().sorted((v1, v2) -> v1.getPosition() < v2.getPosition() ? -1 : 0)
							.map(s -> s.toString()).collect(Collectors.joining("-->")),
					distances[j]));
		}
		long duration = (endTime - startTime);
		System.out.println(String.format("Search execution time: %d nanosecond", duration));
		System.out.println(String.format("Memory used in search: %d\n", memory));
	}
}
