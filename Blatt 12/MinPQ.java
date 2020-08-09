import java.security.KeyException;
import java.util.HashMap;

public class MinPQ {
	
	    private HeapElement[] array;
	    private HashMap<Node, Integer> nodes;
	    private int heap_size;

	    public MinPQ(int n) {
	        array = new HeapElement[n + 1];
	        nodes = new HashMap<Node, Integer>();
	        // Anfangs sind noch keine Nodes im Heap
	        heap_size = 0;
	    }

	    private void heapify(int i) {
	        int l = left(i);
	        int r = right(i);

	        int smallest = i;
	        if (l <= heap_size && array[l].getEdge().getWeight() < array[smallest].getEdge().getWeight()) {
	            // Linker Sohn existiert und ist kleiner
	            smallest = l;
	        }

	        if (r <= heap_size && array[r].getEdge().getWeight() < array[smallest].getEdge().getWeight()) {
	            // Rechter Sohn existiert und ist (noch) größer
	            smallest = r;
	        }

	        // Ist die Heapbedingung verletzt?
	        if (smallest != i) {
	            // Vertauschen des kleinsten Elementes mit dem Vaterelement
	            nodes.put(array[smallest].getNode(), i);
	            nodes.put(array[i].getNode(), smallest);
	            HeapElement a = array[i];
	            array[i] = array[smallest];
	            array[smallest] = a;

	            // Rekursive Wiederherstellung der Heapbedingung im Unterbaum
	            heapify(smallest);
	        }
	    }

	    public void insert(HeapElement element) throws TooManyElementsException, IndexOutOfBoundsException, KeyException {
	        if (heap_size == array.length - 1) {
	            throw new TooManyElementsException();
	        }

	        // Node breits vorhanden
	        if (nodes.containsKey(element.getNode())) throw new KeyException();

	        heap_size++;
	        nodes.put(element.getNode(), heap_size);
	        array[heap_size] = element;

	        // Neues Element wird hochgeführt, bis die Heapbedingung im Baum erfüllt ist
	        siftUP(heap_size);
	    }

	    public HeapElement extractMin() throws RuntimeException {
	        if (heap_size == 0) throw new RuntimeException("Keine Elemente im Heap.");

	        //Austausch des letzten Elements mit dem obersten Element
	        HeapElement min = array[1];
	        array[1] = array[heap_size];
	        nodes.remove(min.getNode());
	        nodes.put(array[1].getNode(), 1);
			array[heap_size] = null;
			heap_size--;
	        //Wiederherstellung der Heapbedingung im Baum
	        heapify(1);

	        assert isHeap(1) : "Heap Bedingung ist nach der Extraktion verletzt!";

	        return min;
	    }

	    public void decreaseWeight(Node node, Edge edge) throws IllegalArgumentException, IndexOutOfBoundsException, KeyException {
	        if (!nodes.containsKey(node)) throw new KeyException();

	        // Distanz des Elements aendern
	        int pos = nodes.get(node);

	        // Neuer Distanzwert muss echt kleiner sein
	        if (array[pos].getEdge().getWeight() <= edge.getWeight()) throw new IllegalArgumentException();

	        array[pos].setEdge(edge);

	        // Distanzwert is eventuell kleiner als der des Elternknoten, deshalb muesste das Element nach oben rutschen
	        siftUP(pos);

	        assert isHeap(nodes.get(node)) : "Heap Bedingung ist nach dem verringern eines Prioritaetswertes verletzt!";
	    }

	    private void siftUP(int pos) {
	    	Node node = array[pos].getNode();

	        // solange wir nicht an der Wurzel angekommen sind und der Distanzwert des Eltern-Knoten groeßer ist
	        // muss das betrachtete Element nach oben rutschen
	        while (pos > 1 && array[parent(pos)].getEdge().getWeight() > array[pos].getEdge().getWeight()) {
	        	nodes.put(array[parent(pos)].getNode(), pos);
	        	nodes.put(node, parent(pos));
	            HeapElement tmp = array[parent(pos)];
	            array[parent(pos)] = array[pos];
	            array[pos] = tmp;
	            pos = parent(pos);
	        }
	    }

	    public int getWeight(Node node) throws KeyException {
	    	if (!nodes.containsKey(node)) throw new KeyException();

	    	int pos = nodes.get(node);
	    	return array[pos].getEdge().getWeight();
		}

		public boolean containsNode(Node node) {
			return nodes.containsKey(node);
		}

	    public int parent(int i) {
	        return i / 2;
	    }

	    public int left(int i) {
	        return 2 * i;
	    }

	    public int right(int i) {
	        return 2 * i + 1;
	    }

	    private boolean isHeap(int i) {
	        if (left(i) <= heap_size && array[i].getEdge().getWeight() > array[left(i)].getEdge().getWeight()) return false;
	        return right(i) > heap_size || array[i].getEdge().getWeight() <= array[right(i)].getEdge().getWeight();
	    }
	    
	    public boolean isEmpty() {
	    	return heap_size == 0;
	    }

}
