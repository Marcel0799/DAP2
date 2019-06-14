import java.util.ArrayList;
import java.util.List;

public class ConvexHull {
	
	public ConvexHull() {
	}
	
	
	public List<Point> simpleConvex(Point[] P) throws IllegalArgumentException {
		List<Point> huelle = new ArrayList<Point>();
		
		for(Point a : P) {
			for (Point b: P) {
				if(a.equals(b)) {
					continue;
				}
				boolean existLeft = false;
				boolean existRight = false;
				for(Point c: P) {
					if(c.equals(b)|| c.equals(a)) {
						continue;
					}
					double position = LinksOderRechts(a.get(0), a.get(1), b.get(0) , b.get(1) , c.get(0) , c.get(1));
					if (position < 0) {
						existRight = true;
					}	else 
					if (position > 0) {
						existLeft = true;
					}
				}
				if(existLeft ^ existRight) {
					huelle.add(a);
					huelle.add(b);
				}
			}
		}
		
		return huelle;
	}
	
	public double LinksOderRechts(double x1,double y1, double x2, double y2 , double x, double y) {
		return (x2 - x1) * (y - y1) - (y2 -y1) * (x - x1);
	}
	


}