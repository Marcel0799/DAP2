public class HeapElement {

	    private Node node;
		private Edge edge;

	    public HeapElement(Node node, Edge edge) {
	        this.node = node;
	        this.edge = edge;
	    }

	    public Edge getEdge() {
	        return this.edge;
	    }

	    public void setEdge(Edge edge) {
	        this.edge = edge;
	    }

	    public Node getNode() {
	        return this.node;
	    }

	    public String toString() {
	        return "(" + this.edge.getWeight() + ", " + this.node.toString() + ")";
	    }
	}
