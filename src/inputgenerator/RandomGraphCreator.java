package inputgenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import domain.Edge;
import domain.GraphBFS;
import domain.Vertex;

public class RandomGraphCreator {
	private static double max_weight = 5000;

	public static GraphBFS createRandomGraph(int vertex_number) {
		List<Vertex> randomVertexList = Collections.synchronizedList(new ArrayList<>());
		for (int i = 0; i < vertex_number; i++) {
			Vertex v = new Vertex(Integer.toString(i), i);
			randomVertexList.add(v);
		}
		GraphBFS graph = new GraphBFS(randomVertexList);

		List<Edge> random_edges = Collections.synchronizedList(new ArrayList<>());
		Random r = new Random();
		
		randomVertexList.parallelStream().forEach(x -> {
			int max_edges_per_vertex = r.nextInt(vertex_number) / 2;
			IntStream range2 = IntStream.rangeClosed(1, max_edges_per_vertex);
			range2.parallel().forEach(i -> {
				if (r.nextFloat() < 0.75) {
					Vertex src = x;
					double weight = -(max_weight) + r.nextDouble() * (max_weight * 2);

					Vertex dest = randomVertexList.get(r.nextInt(vertex_number));
					while (src == dest) {
						dest = randomVertexList.get(r.nextInt(vertex_number));
					}

					boolean redundantedge = false;
					boolean possible_cycle = false;
					// para gerar menos graphos ciclicos
					synchronized(random_edges) {
						for (Edge v : random_edges) {
							if (v.getSource().equals(src) && v.getDestiny().equals(dest)) {
								redundantedge = true;
								break;
							} else if (v.getSource().equals(dest) && v.getDestiny().equals(src)
									&& (v.getWeight() + weight < 0)) {
								possible_cycle = true;
								break;
							}
						}
					}
					

					int retries = 0;
					// try again 3 times to generate before giving up
					while ((redundantedge || possible_cycle) && retries <= 3) {
						dest = randomVertexList.get(r.nextInt(vertex_number));
						while (src == dest) {
							dest = randomVertexList.get(r.nextInt(vertex_number));
						}
						redundantedge = false;
						possible_cycle = false;
						synchronized(random_edges) {	
							for (Edge v : random_edges) {
								if (v.getSource().equals(src) && v.getDestiny().equals(dest)) {
									redundantedge = true;
									break;
								} else if (v.getSource().equals(dest) && v.getDestiny().equals(src)
										&& (v.getWeight() + weight < 0)) {
									possible_cycle = true;
									break;
								}
							}
						}

						retries++;
					}
					
					random_edges.add(new Edge(src, dest, weight));

					synchronized (graph) {
						graph.addUniDirectionalEdge(src, dest, weight);
					}
				}
			});
				
			
		});
		return graph;
	}
}
