package aps;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.LinkedHashSet;

/**
 * Each instance represents a date and holds the tasks to
 * be done on that date.
 */
public class Date implements Serializable {

    /* CONSTRUCTORS */

    public Date(LocalDate date) {
        _date = date;
        _tasks = new LinkedHashSet<>();
        _workTime = 0;
    }

    /* METHODS */

    /**
     * Adds task to _tasks. Also updates total worktime.
     * @param task
     */
    public void addTask(Task task) {
        _tasks.add(task);
        _workTime += task.get_time();
    }

    public void print() {
        System.out.println(_date.format(dateFormat)
                + " (" + _workTime + ")");
        for (Task task : _tasks) {
            System.out.println("   ["
                    + task.get_assignment().get_subject().get_name()
                    + " " + task.get_assignment().get_name() + "] "
                    + task.get_name()
                    + " (" + task.get_time() + ")");
        }
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
    public double get_workTime() {
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

    /**
     * Formatter for printing LocalDates.
     */
    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("EEE MM/dd");

}
