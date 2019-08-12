import java.util.ArrayList;
import java.util.List;

public class Node {
	private ArrayList<Edge> adjazenzliste = new ArrayList<>();
	private int id;
	
	public Node(int ID) {
		this.id = ID;
	}
	
	public int getId() {
		return id;
	}
	
	public List<Edge> getAdjazenListe () {
		return adjazenzliste;
	}
	
	public boolean addEdge(Node dst) {
		return adjazenzliste.add(new Edge(this, dst));
	}
	
	@Override
	public boolean equals (Object other) {
		try {
			Node x = (Node) other;
			return this.id == x.id;
		}	catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
		return false;
	}
	
	
	
	@Override 
	public String toString() {
		return " " + id + " ";
	}
	
	public void showListe () {
		String result = "( ";
		for(Edge x : adjazenzliste) {
			result = result + ", " + x.getDst();
		}
		result = result + " )";
		System.out.println(result);
	}
}
