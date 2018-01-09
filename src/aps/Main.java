package aps;

import java.util.Scanner;

/**
 * The main program for the aps.Assignment Planner system.
 * @author A. J. Wang
 */
public class Main {

    /**
     * Runs aps.Assignment Planner System. Initializes or deserializes
     * system, then executes commands from System.in until receiving
     * a 'quit' ('exit') command. Based off aps.Main.main in the UC
     * Berkeley CS61B database project with Prof. P. N. Hilfinger.
     * @param args
     */
    public static void main(String... args) {
        System.out.println("aps.Assignment Planner System.");

        Scanner input = new Scanner(System.in);

        if (!APS.isInitialized()) {
            _APS = Initialize.initialize(input);
        } else {
            _APS = APS.deserialize();
        }

        CommandInterpreter interpreter =
                new CommandInterpreter(input, _APS);

        while (true) {
            if (!interpreter.command()) {
                _APS.serialize();
                break;
            }
        }
    }

    /**
     * The aps.APS instance being used.
     */
    private static APS _APS;

}
