package examples;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import algorithm.BellmanFordAlgorithm;
import domain.Edge;
import domain.Graph;
import domain.Vertex;
import util.Pair;

public class ExemploComCicloSemExcecao {
	public static void main(String[]args) throws Exception  {
		
		BellmanFordAlgorithm bfa = new BellmanFordAlgorithm();
		
		// cycle loop example - Should throw exception
		Vertex a = new Vertex("a");
		Vertex b = new Vertex("b");
		Vertex c = new Vertex("c");
		Vertex d = new Vertex("d");
		Vertex e = new Vertex("e");
		Vertex f = new Vertex("f");
		
		Edge ac = new Edge(a, c, 14.0);
		Edge cd = new Edge(c, d, -8.0);
		Edge de = new Edge(d, e, 2.0);
		Edge ef = new Edge(e, f, 9.0);
		Edge ce = new Edge(e, c, 5);
		
		ArrayList<Vertex> verts = new ArrayList<>();
		verts.add(a);
		verts.add(b);
		verts.add(c);
		verts.add(d);
		verts.add(f);
		verts.add(e);
		
		ArrayList<Edge> edges = new ArrayList<>();
		edges.add(ac);
		edges.add(cd);
		edges.add(de);
		edges.add(ef);
		edges.add(ce);
		
		Graph graph = new Graph(verts, edges);
		System.out.println("The graph is:");
		System.out.println(graph);
		// should throw exception
		Pair<Double, Set<Edge>>[] result = bfa.getShortestPathWithCycleDetection(a, graph, new HashSet<>(), 3);
		int i = 0;
		for (Vertex v : verts) {
			if (!v.equals(a)) {
				System.out.println(String.format("Shortest path to object %s has cost %.3f and the path is: %s", v.getLabel(), result[i].x, result[i].y));
			}
			i++;
		}
	}
}
