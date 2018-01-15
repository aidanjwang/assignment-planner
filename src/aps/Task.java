package aps;

import java.io.Serializable;

/**
 * Represents a single task of an assignment.
 * @author A. J. Wang
 */
public class Task implements Serializable {

    /* CONSTRUCTORS */

    /**
     * Constructs new Task.
     * @param name Task name
     * @param assignment Assignment which task belongs to
     * @param time time needed to complete
     */
    public Task(String name, Assignment assignment, double time) {
        _name = name;
        _assignment = assignment;
        _time = time;
    }

    /* METHODS */

    /**
     * equals method for use by Assignment removeTask.
     * @param t other Task
     * @return
     */
    public boolean equals(Task t) {
        return (_name.equals(t.getName()) && _assignment.equals(t.getAssignment()));
    }

    /* ACCESSORS */

    /**
     * Accessor for _name.
     * @return
     */
    public String getName() {
        return _name;
    }

    /**
     * Accessor for _assignment.
     * @return
     */
    public Assignment getAssignment() {
        return _assignment;
    }

    /**
     * Accessor for _time.
     * @return
     */
    public double getTime() {
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
