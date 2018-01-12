package aps;

import org.junit.Test;
import ucb.junit.textui;

import java.io.File;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * JUnit tests for the aps.Assignment Planner system. Library
 * from UC Berkeley CS61B course with Prof. P. N. Hilfinger.
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
     * Tests addAssignment, viewCategorical, viewToday, and
     * viewAll methods.
     */
    @Test
    public void testAddAssignment() {
        LinkedHashSet<Subject> subjects = new LinkedHashSet<>();
        Subject CS = new Subject("CS");
        Subject English = new Subject("English");
        subjects.add(CS);
        subjects.add(English);
        double[] dailyHours = {2, 2, 2, 2, 2, 2, 2};
        _APS = new APS(subjects, dailyHours);

        LocalDate dueDate1 = LocalDate.of(2018, 1, 14);
        LocalDate dueDate2 = LocalDate.of(2018, 1, 12);
        Assignment project = new Assignment("project", dueDate1, CS);
        Assignment reading = new Assignment("reading", dueDate2, English);
        Task unitTest = new Task("unitTest", project, 2);
        Task debug = new Task("debug", project, 1);
        Task firstHalf = new Task("firstHalf", reading, 1);
        Task secondHalf = new Task("secondHalf", reading, 1);
        project.addTask(unitTest);
        project.addTask(debug);
        reading.addTask(firstHalf);
        reading.addTask(secondHalf);

        _APS.addAssignment(project);
        _APS.addAssignment(reading);

        _APS.viewCategorical();
        _APS.viewToday();
        _APS.viewAll();
    }

    /**
     * Tests removeAssignment, viewCategorical, viewToday, and
     * viewAll methods.
     */
    @Test
    public void testRemoveAssignment() {
        LinkedHashSet<Subject> subjects = new LinkedHashSet<>();
        Subject CS = new Subject("CS");
        Subject English = new Subject("English");
        subjects.add(CS);
        subjects.add(English);
        double[] dailyHours = {2, 2, 2, 2, 2, 2, 2};
        _APS = new APS(subjects, dailyHours);

        LocalDate dueDate1 = LocalDate.of(2018, 1, 14);
        LocalDate dueDate2 = LocalDate.of(2018, 1, 12);
        Assignment project = new Assignment("project", dueDate1, CS);
        Assignment reading = new Assignment("reading", dueDate2, English);
        Task unitTest = new Task("unitTest", project, 2);
        Task debug = new Task("debug", project, 1);
        Task firstHalf = new Task("firstHalf", reading, 1);
        Task secondHalf = new Task("secondHalf", reading, 1);
        project.addTask(unitTest);
        project.addTask(debug);
        reading.addTask(firstHalf);
        reading.addTask(secondHalf);

        _APS.addAssignment(project);
        _APS.addAssignment(reading);
        _APS.removeAssignment(project);

        _APS.viewCategorical();
        _APS.viewToday();
        _APS.viewAll();
    }

    /**
     * Tests serialize and deserialize methods.
     */
    @Test
    public void testSerialization() {
        testAddAssignment();
        _APS.serialize();
        _APS = APS.deserialize();
        _APS.viewCategorical();
        _file.delete();
    }

    /**
     * Tests containsSubject and getSubject methods.
     */
    @Test
    public void testGetSubject() {
        testAddAssignment();
        assertTrue(_APS.containsSubjectName("CS"));
        Subject CS = _APS.getSubject("CS");
        assertEquals(CS.get_name(), "CS");
    }

    /* CommandInterpreter Tests */

    /**
     * Tests initialize method.
     */
    @Test
    public void testInitialize() {
        String commands = "'CS' 'English'; 5 5 5 5 5 5 5; view subjects;";
        Scanner scanner = new Scanner(commands);
        Tokenizer input = new Tokenizer(scanner, System.out);
        CommandInterpreter interpreter = new CommandInterpreter(input, _APS);
        interpreter.initialize();
        interpreter.command();
    }

}
