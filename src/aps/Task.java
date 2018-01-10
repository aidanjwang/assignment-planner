package aps;

import java.io.Serializable;

/**
 * Represents a single task of an assignment.
 * @author A. J. Wang
 */
public class Task implements Serializable {

    /* CONSTRUCTORS */

    /**
     * Constructs new aps.Task.
     * @param name
     * @param assignment
     * @param time
     */
    public Task(String name, Assignment assignment, double time) {
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
    public double get_time() {
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
    private double _time;

}
