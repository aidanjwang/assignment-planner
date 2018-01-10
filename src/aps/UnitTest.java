package aps;

import com.sun.xml.internal.xsom.impl.scd.Token;
import org.junit.Test;
import ucb.junit.textui;

import java.io.File;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Scanner;

/**
 * JUnit tests for the aps.Assignment Planner system.
 * @author A. J. Wang
 */
public class UnitTest {

    /**
     * Run all JUnit tests.
     */
    public static void main(String[] ignored) {
        textui.runClasses(UnitTest.class);
    }

    /* APS Tests */

    /**
     * APS instance for tests.
     */
    private APS _APS;

    /**
     * File path where APS is serialized.
     */
    private File _file = new File("aps");

    /**
     * Initializes _APS instance for tests.
     */
    private void initializeTest() {
        LinkedHashSet<Subject> subjects = new LinkedHashSet<>();
        Subject CS = new Subject("CS");
        Subject English = new Subject("English");
        subjects.add(CS);
        subjects.add(English);
        double[] dailyHours = {5, 5, 5, 5, 5, 5, 5};
        _APS = new APS(subjects, dailyHours);

        LocalDate dueDate1 = LocalDate.of(2018, 1, 14);
        LocalDate dueDate2 = LocalDate.of(2018, 1, 12);
        Assignment CSProj = new Assignment("CSProj", dueDate1, CS);
        Assignment reading = new Assignment("reading", dueDate2, English);
        CS.addAssignment(CSProj);
        English.addAssignment(reading);

        Task unitTest = new Task("unitTest", CSProj, 2);
        Task firstHalf = new Task("firstHalf", reading, 1);
        CSProj.addTask(unitTest);
        reading.addTask(firstHalf);
    }

    /**
     * Tests APS.viewCategorical.
     */
    @Test
    public void testViewCategorical() {
        initializeTest();
        _APS.viewCategorical();
    }

    /**
     * Tests serialize and deserialize methods.
     */
    @Test
    public void testSerialize() {
        initializeTest();
        _APS.serialize();
        _APS = APS.deserialize();
        _APS.viewCategorical();
        _file.delete();
    }

    /* AddAssignment Tests */

    /* Main Tests */

    /* Initialize Tests */

    /**
     * Tests initialize method.
     */
    @Test
    public void testInitialize() {
        String commands = "'CS' 'English'; 5 5 5 5 5 5 5;";
        Scanner scanner = new Scanner(commands);
        Tokenizer input = new Tokenizer(scanner, System.out);
        _APS = Initialize.initialize(input);
        _APS.viewCategorical();
    }

}
