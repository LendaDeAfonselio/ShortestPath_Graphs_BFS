package examples;
import java.util.ArrayList;
import java.util.Locale;

import algorithm.BellmanFordAlgorithm;
import domain.Edge;
import domain.Graph;
import domain.Vertex;

public class ExemploNormal {

	public static void main(String[] args) throws Exception {
		BellmanFordAlgorithm bfa = new BellmanFordAlgorithm();

		// cycle loop example - Should throw exception
		Vertex a = new Vertex("a");
		Vertex b = new Vertex("b");
		Vertex c = new Vertex("c");
		Vertex d = new Vertex("d");

		Edge ac = new Edge(a, c, 0);
		Edge db = new Edge(d, b, -300.0);
		Edge ad = new Edge(a, d, 99.0);
		Edge ab = new Edge(a, b, 1.0);
		Edge bc = new Edge(b, c, 1.0);

		ArrayList<Vertex> verts = new ArrayList<>();
		verts.add(a);
		verts.add(b);
		verts.add(c);
		verts.add(d);

		ArrayList<Edge> edges = new ArrayList<>();
		edges.add(ac);
		edges.add(ad);
		edges.add(ab);
		edges.add(db);
		edges.add(bc);

		Graph graph = new Graph(verts, edges);
		
		double[] result = bfa.getShortestPath(a, graph);
		int i = 0;
		for (Vertex v : verts) {
			if (!v.equals(a)) {
				System.out.println(String.format(Locale.ENGLISH, "Shortest path to object %s has cost %.3f", v.getLabel(), result[i]));
			}
			i++;
		}

	}

}
