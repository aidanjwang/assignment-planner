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
     */
    public Assignment(String name, LocalDate dueDate) {
        _name = name;
        _dueDate = dueDate;
        _tasks = new LinkedHashSet<>();
        _time = 0;
    }

    /* METHODS */

    public void addTask(Task task) {
        _tasks.add(task);
        _time += task.get_time();
    }


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

    private int _time;

}
