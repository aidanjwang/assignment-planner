import java.util.ArrayList;

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
        _assignments = new ArrayList<>();
    }

    /* METHODS */

    /**
     * Adds a new assignment to this subject.
     * @param name
     */
    public void add(Assignment name) {
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
    public ArrayList<Assignment> get_assignments() {
        return _assignments;
    }

    /* FIELDS */

    /**
     * Name of subject.
     */
    private String _name;

    /**
     * List of subject's assignments.
     */
    private ArrayList<Assignment> _assignments;

}
