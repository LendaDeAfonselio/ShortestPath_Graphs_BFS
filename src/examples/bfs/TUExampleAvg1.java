package examples.bfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import algorithm.BreadthFirstSearchForWeightedGraphs;
import domain.Graph;
import domain.Vertex;

/**
 * Example for a typical case
 * @author Afonso
 *
 */
public class TUExampleAvg1 {
	public static void main(String[] args) {
		// cycle loop example - Should throw exception
		Vertex a = new Vertex("A", 0);
		Vertex b = new Vertex("B", 1);
		Vertex c = new Vertex("C", 2);
		Vertex d = new Vertex("D", 3);
		Vertex e = new Vertex("E", 4);
		Vertex f = new Vertex("F", 5);
		Vertex g = new Vertex("G", 6);

		ArrayList<Vertex> verts = new ArrayList<>();
		verts.add(a);
		verts.add(b);
		verts.add(c);
		verts.add(d);
		verts.add(e);
		verts.add(f);
		verts.add(g);

		Graph graph = new Graph(verts);
		graph.addUniDirectionalEdge(a, c, 1);
		graph.addUniDirectionalEdge(a, b, 2);
		graph.addUniDirectionalEdge(b, f, 1);
		graph.addUniDirectionalEdge(c, d, -1);
		graph.addUniDirectionalEdge(d, e, 1);
		graph.addUniDirectionalEdge(b, e, 4);
		graph.addUniDirectionalEdge(e, g, 4);

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
