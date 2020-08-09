import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringJoiner;
import java.util.StringTokenizer;
import java.util.function.Predicate;

class Graph {

	private ArrayList<Node> nodes = new ArrayList<Node>();

	/**
	 * Check if a node with the given id is present within the graph.
	 * 
	 * @param int id of the node in question
	 * @return true if a node with the given id is present, false otherwise
	 */
	public boolean contains(int id) {
		Predicate<Node> idPredicate = node -> node.getId() == id;
		return nodes.stream().anyMatch(idPredicate);

		/** Old-school, pre Java 8: */
		/*
		 * for (Node n : this.nodes) { if (n.getId() == id) { return true; } } return
		 * false;
		 */
	}

	/**
	 * Add a node to the graph if the id is still free.
	 * 
	 * @param int id of the new node
	 * @return true if node is successfully added, false otherwise
	 */
	public boolean addNode(int id) throws IllegalArgumentException {
		if (!this.contains(id)) {
			Node n = new Node(id);
			this.nodes.add(n);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @return ArrayList containing all nodes in the graph.
	 */
	public ArrayList<Node> getNodes() {
		return this.nodes;
	}

	/**
	 * Get the node with the given id from the graph.
	 * 
	 * @param int id of the node in question
	 * @return the Node if (it is present), null otherwise
	 */
	public Node getNode(int id) {
		for (Node n : this.nodes) {
			if (n.getId() == id) {
				return n;
			}
		}
		return null;
	}

	/**
	 * Add an Edge between the nodes with the ids srcid and dstid if both are
	 * present
	 * 
	 * @param int source id
	 * @param int destination id
	 * @return true if the edge is successfully added, false otherwise
	 */
	public boolean addEdge(int srcid, int dstid, int weight) throws IllegalArgumentException {
		if (!(this.contains(srcid)) || !(this.contains(dstid))) {
			return false;
		} else {
			this.getNode(srcid).addEdge(this.getNode(dstid), weight);
			return true;
		}
	}

	/**
	 * Read a graph from a .graph file using the format: Each line contains an edge,
	 * specified by source id and destination id, seperated by a comma. Example: 1,2
	 * 1,3 2,3
	 * 
	 * @param String filename/path of a .graph file
	 * @throws IOException
	 * @throws IllegalArgumentException
	 * @returns Graph built from the file
	 */
	public static Graph fromFile(String filename) throws  IllegalArgumentException, IOException {
		BufferedReader file = new BufferedReader(new FileReader(filename));
		String line;
		StringTokenizer st;
		Graph g = new Graph();
		while ((line = file.readLine()) != null) {
			st = new StringTokenizer(line, ",");
			int src = Integer.parseInt(st.nextToken());
			int dest = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			g.addNode(src);
			g.addNode(dest);
			g.addEdge(src, dest, weight);
		}
		file.close();
		return g;
	}

	/**
	 * Return string representation of a graph, listing each of its nodes with a
	 * list of outgoing edges
	 * 
	 * @return String representation of the graph
	 */
	public String toString() {
		StringBuilder result = new StringBuilder();
		for (Node n : nodes) {
			StringJoiner joiner = new StringJoiner(", ");
			result.append("Knoten ").append(n.getId()).append(" verbunden zu: ");
			for (Edge e : n.getAdjList()) {
				joiner.add(e.toStringTarget());
			}
			result.append(joiner.toString());
			result.append('\n');
		}
		return result.toString();
	}

	/**
	 * @return int number of nodes in the graph
	 */
	public int numNodes() {
		return this.nodes.size();
	}

}
