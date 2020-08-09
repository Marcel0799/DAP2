public class HeapElement {

	    private int distance;
	    private Node node;

	    public HeapElement(int distance, Node node) {
	        this.distance = distance;
	        this.node = node;
	    }

	    public int getDistance() {
	        return this.distance;
	    }

	    public void setDistance(int distance) {
	        this.distance = distance;
	    }

	    public Node getNode() {
	        return this.node;
	    }

	    public String toString() {
	        return "(" + this.distance + ", " + this.node.toString() + ")";
	    }
	}
