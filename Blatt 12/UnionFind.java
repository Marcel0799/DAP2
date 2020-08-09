import java.util.ArrayList;
import java.util.HashMap;

public class UnionFind {
    ArrayList<ArrayList<Node>> sets = new ArrayList<>();
    HashMap<Node, ArrayList<Node>> setHash = new HashMap<>();

    public UnionFind(ArrayList<Node> nodes) {
        for (Node node : nodes) {
            ArrayList<Node> set = makeSet(node);
            sets.add(set);
            setHash.put(node, set);
        }
    }

    public Node find(Node x) {
        return setHash.get(x).get(0);
    }

    public void union(Node x, Node y) {
        // Beide Listen einer Zusammenhangskomponente laden
        ArrayList<Node> setx = setHash.get(x);
        ArrayList<Node> sety = setHash.get(y);

        /* Rollen von x und y vertauschen um moeglichst wenige Pointer zu aendern und
        wenige Knoten zwischen den Listen kopieren zu muessen */
        if (setx.size() < sety.size()) {
            ArrayList<Node> tmp = setx;
            setx = sety;
            sety = tmp;
        }

        // Pointer von allen Knoten aus Zusammenhangskomponente y aktualisieren
        for (Node node : sety) {
            setHash.put(node, setx);
        }

        // Knoten aus y zu x hinzufuegen und y aus Liste der Zusammenhangskomponenten entfernen
        setx.addAll(sety);
        sets.remove(sety);

        assert setHash.get(x) == setHash.get(y);
    }

    public static ArrayList<Node> makeSet(Node x) {
        ArrayList<Node> set = new ArrayList<>();
        set.add(x);
        return set;
    }

    public int getSetCount() {
        return sets.size();
    }
}
