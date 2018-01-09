package aps;

import java.util.LinkedHashSet;

/**
 * Contains methods for initializing aps.Assignment Planner system.
 * @author A. J. Wang
 */
public class Initialize {

    /* METHODS */

    public static APS initialize(Tokenizer input) {
        System.out.println("Welcome. We will now initialize your Assignment Planner System.");

        System.out.println("What subjects will you add? (Type ; when done)");
        LinkedHashSet<Subject> subjects = new LinkedHashSet<>();
        while (!input.nextIf(";")) {
            subjects.add(new Subject(input.next()));
        }

        System.out.println("How many hours will you work on each day of the week?");
        double[] dailyHours = new double[7];
        for (int x = 0; x < dailyHours.length; x += 1) {
            dailyHours[x] = Double.parseDouble(input.next());
        }

        System.out.println("Initialization complete.");
        return new APS(subjects, dailyHours);
    }

}
