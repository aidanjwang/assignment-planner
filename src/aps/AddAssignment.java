package aps;

import java.time.LocalDate;

/**
 * Contains methods for adding assignments to aps.Assignment Planner
 * system.
 * @author A. J. Wang
 */
public class AddAssignment {

    /* METHODS */
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

        // Add tasks
        System.out.println("What are the tasks that make up this assignment?");


        return assignment;
    }

}
