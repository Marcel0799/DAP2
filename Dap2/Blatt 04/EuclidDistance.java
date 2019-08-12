
public class EuclidDistance implements Distance{

	public double distance (Point p1, Point p2) {
			
		int dim = p1.getDim();
		double[] abst�nde = new double[dim];
		double summe = 0;
		
		if(p1.getDim() != p2.getDim()) {
			return -1;
		}
		
		for(int i = 0 ; i< dim ; i++) {
			abst�nde[i] = p1.get(i) - p2.get(i);
			System.out.println("abst�nde bei " + i + " -> " + abst�nde[i] );
		}
		for (int i = 0 ; i< dim ; i++) {
			summe = summe + (abst�nde[i] * abst�nde[i]);
		}
		
		summe = Math.sqrt(summe);
		
		return summe;
	}
}
