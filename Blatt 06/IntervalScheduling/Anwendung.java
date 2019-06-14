import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Anwendung {
	public static void main (String Args[]) {
		
		String datei = "datenBsp3.zahlen";
		String scheduling = "Lateness";
		
		BufferedReader file;
		String zeile = "";
		StringTokenizer st;
		ArrayList<Interval> intervals = new ArrayList<Interval>();
		ArrayList<Job> jobs = new ArrayList<Job>();
		
		try {
			
			file = new BufferedReader( new FileReader(
					"C:\\Users\\marce\\Desktop\\Dateien\\Universität\\Semester 002"
					+ "\\Datenstrukturen Algorithmik Programmieren 2\\Langaufgaben\\Blatt06 Vorlagen\\" + datei) 
					);
			
			zeile = file.readLine();
			while (zeile != null) {
				
				st = new StringTokenizer(zeile,",");
				int wert1 = Integer.parseInt(st.nextToken());
				int wert2 = Integer.parseInt(st.nextToken());
				
				Interval ivall = new Interval(wert1, wert2);
				intervals.add(ivall);
				
				Job job = new Job(wert1, wert2);
				jobs.add(job);
				
				zeile = file.readLine();
			}
		} catch (Exception e) {
			System.out.println("Fehler");
		}
		
		if(scheduling.equals("Interval")) {
			
			for(Interval a: intervals) {
				System.out.println(a);
			}
			
			intervals = intervalScheduling(intervals);
			System.out.println("__________________");
			
			for(Interval a: intervals) {
				System.out.println(a);
			}
			
		
		
		
		}	else if (scheduling.equals("Lateness")) {
			
			for(Job a: jobs) {
				System.out.println(a);
			}
			
			jobs.sort(null);
			System.out.println("_______sortiert__________");
			for(Job a: jobs) {
				System.out.println(a);
			}
			
			int[] jobbs = latenessScheduling(jobs);
			System.out.println("________Fertig__________");
			

			int verspaetung = 0;
			
			for(int i=0; i<jobbs.length;i++) {
				
				int aktuelleVerspaetung = jobbs[i]-jobs.get(i).getDeadline()+1; 
				
				if(verspaetung<aktuelleVerspaetung) {
					verspaetung = aktuelleVerspaetung;
				}
				
				System.out.println(jobbs[i]);
			}
			
			System.out.println("Verspätung = " + verspaetung);
			
		
		
		
		}	else {
			System.out.println("Verwalt Paramter angeben");
		}
	}
	
	
	
	
	
	public static ArrayList<Interval> intervalScheduling(ArrayList<Interval> intervals) {
		
		intervals.sort(null);
		
		
		ArrayList<Interval> result = new ArrayList<Interval> ();
		result.add(intervals.get(0));
		int i = 1;
		int j = 0;
		
		while(i<intervals.size()) {
			if(intervals.get(i).getStart()>= intervals.get(j).getEnd()) {
				result.add(intervals.get(i));
				j = i;
			}
			i++;
		}
		
		return result;
	}
	
	
	
	public static int[] latenessScheduling(ArrayList<Job> jobs) {
		
		int[] result = new int[jobs.size()];
		
		int z = 0;
		for (int i = 0; i<jobs.size(); i++) {
			result[i] = z;
			z = z + jobs.get(i).getDauer();
		}
		
	return result;
	}
}
