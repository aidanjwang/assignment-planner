import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents a single assignment.
 * @author A. J. Wang
 */
public class Assignment {

    /* CONSTRUCTOR */

    public Assignment(String name, LocalDate dueDate, ArrayList<Task> tasks) {
        _name = name;
        _dueDate = dueDate;
        _tasks = tasks;
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
    public ArrayList<Task> get_tasks() {
        return _tasks;
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
     * List of the assignment's tasks, in correct order.
     */
    private ArrayList<Task> _tasks;

}
