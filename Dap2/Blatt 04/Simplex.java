
public abstract class Simplex {
	private int dimension = 0;
	private Point[] eckPunkte = {};
	
	public Simplex (int d, Point... ecken) throws Exception {
		dimension = d;
		eckPunkte = ecken;
	}
	
	public int getDim() {
		return dimension;
	}
	
	public Point getEcke (int i) throws Exception{
		if (i >= 0 && i<dimension+1) {
			return eckPunkte[i];
		}	else {
			throw new IllegalStateException();
		}
	}
	
	public double perimeter() {
		double umfang = 0;
		EuclidDistance x = new EuclidDistance();
		
		for (int i = 0; i<eckPunkte.length-1 ; i++) {
			umfang = umfang + x.distance(eckPunkte[i], eckPunkte[i+1]);
		}
		umfang = umfang + x.distance(eckPunkte[eckPunkte.length-1],eckPunkte[0]);
		
		return umfang;
	}
	
	public void SysOut() {
		System.out.println("Simplex hat " + this.dimension + " dimensionen" );
		for (int i = 0 ; i < eckPunkte.length ; i++) {
			System.out.println("Punkt " + i);
			eckPunkte[i].SysOut();
		}
	}
	
	public abstract boolean validate();
}
