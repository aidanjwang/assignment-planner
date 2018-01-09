package aps;

import java.util.LinkedHashSet;
import java.util.Scanner;

/**
 * Contains methods for initializing aps.Assignment Planner system.
 * @author A. J. Wang
 */
public class Initialize {

    /* METHODS */

    public static APS initialize(Scanner input) {
        System.out.println("Welcome. We will now initialize your aps.Assignment Planner System.");

        LinkedHashSet<Subject> subjects = new LinkedHashSet<>();
        do {
            System.out.println("What subject will you add?");
            subjects.add(new Subject(input.next()));
            System.out.println("Do you have another subject to add? [yes/no]");
        } while (input.next().equals("yes"));

        double[] dailyHours = new double[7];
        for (int x = 0; x < _daysofWeek.length; x += 1) {
            System.out.printf("How many hours will you work on %s?%n", _daysofWeek[x]);
            dailyHours[x] = input.nextDouble();
        }

        return new APS(subjects, dailyHours);
    }

    /* FIELDS */

    private static String[] _daysofWeek = {"Sundays", "Mondays", "Tuesdays",
            "Wednesdays", "Thursdays", "Fridays", "Saturdays"};

}
