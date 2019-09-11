public class Test {
	public static void main (String Args[]) {
		int[] array = {5,2,7,4,3,12};
		SearchTree one = new SearchTree(array);

// AUSGABE 
		System.out.println("Normal");
		for ( int i = 0 ; i<array.length ; i++ ) {
			System.out.print(" " + array[i] + ",");
		}
		System.out.println();
		
		System.out.println("PRE");		
		one.preOrder();
		System.out.println();

		System.out.println("IN");		
		one.inOrder();
		System.out.println();

		System.out.println("POST");		
		one.postOrder();
		System.out.println();
		
	}
}