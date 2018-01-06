import java.util.TreeSet;

/**
 * Represents a single subject category for assignments.
 * @author A. J. Wang
 */
public class Subject {

    /* CONSTRUCTORS */

    /**
     * Constructs new Subject.
     * @param name
     */
    public Subject(String name) {
        _name = name;
        _assignments = new TreeSet<>();
    }

    /* METHODS */

    /**
     * Adds a new assignment to this subject.
     * @param name
     */
    public void addAssignment(Assignment name) {
        _assignments.add(name);
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
     * Accessor for _assignments.
     * @return
     */
    public TreeSet<Assignment> get_assignments() {
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