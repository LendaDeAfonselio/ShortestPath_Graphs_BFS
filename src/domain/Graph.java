package domain;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Class that represents the whole graph structure
 */
public class Graph {
	private List<Vertex> vertexes = new ArrayList<>();
	private List<Edge> edges[];
	private int edgeNumber = 0;

	@SuppressWarnings("unchecked")
	/**
	 * Constructor for graph, needs to be passed a list of vertex
	 * @param vertexes - the list of vertexes
	 */
	public Graph(List<Vertex> vertexes) {
		this.vertexes = vertexes;
		edges = (List<Edge>[]) new LinkedList[vertexes.size()];
		for (int i = 0; i < edges.length; ++i) {
			edges[i] = new LinkedList<>();
		}
	}

	public Graph(List<Vertex> vertexes, List<Edge>[] edges) {
		this.vertexes = vertexes;
		this.edges = edges;
	}

	/**
	 * function to retrieve the number of vertex in the graph
	 * @return the number of vertex
	 */
	public int vertexNumber() {
		return vertexes.size();
	}

	/**
	 * add a vertex to the graph
	 * @param toBeAdded the vertex to be added
	 */
	public void addVertex(Vertex toBeAdded) {
		this.vertexes.add(toBeAdded);
	}

	/**
	 * Adds an unidirectional edge between two nodes of the graph
	 * @param source - the source vertex
	 * @param destiny - the destiny
	 * @param weight - the weight of the path between them
	 */
	public void addUniDirectionalEdge(Vertex source, Vertex destiny, double weight) {
		Edge toBeAdded = new Edge(source, destiny, weight);
		edges[source.getPosition()].add(toBeAdded);
		edgeNumber++;
	}

	/**
	 * Adds a bidirectional graph between two vertexes (for example for A and B it would  add the edge A->B and B->A)
	 * @param x - a vertex
	 * @param y- a vertex
	 * @param weight - the weight of the path
	 */
	public void addBiDirectionalEdge(Vertex x, Vertex y, double weight) {
		Edge toBeAdded = new Edge(x, y, weight);
		edges[x.getPosition()].add(toBeAdded);

		Edge toBeAddedSecond = new Edge(y, x, weight);
		edges[y.getPosition()].add(toBeAddedSecond);
		edgeNumber += 2;
	}

	/**
	 * gets the vertex list and assignes an index to each one of them
	 * @return the list of vertexes with their index
	 */
	public List<Vertex> getIndexedVertexes() {
		int count = 0;
		for (Vertex v : vertexes) {
			v.setPosition(count++);
		}
		return vertexes;
	}

	/**
	 * retrieves all of the edges that have the source in vertex x
	 * @param x the source vertex
	 * @return the list of outgoing edges
	 */
	public List<Edge> getEdgesForVertex(Vertex x) {
		return edges[x.getPosition()];
	}
	
	public List<Vertex> getVertexes() {
		return vertexes;
	}
	
	/**
	 * Retrieves the number of edges in this graph
	 * @return the number of edges
	 */
	public int getEdgeNumber() {
		return edgeNumber;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Vertexes:" + vertexes + "\n");
		sb.append("Edges:\n");
		for(int i = 0; i < edges.length; i++) {			
			for (Edge e : edges[i]) {
				sb.append(e + "\n");
			}
		}
		return sb.toString();
	}

}
