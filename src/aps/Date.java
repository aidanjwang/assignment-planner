package aps;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedHashSet;

import static aps.Utils.getDatePrintFormat;

/**
 * Each instance represents a date and holds the tasks to
 * be done on that date.
 * @author A. J. Wang
 */
public class Date implements Serializable {

    /* CONSTRUCTORS */

    /**
     * Constructs a new Date instance.
     * @param date the date
     */
    public Date(LocalDate date) {
        _date = date;
        _tasks = new LinkedHashSet<>();
        _workTime = 0;
    }

    /* METHODS */

    /**
     * Adds task to _tasks. Also updates total worktime.
     * @param task new task
     */
    public void addTask(Task task) {
        _tasks.add(task);
        _workTime += task.getTime();
    }

    /**
     * Prints this Date's tasks in correct format.
     */
    public void print() {
        System.out.println(_date.format(getDatePrintFormat())
                + " (" + _workTime + ")");
        for (Task task : _tasks) {
            System.out.println("   ["
                    + task.getAssignment().getSubject().getName()
                    + " " + task.getAssignment().getName() + "] "
                    + task.getName()
                    + " (" + task.getTime() + ")");
        }
    }

    /* ACCESSORS */

    /**
     * Accessor for _date.
     * @return
     */
    public LocalDate getDate() {
        return _date;
    }

    /**
     * Accessor for _workTime.
     * @return
     */
    public double getWorkTime() {
        return _workTime;
    }

    /* FIELDS */

    /**
     * The date this aps.Date represents.
     */
    private LocalDate _date;

    /**
     * The tasks to be done on this date.
     */
    private LinkedHashSet<Task> _tasks;

    /**
     * Amount of time currently allocated to tasks on this date.
     */
    private double _workTime;

}
