package inputgenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import domain.Edge;
import domain.Graph;
import domain.Vertex;

public class RandomGraphCreator {
	private static double max_weight = 10000;
	public static Graph createRandomGraph(int vertex_number) {
		List<Vertex> randomVertexList = new ArrayList<>();
		for(int i = 0; i < vertex_number; i++) {
			Vertex v = new Vertex(Integer.toString(i));
			randomVertexList.add(v);
		}
		
		List<Edge> random_edges = new ArrayList<>();
		Random r = new Random();
		int edge_number = r.nextInt(vertex_number) + vertex_number;
		
		for(int i = 0; i < edge_number; i++) {
			Vertex src = null;
			Vertex dest = null;
			double weight = -(max_weight) + r.nextDouble() * (max_weight * 2);
			while (src == dest) {
				src = randomVertexList.get(r.nextInt(vertex_number));
				dest = randomVertexList.get(r.nextInt(vertex_number));
			}
			Edge new_edge = new Edge(src,dest,weight);
			random_edges.add(new_edge);
			
		}
		return new Graph(randomVertexList, random_edges);
	}
}
