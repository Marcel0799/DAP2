
public class Job implements Comparable{
	
	private int dauer;
	private int deadline;
	
	public Job(int dauer, int deadline) {
		this.dauer = dauer;
		this.deadline = deadline;
	}
	
	public int getDeadline() {
		return deadline;
	}
	public int getDauer() {
		return dauer;
	}
	@Override
	public String toString() {
		return "(" + dauer + ", " + deadline + ")";
	}
	@Override
	public int compareTo(Object o) {
		Job oo = (Job) o;
		return this.getDeadline() - oo.getDeadline();
	}
}
