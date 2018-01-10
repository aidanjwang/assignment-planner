package aps;

import static aps.Utils.error;

/**
 * Reads and interprets commands from an input source.
 * Based off the CommandInterpreter class in the UC Berkeley
 * CS61B database project with Prof. P. N. Hilfinger.
 * @author A. J. Wang
 */
public class CommandInterpreter {

    /* CONSTRUCTORS */

    CommandInterpreter(Tokenizer inp, APS aps) {
        _input = inp;
        _APS = aps;
    }

    /* METHODS */

    public boolean command() {
        switch (_input.peek()) {
            case "add":
                addCommand();
                break;
            case "remove":
                removeCommand();
                break;
            case "view":
                viewCommand();
                break;
            case "exit":
            case "quit":
                return false;
            default:
                throw error("That is an unrecognizable command.");
        }
        return true;
    }

    /**
     * Executes add command. Calls AddAssignment class.
     */
    private void addCommand() {
        _input.next();
        _APS.addAssignment(AddAssignment.add(_input, _APS));
    }

    /**
     * Executes remove command. Prompts user for assignment
     * name to remove.
     */
    private void removeCommand() {
        _input.next();
        //TODO
    }

    private void viewCommand() {
        _input.next();
        switch (_input.peek()) {
            case "today":
                viewTodayCommand();
                break;
            case "all":
                viewAllCommand();
                break;
            case "subjects":
                viewSubjectsCommand();
                break;
        }
    }

    /**
     * Executes view all command.
     */
    private void viewSubjectsCommand() {
        _input.next();
        _input.next(";");
        _APS.viewCategorical();
    }

    /**
     * Executes view today command.
     */
    private void viewTodayCommand() {
        _input.next();
        _input.next(";");
        _APS.viewToday();
    }

    /**
     * Executes view all lists command.
     */
    private void viewAllCommand() {
        _input.next();
        _input.next(";");
        _APS.viewAll();
    }

    /**
     * Parse a literal and return the string it represents (i.e., without
     * single quotes).
     */
    String literal() {
        String lit = _input.next(Tokenizer.LITERAL);
        return lit.substring(1, lit.length() - 1).trim();
    }

    /**
     * Advance the input past the next semicolon.
     */
    void skipCommand() {
        while (true) {
            try {
                while (!_input.nextIf(";") && !_input.nextIf("*EOF*")) {
                    _input.next();
                }
                return;
            } catch (RuntimeException excp) {
                /* No action */
            }
        }
    }

    /* FIELDS */

    private Tokenizer _input;

    private APS _APS;

}
