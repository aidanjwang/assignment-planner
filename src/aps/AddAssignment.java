package aps;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Contains methods for adding assignments to aps.Assignment Planner
 * system.
 * @author A. J. Wang
 */
public class AddAssignment {

    /* METHODS */

    /**
     * Prompts user input to create a new assignment with tasks.
     * @param input
     * @param aps
     * @return
     */
    public static Assignment add(Tokenizer input, APS aps) {
        System.out.println("Which subject is the assignment in?");
        String next = input.next();
        while (!aps.containsSubjectName(next)) {
            System.out.println("That subject doesn't exist. Please enter another.");
            next = input.next();
        }
        Subject subject = aps.getSubject(next);

        System.out.println("What is the name of the assignment?");
        String name = input.next();

        System.out.println("When is the assignment due?");
        LocalDate dueDate = LocalDate.parse(input.next());

        Assignment assignment = new Assignment(name, dueDate, subject);

        System.out.println("What are the tasks that make up this assignment?");
        ArrayList<String> taskNames = new ArrayList<>();
        while (!input.nextIf(";")) {
            taskNames.add(input.next());
        }
        System.out.println("How many hours will each task take?");
        double[] taskTimes = new double[taskNames.size()];
        for (int x = 0; x < taskTimes.length; x += 1) {
            System.out.println(taskNames.get(x) + ": ");
            taskTimes[x] = Double.parseDouble(input.next());
        }
        for (int x = 1; x < taskNames.size(); x += 1) {
            assignment.addTask(new Task(taskNames.get(x), assignment, taskTimes[x]));
        }

        return assignment;
    }

}
