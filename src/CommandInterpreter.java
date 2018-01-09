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
                return false;
            default:
                System.out.println("That is an unrecognizable command.");
        }
        return true;
    }

    /**
     * Executes add command. Calls AddAssignment class.
     */
    private void addCommand() {
        _APS.addAssignment(AddAssignment.add());
    }

    /**
     * Executes remove command. Prompts user for assignment
     * name to remove.
     */
    private void removeCommand() {
        /*String subjectName;
        do {
            System.out.println("Remove assignment from which subject?");
            subjectName = _input.next();
        } while (!_APS.containsSubjectName(subjectName));
        Subject subject = _APS.getSubject(subjectName);

        String name;
        do {
            System.out.println("Remove which assignment?");
            name = _input.next();
        } while (!subject.get_assignments().contains())

        String name = _input.next();
        Assignment a = new Assignment(name, )
        if (_APS.get_assignments().contains())
            //TODO*/
    }

    /**
     * Executes view all command.
     */
    private void viewAllCommand() {
        _APS.viewCategorical();
    }

    /**
     * Executes view today command.
     */
    private void viewTodayCommand() {
        _APS.viewToday();
    }

    /**
     * Executes view all lists command.
     */
    private void viewAllListsCommand() {
        _APS.viewAll();
    }

    /* FIELDS */

    private Scanner _input;

    private APS _APS;

}
