
public class Münzwechsel {
	public static void main(String Args[]) {
		
		String fehler = "bitte geben sie als ersten Parameter Auro oder Alternative an und als zweiten Parameter den auszugeben Betrag in cent";
		
		String waehrung = "";
		int b = 0;
			
		if(Args.length == 2) {
			try {
				waehrung = Args[0];
				b = Integer.parseInt(Args[1]);
				if(b<0) {
					throw new IllegalArgumentException();
				}
				if(!(waehrung.equals("Euro")||waehrung.equals("Alternative"))) {
					throw new IllegalArgumentException();			
				}
			} catch (Exception e) {
				System.out.println(fehler);
				System.exit(-1);
			}
			
		}	else {
			System.out.println(fehler);
			System.exit(-1);
		}
		
		int[] w;
		
		if(waehrung.equals("Euro")) {
			w = new int[7];
			w[0] = 200;
			w[1] = 100;
			w[2] = 50;
			w[3] = 10;
			w[4] = 5;
			w[5] = 2;
			w[6] = 1;
		}	else {
			w = new int[8];
			w[0] = 200;
			w[1] = 100;
			w[2] = 50;
			w[3] = 10;
			w[4] = 5;
			w[5] = 4;
			w[6] = 2;
			w[7] = 1;
		}
		
		int[] result = change(b,w);
		
		for (int i = 0 ; i<result.length ; i++) {
			if(i == 0) {
				System.out.print("(" + result[i]);
			} else {
				System.out.print(", " + result[i]);
			}
		}
		System.out.print(")\n");
		for (int i = 0 ; i<w.length ; i++) {
			if(i == 0) {
				System.out.print("(" + w[i]);
			} else {
				System.out.print(", " + w[i]);
			}
		}
		System.out.print(")\n");
	}
	
	public static int[] change (int b , int[] w) {
		int[] result = new int[w.length];
		int i = 0;
		while (b>0 && i<w.length) {
			result[i] = b / w[i];
			b = b%w[i];
			i++;
		}
		return result;
	}
	
}
