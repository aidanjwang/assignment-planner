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
