class DijkstraResult {
	private Graph path;
	private int src;
	private int distance;

	public DijkstraResult(Graph path, int src, int distance) {
		this.src = src;
		this.path = path;
		this.distance = distance;
	}

	public Graph getPath() {
		return path;
	}

	public int getDistance() {
		return distance;
	}

	/**
	 * Return string representation of the path and its length
	 */
	public String toString() {
		String ret = "Kuerzester Pfad ";
		Node current = path.getNode(src);
		while (!current.getAdjList().isEmpty()) {
			assert current.getAdjList().size() == 1;
			ret += current.toString() + ", ";
			current = current.getAdjList().get(0).getDst();
		}
		ret += current.toString() + " ";
		return ret + "mit Laenge " + distance + " gefunden.";
	}
}