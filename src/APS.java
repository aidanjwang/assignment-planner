import java.io.Serializable;
import java.util.ArrayList;

/**
 * Contains all subjects, assignments, and tasks in the system.
 * Contains methods for most system commands.
 * @author A. J. Wang
 */
public class APS implements Serializable {

    /* CONSTRUCTORS */

    /**
     * Constructs new APS.
     * @param subjects
     */
    public APS (ArrayList<Subject> subjects) {
        _subjects = subjects;
    }

    /* METHODS */

    /**
     * Prints categorical view of subject, assignments, and tasks.
     */
    public void viewCategorical() {
        System.out.println("=== Assignments ===\n");
        for (Subject subject : _subjects) {
            System.out.println(subject.get_name());
            for (Assignment assignment : subject.get_assignments()) {
                System.out.println("  " + assignment.get_name());
                for (Task task : assignment.get_tasks()) {
                    System.out.println("    " + task.get_name());
                }
            }
            System.out.println();
        }
    }

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
     * List of subjects in APS in user's order.
     */
    private ArrayList<Subject> _subjects;

    /**
     * Contains hours of work time for each day of the week.
     */
    private int[] _dailyHours;

}
