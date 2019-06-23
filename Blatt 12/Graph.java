import java.util.ArrayList;

public class Graph {
	private ArrayList<Node> nodes = new ArrayList<Node> ();
	
	public Graph () {
	}
	
	public boolean contains(int id) {
		for(Node x: nodes) {
			if (x.getId() == id) {
				return true;
			}
		}
		return false;
	}
	
	public boolean addNote(int id) {
		if(this.contains(id)) {
			return false;
		}
		nodes.add(new Node(id));
		return true;
	}
	
	public Node getNode(int id) {
		for(Node x : nodes) {
			if(x.getId() == id) {
				return x;
			}
		}
		return null;
	}

	public void addEdge(int src , int dst) {
		for(Node i : nodes) {
			if(i.getId() == src) {
				for (Node j : nodes) {
					if(j.getId() == dst) {
						i.addEdge(j);
					}
				}
			}
		}
		
	}
}
