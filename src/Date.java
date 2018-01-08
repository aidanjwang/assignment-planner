import java.time.LocalDate;
import java.util.HashSet;

/**
 * Each instance represents a date and holds the tasks to
 * be done on that date.
 */
public class Date {

    /* CONSTRUCTORS */

    public Date(LocalDate date) {
        _date = date;
        _tasks = new HashSet<>();
        _workTime = 0;
    }

    /* ACCESSORS */

    /**
     * Accessor for _date.
     * @return
     */
    public LocalDate get_date() {
        return _date;
    }

    /**
     * Accessor for _tasks.
     * @return
     */
    public HashSet<Task> get_tasks() {
        return _tasks;
    }

    /**
     * Accessor for _workTime.
     * @return
     */
    public int get_workTime() {
        return _workTime;
    }

    /* FIELDS */

    /**
     * The date this Date represents.
     */
    private LocalDate _date;

    /**
     * The tasks to be done on this date.
     */
    private HashSet<Task> _tasks;

    /**
     * Amount of time currently allocated to tasks on this date.
     */
    private int _workTime;

}
