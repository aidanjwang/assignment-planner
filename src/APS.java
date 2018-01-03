import java.io.Serializable;
import java.util.ArrayList;

/**
 * Contains all subjects, assignments, and tasks and most system commands.
 * @author A. J. Wang
 */
public class APS implements Serializable {

    /* CONSTRUCTORS */


    /* METHODS */

    /**
     * Prints categorical view of assignments.
     */
    public void viewCategorical() {}

    /**
     * Prints to-do list for today.
     */
    public void viewToday() {}

    /**
     * Prints to-do lists for every day until last due date.
     */
    public void viewAll() {}

    /* FIELDS */

    /**
     * List of subjects in APS.
     */
    private ArrayList<Subject> _subjects;

}
