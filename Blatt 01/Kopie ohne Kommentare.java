public class SiebDesEratosthenes{
	public static void main (String Args[]) {
		
		int obergrenze = 0;
		String Ausgabe = "";
		
		if(Args.length > 0) {
			obergrenze = Integer.parseInt(Args[0]);
			boolean[] isPrime = new boolean[obergrenze];
			for (int i  = 0; i<isPrime.length ; i++ ) {
				isPrime[i] = true;
			}
			
			if ( Args.length > 1) {
				Ausgabe = Args[1];
			}
			
			isPrime = primsBerechenen(isPrime);
			
			if(Ausgabe.equals("-o")) {
				
				for (int i = 0 ; i<isPrime.length; i++) {
					if(isPrime[i]) {
						System.out.println(i);
					}
				}
				
			}
		} else {
			System.out.println("bitte obergrenze angeben");
		}
	}
	
	public static boolean[] primsBerechenen(boolean[] isPrime) {
		for (int i = 2; i<isPrime.length ; i++ ) {
			for ( int y = 2 ; y*i<isPrime.length ; y++ ) {
				isPrime[i*y] = false;
			}
		}
		isPrime[0] = false;
		isPrime[1] = false;
		return isPrime;
	}
}

/*
	for (boolean y : isPrime ) {
		System.out.println(y);
	}
*/