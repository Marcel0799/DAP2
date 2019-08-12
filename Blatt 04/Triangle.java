
public class Triangle extends Simplex{

	int dimension = 0;
	Point[] eckPunkte = {};
	
	public Triangle(int d, Point ... ecken) throws Exception {
		super(d, ecken);
		this.dimension = d;
		eckPunkte = ecken;
	}
	
	@Override
	public boolean validate() {
		if(eckPunkte.length == 3 && dimension == 2) {
			for (int i = 0 ; i<eckPunkte.length ; i++ ) {
				if (eckPunkte[i].getDim()!= 2) {
					return false;
				}
			}
			return true;
		}	else {
			return false;
		}
	}
}
