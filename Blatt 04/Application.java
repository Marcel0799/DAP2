import java.util.Random;

public class Application {
	public static void main(String Args[]) throws Exception {
		Point a;
		Point b;
		Point c;
		
		Random r = new Random();
		Triangle dreieck;
		
		
		if(Args.length == 6) {
			a = new Point(2,Integer.parseInt(Args[0]),Integer.parseInt(Args[1]));
			b = new Point(2,Integer.parseInt(Args[2]),Integer.parseInt(Args[3]));
			c = new Point(2,Integer.parseInt(Args[4]),Integer.parseInt(Args[5]));
		} 	else if (Args.length != 0 && Args.length!= 6) {
			System.out.println("bitte genau 6 Parameter übegeben oder keine");
			System.out.println("java Application x1 y1 x2 y2 x3 y3");
			a = new Point(2,0,0);
			b = new Point(2,0,0);
			c = new Point(2,0,0);
		
		}	else {
			a = new Point(2,r.nextInt(2001)-1000,r.nextInt(2001)-1000);
			b = new Point(2,r.nextInt(2001)-1000,r.nextInt(2001)-1000);
			c = new Point(2,r.nextInt(2001)-1000,r.nextInt(2001)-1000);
		}

		dreieck = new Triangle(2, a,b,c);
		dreieck.SysOut();
		System.out.println("_____________________________________-");
		System.out.println("Umfang des Dreiecks ist = " + dreieck.perimeter());
		
		
	}
}
