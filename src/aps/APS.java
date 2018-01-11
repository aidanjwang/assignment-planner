package aps;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

/**
 * Contains all subjects, assignments, and tasks in the system.
 * Contains methods for most system commands.
 * @author A. J. Wang
 */
public class APS implements Serializable {

    /* CONSTRUCTORS */

    /**
     * Constructs new aps.APS.
     * @param subjects
     */
    public APS (LinkedHashSet<Subject> subjects, double[] dailyHours) {
        _subjects = subjects;
        _dailyHours = dailyHours;
        _assignments = new TreeSet<>();
        _toDoLists = new ArrayList<>();
    }

    /* METHODS */

    /**
     * Adds assignment to its subject and _assignments, then updates
     * the _toDoLists.
     * @param assignment
     */
    public void addAssignment(Assignment assignment) {
        assignment.get_subject().addAssignment(assignment);
        _assignments.add(assignment);
        update();
    }

    /**
     * Removes assignment from its subject and _assignments, then
     * updates the _toDoLists.
     * @param assignment
     */
    public void removeAssignment(Assignment assignment) {
        assignment.get_subject().removeAssignment(assignment);
        _assignments.remove(assignment);
        update();
    }

    /**
     * Clears, then reassigns to-do lists for every day from today
     * till last due date.
     */
    private void update() {
        resetDates();

        int numDays, x, dayOfWeek;
        double dailyTime, remainingTime, runningTime;
        double[] dailyHours;
        Iterator<Task> taskIterator;
        Task task;
        Date day;
        for (Assignment assignment : _assignments) {
            numDays = assignment.get_dueDate().compareTo(LocalDate.now());
            dailyTime = assignment.get_time() / numDays;
            remainingTime = remainingTime(numDays);
            taskIterator = assignment.get_tasks().iterator();
            if (remainingTime < assignment.get_time()) {
                dailyHours = arrayScaler(_dailyHours,
                        assignment.get_time() / remainingTime);
            } else {
                dailyHours = _dailyHours.clone();
            }
            x = 0;
            runningTime = 0;
            while (taskIterator.hasNext()) {
                task = taskIterator.next();
                day = _toDoLists.get(x);
                dayOfWeek = day.get_date().getDayOfWeek().getValue();
                if (day.get_workTime() > dailyHours[dayOfWeek]
                        || runningTime > dailyTime) {
                    x += 1;
                    runningTime = 0;
                }
                _toDoLists.get(x).addTask(task);
                runningTime += task.get_time();
            }
        }
    }

    /**
     * Clears _toDoLists and replaces with a new list of dates
     * starting from today till the last assignment due date.
     * Used in update().
     */
    private void resetDates() {
        _toDoLists.clear();
        LocalDate today = LocalDate.now();
        LocalDate lastDueDate = _assignments.last().get_dueDate();
        for (int x = 0; x < lastDueDate.compareTo(today); x += 1) {
            _toDoLists.add(new Date(today.plusDays(x)));
        }
    }

    /**
     * Calculates total unallocated worktime between today and
     * given number of subsequent days. Used in update().
     * @param numDays
     * @return
     */
    private double remainingTime(int numDays) {
        double remainingTime = 0;
        int x = 0;
        Date d = _toDoLists.get(x);
        int today = d.get_date().getDayOfWeek().getValue();
        while (x < numDays) {
            d = _toDoLists.get(x);
            remainingTime += _dailyHours[(today + x) % 7] - d.get_workTime();
            x += 1;
        }
        return remainingTime;
    }

    /**
     * Scales each element in the given array by the given scalar.
     * Used in update().
     * @param array
     * @param scalar
     * @return
     */
    private double[] arrayScaler(double[] array, double scalar) {
        for (int x = 0; x < array.length; x += 1) {
            array[x] *= scalar;
        }
        return array;
    }

    /**
     * Prints categorical view of subject, assignments, and tasks.
     */
    public void viewCategorical() {
        System.out.println("=== Assignments ===");
        for (Subject subject : _subjects) {
            System.out.println("[" + subject.get_name() + "]");
            for (Assignment assignment : subject.get_assignments()) {
                System.out.println("   " + assignment.get_name()
                        + ", due " + assignment.get_dueDate());
                for (Task task : assignment.get_tasks()) {
                    System.out.println("      " + task.get_name()
                            + " (" + task.get_time() + ")");
                }
            }
        }
        System.out.println();
    }

    /**
     * Prints to-do list for today.
     */
    public void viewToday() {
        System.out.println("=== Today's To-Do List ===");
        Date today = _toDoLists.get(0);
        today.print();
        System.out.println();
    }

    /**
     * Prints to-do lists for every day until last due date. Doesn't
     * print days with no work assigned.
     */
    public void viewAll() {
        System.out.println("=== All To-Do Lists ===");
        for (Date day : _toDoLists) {
            if (day.get_workTime() == 0) {
                break;
            }
            day.print();
        }
        System.out.println();
    }

    /**
     * Returns true if _subjects contains given subject name.
     * @param name
     * @return
     */
    public boolean containsSubjectName(String name) {
        for (Subject subject : _subjects) {
            if (subject.get_name().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the Subject in _subjects with the given name.
     * @param name
     * @return
     */
    public Subject getSubject(String name) {
        for (Subject subject : _subjects) {
            if (subject.get_name().equals(name)) {
                return subject;
            }
        }
        throw new NoSuchElementException(
                "Subject with given name does not exist.");
    }

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

    /* ACCESSORS */

    /**
     * Accessor for _subjects.
     * @return
     */
    public LinkedHashSet<Subject> get_subjects() {
        return _subjects;
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
     * Set of subjects in APS in user's order.
     */
    private LinkedHashSet<Subject> _subjects;

    /**
     * Contains hours of work time for each day of the week.
     * _dailyHours[0] corresponds to Sunday, and so forth.
     */
    private double[] _dailyHours;

    /**
     * Contains all assignments from all subjects, sorted by
     * due date sooner to later.
     */
    private TreeSet<Assignment> _assignments;

    /**
     * Contains aps.Date objects from today till the last assignment's
     * due date.
     */
    private ArrayList<Date> _toDoLists;

    /**
     * The filepath where the APS is stored.
     */
    private static File _file = new File("aps");

}
