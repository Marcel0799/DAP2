public class SiebDesEratosthenes{
	public static void main (String Args[]) {
		
		int obergrenze = 0;		// erstellen der Obergrenze, die anfangs noch nicht belegt ist 
		String Ausgabe = "";	// erstellen des textes in dem spaeter gepeichert werden soll ob eine ausgabe stattfindden  soll
		
		if(Args.length > 0) {	// ueberpruefen ob eine obergrenze angegeben wurde
			obergrenze = Integer.parseInt(Args[0]);	// speichern der Obergrenze
			boolean[] isPrime = new boolean[obergrenze];	// erstellen der Liste in der Die Prmzahlen berechnet werden sollen
			for (int i  = 0; i<isPrime.length ; i++ ) {		// es wird davon ausgegangen das alle zahlen erstmal primzahlen snd 
				isPrime[i] = true; // dies wird hier umgesetzt
			}
			
			if ( Args.length > 1) {	// es wird ueberprueft ob eine angabe zur ausgabe der Primahlen geacht wird 
				Ausgabe = Args[1];	// wenn ja wrd diese Angabe in dem String Ausgabe gespeichert
			}
			
			isPrime = primsBerechenen(isPrime); // der algorithmus von erastotheles wird aufgerufen und die Primzahen zu berechnen
			
			if(Ausgabe.equals("-o")) { 	// es wird ueberprueft ob der benutzer moechte das eine ausgabe der primzahlen stattfindet
				
				for (int i = 0 ; i<isPrime.length; i++) { // wenn ja werden diese ausgegeben
					if(isPrime[i]) {	// wenn eine zahl als wahr abgespeichert ist wird sie ausgegeben
						System.out.println(i);
					}
				}
				
			}
		} else {
			System.out.println("bitte obergrenze angeben"); // falss keine obergrenze angegeben wurde wird der benutzer hier darauf hingewiesen
		}
	}
	
	public static boolean[] primsBerechenen(boolean[] isPrime) { 	// algorithmus von erastotheles
		for (int i = 2; i<isPrime.length ; i++ ) {	// der algorithmus geht alle zahlen groesser zwei durch 
			for ( int y = 2 ; y*i<isPrime.length ; y++ ) { // und entfernt alle ihre vielfachen aus der liste der Primzahen
				isPrime[i*y] = false;
			}
		}
		isPrime[0] = false; // der algorithmus entfernt 0 als primzahle
		isPrime[1] = false; // der algorithmus entfernt 1 als Primzahle
		return isPrime; // er gibt die liste der Primahlen zurueck
	}
}
