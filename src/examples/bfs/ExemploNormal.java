package examples.bfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import algorithm.BreadthFirstSearch;
import domain.GraphBFS;
import domain.Vertex;

public class ExemploNormal {
	public static void main(String[] args) {
		// cycle loop example - Should throw exception
		Vertex a = new Vertex("a", 0);
		Vertex b = new Vertex("b", 1);
		Vertex c = new Vertex("c", 2);
		Vertex d = new Vertex("d", 3);

		ArrayList<Vertex> verts = new ArrayList<>();
		verts.add(a);
		verts.add(b);
		verts.add(c);
		verts.add(d);

		GraphBFS graph = new GraphBFS(verts);
		graph.addUniDirectionalEdge(a, c, 0);
		graph.addUniDirectionalEdge(d, b, -300);
		graph.addUniDirectionalEdge(a, d, 99);
		graph.addUniDirectionalEdge(a, b, 1);
		graph.addUniDirectionalEdge(b, c, 1);

		System.out.println("Graph:");
		System.out.println(graph);

		BreadthFirstSearch bfs = new BreadthFirstSearch();

		List<Set<Vertex>> path = new ArrayList<>();
		Vertex[] pred = new Vertex[graph.vertexNumber()];
		double[] distances = new double[graph.vertexNumber()];

		bfs.BreadFirstSearch(graph, a, graph.vertexNumber(), pred, path, distances);
		for (int j = 0; j < distances.length; j++) {
			System.out.println(String.format("The optimal path from %s to %s with path %s and has cost %.2f", a,
					graph.getVertexes().get(j).toString(),
					path.get(j).stream().sorted((v1, v2) -> v1.getPosition() < v2.getPosition() ? -1 : 0)
							.map(s -> s.toString()).collect(Collectors.joining("-->")),
					distances[j]));
		}

	}
}
