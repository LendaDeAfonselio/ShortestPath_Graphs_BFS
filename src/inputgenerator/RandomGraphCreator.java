package inputgenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import domain.Edge;
import domain.Graph;
import domain.Vertex;

/**
 * Class that is responsible for Creating random graphs
 */
public class RandomGraphCreator {
	private static double max_weight = 5000;
	
	/**
	 * Generates a random graph given the desired number of vertex
	 * @param vertex_number - the number of vertexes the graph will have
	 * @return a random graph
	 */
	public static Graph createRandomGraph(int vertex_number) {
		List<Vertex> randomVertexList = new ArrayList<>();
		for (int i = 0; i < vertex_number; i++) {
			Vertex v = new Vertex(Integer.toString(i),i);
			randomVertexList.add(v);
		}
		Graph graph = new Graph(randomVertexList);

		List<Edge> random_edges = new ArrayList<>();
		// put a seed for predictable results e.g - 2
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
					while ((redundantedge || possible_cycle) && retries <= 2) {
						dest = randomVertexList.get(r.nextInt(vertex_number));
						while (src == dest) {
							dest = randomVertexList.get(r.nextInt(vertex_number));
						}
						Vertex f = dest;
						redundantedge = random_edges.stream()
								.noneMatch(v -> v.getSource().equals(src) && v.getDestiny().equals(f));
						possible_cycle = random_edges.stream().noneMatch(v -> v.getSource().equals(f)
								&& v.getDestiny().equals(src) && (v.getWeight() + weight < 0));
						retries ++;
					}
					
					Edge new_edge = new Edge(src, dest, weight);
					random_edges.add(new_edge);
					graph.addUniDirectionalEdge(src, dest, weight);
				}
			}
		}
		return graph;
	}
	
	/**
	 * Graph creator using parallelization in order to be faster for larger vertex ammount
	 * <p>PSA - The results here even with fixed random seeds aren't reliable since the order of the parallel loops might change.</p>
	 * @param vertex_number - the number of vertex
	 * @return a random graph
	 */
	public static Graph createRandomGraphParallel(int vertex_number) {
		List<Vertex> randomVertexList = Collections.synchronizedList(new ArrayList<>());
		for (int i = 0; i < vertex_number; i++) {
			Vertex v = new Vertex(Integer.toString(i), i);
			randomVertexList.add(v);
		}
		Graph graph = new Graph(randomVertexList);

		List<Edge> random_edges = Collections.synchronizedList(new ArrayList<>());
		
		randomVertexList.parallelStream().forEach(x -> {
			Random r1 = new Random();
			int max_edges_per_vertex = r1.nextInt(vertex_number)/2;
			IntStream range2 = IntStream.rangeClosed(1, max_edges_per_vertex);
			range2.parallel().forEach(i -> {
				Random r = new Random();
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
