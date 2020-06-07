package examples.bfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import algorithm.BreadthFirstSearchForWeightedGraphs;
import domain.Graph;
import domain.Vertex;

/**
 * Example for the worst "simple" scenario in the report 
 * @author Afonso
 *
 */
public class TUExampleWrst1 {
	public static void main(String[] args) {
		Vertex a = new Vertex("A", 0);
		Vertex b = new Vertex("B", 1);
		Vertex c = new Vertex("C", 2);
		Vertex d = new Vertex("D", 3);
		Vertex h = new Vertex("H", 4);
		Vertex i = new Vertex("I", 5);
		Vertex j = new Vertex("J", 6);
		Vertex t = new Vertex("T", 7);
		Vertex x = new Vertex("X", 8);
		Vertex k = new Vertex("K", 9);
		Vertex l = new Vertex("L", 10);
		Vertex e = new Vertex("E", 11);

		ArrayList<Vertex> verts = new ArrayList<>();
		verts.add(a);
		verts.add(b);
		verts.add(c);
		verts.add(d);
		verts.add(h);
		verts.add(i);
		verts.add(j);
		verts.add(t);
		verts.add(x);
		verts.add(k);
		verts.add(l);
		verts.add(e);

		Graph graph = new Graph(verts);
		graph.addUniDirectionalEdge(c, a, 1);
		graph.addUniDirectionalEdge(c, d, -1);
		graph.addUniDirectionalEdge(a, b, 2);
		graph.addUniDirectionalEdge(d, h, -1);
		graph.addUniDirectionalEdge(h, i, -2);
		graph.addUniDirectionalEdge(i, j, -1);
		graph.addUniDirectionalEdge(j, b, 0);
		graph.addUniDirectionalEdge(b, e, 2);
		graph.addUniDirectionalEdge(e, t, 1);
		graph.addUniDirectionalEdge(t, x, 1);
		graph.addUniDirectionalEdge(e, k, 1);
		graph.addUniDirectionalEdge(k, l, 7);


		System.out.println("Graph:");
		System.out.println(graph);
		Runtime runtime = Runtime.getRuntime();
		runtime.gc();
		long startTime = System.nanoTime();
		BreadthFirstSearchForWeightedGraphs bfs = new BreadthFirstSearchForWeightedGraphs();
		List<Set<Vertex>> path = new ArrayList<>();
		Vertex[] pred = new Vertex[graph.vertexNumber()];
		double[] distances = new double[graph.vertexNumber()];

		bfs.BreadFirstSearchForGraphs(graph, c, graph.vertexNumber(), pred, path, distances);
		long endTime = System.nanoTime();
		long memory = runtime.totalMemory() - runtime.freeMemory();
		for (int rs = 0; rs < distances.length; rs++) {
			System.out.println(String.format("The optimal path from %s to %s with path %s and has cost %.2f", c,
					graph.getVertexes().get(rs).toString(),
					path.get(rs).stream().sorted((v1, v2) -> v1.getPosition() < v2.getPosition() ? -1 : 0)
							.map(s -> s.toString()).collect(Collectors.joining("-->")),
					distances[rs]));
		}
		long duration = (endTime - startTime);
		System.out.println(String.format("Search execution time: %d nanosecond", duration));
		System.out.println(String.format("Memory used in search: %d\n", memory));
	}
}
