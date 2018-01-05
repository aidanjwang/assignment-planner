import java.util.Scanner;

/**
 * The main program for the Assignment Planner system.
 * @author A. J. Wang
 */
public class Main {

    /**
     * Runs Assignment Planner System. Initializes or deserializes
     * system, then executes commands from System.in until receiving
     * a 'quit' ('exit') command. Based off Main.main in the UC
     * Berkeley CS61B database project with Prof. P. N. Hilfinger.
     * @param args
     */
    public static void main(String... args) {
        System.out.println("Assignment Planner System");

        if (!APS.isInitialized()) {
            // Initialize
        } else {
            _APS = APS.deserialize();
        }

        Scanner input = new Scanner(System.in);
        CommandInterpreter interpreter =
                new CommandInterpreter(input, System.out);

        while (true) {
            if (!interpreter.command()) {
                break;
            }
        }
    }

    /**
     * The APS instance being used.
     */
    private static APS _APS;

}
