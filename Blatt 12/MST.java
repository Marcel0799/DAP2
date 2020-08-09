import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class MST {
    public static void main(String[] args) {
        if (args.length != 1) {
            fatal("Exakt ein Dateipfad als Argument erwartet.");
        }

        Graph g = new Graph();
        try {
            g = Graph.fromFile(args[0]);
        } catch (IllegalArgumentException e) {
            fatal("Datei konnte nicht gelesen werden.");
        } catch (IOException e) {
            fatal("Datei exisitert nich oder konnte nicht geoeffnet werden.");
        }

        ArrayList<Edge> edgesMSTKruskal = timeKruskal(g);
        if (edgesMSTKruskal == null) {
            System.out.println("Graph nicht zusammenhaengend!");
        } else if (edgesMSTKruskal.size() <= 20) {
            Collections.sort(edgesMSTKruskal);
            System.out.println(edgesMSTKruskal);
        }

        ArrayList<Edge> edgesMSTPrim = timePrim(g);

        if (edgesMSTPrim == null) {
            System.out.println("Graph nicht zusammenhaengend!");
        } else if (edgesMSTPrim.size() <= 20) {
            Collections.sort(edgesMSTPrim);
            System.out.println(edgesMSTPrim);
        }
    }

    private static ArrayList<Edge> timePrim(Graph g) {
        long start = System.currentTimeMillis();
        ArrayList<Edge> edgesMST = Prim.solve(g);
        double time = ((double) (System.currentTimeMillis() - start)) / 1000.0;
        System.out.println("Prim: " + time + "s");
        return edgesMST;
    }

    private static ArrayList<Edge> timeKruskal(Graph g) {
        long start = System.currentTimeMillis();
        ArrayList<Edge> edgesMST = Kruskal.solve(g);
        double time = ((double) (System.currentTimeMillis() - start)) / 1000.0;
        System.out.println("Kruskal: " + time + "s");
        return edgesMST;
    }

    private static void fatal(String message) {
        System.err.println("FEHLER: " + message);
        System.err.println("Aufruf: java GraphApplication <filename>");
        System.exit(1);
    }
}
