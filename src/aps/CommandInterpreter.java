package aps;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedHashSet;

import static aps.Utils.error;

/**
 * Reads and interprets commands from an input source.
 * Based off the CommandInterpreter class in the UC Berkeley
 * CS61B database project with Prof. P. N. Hilfinger.
 * @author A. J. Wang
 */
public class CommandInterpreter implements Serializable {

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
     * Initializes APS by accepting settings from user.
     */
    public void initialize() {
        System.out.println("Initialize your Assignment Planner System...");

        _input.next("subjects");
        LinkedHashSet<Subject> subjects = new LinkedHashSet<>();
        subjects.add(newSubject());
        while (_input.nextIf(",")) {
            subjects.add(newSubject());
        }
        _input.next("and");
        _input.next("daily");
        _input.next("hours");
        double[] dailyHours = new double[7];
        for (int x = 0; x < 6; x += 1) {
            dailyHours[x] = time();
            _input.next(",");
        }
        dailyHours[6] = time();
        _input.next(";");
        _APS = new APS(subjects, dailyHours);
        System.out.println("Initialization complete.");
    }

    /**
     * Parses a new subject clause and returns a new Subject with
     * the given name.
     * @return
     */
    private Subject newSubject() {
        String name = name();
        return new Subject(name);
    }

    /**
     * Accepts a single user statement, returning false if it
     * is an exit statement.
     * @return
     */
    public boolean statement() {
        switch (_input.peek()) {
            case "add":
                addStatement();
                break;
            case "remove":
                removeStatement();
                break;
            case "view":
                viewStatement();
                break;
            case "exit":
            case "quit":
                exitStatement();
                return false;
            default:
                throw error("That is an unrecognizable command.");
        }
        return true;
    }

    /**
     * Executes add statement. Accepts input for subject, name, and
     * due date, and tasks that make up the assignment.
     */
    private void addStatement() {
        _input.next("add");
        Subject subject = subject();
        String name = name();
        _input.next("due");
        LocalDate dueDate = date();
        Assignment assignment = new Assignment(name, dueDate, subject);
        _input.next("with");
        _input.next("tasks");
        assignment.addTasks(tasks(assignment));
        _input.next(";");
        _APS.addAssignment(assignment);
    }

    /**
     * Converts user input for tasks into a LinkedHashSet of tasks.
     * @param assignment
     * @return
     */
    private LinkedHashSet<Task> tasks(Assignment assignment) {
        LinkedHashSet<Task> tasks = new LinkedHashSet<>();
        tasks.add(task(assignment));
        while (_input.nextIf(",")) {
            tasks.add(task(assignment));
        }
        return tasks;
    }

    /**
     * Converts user input into a Task.
     * @param assignment
     * @return
     */
    private Task task(Assignment assignment) {
        String name = literal();
        _input.next("(");
        Double time = time();
        _input.next(")");
        return new Task(name, assignment, time);
    }

    /**
     * Executes remove command. Accepts user input for subject,
     * assignment name to remove, and due date.
     */
    private void removeStatement() {
        _input.next("remove");
        Subject subject = subject();
        String name = name();
        _input.next("due");
        LocalDate dueDate = date();
        Assignment assignment = new Assignment(name, dueDate, subject);
        if (_input.nextIf(";")) {
            _APS.removeAssignment(assignment);
        } else {
            _input.next("task");
            String taskname = literal();
            _input.next(";");
            Task task = new Task(taskname, assignment, 0);
            assignment.removeTask(task);
        }
    }

    /**
     * Executes view statement.
     */
    private void viewStatement() {
        _input.next("view");
        if (_input.nextIf("today")) {
            _input.next(";");
            _APS.viewToday();
        } else if (_input.nextIf("all")) {
            _input.next(";");
            _APS.viewAll();
        } else {
            _input.next("subjects");
            _input.next(";");
            _APS.viewSubjects();
        }
    }

    /**
     * Parses exit statement. First token is either "exit" or "quit."
     */
    private void exitStatement() {
        _input.next();
        _input.next(";");
    }

    /**
     * Parses a subject name and returns the subject from the APS.
     * @return
     */
    private Subject subject() {
        return _APS.getSubject(name());
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

    /**
     * Parse a date token and return the LocalDate it represents.
     * @return
     */
    private LocalDate date() {
        String d = _input.next(Tokenizer.DATE);
        try {
            return LocalDate.parse(d, _dateFormat);
        } catch (DateTimeParseException e) {
            throw error("invalid date: %s", d);
        }
    }

    /**
     * Parse a time token and return the double it represents.
     * @return
     */
    private double time() {
        String string = _input.next(Tokenizer.TIME);
        return Double.parseDouble(string);
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
