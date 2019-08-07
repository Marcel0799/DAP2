import java.io.*;
import java.net.*;
import java.util.Scanner;

public class getHtml {


	public static void main (String Args[]) throws Exception {
		
		Scanner scanIn = new Scanner(System.in);
		
		System.out.print("Link Angeben: ");
		String link = scanIn.next();
		System.out.println("");
		
		System.out.print("Bitte neuen DateiNamen Angeben : ");
		String dateiName = scanIn.next();
		System.out.println("");
		
		getCode(link,dateiName);		

	}


	

	public static void readCode() {

	}	

	public static void getCode(String link, String dateiName) throws Exception {
		URL u = new URL (link);

		Scanner s = new Scanner(u.openStream());
		PrintWriter pWriter = null;

 
        	try { 
        	    pWriter = new PrintWriter(new BufferedWriter(new FileWriter("./AutoSeiten/" + dateiName + ".txt"))); 
        	} catch (IOException ioe) { 
        	    ioe.printStackTrace(); 
        	}
 
		boolean began = false;
		boolean ended = false;
	
		while(s.hasNext()) {
			
			String currentLine = s.nextLine();
			
			if(currentLine.contains("srchrslt-adtable")) {
				began = true;
			}
			if(currentLine.contains("srchrslt-shngls") && currentLine.contains("a-padded l-container align-center")) {
				ended = true;
			}
			if(began && !ended) {
				pWriter.println(currentLine);
			}
		}

        	if (pWriter != null){ 
        		pWriter.flush(); 
        		pWriter.close(); 
        	}
	}
}