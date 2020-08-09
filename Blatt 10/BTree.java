import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class BTree implements Serializable {

    private static final long serialVersionUID = 1L;

    /* Zählvariable für IDs */
    private static int COUNTER = 0;

    /* Zählvariablen für Festplattenzugriffe */
    public static int READ_OPS = 0;
    public static int WRITE_OPS = 0;

    /* Attribute */
    private int id;
    private int t;
    private Integer[] children;
    private Integer[] keys;
    private int nKeys;
    private boolean isLeaf;

    /* Konstruktor der Standardwerte anlegt und auf die Festplatte speichert */
    public BTree(int t) throws IOException {
        id = COUNTER++;
        this.t = t;
        children = new Integer[2 * t];
        keys = new Integer[2 * t - 1];
        nKeys = 0;
        isLeaf = true;
        save();
    }

    /* Druckt die Struktur des BTree in die Kommandozeile */
    public void printStructure() throws IOException, ClassNotFoundException {
        if (isLeaf) {
            System.out.println("Knoten " + id + " ist ein Blatt mit " + nKeys + " Werten");
        } else {
            System.out.print("Knoten " + id + " hat " + (nKeys + 1) + " Kinder: ");
            for (int i = 0; i <= nKeys; i++) {
                System.out.print(children[i] + " ");
            }
            System.out.println();
            for (int i = 0; i <= nKeys; i++) {
                read(children[i]).printStructure();
            }
        }
    }

    /* Druckt alle Schlüssel im BTree inorder mit Leerzeichen getrennt in die Standardausgabe */
    public void printInOrder() throws IOException, ClassNotFoundException {
        StringBuilder sb = new StringBuilder();
        collectInOrder(sb);
        System.out.println(sb.toString());
    }

    /* Sammelt rekursiv einen Ausgabestring des inorder Durchlaufs */
    private void collectInOrder(StringBuilder sb) throws IOException, ClassNotFoundException {
        if (isLeaf) {
            for (int i = 0; i < nKeys; i++) {
                sb.append(keys[i]);
                sb.append(" ");
            }
        } else {
            for (int i = 0; i < nKeys; i++) {
                read(children[i]).collectInOrder(sb);
                sb.append(keys[i]);
                sb.append(" ");
            }
            read(children[nKeys]).collectInOrder(sb);
        }
    }

    /* Fügt den Schlüssel k in einem Blatt ein */
    private void sortInsert(int k) {
        int i = nKeys;
        while (i > 0 && keys[i - 1] > k) {
            keys[i] = keys[i - 1];
            i--;
        }
        keys[i] = k;
        nKeys++;
    }

    /* 
     * Aufruf an die Wurzel um einen Schlüssel k zu löschen.
     * Gibt die nach Abschluss der Operation aktuelle Wurzel zurück.
     */
    public BTree rootDelete(int k) throws IOException, ClassNotFoundException {
        delete(k);
        if (nKeys == 0 && !isLeaf) {
            return read(children[0]);
        } else {
            return this;
        }
    }

    /* Rekursiver Aufruf um einen Schlüssel k aus dem BTree zu löschen */
    private void delete(int k) throws IOException, ClassNotFoundException {
        /* Invariante aus der VL */
        assert keys.length > 1: "Not enough children in node for delete";
        /* Ersten Schlüsselindex suchen, der >= k ist */
        int i = nKeys;
        while (i > 0 && keys[i - 1] >= k)
            i--;
        /* Fall 1: Aus Blatt löschen */
        if (isLeaf) {
            if (keys[i] == k) {
                /* Alle Schlüssel nach vorne bewegen, wodurch der letzte verschwindet */
                for (int j = i; j < nKeys - 1; j++) {
                    keys[j] = keys[j + 1];
                }
                nKeys--;
            }
        /* Randfall: Aus innerem Knoten löschen, k ist im rechtesten Teilbaum */
        } else if (i == nKeys) {
            /* Kinder von Festplatte laden */
            BTree lchild = read(children[i - 1]);
            BTree rchild = read(children[i]);
            /* Randfall 1: Beide Kinder sind zu klein */
            if (lchild.nKeys < t && rchild.nKeys < t) {
                /* Linkes und rechtes Kinde kombinieren */
                lchild.keys[lchild.nKeys] = keys[i - 1];
                System.arraycopy(rchild.keys, 0, lchild.keys, lchild.nKeys + 1, rchild.nKeys);
                System.arraycopy(rchild.children, 0, lchild.children, lchild.nKeys + 1, rchild.nKeys + 1);
                lchild.nKeys += rchild.nKeys + 1;
                nKeys--;
            /* Randfall 2: Rechtestes Kind hat zu wenig Werte */
            } else {
                if (rchild.nKeys < t) {
                    /* Schlüssel nach rechts bewegen */
                    for (int j = rchild.nKeys; j > 0; j--) {
                        rchild.keys[j] = rchild.keys[j - 1];
                    }
                    /* Kinder nach rechts bewegen */
                    for (int j = rchild.nKeys + 1; j > 0; j--) {
                        rchild.children[j] = rchild.children[j - 1];
                    }
                    /* Rechts rotieren (s. VL) */
                    rchild.keys[0] = keys[i - 1];
                    rchild.children[0] = lchild.children[lchild.nKeys];
                    rchild.nKeys++;
                    keys[i - 1] = lchild.keys[lchild.nKeys - 1];
                    lchild.nKeys--;
                    lchild.save();
                }
                /* Umbenennen um äußeres delete mit zu nutzen */
                lchild = rchild;
            }
            lchild.delete(k);
        /* Aus innerem Knoten löschen */
        } else {
            /* Kinder von Festplatte laden */
            BTree lchild = read(children[i]);
            BTree rchild = read(children[i + 1]);
            /* Randfall 1: Beide Kinder sind zu klein */
            if (lchild.nKeys < t && rchild.nKeys < t) {
                /* Linkes und rechtes Kinde kombinieren */
                lchild.keys[lchild.nKeys] = keys[i];
                System.arraycopy(rchild.keys, 0, lchild.keys, lchild.nKeys + 1, rchild.nKeys);
                System.arraycopy(rchild.children, 0, lchild.children, lchild.nKeys + 1, rchild.nKeys + 1);
                lchild.nKeys += rchild.nKeys + 1;
                /* Schlüssel und Kinder weiter rechts nach links schieben */
                for (int j = i; j < nKeys - 1; j++) {
                    keys[j] = keys[j + 1];
                    children[j + 1] = children[j + 2];
                }
                nKeys--;
            } else {
                /* Randfall 2: Linkes Kind hat zu wenig Werte */
                if (lchild.nKeys < t) {
                    /* Links rotieren (s. VL) */
                    lchild.keys[lchild.nKeys] = keys[i];
                    lchild.children[lchild.nKeys + 1] = rchild.children[0];
                    lchild.nKeys++;
                    keys[i] = rchild.keys[0];
                    /* Schlüssel nach links bewegen */
                    for (int j = 1; j < rchild.nKeys; j++) {
                        rchild.keys[j - 1] = rchild.keys[j];
                    }
                    /* Kinder nach links bewegen */
                    for (int j = 1; j <= rchild.nKeys; j++) {
                        rchild.children[j - 1] = rchild.children[j];
                    }
                    rchild.nKeys--;
                    rchild.save();
                /* Randfall 3: Kind ist im inneren Knoten gespeichert */
                } else if (keys[i] == k) {
                    BTree help = lchild;
                    while (!help.isLeaf) {
                        help = read(help.children[help.nKeys]);
                    }
                    keys[i] = help.keys[help.nKeys - 1];
                    k = keys[i];
                }
            }
            lchild.delete(k);
        }
        save();
    }

    /* 
     * Aufruf an die Wurzel um einen Schlüssel k hinzuzufügen.
     * Gibt die nach Abschluss der Operation aktuelle Wurzel zurück.
     */
    public BTree rootInsert(int k) throws IOException, ClassNotFoundException {
        /* Wenn Wurzel voll ist, aufteilen */
        if (nKeys == keys.length) {
            BTree newRoot = new BTree(t);
            newRoot.nKeys = 0;
            newRoot.isLeaf = false;
            newRoot.children[0] = this.id;
            newRoot.split(0, this);
            newRoot.insert(k);
            return newRoot;
        } else {
            /* Sonst normal rekursiv einfügen */
            insert(k);
            return this;
        }
    }

    /* Rekursiver Aufruf um einen Schlüssel k in den BTree hinzuzufügen */
    private void insert(int k) throws IOException, ClassNotFoundException {
        if (isLeaf) {
            /* In Blättern einfach einfügen und wegspeichern */
            sortInsert(k);
            save();
        } else {
            /* Sonst passenden Kindknoten suchen, notfals splitten und rekursiv speichern */
            int i = nKeys;
            while (i > 0 && keys[i - 1] > k)
                i--;
            BTree child = read(children[i]);
            if (child.nKeys == child.keys.length) {
                BTree sibling = split(i, child);
                if (keys[i] < k) {
                    child = sibling;
                }
            }
            child.insert(k);
        }
    }

    /* Teilt den i-ten Kindknoten und gibt den dabei neu erstellten Geschwisterknoten zurück */
    private BTree split(int i, BTree child) throws IOException {
        BTree sibling = new BTree(t);
        /* Daten von Kind in Geschwisterknoten übertragen */
        sibling.isLeaf = child.isLeaf;
        System.arraycopy(child.keys, t, sibling.keys, 0, t - 1);
        System.arraycopy(child.children, t, sibling.children, 0, t);
        child.nKeys = sibling.nKeys = t - 1;
        for (int j = nKeys + 1; j > i + 1; j--) {
            children[j] = children[j - 1];
        }
        children[i + 1] = sibling.id;
        /* Medianwert in Elternknoten speichern */
        sortInsert(child.keys[t - 1]);
        /* Alles zur Festplatte sichern */
        save();
        child.save();
        sibling.save();
        return sibling;
    }

    /* Schreibt aktuellen Zustand auf die Festplatte */
    public void save() throws IOException {
        String filepath = filename();
        FileOutputStream fileOut = new FileOutputStream(filepath);
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(this);
        objectOut.close();
        WRITE_OPS++;
    }

    /* Liest letzten gesicherten Zustand eines Knoten von der Festplatte */
    public static BTree read(int id) throws IOException, ClassNotFoundException {
        String filepath = filename(id);
        FileInputStream fileIn = new FileInputStream(filepath);
        ObjectInputStream objectIn = new ObjectInputStream(fileIn);
        BTree ret = (BTree) objectIn.readObject();
        objectIn.close();
        READ_OPS++;
        return ret;
    }

    /* Übersetzt eine ID in einen Dateinamen */
    private static String filename(int id) {
        return id + ".node";
    }

    /* Abkürzung, um den Dateinamen eines Knoten direkt zu bestimmen */
    private String filename() {
        return BTree.filename(this.id);
    }

}