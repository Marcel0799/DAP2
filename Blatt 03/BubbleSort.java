
public class BubbleSort {

    public static void bubbleSort(int[] array) { //Gibt die Zeit in s zurueck, die zum Sortieren benoetigt wurde
        for (int i=0; i < array.length; i++) {
            for (int j = array.length - 1; j >= i + 1; j--) {
                if (array[j - 1] > array[j]) {
                    int tmp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = tmp;
                }
            }
        }
    }

    public static int[] genArray(int n) {
        // Erzeugt ein integer array der Groesse n und befuellt es in absteigender Reihenfolge
        int[] array = new int[n];
        for (int i = 0; i < n; i++)
            array[i] = n - i;

        return array;
    }

    public static void main(String[] args) {

        if (args.length > 0){
            System.err.println("FEHLER: Es sollen keine Parameter uebergeben werden.");
            System.exit(1);
        }

        long tStart, tEnd;
        double secs;
        int[] array = genArray(50000);

        // Startzeitpunkt der Zeitmessung
        tStart = System.currentTimeMillis();

        bubbleSort(array);

        // Endzeitpunkt der Zeitmessung
        tEnd = System.currentTimeMillis();

        // Umrechnung von ms in s
        secs = (tEnd - tStart) / 1000.0;

        System.out.println(secs + "s");
    }
}
