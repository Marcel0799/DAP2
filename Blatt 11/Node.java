import java.util.ArrayList;
import java.util.Objects;

class Node {
	private int id;
	private ArrayList<Edge> adjList = new ArrayList<Edge>();

	/**
	 * Create new node with empty adjacency list.
	 * 
	 * @param int id identifier of the node
	 */
	public Node(int id) {
		if (id < 0) {
			throw new IllegalArgumentException();
		}
		this.id = id;
	}

	/**
	 * @return identifier
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return ArrayList<Edge> adjacency list.
	 */
	public ArrayList<Edge> getAdjList() {
		return adjList;
	}

	/**
	 * Check if this node has an edge to the node with the given identifier
	 * 
	 * @param int id of the dst node in question
	 * @return true if this node has an edge to the node with the given id, false
	 *         otherwise
	 */
	public boolean hasEdgeTo(int id) {
		for (Edge e : this.adjList) {
			if (e.getDst().getId() == id) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Add an Edge to this node.
	 * 
	 * @param Edge to be added
	 */
	public void addEdge(Edge e) {
		this.adjList.add(e);
	}

	/**
	 * Add an undirected edge to the given node to this node i.e. an edge from this
	 * to dst with the given weight
	 * 
	 * @param destination node for the edge
	 * @param weight      of the edge
	 * @return true if the edge is added, false otherwise
	 */
	public boolean addEdge(Node dst, int weight) {
		if (this.hasEdgeTo(dst.getId())) { // already in list
			return false;
		}
		addEdge(new Edge(this, dst, weight));
		return true;
	}

	/**
	 * Compare two nodes for equality of ids
	 * 
	 * @param other (node) Object
	 * @return true if this == other (only id is considered), false otherwise
	 */
	public boolean equals(Object other) {
		if (other == this)
			return true;
		if (!(other instanceof Node))
			return false;
		Node otherNode = (Node) other;
		return Objects.equals(id, otherNode.id);
	}

	public String toString() {
		return Integer.toString(id);
	}
}
