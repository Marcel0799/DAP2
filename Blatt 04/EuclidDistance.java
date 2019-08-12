
public class EuclidDistance implements Distance{

	public double distance (Point p1, Point p2) {
			
		int dim = p1.getDim();
		double[] abstände = new double[dim];
		double summe = 0;
		
		if(p1.getDim() != p2.getDim()) {
			return -1;
		}
		
		for(int i = 0 ; i< dim ; i++) {
			abstände[i] = p1.get(i) - p2.get(i);
			System.out.println("abstände bei " + i + " -> " + abstände[i] );
		}
		for (int i = 0 ; i< dim ; i++) {
			summe = summe + (abstände[i] * abstände[i]);
		}
		
		summe = Math.sqrt(summe);
		
		return summe;
	}
}
