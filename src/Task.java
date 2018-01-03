/**
 * Represents a single task of an assignment.
 * @author A. J. Wang
 */
public class Task {

    /* CONSTRUCTORS */

    /**
     * Constructs new Task.
     * @param name
     * @param assignment
     * @param time
     */
    public Task(String name, Assignment assignment, int time) {
        _name = name;
        _assignment = assignment;
        _time = time;
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
     * Accessor for _assignment.
     * @return
     */
    public Assignment get_assignment() {
        return _assignment;
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
     * Name of task.
     */
    private String _name;

    /**
     * The assignment which this task is part of.
     */
    private Assignment _assignment;

    /**
     * Time needed for completion of task.
     */
    private int _time;

}
