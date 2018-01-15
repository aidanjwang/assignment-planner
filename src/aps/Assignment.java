package aps;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedHashSet;

import static aps.Utils.error;

/**
 * Represents a single assignment.
 * @author A. J. Wang
 */
public class Assignment implements Comparable<Assignment>, Serializable {

    /* CONSTRUCTORS */

    /**
     * Constructs new aps.Assignment.
     * @param name Assignment name
     * @param dueDate due date
     * @param subject subject that Assignment belongs to
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
     * @param task new task
     */
    public void addTask(Task task) {
        _tasks.add(task);
        _time += task.getTime();
    }

    /**
     * Adds all given tasks to this assignment, in original
     * order.
     * @param tasks tasks to add
     */
    public void addTasks(LinkedHashSet<Task> tasks) {
        for (Task task : tasks) {
            addTask(task);
        }
    }

    /**
     * Removes the task with given task name.
     * @param task the task
     */
    public boolean removeTask(Task task) {
        return _tasks.remove(task);
    }

    public Task getTask(String name) {
        Task task = new Task(name, this, 0);
        for (Task t : _tasks) {
            if (task.equals(t)) {
                return t;
            }
        }
        throw error("Task does not exist.");
    }

    /**
     * compareTo() method for sorting. Assignments with sooner
     * due dates have higher priority.
     * @param a other assignment
     * @return
     */
    public int compareTo(Assignment a) {
        return _dueDate.compareTo(a.getDueDate());
    }

    /**
     * equals method for use in APS removeAssignment.
     * @param a other Assignment
     * @return
     */
    public boolean equals(Assignment a) {
        if (_name.equals(a.getName())) {
            if (_subject.equals(a.getSubject())) {
                if (_dueDate.equals(a.getDueDate())) {
                    return true;
                }
            }
        }
        return false;

        /*return (_name.equals(a.getName())
                && _subject.equals(a.getSubject())
                && _dueDate.equals(a.getDueDate()));*/
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
     * Accessor for _dueDate.
     * @return
     */
    public LocalDate getDueDate() {
        return _dueDate;
    }

    /**
     * Accessor for _tasks.
     * @return
     */
    public LinkedHashSet<Task> getTasks() {
        return _tasks;
    }

    /**
     * Accessor for _time.
     * @return
     */
    public int getTime() {
        return _time;
    }

    /**
     * Accessor for _subject.
     * @return
     */
    public Subject getSubject() {
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
