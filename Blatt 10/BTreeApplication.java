import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.function.Supplier;

public class BTreeApplication {

    /* Custom exception type */
    private final static class ExitProgramException extends IllegalStateException {
        private static final long serialVersionUID = 1L;
    }

    /* Print error message if boolean is met */
    private static void condError(Boolean condition, String msg) {
        if (condition) {
            error(msg);
        }
    }

    /* Print error message and throw exception (exit program) */
    private static void error(String msg) {
        System.out.println("FEHLER: " + msg);
        throw new ExitProgramException();
    }

    /* Parse integer from string or trigger error */
    private static int intOrError(String s, String msg) {
        int ret = 0;
        try {
            ret = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            error(msg);
        }
        return ret;
    }

    /* Basically all the "necessary stuff" in this class */
    public static void main(String[] args) {
        try {
            /* Check input count */
            if (args.length != 2) {
                error("Falsche Anzahl Argumente.\nKorrekte Nutzung: ...");
            }
            /* Read arguments and trigger exceptions if something is wrong */
            String file = args[0];
            int t = intOrError(args[1], "Zweites Argument muss Zahl sein.");
            condError(t < 2, "Zweites Argument muss mindestesn 2 sein.");
            /* Parse input file line by line */
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                int read = 0;
                BTree root = new BTree(t);
                for (String line = br.readLine(); line != null; line = br.readLine()) {
                    /* Read tokens from line and switch cases for insert or delete */
                    StringTokenizer st = new StringTokenizer(line, ",");
                    condError(st.countTokens() != 2, "Wrong number of values in row");
                    String opStr = st.nextToken();
                    int k = intOrError(st.nextToken(), "Zeile enthält keine Zahl");
                    switch (opStr) {
                        case "i":
                            root = root.rootInsert(k);
                            break;
                        case "d":
                            root = root.rootDelete(k);
                            break;
                        default:
                            error("Operation nicht verstanden");
                    }
                    read++;
                }
                /* Final prints */
                System.out.println(read + " Zeilen gelesen");
                System.out.println("Festplatte geschrieben: " + BTree.WRITE_OPS);
                System.out.println("Festplatte gelesen: " + BTree.READ_OPS);
                root.printStructure();
                root.printInOrder();
                br.close();
            } catch (FileNotFoundException e) {
                error("Datei existiert nicht");
            } catch (IOException | ClassNotFoundException e) {
                error("IO mit Festplatte hat nicht geklappt");
            }
        } catch (ExitProgramException e) {
        } finally {
            /* Delete all temporary files */
            for (File f : new File("./").listFiles()) {
                if (f.toString().endsWith(".node")) {
                    f.delete();
                }
            }
        }
    }
    
}