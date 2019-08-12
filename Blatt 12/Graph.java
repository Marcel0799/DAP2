import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

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

	public void fromFile(String filepath) {
		
		BufferedReader file;
		String zeile = "";
		StringTokenizer st;

		try {			
			file = new BufferedReader( new FileReader( "C:\\Users\\marce\\Desktop\\Kurzzeitaufgaben\\Blatt 12\\" + filepath) );
			
			zeile = file.readLine();
			while (zeile != null) {
				
				st = new StringTokenizer(zeile,",");
				int wert1 = Integer.parseInt(st.nextToken());
				int wert2 = Integer.parseInt(st.nextToken());
				
				Node knoten1 = new Node(wert1);
				Node knoten2 = new Node(wert2);
				this.addNote(knoten1.getId());		
				this.addNote(knoten2.getId());
				this.addEdge(knoten1.getId(),knoten2.getId());

				zeile = file.readLine();
			}
		} catch (Exception e) {
			System.out.println("Fehler");
		}
	}
}
