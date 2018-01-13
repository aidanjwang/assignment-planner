package aps;

import java.io.Serializable;
import java.util.NoSuchElementException;
import java.util.TreeSet;

/**
 * Represents a single subject category for assignments.
 * @author A. J. Wang
 */
public class Subject implements Serializable {

    /* CONSTRUCTORS */

    /**
     * Constructs new Subject.
     * @param name Subject name
     */
    public Subject(String name) {
        _name = name;
        _assignments = new TreeSet<>();
    }

    /* METHODS */

    /**
     * Adds a new assignment to this subject.
     * @param name Assignment name
     */
    public void addAssignment(Assignment name) {
        _assignments.add(name);
    }

    /**
     * Removes the given assignment from this subject.
     * @param name Assignment name
     */
    public void removeAssignment(Assignment name) {
        _assignments.remove(name);
    }

    /**
     * Returns true if _assignments contains given assignment name.
     * @param name Assignment name
     * @return
     */
    public boolean containsAssignmentName(String name) {
        for (Assignment assignment : _assignments) {
            if (assignment.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the aps.Assignment in _assignments with the given name.
     * @param name Assignment name
     * @return
     */
    public Assignment getAssignments(String name) {
        for (Assignment assignment : _assignments) {
            if (assignment.getName().equals(name)) {
                return assignment;
            }
        }
        throw new NoSuchElementException(
                "Assignment with given name does not exist.");
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
     * Accessor for _assignments.
     * @return
     */
    public TreeSet<Assignment> getAssignments() {
        return _assignments;
    }

    /* FIELDS */

    /**
     * Name of subject.
     */
    private String _name;

    /**
     * List of subject's assignments sorted by due date.
     */
    private TreeSet<Assignment> _assignments;

}
