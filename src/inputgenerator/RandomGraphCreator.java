package inputgenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import domain.Edge;
import domain.Graph;
import domain.Vertex;

public class RandomGraphCreator {
	private static double max_weight = 5000;

	public static Graph createRandomGraph(int vertex_number) {
		List<Vertex> randomVertexList = new ArrayList<>();
		for (int i = 0; i < vertex_number; i++) {
			Vertex v = new Vertex(Integer.toString(i));
			randomVertexList.add(v);
		}

		List<Edge> random_edges = new ArrayList<>();
		// para tamanho 5, usar a seeds 5 nao eh mau
		Random r = new Random();

		for (Vertex x : randomVertexList) {
			int max_edges_per_vertex = r.nextInt(vertex_number);
			for (int i = 0; i < max_edges_per_vertex; i++) {
				if (r.nextFloat() < 0.75) {
					Vertex src = x;
					double weight = -(max_weight) + r.nextDouble() * (max_weight * 2);

					Vertex dest = randomVertexList.get(r.nextInt(vertex_number));
					while (src == dest) {
						dest = randomVertexList.get(r.nextInt(vertex_number));
					}
					Vertex d = dest;
										
					// para gerar menos graphos ciclicos
					boolean redundantedge = random_edges.stream()
							.noneMatch(v -> v.getSource().equals(src) && v.getDestiny().equals(d));
					boolean possible_cycle = random_edges.stream().noneMatch(v -> v.getSource().equals(d)
							&& v.getDestiny().equals(src) && (v.getWeight() + weight < 0));
					
					int retries = 0;
					// try again 3 times to generate before giving up
					while ((redundantedge || possible_cycle) && retries <= 3) {
						dest = randomVertexList.get(r.nextInt(vertex_number));
						while (src == dest) {
							dest = randomVertexList.get(r.nextInt(vertex_number));
						}
						redundantedge = random_edges.stream()
								.noneMatch(v -> v.getSource().equals(src) && v.getDestiny().equals(d));
						possible_cycle = random_edges.stream().noneMatch(v -> v.getSource().equals(d)
								&& v.getDestiny().equals(src) && (v.getWeight() + weight < 0));
						retries ++;
					}
					
					Edge new_edge = new Edge(src, dest, weight);
					random_edges.add(new_edge);
				}
			}
		}
		return new Graph(randomVertexList, random_edges);
	}
}
