
class Edge {
	private Node src;
	private Node dst;
	private int weight;

	/**
	 * Create a new Edge
	 * 
	 * @param source      Node of the edge
	 * @param destination Node of the edge
	 */
	public Edge(Node src, Node dst, int weight) {
		this.src = src;
		this.dst = dst;
		if (weight < 1) {
			throw new IllegalArgumentException();
		}
		this.weight = weight;
	}

	/**
	 * @return weight of edge
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * @return source Node
	 */
	public Node getSrc() {
		return this.src;
	}

	/**
	 * @return destination Node
	 */
	public Node getDst() {
		return this.dst;
	}
	
	/**
	 * @return a string representation of the edge (without src)
	 */
	public String toStringTarget() {
		StringBuilder sb = new StringBuilder();
		sb.append(dst.getId()).append(" (").append(weight).append(")");
		return sb.toString();
	}
	
	/**
	 * @return a string representation of the edge
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(src.getId()).append(" zu ").append(dst.getId()).append(" (").append(weight).append(")");
		return sb.toString();
	}

}
