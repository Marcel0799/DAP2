import java.io.IOException;
import java.util.NoSuchElementException;

public class GraphApplication {

	public static void main(String[] args) {
		if (args.length != 1) {
			fatal("Exakt ein Dateipfad als Argument erwartet.");
		}
		try {
			Graph g = Graph.fromFile(args[0]);
			System.out.print(g.toString());
		} catch (IllegalArgumentException e) {
			fatal("Datei konnte nicht gelesen werden.");

		} catch (IOException | NoSuchElementException e) {
			fatal("Datei exisitert nicht oder konnte nicht geoeffnet werden.");
		}
	}

	private static void fatal(String message) {
		System.err.println("FEHLER: " + message);
		System.err.println("Aufruf: java GraphApplication <filename>");
		System.exit(1);
	}

}
