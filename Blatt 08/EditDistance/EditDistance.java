public class EditDistance {
	public static void main(String Args[]) {
		if(Args.length == 2) {
			String a = Args[0];
			String b = Args[1];
			distance(a,b);
		} else {
			System.out.println("falsche Eingabe");
		}
	}
	
	
	public static int distance (String a, String b) {
		
		int [] [] array = new int[a.length()+1] [b.length()+1];
		
		char [] ar = a.toCharArray();
		char [] br = b.toCharArray();
		
		
		for(char e: ar) {
			System.out.print(e);
		}
		System.out.println();
		
		for(char e: br) {
			System.out.print(e);
		}
		System.out.println();
		
		
		array [0] [0] = 0;
		
		for (int i = 1; i<ar.length+1 ; i++) {
			array[i] [0] = i;
		}
		for (int i = 1; i<br.length+1 ; i++) {
			array[0] [i] = i;
		}
		
		int maxNumber = Math.max(2 * a.length(), 2*b.length());
		
		
		for(int i = 1; i<ar.length+1; i++) {
			for(int j = 1 ; j<br.length+1; j++) {
				array[i] [j] = maxNumber;
				if(i<ar.length && j<br.length) {
					if(ar[i] - br[j] == 0) {
						array[i] [j] = array[i-1] [j-1];
					}
				}
				if(array[i] [j] > array[i-1] [j-1]+1) {
					array[i] [j] = array[i-1] [j-1]+1;
				}
				if(array[i] [j]> array[i] [j-1]+1) {
					array[i] [j] = array[i] [j-1]+1;
				}
				if(array[i] [j]> array[i-1] [j]+1) {
					array[i] [j] = array[i-1] [j]+1;
				}
			}
		}
		
		
		

		for(int i = 0; i<ar.length+1; i++) {
			for(int j = 0 ; j<br.length+1; j++) {
				System.out.printf(" %2d ", array[i] [j]);
			}
			System.out.println();
		}
		
		return 1;
	}
}