import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class GraphTest {

    /**
     * Loest das SSSP-Proplem mit Hilfe des Algortihmus von Bellman-Ford.
     *
     * @param g      der Graph
     * @param source id des Startknotes
     * @return Array mit Weglaengen; Element i gibt die Laenge eines kuerzesten
     * Weges von dem Knoten mit der id source zu dem Knoten mit id i an
     */
    public static double[] sssp(Graph g, int source) {
        // Knoten holen
        ArrayList<Node> nodes = g.getNodes();
        // Startknoten bestimmen
        Node s = g.getNode(source);
        // Tabelle anlegen
        double[][] result = new double[nodes.size()][nodes.get(nodes.size() - 1).getId() + 1];
        // Kostenmatrix aus dem Graphen holen
        double[][] costMatrix = g.getWeights();
        // Tabelle initialisieren
        for (Node v : nodes) {
            if (v == s) continue;
            result[0][v.getId()] = Double.POSITIVE_INFINITY;
        }

        // Eigentlicher Algorithmus
        for (int i = 1; i < nodes.size(); i++) {
            for (Node v : nodes) {
                double minCost = result[i - 1][v.getId()], tmpCost;
                for (Node u : nodes) {
                    // Fuer alle Knotenpaare testen, ob Kante exisitiert
                    // und Minimale Kosten bestimmen
                    tmpCost = costMatrix[u.getId()][v.getId()];
                    if (tmpCost == 0) continue;
                    minCost = Math.min(minCost, result[i - 1][u.getId()] + tmpCost);
                }
                result[i][v.getId()] = minCost;
            }
        }

        // Nichtgenutzte Knoten-IDs auf Distanz unendlich setzen

        for (int i = 0; i < result[0].length; i++) {
            if (i == source) continue;
            if (result[nodes.size() - 1][i] == 0) {
                result[nodes.size() - 1][i] = Double.POSITIVE_INFINITY;
            }
        }

        // Endgueltige Distanz zurueckgeben
        return result[nodes.size() - 1];
    }

    public static void main(String[] args) throws NumberFormatException, NoSuchElementException {
        if (args.length != 2) {
            fatal("Falsche Anzahl an Parametern");
        }

        Graph g = new Graph();
        try {
            g = Graph.fromFile(args[0]);
        } catch (IOException e) {
            fatal("Angegebene Datei konnte nicht gelesen werden.");
        } catch (IllegalArgumentException e) {
            fatal("Graph Datei konnte auf Grund eines Formatierungsfehlers nicht gelesen werden.");
        }

        if (g.getNodes().isEmpty()) {
            fatal("Leerer Graph.");
        }

        // Fuehre Bellman-Ford-Algorithmus aus
        int nodenumber = -1;
        try {
            nodenumber = Integer.parseInt(args[1]);
        } catch (NumberFormatException ex) {
            fatal("Zweiter Parameter muss ein Integer sein");
        }
        if (g.getNode(nodenumber) == null) {
            fatal("Ungueltiger Startknoten angegeben: " + args[1]);
        }
        double[] minCost = sssp(g, nodenumber);
        ArrayList<Node> nodes = g.getNodes();
        Node s = g.getNode(nodenumber), e = s;
        double maxDist = 0d;
        for (Node n : nodes) {
            if (nodes.size() <= 20) {
                System.out.println("Abstand von Knoten " + n.getId() + " zu Knoten " + s.getId() + ": " + minCost[n.getId()]);
            }
            if (minCost[n.getId()] != Double.POSITIVE_INFINITY && minCost[n.getId()] > maxDist) {
                maxDist = minCost[n.getId()];
                e = n;
            }
        }
        System.out.println("Der maximale Abstand ist " + maxDist + " fuer Knoten " + e.getId());
    }

    private static void fatal(String message) {
        System.err.println("FEHLER: " + message);
        System.err.println("Aufruf: java GraphTest <filename> <nodenumber>");
        System.exit(1);
    }
}
