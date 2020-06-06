package algorithm;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import domain.Edge;
import domain.Graph;
import domain.Vertex;


/**
 * 
 * @author Afonso
 *
 */
public class BreadthFirstSearchForGraphs {

	/**
	 * 
	 * @param graph
	 * @param origin
	 * @param totalVertexNumber
	 * @param predecessors
	 * @param shortestPaths
	 * @param distances
	 */
	public void BreadFirstSearchForGraphs(Graph graph, Vertex origin, int totalVertexNumber, Vertex predecessors[],
			List<Set<Vertex>> shortestPaths, double distances[]) {

		// a queue to maintain queue of vertices whose
		// adjacency list is to be scanned - linkedlist is better for pop operations
		LinkedList<Vertex> queue = new LinkedList<>();

		// boolean array visited[] which stores the
		// information whether ith vertex was reached previously
		boolean visited[] = new boolean[totalVertexNumber];

		// setup the arrays initially
		for (int i = 0; i < totalVertexNumber; i++) {
			visited[i] = false;
			distances[i] = Double.POSITIVE_INFINITY;
			predecessors[i] = null;
			shortestPaths.add(new HashSet<Vertex>());
		}

		distances[origin.getPosition()] = 0.0;
		visited[origin.getPosition()] = true;
		queue.add(origin);

		// Continue to search while the queue ain't empty
		while (!queue.isEmpty()) {
			Vertex vertex = queue.remove();
			int currentVertexPosition = vertex.getPosition();

			for (Edge e : graph.getEdgesForVertex(vertex)) {
				Vertex toBeVisitedNode = e.getDestiny();
				int toBeVisitedNodeIndex = toBeVisitedNode.getPosition();
				double current = distances[currentVertexPosition] + e.getWeight();
				Set<Vertex> currentPath = shortestPaths.get(currentVertexPosition);
				// if the distance is better than what we currently have and if it is not a
				// cycle we must alter the values
				if (!visited[toBeVisitedNodeIndex] || current < distances[toBeVisitedNodeIndex] && !predecessors[toBeVisitedNodeIndex].equals(vertex)
						&& !currentPath.contains(toBeVisitedNode)) {
					// update values
					visited[toBeVisitedNodeIndex] = true;
					distances[toBeVisitedNodeIndex] = current;
					predecessors[toBeVisitedNodeIndex] = vertex;
					queue.add(toBeVisitedNode); // even if we explored it previously we might need to alter the values

					// create a copy of the current path and update it with the vertex that is the
					// source of the edge
					Set<Vertex> currentPathCopy = new HashSet<>(currentPath);
					currentPathCopy.add(vertex);
					shortestPaths.set(toBeVisitedNodeIndex, currentPathCopy);
				}

			}
		}
		return;
	}
}
