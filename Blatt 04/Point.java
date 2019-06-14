
public class Point  {
	private int dimension = 0;
	private double[] values = {};
	
	public Point (int d, double ... values) throws IllegalArgumentException{
		if(values.length != d) {
			throw new IllegalArgumentException();
		} else {
			dimension = d;
			this.values = values;
		}
	}
	public double get( int i ) {
		return values[i];
	}
	public int getDim() {
		return dimension;
	}
	public void SysOut() {
		System.out.print("Punkt: ( ");
		for (int i = 0 ; i< values.length ; i++) {
			System.out.print(" " +values[i]+ " ");
		}
		System.out.print(" )\n");
	}
}