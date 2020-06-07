package algorithm;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import domain.Edge;
import domain.Graph;
import domain.Vertex;

/**
 * Class that implements the algorithm described in the report that uses BFS to
 * get the shortest paths in a graph
 *
 */
public class BreadthFirstSearchForWeightedGraphs {

	/**
	 * The method that tries to find the shortest paths and the distances from an
	 * origin node to the remaining nodes of the graph. In the end the results will
	 * be stored in the predecessors, shortestPath and distances, so use those
	 * structures if you want access to them!
	 * 
	 * @param graph
	 *            - the graph to be explored
	 * @param origin
	 *            - the origin vertex
	 * @param totalVertexNumber
	 *            - the total number of vertexes the graph has
	 * @param predecessors
	 *            - an initialized array with length totalVertexNumber that will
	 *            store each current predecessor
	 * @param shortestPaths
	 *            - the current shortest paths to each one of the vertexes in the
	 *            graph
	 * @param distances
	 *            - the current distances to each one of the nodes in the graphs
	 */
	public void BreadFirstSearchForGraphs(Graph graph, Vertex origin, int totalVertexNumber, Vertex predecessors[],
			List<Set<Vertex>> shortestPaths, double distances[]) {

		// a queue to maintain queue of vertices whose
		// adjacency list is to be scanned - linkedlist is better than arraylist for
		// simple "pop and push" operations ( O(1) vs O(n))
		LinkedList<Vertex> queue = new LinkedList<>();

		// boolean array visited[] which stores the
		// information whether - ith vertex was reached previously
		boolean visited[] = new boolean[totalVertexNumber];

		// setup the arrays initially O(n)
		for (int i = 0; i < totalVertexNumber; i++) {
			visited[i] = false;
			distances[i] = Double.POSITIVE_INFINITY;
			predecessors[i] = null;
			shortestPaths.add(new HashSet<Vertex>());
		}

		// initialize the origin position with visited and 0 distance and add it to the
		// queue to be explored.
		distances[origin.getPosition()] = 0.0;
		visited[origin.getPosition()] = true;
		queue.add(origin);

		// Continue to search while the queue ain't empty
		// O(|V|)
		while (!queue.isEmpty()) {
			Vertex vertex = queue.remove(); // O(1)
			int currentVertexPosition = vertex.getPosition(); // O(1)

			// O(|E|)
			for (Edge e : graph.getEdgesForVertex(vertex)) {
				Vertex toBeVisitedNode = e.getDestiny(); // O(1)
				int toBeVisitedNodeIndex = toBeVisitedNode.getPosition(); // O(1)
				double current = distances[currentVertexPosition] + e.getWeight(); // O(1)
				Set<Vertex> currentPath = shortestPaths.get(currentVertexPosition); // O(1*) - might be O(nlogn) but
																					// unlikely
				if (e.getDestiny() != origin) {
					// if the distance is better than what we currently have and if it is not a
					// cycle we must alter the values
					if (!visited[toBeVisitedNodeIndex] || current < distances[toBeVisitedNodeIndex]
							&& !predecessors[toBeVisitedNodeIndex].equals(vertex)
							&& !currentPath.contains(toBeVisitedNode)) { // O(1*) - might be O(nlogn) because of
																			// contains, but unlikely
						// update values
						visited[toBeVisitedNodeIndex] = true; // O(1)
						distances[toBeVisitedNodeIndex] = current; // O(1)
						predecessors[toBeVisitedNodeIndex] = vertex; // O(1)
						queue.add(toBeVisitedNode); // even if we explored it previously we might need to alter the
													// values due to newer updates - O(1)

						// create a copy of the current path and update it with the vertex that is the
						// source of the edge
						Set<Vertex> currentPathCopy = new HashSet<>(currentPath); // O(1)
						currentPathCopy.add(vertex); // O(1)
						shortestPaths.set(toBeVisitedNodeIndex, currentPathCopy); // O(1*) - might be O(nlogn) but
																					// unlikely
					}
				}
			}
		}
		return;
	}
}
