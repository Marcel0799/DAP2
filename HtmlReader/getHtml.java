import java.io.*;
import java.net.*;
import java.util.Scanner;

public class getHtml {
	public static void main (String Args[]) throws Exception {
		URL u = new URL ("https://www.google.com/");
		Scanner s = new Scanner(u.openStream());

		while(s.hasNext()) {
			System.out.println(s.nextLine());
		}
	}
}