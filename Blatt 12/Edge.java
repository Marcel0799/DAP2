
public class Edge {
	private Node src;
	private Node dst;
	
	public Edge (Node von , Node zu) {
		this.src = von;
		this.dst = zu;
	}
	
	public Node getSrc() {
		return src;
	}
	
	public Node getDst() {
		return dst;
	}
	
	@Override
	public String toString() {
		return "( Von: " + src + ", nach : " + dst + " )";
	}
}
