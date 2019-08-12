	import java.io.BufferedReader;
	import java.io.FileReader;
	import java.util.StringTokenizer;
	
public class TestReadFile {
	public static void main(String Args[]) {
		BufferedReader file;
		String zeile = "";
		StringTokenizer st;
		
		try {
			
			file = new BufferedReader( new FileReader("C:\\Users\\M.Bienia\\Desktop\\Spielwiese\\Blatt06 Vorlagen\\datenBsp1.zahlen") );
			zeile = file.readLine();
			while (zeile != null) {
				
				st = new StringTokenizer(zeile,",");
				int start = Integer.parseInt(st.nextToken());
				int ende = Integer.parseInt(st.nextToken());
				Interval ivall = new Interval(start, ende);
				System.out.println(ivall);
				zeile = file.readLine();
			}
		} catch (Exception e) {
			System.out.println("Fehler");
		}
	}
}
