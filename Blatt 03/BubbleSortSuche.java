public class BubbleSortSuche {

    public static double bubbleSort(int[] array) { //Gibt die Zeit in s zurueck, die zum Sortieren benoetigt wurde
        long start = System.currentTimeMillis();
        for (int i=0; i < array.length; i++) {
            for (int j = array.length - 1; j >= i + 1; j--) {
                if (array[j - 1] > array[j]) {
                    int tmp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = tmp;
                }
            }
        }
        return ((double) (System.currentTimeMillis() - start)) / 1000.0;
    }

    public static int[] genArray(int n) {
        // Erzeugt ein integer array der Groesse n und befuellt es in absteigender Reihenfolge
        int[] array = new int[n];
        for (int i = 0; i < n; i++)
            array[i] = n - i;

        return array;
    }

    public static void main(String[] args) {
        double timeWanted = 5.0;
        double timeSplit1, timeSplit2, timeLower, timeUpper;
        int upper, lower, splitPoint1, splitPoint2;

        timeLower = timeUpper = 0.0;

        //--------Eingabe lesen - Start --------------

        //Keine Eingabe
        if (args.length < 1) {
            System.err.println("FEHLER: Bitte Zeit angeben.");
            System.exit(1);
        }

        if (args.length > 1) {
            System.err.println("FEHLER: Zu viele Parameter uebergeben.");
            System.exit(1);
        }

        //Keine Zahl?
        try {
            timeWanted = Double.parseDouble(args[0]);
        } catch (NumberFormatException ex) {
            System.err.println("FEHLER: Ungueltige Zeitangabe.");
            System.exit(1);
        }

        //Wir wollen nur Zahlen > 0
        if (timeWanted <= 0.0) {
            System.err.println("FEHLER: Die angegebene Zeit, muss groesser als 0 sein.");
            System.exit(1);
        }
        // ------Eingabe lesen - Ende ---------

        //Erstmal obere Schranke finden
        upper = 500;
        do {
            timeLower = timeUpper;
            System.gc();
            upper *= 2;
            timeUpper = bubbleSort(genArray(upper));
            System.out.println("Size: " + upper + ", Time: " + timeUpper + "s");
        } while (timeUpper < timeWanted);

        lower = upper / 2;

        //Jetzt Intervallschachtelung betreiben
        do {
            splitPoint1 = lower + (upper - lower) / 3;
            splitPoint2 = lower + (upper - lower) * 2 / 3;

            System.gc();

            timeSplit1 = bubbleSort(genArray(splitPoint1));

            System.gc();

            timeSplit2 = bubbleSort(genArray(splitPoint2));

            System.out.println("Sizes: " + lower + ", " + splitPoint1 + ", " + splitPoint2 + ", " + upper);
            System.out.println("Times: " + timeLower + "s, " + timeSplit1 + "s, " + timeSplit2 + "s, " + timeUpper + "s");

            // Fallunterscheidung um das richtige Intervall zu finden
            if (timeSplit1 > timeWanted){
                upper = splitPoint1;
                timeUpper = timeSplit1;
            }
            else if (timeSplit1 <= timeWanted && timeSplit2 > timeWanted) {
                upper = splitPoint2;
                timeUpper = timeSplit2;
                lower = splitPoint1;
                timeLower = timeSplit1;
            } else {
                lower = splitPoint2;
                timeLower = timeSplit2;
            }
        } while (Math.min(Math.abs(timeSplit1 - timeWanted), Math.abs(timeSplit2 - timeWanted)) > 0.1);

        // Jetzt überprüfen welche Intervallgrenze den kleinsten Laufzeitabstand von der vorgegebenen Laufzeit hat
        if (Math.abs(timeSplit1 - timeWanted) < Math.abs(timeSplit2 - timeWanted))
            System.out.println("Size: " + splitPoint1 + ", Time: " + timeSplit1 + "s");
        else
            System.out.println("Size: " + splitPoint2 + ", Time: " + timeSplit2 + "s");
    }
}
