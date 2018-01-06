import java.io.File;
import java.io.Serializable;
import java.util.LinkedHashSet;

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
    public APS (LinkedHashSet<Subject> subjects, int[] dailyHours) {
        _subjects = subjects;
        _dailyHours = dailyHours;
    }

    /* METHODS */

    public void addAssignment(Assignment assignment, Subject subject) {
        subject.addAssignment(assignment);
    }

    /**
     * Prints categorical view of subject, assignments, and tasks.
     */
    public void viewCategorical() {
        System.out.println("=== Assignments ===");
        for (Subject subject : _subjects) {
            System.out.println(subject.get_name());
            for (Assignment assignment : subject.get_assignments()) {
                System.out.println("  " + assignment.get_name() + " due " + assignment.get_dueDate());
                for (Task task : assignment.get_tasks()) {
                    System.out.println("    " + task.get_name() + " (" + task.get_time() + ")");
                }
            }
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

    /**
     * Returns true if APS system is initialized.
     * @return
     */
    public static boolean isInitialized() {
        return _file.isFile();
    }

    /**
     * Serializes this into the _file filepath.
     */
    public void serialize() {
        Utils.writeObject(_file, this);
    }

    /**
     * Deserializes the APS object from the _file filepath.
     * @return
     */
    public static APS deserialize() {
        return Utils.readObject(_file, APS.class);
    }

    /* FIELDS */

    /**
     * Set of subjects in APS in user's order.
     */
    private LinkedHashSet<Subject> _subjects;

    /**
     * Contains hours of work time for each day of the week.
     */
    private int[] _dailyHours;

    /**
     * The filepath where the APS is stored.
     */
    private static File _file = new File("APS");

}
