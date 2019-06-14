import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class Main {
	public static void main (String[] Args) throws IllegalArgumentException{
		
		Random r = new Random();
		
		Point a = new Point(2,10,10);
		Point b = new Point(2,10,100);
		Point c = new Point(2,100,10);
		
		Point[] P = new Point[1000];
		P[0] = a;
		P[P.length-1] = b;
		P[1] = c;
		
		for(int i = 2 ; i<P.length-1; i++) {
			double unteregrenzeX = 10;
			double oberegrenzeX = 100;
			double x = r.nextDouble()*(oberegrenzeX-unteregrenzeX)+unteregrenzeX;
			
			double unteregrenzeY = 10;
			double oberegrenzeY = -x+110;
			double y = r.nextDouble()*(oberegrenzeY-unteregrenzeY)+unteregrenzeY;
			
			P[i] = new Point(2, x, y);
		
		}
		
		ConvexHull test = new ConvexHull();
		List<Point> test2 = test.simpleConvex(P);
		ListIterator<Point> it = test2.listIterator();
		while (it.hasNext()) {
			it.next().SysOut();
		}
	}
}
