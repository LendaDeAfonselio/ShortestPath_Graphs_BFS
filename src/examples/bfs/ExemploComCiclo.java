package examples.bfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import algorithm.BreadthFirstSearchForGraphs;
import domain.Graph;
import domain.Vertex;

public class ExemploComCiclo {
	public static void main(String[] args) {

		Vertex a = new Vertex("a");
		Vertex b = new Vertex("b");
		Vertex c = new Vertex("c");
		Vertex d = new Vertex("d");
		Vertex e = new Vertex("e");
		Vertex f = new Vertex("f");

		ArrayList<Vertex> verts = new ArrayList<>();
		verts.add(a);
		verts.add(b);
		verts.add(c);
		verts.add(d);
		verts.add(f);
		verts.add(e);

		Graph graph = new Graph(verts);
		graph.getIndexedVertexes();
		graph.addUniDirectionalEdge(a, c, 14);
		graph.addUniDirectionalEdge(c, d, -8);
		graph.addUniDirectionalEdge(d, e, 2);
		graph.addUniDirectionalEdge(e, f, 9);
		graph.addUniDirectionalEdge(e, c, 5);

		System.out.println("Graph:");
		System.out.println(graph);

		BreadthFirstSearchForGraphs bfs = new BreadthFirstSearchForGraphs();

		List<Set<Vertex>> path = new ArrayList<>();
		Vertex[] pred = new Vertex[graph.vertexNumber()];
		double[] distances = new double[graph.vertexNumber()];

		bfs.BreadFirstSearchForGraphs(graph, a, graph.vertexNumber(), pred, path, distances);
		for (int j = 0; j < distances.length; j++) {
			System.out.println(String.format("The optimal path from %s to %s with path %s and has cost %.2f", a,
					graph.getVertexes().get(j).toString(),
					path.get(j).stream().sorted((v1, v2) -> v1.getPosition() < v2.getPosition() ? -1 : 0)
							.map(s -> s.toString()).collect(Collectors.joining("-->")),
					distances[j]));
		}
	}
}
