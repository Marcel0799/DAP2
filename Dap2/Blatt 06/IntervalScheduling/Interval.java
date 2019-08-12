
public class Interval implements Comparable {
	private int start;
	private int end;
	
	public Interval(int start, int end) {
		this.start = start;
		this.end = end;
	}

	public int getStart() {
		return start;
	}
	public int getEnd() {
		return end;
	}
	@Override
	public String toString() {
		return "(" + start + ", " + end + ")";
	}

	@Override
	public int compareTo(Object o) {
		Interval z = (Interval) o;
		return this.getEnd()-z.getEnd();
	}
	

}
