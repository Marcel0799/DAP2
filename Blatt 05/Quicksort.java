import java.util.Random;

public class Quicksort {
	
	public static void main(String Args[]) {
		int n = 0;								// erstellen einer Grenze
		
		try {
			n = Integer.parseInt(Args[0]);					// setzten der Grenze
			if(n<0) {
				throw new IllegalArgumentException();
			}
		}	catch (Exception e) {						// abfangen falscher eingaben
			System.out.println("Bitte genau eine Positive Zahl angeben");
		}
		
		int[] a = ArrayErstellen(n);						// erstellen der zuälligen Zahlenlisten
		
		quicksort(a,0,a.length-1);						// aufruf von Quicksort	
		
		isSorted(a);								// überprüfen ob das feld sortiert ist
	
	}
	
	public static void quicksort(int[] a , int l , int r) {
		if(l<r) {
			int i = l;							
			int j = r;
			int pivot = a[(l+r)/2];						
			while (i<=j) {											
				while (a[i] < pivot) {
					i++;
				}
				while (a[j] > pivot) {
					j--;
				}
				if(i <= j) {
					int tmp = a[i];
					a[i] = a[j];
					a[j] = tmp;
					i++;
					j--;
				}
			}
			quicksort(a,l,j);
			quicksort(a,i,r);
		}
	}
	
	public static int[] ArrayErstellen (int n) {
		Random r = new Random();
		int[] result = new int[n];
		for(int i = 0; i<result.length ; i++) {
			result[i] = r.nextInt(100);
		}
		return result;
	}
	
	public static boolean isSorted(int[] array) {
		for (int i = 1 ; i< array.length ; i++) {
			if(array[i-1] > array[i]) {
				System.out.println("Feld NICHT sortiert!");
				return false;
			}
		}
		System.out.println("Feld sortiert");
		return true;
	}
}