package aps;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.LinkedHashSet;

import static aps.Utils.error;

/**
 * Reads and interprets commands from an input source.
 * Based off the CommandInterpreter class in the UC Berkeley
 * CS61B database project with Prof. P. N. Hilfinger.
 * @author A. J. Wang
 */
public class CommandInterpreter {

    /* CONSTRUCTORS */

    /**
     * Constructs new CommandInterpreter.
     * @param inp
     * @param aps
     */
    CommandInterpreter(Tokenizer inp, APS aps) {
        _input = inp;
        _APS = aps;
    }

    /* METHODS */

    /**
     * Initializes APS by prompting settings from user.
     */
    public void initialize() {
        System.out.println("Initializing your Assignment Planner System...");

        System.out.println("What are your subjects? (Type ; when done)");
        LinkedHashSet<Subject> subjects = new LinkedHashSet<>();
        while (!_input.nextIf(";")) {
            subjects.add(new Subject(literal()));
        }

        System.out.println("How many hours will you work on each day of the week? (Start from Sunday)");
        double[] dailyHours = new double[7];
        for (int x = 0; x < dailyHours.length; x += 1) {
            dailyHours[x] = Double.parseDouble(_input.next());
        }
        _input.next(";");

        _APS = new APS(subjects, dailyHours);
        System.out.println("Initialization complete.");
    }

    /**
     * Accepts a single user command, returning false if it
     * is an exit command.
     * @return
     */
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
     * Parse and return a valid name (identifier) from the token stream.
     */
    private String name() {
        return _input.next(Tokenizer.IDENTIFIER);
    }

    /**
     * Parse a literal and return the string it represents (i.e., without
     * single quotes).
     */
    private String literal() {
        String lit = _input.next(Tokenizer.LITERAL);
        return lit.substring(1, lit.length() - 1).trim();
    }

    private LocalDate date() {
        String d = _input.next(Tokenizer.DATE);
        try {
            return LocalDate.parse(d, _dateFormat);
        } catch (DateTimeParseException e) {
            throw error("invalid date: %s", d);
        }
    }

    /**
     * Advance the input past the next semicolon.
     */
    public void skipCommand() {
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

    /**
     * Formatter for accepting date inputs.
     */
    private DateTimeFormatter _dateFormat =
            DateTimeFormatter.ofPattern("MM/dd/yyyy");

}
