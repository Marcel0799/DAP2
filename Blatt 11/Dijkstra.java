import java.io.IOException;
import java.security.KeyException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.NoSuchElementException;

class Dijkstra {
	/**
	 * Dijkstra search for the shortest path from s to t
	 *
	 * @param g the graph to be traversed
	 * @param s the starting node
	 * @param t the target node
	 * @throws TooManyElementsException
	 * @throws KeyException
	 * @throws IndexOutOfBoundsException
	 *
	 * @returns Distances from s to t null if s is not in the graph
	 */
	private static DijkstraResult dijkstra(Graph g, int s, int t)
			throws IndexOutOfBoundsException, KeyException, TooManyElementsException {
		Node start = g.getNode(s);
		Node destination = g.getNode(t);
		if (start == null || destination == null)
			return null;
		else if (start == destination) {
			Graph path = new Graph();
			path.addNode(s);
			return new DijkstraResult(path, s, 0);
		}

		// initialize Distances and predecessors
		HashMap<Node, Integer> distances = new HashMap<Node, Integer>();
		HashMap<Node, Node> predecessor = new HashMap<Node, Node>();

		// Priority Queue for non visited nodes
		MinPQ queue = new MinPQ(g.numNodes());
		queue.insert(new HeapElement(0, start));
		distances.put(start, 0);
		for (Node v : g.getNodes()) {
			// every Node not contained in distances has infinity distance otherwise
			// checking sum of lengths can cause an overflow
			if (v != start) {
				// for the queue MAX_VALUE is used to represent infinity
				queue.insert(new HeapElement(Integer.MAX_VALUE, v));
			}
		}

		HashSet<Node> visited = new HashSet<Node>();

		while (!queue.isEmpty()) {
			HeapElement currentElement = queue.extractMin();
			Node currentNode = currentElement.getNode();

			if (!visited.contains(currentNode)) { // node not visited/black
				if (!distances.containsKey(currentNode)) {
					return null; // no path available
				}
				if (currentNode == destination) { // shortest path found
					return new DijkstraResult(reconstructShortestPath(predecessor, start, destination), s,
							distances.get(destination));
				}
				visited.add(currentNode);

				// consider all adjacent nodes
				for (Edge e : currentNode.getAdjList()) {
					Node dst = e.getDst();
					if (!distances.containsKey(dst)
							|| distances.get(currentNode) + e.getWeight() < distances.get(dst)) {
						distances.put(dst, distances.get(currentNode) + e.getWeight());
						predecessor.put(dst, currentNode);
						queue.decreaseDistance(dst, distances.get(dst));
					}
				}
			}
		}
		return new DijkstraResult(reconstructShortestPath(predecessor, start, destination), s,
				distances.get(destination));
	}

	/**
	 * Reconstruction of the shortest path
	 * 
	 * @param predecessor of nodes in the shortest path
	 * @param s           the starting node
	 * @param t           the target node
	 * @return a new graph representing the shortest path from s to t
	 */
	private static Graph reconstructShortestPath(HashMap<Node, Node> predecessor, Node s, Node t) {
		Graph path = new Graph();
		Node current = t;
		Node prev = predecessor.get(current);
		while (current != s) { // or (prev != null)
			path.addNode(current.getId());
			path.addNode(prev.getId());
			for (Edge e : prev.getAdjList()) {
				// find the edge on the shortest path (connecting predecessor with current)
				if (e.getDst() == current) {
					path.addEdge(prev.getId(), current.getId(), e.getWeight());
					break;
				}
			}
			current = prev;
			prev = predecessor.get(current);
		}
		return path;
	}

	public static void main(String[] args) {
		int s = -1;
		int t = -1;
		Graph g = null;
		if (args.length != 3) {
			fatal("Exakt ein Dateipfad und IDs von Start- und Zielknoten als Argumente erwartet.");
		}
		try {
			g = Graph.fromFile(args[0]);
		} catch (IllegalArgumentException e) {
			fatal("Datei konnte nicht gelesen werden.");

		} catch (IOException | NoSuchElementException e) {
			fatal("Datei exisitert nicht oder konnte nicht geoeffnet werden.");
		}
		try {
			s = Integer.parseInt(args[1]);
			t = Integer.parseInt(args[2]);
			if (!g.contains(s) || !g.contains(t)) {
				fatal("Start und Zielkonten muessen im Graph enthalten sein.");
			}
		} catch (NumberFormatException e) {
			fatal("Keine gueltige ID fuer Start- oder Zielknoten.");
		}
		try {
			DijkstraResult result = dijkstra(g, s, t);
			if (result == null) {
				System.out.println("Es wurde kein Pfad von Knoten " + s + " zu Knoten " + t + " gefunden.");

			} else {
				System.out.println(result.toString());
			}
		} catch (IndexOutOfBoundsException | KeyException |TooManyElementsException e) {
			fatal("Pfad konnte nicht berechnet werden.");
		}
	}

	private static void fatal(String message) {
		System.err.println("FEHLER: " + message);
		System.err.println("Aufruf: java Diskstra <filename> <start-id> <destination-id>");
		System.exit(1);
	}

}
