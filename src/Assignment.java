import java.time.LocalDate;
import java.util.LinkedHashSet;

/**
 * Represents a single assignment.
 * @author A. J. Wang
 */
public class Assignment implements Comparable<Assignment> {

    /* CONSTRUCTORS */

    /**
     * Constructs new Assignment.
     * @param name
     * @param dueDate
     * @param subject
     */
    public Assignment(String name, LocalDate dueDate, Subject subject) {
        _name = name;
        _dueDate = dueDate;
        _tasks = new LinkedHashSet<>();
        _time = 0;
        _subject = subject;
    }

    /* METHODS */

    /**
     * Adds a task to this assignment.
     * @param task
     */
    public void addTask(Task task) {
        _tasks.add(task);
        _time += task.get_time();
    }


    /**
     * compareTo method for sorting.
     * @param a
     * @return
     */
    public int compareTo(Assignment a) {
        return this._dueDate.compareTo(a._dueDate);
    }

    /* ACCESSORS */

    /**
     * Accessor for _name.
     * @return
     */
    public String get_name() {
        return _name;
    }

    /**
     * Accessor for _dueDate.
     * @return
     */
    public LocalDate get_dueDate() {
        return _dueDate;
    }

    /**
     * Accessor for _tasks.
     * @return
     */
    public LinkedHashSet<Task> get_tasks() {
        return _tasks;
    }

    /**
     * Accessor for _time.
     * @return
     */
    public int get_time() {
        return _time;
    }

    /**
     * Accessor for _subject.
     * @return
     */
    public Subject get_subject() {
        return _subject;
    }

    /* FIELDS */

    /**
     * Name of assignment.
     */
    private String _name;

    /**
     * Due date of assignment.
     */
    private LocalDate _dueDate;

    /**
     * Set of the assignment's tasks, in initial order.
     */
    private LinkedHashSet<Task> _tasks;

    /**
     * Total time needed to complete assignment's tasks.
     */
    private int _time;

    /**
     * The subject this assignment belongs to.
     */
    private Subject _subject;

}
