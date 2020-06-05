package algorithm;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import domain.Edge;
import domain.GraphBFS;
import domain.Vertex;

public class BreadthFirstSearch {

	public void BreadFirstSearch(GraphBFS graph, Vertex src, int totalVertexNumber, Vertex predecessors[],
			List<Set<Vertex>> paths, double distances[]) {

		for (int i = 0; i < totalVertexNumber; i++) {
			paths.add(new HashSet<Vertex>());
		}

		// a queue to maintain queue of vertices whose
		// adjacency list is to be scanned as per normal
		// BFS algorithm using LinkedList of Integer type
		LinkedList<Vertex> queue = new LinkedList<>();

		// boolean array visited[] which stores the
		// information whether ith vertex is reached
		// at least once in the Breadth first search
		boolean visited[] = new boolean[totalVertexNumber];

		// initially all vertices are unvisited
		// so v[i] for all i is false
		// and as no path is yet constructed
		// dist[i] for all i set to infinity
		for (int i = 0; i < totalVertexNumber; i++) {
			visited[i] = false;
			distances[i] = Double.POSITIVE_INFINITY;
			predecessors[i] = null;
		}

		distances[src.getPosition()] = 0.0;
		// now source is first to be visited and
		// distance from source to itself should be 0
		visited[src.getPosition()] = true;
		distances[src.getPosition()] = 0;
		queue.add(src);

		// bfs Algorithm
		while (!queue.isEmpty()) {
			Vertex vertex = queue.remove();
			int currentVertexPosition = vertex.getPosition();

			for (Edge e : graph.getEdgesForVertex(vertex)) {
				Vertex toBeVisitedNode = e.getDestiny();
				int toBeVisitedNodeIndex = toBeVisitedNode.getPosition();
				double current = distances[currentVertexPosition] + e.getWeight();

				if (e.getDestiny() != src) {
					// avoid repeated nodes
					if (!visited[toBeVisitedNodeIndex] || (current < distances[toBeVisitedNodeIndex]
							&& !predecessors[toBeVisitedNodeIndex].equals(vertex))) {
						visited[toBeVisitedNodeIndex] = true;
						distances[toBeVisitedNodeIndex] = current;
						predecessors[toBeVisitedNodeIndex] = vertex;
						queue.add(toBeVisitedNode);

						Set<Vertex> currentPath = new HashSet<>(paths.get(currentVertexPosition));
						if (!currentPath.add(vertex)) {
							System.err.println("F");
						}
						paths.set(toBeVisitedNodeIndex, currentPath);
					}
				}
				
			}
		}
		return;
	}
}
