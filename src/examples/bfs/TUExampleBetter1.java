package examples.bfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import algorithm.BreadthFirstSearchForWeightedGraphs;
import domain.Graph;
import domain.Vertex;

public class TUExampleBetter1 {
	public static void main(String[] args) {
		Vertex a = new Vertex("0", 0);
		Vertex b = new Vertex("1", 1);
		Vertex c = new Vertex("2", 2);
		Vertex d = new Vertex("3", 3);
		Vertex e = new Vertex("4", 4);
		Vertex f = new Vertex("5", 5);
		Vertex g = new Vertex("6", 6);

		ArrayList<Vertex> verts = new ArrayList<>();
		verts.add(a);
		verts.add(b);
		verts.add(c);
		verts.add(d);
		verts.add(e);
		verts.add(f);
		verts.add(g);

		Graph graph = new Graph(verts);
		graph.addUniDirectionalEdge(a, c, 2);
		graph.addUniDirectionalEdge(a, e, 1);
		graph.addUniDirectionalEdge(c, e, 2);
		graph.addUniDirectionalEdge(a, f, 2);
		graph.addUniDirectionalEdge(e, f, 3);
		graph.addUniDirectionalEdge(d, b, 1);
		graph.addUniDirectionalEdge(b, g, 1);

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
