package aps;

import java.time.LocalDate;
import java.util.ArrayList;

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
     * Executes add command. Prompts user for subject, name, and
     * due date. Then prompts for tasks that make up assignment.
     */
    private void addCommand() {
        _input.next();
        System.out.println("Which subject is the assignment in?");
        String next = literal();
        while (!_APS.containsSubjectName(next)) {
            System.out.println("That subject doesn't exist. Please enter another.");
            next = literal();
        }
        Subject subject = _APS.getSubject(next);

        System.out.println("What is the name of the assignment?");
        String name = literal();

        System.out.println("When is the assignment due?");
        LocalDate dueDate = LocalDate.parse(literal());

        Assignment assignment = new Assignment(name, dueDate, subject);

        System.out.println("What are the tasks that make up this assignment?");
        ArrayList<String> taskNames = new ArrayList<>();
        while (!_input.nextIf(";")) {
            taskNames.add(literal());
        }
        System.out.println("How many hours will each task take?");
        double[] taskTimes = new double[taskNames.size()];
        for (int x = 0; x < taskTimes.length; x += 1) {
            System.out.println(taskNames.get(x) + ": ");
            taskTimes[x] = Double.parseDouble(_input.next());
        }
        for (int x = 1; x < taskNames.size(); x += 1) {
            assignment.addTask(new Task(taskNames.get(x), assignment, taskTimes[x]));
        }

        _APS.addAssignment(assignment);
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
     * Executes view subjects command.
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
     * Executes view all command.
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

    /**
     * The input for commands.
     */
    private Tokenizer _input;

    /**
     * The APS to act upon.
     */
    private APS _APS;

}
