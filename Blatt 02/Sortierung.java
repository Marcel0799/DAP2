import java.util.Random;

public class Sortierung {
	public static void main (String Args[]) throws Exception  {
		
		int arrayLaenge = -1;
		String arraySortierung = "";
		String whichSort = "";
		
		if(Args.length==3) {
			arrayLaenge = Integer.parseInt(Args[0]);
			arraySortierung = Args[1];
			whichSort = Args[2];
		}	else {
			System.out.println("bitte genau drei Parameter angeben -> Sortierung (Obergrenze) , auf/ab/rand , insert / merge");
			throw new IllegalStateException();
		}
		if(arrayLaenge<0 || (!(arraySortierung.equals("auf")) && !(arraySortierung.equals("ab")) && !(arraySortierung.equals("rand"))) || 
			(!(whichSort.equals("insert")) && !(whichSort.equals("merge")))) {
			System.out.println("bitte nur genau diese drei Parameter angeben -> Sortierung (Obergrenze) , auf/ab/rand , insert / merge");
			throw new IllegalStateException();
		}
		
		long tBeginn, tEnde , mSec;
		int[] array = arrayErstellen(arrayLaenge, arraySortierung);
		
		tBeginn = System.currentTimeMillis();
		if(whichSort.equals("insert")) {
			insertionSort(array);
		} 	else if (whichSort.equals("merge")) {
			mergeSort(array);
		}	else {
			System.out.println("sollte unmöglich sein");
		}
		tEnde = System.currentTimeMillis();
		
		mSec = tEnde - tBeginn;
		
		System.out.println("_________ende____________");
		if(arrayLaenge<=100) {
			for(int i = 0; i<array.length ; i++) {
			System.out.println(i + " -> " + array[i]);
			}
		}
		System.out.println("Die benötigte Zeit war: " + mSec + " milli Sekunden");
		
	} 
	
	public static void insertionSort(int[] array) {
		int key = 0;
		int i = 0;
		
		for ( int j = 1 ; j<array.length ; j++ ) {
			key = array[j];
			i = j-1;
			while( i>-1 && array[i]> key) {
				array[i+1] = array[i];
				i=i-1;
			}
			array[i+1] = key;
		}	
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
	
	public static int[] arrayErstellen(int laenge, String art) {
		if(art.equals("auf")) {
			return aufArray(laenge);
		}
		if(art.equals("ab")) {
			return abArray(laenge);
		}
		return randArray(laenge);
	}
	
	public static int[] aufArray(int laenge) {
		int[] array = new int[laenge];
		for ( int i = 0 ; i< array.length ; i++) {
			array[i] = i;
		}
		if(array.length<=100) {
			for (int i = 0 ; i<array.length ; i++) {
				System.out.println(array[i]);
			}
		}
		return array;
	}
	
	public static int[] abArray(int laenge) {
		int[] array = new int[laenge];
		for ( int i = 0 ; i< array.length ; i++) {
			array[i] = array.length-1-i;
		}
		if(array.length<=100) {
			for (int i = 0 ; i<array.length ; i++) {
				System.out.println(array[i]);
			}
		}
		return array;
	}
	
	public static int[] randArray(int laenge) {
		int[] array = new int[laenge];
		Random r = new Random();
		for ( int i = 0 ; i<array.length ; i++) {
			array[i] = r.nextInt(1000);
		}
		if(array.length<=100) {
			for (int i = 0 ; i<array.length ; i++) {
				System.out.println(array[i]);
			}
		}
		return array;
	}
	
	public static void mergeSort(int[] array) {
		int[] tmpArray = new int[array.length];
		mergeSort(array, tmpArray, 0, array.length-1);
//		assert isSorted(array);
	}
	
	public static void mergeSort(int[] array ,int[] tmpArray, int begin , int end) {
		if(begin<end) {
			int mitte = (begin+end)/2;
			mergeSort(array,tmpArray,begin,mitte);
			mergeSort(array,tmpArray,mitte+1,end);
			mergeSort(array,tmpArray,begin,mitte,end);
		}
	}
	public static void mergeSort(int[] array,int[] tmpArray, int begin, int mitte, int end) {
		int a = begin;
		int b = mitte+1;
		int c = begin;
		while(a<=mitte || b<=end) {
			if(a<=mitte && b<=end && (array[a] <= array[b])) {
				tmpArray[c] = array[a];
				c++;
				a++;
			}	else if(a<=mitte && b<=end && (array[a] > array[b])){
				tmpArray[c] = array[b];
				c++;
				b++;
			}	else if(a>mitte && !(b>end)) {
				tmpArray[c] = array[b];
				c++;
				b++;
			}	else if(b>end && !(a>mitte)) {
				tmpArray[c] = array[a];
				c++;
				a++;
			}	else {
				System.out.println("unmöglich");
			}
		}
		for (int i = begin; i<=end ; i++) {
			array[i] = tmpArray[i];
		}
	}
}