import java.io.PrintStream;
import java.util.Scanner;

/**
 * Reads and interprets commands from an input source.
 * Based off the CommandInterpreter class in the UC Berkeley
 * CS61B database project with Prof. P. N. Hilfinger.
 * @author A. J. Wang
 */
public class CommandInterpreter {

    /* CONSTRUCTORS */

    CommandInterpreter(Scanner inp, APS aps) {
        _input = inp;
        _APS = aps;
    }

    /* METHODS */

    public boolean command() {
        switch (_input.next()) {
            case "add":
                addCommand();
                break;
            case "remove":
                removeCommand();
                break;
            case "view all":
                viewAllCommand();
                break;
            case "view today":
                viewTodayCommand();
                break;
            case "view all lists":
                viewAllListsCommand();
                break;
            case "exit":
            case "quit":
                exitCommand();
                return false;
            default:
                throw new RuntimeException("unrecognizable command");
        }
        return true;
    }

    /**
     * Executes add command.
     */
    private void addCommand() {
        _APS.addAssignment(AddAssignment.add());
    }

    private void removeCommand() {

    }

    /* FIELDS */

    private Scanner _input;

    private APS _APS;

}
