package aps;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static aps.Utils.error;
import static aps.Utils.getDatePrintFormat;

/**
 * Contains all subjects, assignments, and tasks in the system.
 * Contains methods for most system commands.
 * @author A. J. Wang
 */
public class APS implements Serializable {

    /* CONSTRUCTORS */

    /**
     * Constructs new APS.
     * @param subjects subjects in the system
     * @param dailyHours hours of worktime for every day
     */
    public APS(LinkedHashSet<Subject> subjects, double[] dailyHours) {
        _subjects = subjects;
        _dailyHours = dailyHours;
        _assignments = new TreeSet<>();
        _toDoLists = new ArrayList<>();
    }

    /* METHODS */

    /**
     * Adds assignment to its subject and _assignments, then updates
     * the _toDoLists.
     * @param assignment assignment
     */
    public void addAssignment(Assignment assignment) {
        assignment.getSubject().addAssignment(assignment);
        _assignments.add(assignment);
        update();
    }

    /**
     * Removes assignment from its subject and _assignments, then
     * updates the _toDoLists.
     * @param assignment assignment
     */
    public void removeAssignment(Assignment assignment) {
        assignment.getSubject().removeAssignment(assignment);
        _assignments.remove(assignment);
        update();
    }

    /**
     * Removes Task from the given Assignment, then updates _toDoLists.
     * @param assignment assignment of task
     * @param task task to remove
     */
    public void removeTask(Assignment assignment, Task task) {
        assignment.removeTask(task);
        update();
    }

    /**
     * Returns the Assignment from _assignments that has the given
     * name, subject name, and due date.
     * @param name assignment name
     * @param subject subject of assignment
     * @param date due date
     * @return
     */
    public Assignment getAssignment(String name, Subject subject,
                                    LocalDate date) {
        Assignment assignment = new Assignment(name, date, subject);
        for (Assignment a : _assignments) {
            if (assignment.equals(a)) {
                return a;
            }
        }
        throw error("Assignment does not exist.");
    }

    /**
     * Clears, then reassigns to-do lists for every day from today
     * till last due date.
     */
    public void update() {
        resetDates();

        int numDays, x, dayOfWeek;
        double dailyTime, remainingTime, runningTime;
        double[] dailyHours;
        Iterator<Task> taskIterator;
        Task task;
        Date day;
        for (Assignment assignment : _assignments) {
            numDays = assignment.getDueDate().compareTo(LocalDate.now());
            dailyTime = (double) assignment.getTime() / numDays;
            remainingTime = remainingTime(numDays);
            taskIterator = assignment.getTasks().iterator();
            if (remainingTime < assignment.getTime()) {
                dailyHours = arrayScaler(_dailyHours,
                        assignment.getTime() / remainingTime);
            } else {
                dailyHours = _dailyHours.clone();
            }
            x = 0;
            runningTime = 0;
            while (taskIterator.hasNext()) {
                task = taskIterator.next();
                day = _toDoLists.get(x);
                dayOfWeek = day.getDate().getDayOfWeek().getValue();
                if (day.getWorkTime() >= dailyHours[dayOfWeek]
                        || runningTime >= dailyTime) {
                    x += 1;
                    runningTime = 0;
                }
                _toDoLists.get(x).addTask(task);
                runningTime += task.getTime();
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
        if (_assignments.isEmpty()) {
            return;
        }
        LocalDate today = LocalDate.now();
        LocalDate lastDueDate = _assignments.last().getDueDate();
        for (int x = 0; x < lastDueDate.compareTo(today); x += 1) {
            _toDoLists.add(new Date(today.plusDays(x)));
        }
    }

    /**
     * Calculates total unallocated worktime between today and
     * given number of subsequent days. Used in update().
     * @param numDays number of work days
     * @return
     */
    private double remainingTime(int numDays) {
        double remainingTime = 0;
        int x = 0;
        Date day = _toDoLists.get(x);
        int dayOfWeek = day.getDate().getDayOfWeek().getValue();
        while (x < numDays) {
            day = _toDoLists.get(x);
            remainingTime += _dailyHours[(dayOfWeek + x) % 7]
                    - day.getWorkTime();
            x += 1;
        }
        return remainingTime;
    }

    /**
     * Scales each element in the given array by the given scalar.
     * Used in update().
     * @param array array
     * @param scalar scalar value
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
    public void viewSubjects() {
        System.out.println("=== Assignments ===");
        for (Subject subject : _subjects) {
            System.out.println("[" + subject.getName() + "]");
            for (Assignment assignment : subject.getAssignments()) {
                System.out.println("   " + assignment.getName()
                        + ", due "
                        + assignment.getDueDate().format(getDatePrintFormat()));
                for (Task task : assignment.getTasks()) {
                    System.out.println("      " + task.getName()
                            + " (" + task.getTime() + ")");
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
            if (day.getWorkTime() == 0) {
                break;
            }
            day.print();
        }
        System.out.println();
    }

    /**
     * Returns true if _subjects contains given subject name.
     * @param name subject name
     * @return
     */
    public boolean containsSubjectName(String name) {
        for (Subject subject : _subjects) {
            if (subject.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the Subject in _subjects with the given name.
     * @param name subject name
     * @return
     */
    public Subject getSubject(String name) {
        for (Subject subject : _subjects) {
            if (subject.getName().equals(name)) {
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
     * Contains Date objects from today till the last assignment's
     * due date.
     */
    private ArrayList<Date> _toDoLists;

    /**
     * The filepath where the APS is stored.
     */
    private static File _file = new File("aps");

}
