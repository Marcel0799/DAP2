public class Euclid {
	public static void main (String[] Args) {	//beginn des Programms
		if (Args.length == 2) {		// ueberprueft ob der benutzer wirklich 2 zahlen angeben hat
			int zahl1 = Integer.parseInt(Args[0]); 	// uebernimmt die erste Zahl
			int zahl2 = Integer.parseInt(Args[1]);	// uebernimmt die zweite Zahl
			if ( zahl1>=0 && zahl2>=0 ) {		// ueberprueft ob beide zahlen positiv sind (da dies die vorgabe ist) 
				int ergebnis = ggtBerechnen(zahl1,zahl2);	// ruft den algoritmus auf und speichert das Ergebnis
				System.out.println("Groesster gemeinsamer Teiler: " + ergebnis);		// zeigt dem Benutzer das Ergebnis
			}	
			else {		
				System.out.println("Nur zwei Positive Zahlen angeben");	// sollten die zahlen nicht beide positiv gewesen sein weist das programm den nutzer darauf hin
			}			
		}
		else {
			System.out.println("Bitte genau Zwei Zahlen angeben");	// falls nicht genau zwei zahlen angegeben wurden weist das programm den nutzer darauf hin
		}
	}
	public static int ggtBerechnen(int a,int b) { 	// beginn des Algorithm
		if(b==0) {			// ueberprueft ob b gleich null ist da anosnten danach durch 0 geteilt werden wuerde
			return a;  		// sollte b null sein ist der algortimus fertig und gibt a zurueck
		}
		else {
			return ggtBerechnen(b,a % b);	// ruft den algoritmus erneut auf mit vertauschten zahlen und der ersten zahl modulo der zweiten, 
							// da die zweite offenbar nicht null ist. Durch das Teilen ergibt sich jeweils ein gemeinsame Teiler 
							// durch wiederholtes aufrufen der Funktion ergibt sich so der groeﬂte gemeinsame Teiler
		}
	}
}