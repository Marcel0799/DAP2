import java.security.KeyException;
import java.util.ArrayList;

public class Prim {
    public static ArrayList<Edge> solve(Graph g) {
        ArrayList<Node> nodes = g.getNodes();
        MinPQ minPQ = new MinPQ(nodes.size() - 1);

        ArrayList<Edge> edgesMST = new ArrayList<>();

        // MinPQ befuellen
        for (int i = 1; i < nodes.size(); i++) {
            Edge e = nodes.get(i).getAdjacentEdge(nodes.get(0));
            if (e == null) {
                try {
                    minPQ.insert(new HeapElement(nodes.get(i), new Edge(nodes.get(i), nodes.get(0), Integer.MAX_VALUE)));
                } catch (TooManyElementsException | KeyException tooManyElementsException) {
                    tooManyElementsException.printStackTrace();
                }
            } else {
                try {
                    minPQ.insert(new HeapElement(nodes.get(i), e));
                } catch (TooManyElementsException | KeyException tooManyElementsException) {
                    tooManyElementsException.printStackTrace();
                }
            }
        }

        assert !minPQ.isEmpty() : "Graph ist leer!";

        while (!minPQ.isEmpty()) {
            HeapElement min = minPQ.extractMin();

            // Wenn eine Kante die nicht existiert extrahiert wird, dann ist der Graph nicht zusammenhaengend
            if (min.getEdge().getWeight() == Integer.MAX_VALUE) {
                return null;
            }
            edgesMST.add(min.getEdge());
            updateMinPQ(minPQ, min.getEdge().getSrc());
        }

        return edgesMST;
    }

    public static void updateMinPQ(MinPQ minPQ, Node newNode) {
        assert newNode.getAdjList().size() > 0;

        /* Neue Kanten die nun aus der Zusammenhangskomponenten herausfuehren koennen das Gewicht einiger Knoten
        reduzieren. */
        for (Edge edge : newNode.getAdjList()) {
            try {
                if (minPQ.containsNode(edge.getDst()) && minPQ.getWeight(edge.getDst()) > edge.getWeight()) {
                    minPQ.decreaseWeight(edge.getDst(), edge.getSiblingEdge());
                }
            } catch (KeyException e) {
                e.printStackTrace();
            }
        }
    }
}
