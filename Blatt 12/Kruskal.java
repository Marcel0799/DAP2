import java.util.ArrayList;
import java.util.Collections;

public class Kruskal {
    public static ArrayList<Edge> solve(Graph g) {
        ArrayList<Edge> edgesMST = new ArrayList<>();

        // alle Kanten sortieren
        ArrayList<Edge> edges = g.getEdges();
        Collections.sort(edges);

        // Knoten kommen in die Union-Find-Datenstruktur
        ArrayList<Node> nodes = g.getNodes();
        UnionFind connectedComponents = new UnionFind(nodes);

        for (Edge edge : edges) {
            int oldSetCount = connectedComponents.getSetCount();

            // Fuege Kante hinzu, wenn sie nicht innerhalb einer existierenden Zusammenhangskomponente verlauft
            if (!connectedComponents.find(edge.getSrc()).equals(connectedComponents.find(edge.getDst()))) {
                edgesMST.add(edge);
                connectedComponents.union(edge.getSrc(), edge.getDst());
                assert oldSetCount < connectedComponents.getSetCount();
            }

            // Wenn nur noch eine Zusammenhangskomponente existiert haben wir einen MST gefunden und koennen beenden
            if (connectedComponents.getSetCount() == 1) {
                break;
            }
        }

        /* Wenn alle Kanten betrachtet wurden aber noch mehr als eine Zusammenhangskomponente existiert, dann ist
           der Graph nicht zusammenhaengend. */
        if (connectedComponents.getSetCount() > 1) {
            return null;
        }
        return edgesMST;
    }
}
