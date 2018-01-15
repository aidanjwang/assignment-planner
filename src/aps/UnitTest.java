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
    private APS _aps;

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
        Subject cs = new Subject("CS");
        Subject english = new Subject("English");
        subjects.add(cs);
        subjects.add(english);
        double[] dailyHours = {2, 2, 2, 2, 2, 2, 2};
        _aps = new APS(subjects, dailyHours);

        LocalDate dueDate1 = LocalDate.of(2018, 1, 14);
        LocalDate dueDate2 = LocalDate.of(2018, 1, 12);
        Assignment project = new Assignment("project", dueDate1, cs);
        Assignment reading = new Assignment("reading", dueDate2, english);
        Task unitTest = new Task("unitTest", project, 2);
        Task debug = new Task("debug", project, 1);
        Task firstHalf = new Task("firstHalf", reading, 1);
        Task secondHalf = new Task("secondHalf", reading, 1);
        project.addTask(unitTest);
        project.addTask(debug);
        reading.addTask(firstHalf);
        reading.addTask(secondHalf);

        _aps.addAssignment(project);
        _aps.addAssignment(reading);

        _aps.viewCategorical();
        _aps.viewToday();
        _aps.viewAll();
    }

    /**
     * Tests removeAssignment, viewCategorical, viewToday, and
     * viewAll methods.
     */
    @Test
    public void testRemoveAssignment() {
        LinkedHashSet<Subject> subjects = new LinkedHashSet<>();
        Subject cs = new Subject("CS");
        Subject english = new Subject("English");
        subjects.add(cs);
        subjects.add(english);
        double[] dailyHours = {2, 2, 2, 2, 2, 2, 2};
        _aps = new APS(subjects, dailyHours);

        LocalDate dueDate1 = LocalDate.of(2018, 1, 14);
        LocalDate dueDate2 = LocalDate.of(2018, 1, 12);
        Assignment project = new Assignment("project", dueDate1, cs);
        Assignment reading = new Assignment("reading", dueDate2, english);
        Task unitTest = new Task("unitTest", project, 2);
        Task debug = new Task("debug", project, 1);
        Task firstHalf = new Task("firstHalf", reading, 1);
        Task secondHalf = new Task("secondHalf", reading, 1);
        project.addTask(unitTest);
        project.addTask(debug);
        reading.addTask(firstHalf);
        reading.addTask(secondHalf);

        _aps.addAssignment(project);
        _aps.addAssignment(reading);
        _aps.removeAssignment(project);

        _aps.viewCategorical();
        _aps.viewToday();
        _aps.viewAll();
    }

    /**
     * Tests serialize and deserialize methods.
     */
    @Test
    public void testSerialization() {
        testAddAssignment();
        _aps.serialize();
        _aps = APS.deserialize();
        _aps.viewCategorical();
        _file.delete();
    }

    /**
     * Tests containsSubject and getSubject methods.
     */
    @Test
    public void testGetSubject() {
        testAddAssignment();
        assertTrue(_aps.containsSubjectName("CS"));
        Subject cs = _aps.getSubject("CS");
        assertEquals(cs.getName(), "CS");
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
        CommandInterpreter interpreter = new CommandInterpreter(input, _aps);
        interpreter.initialize();
        interpreter.statement();
    }

    @Test
    public void testAddStatement() {}

    @Test
    public void testRemoveStatement() {}

    @Test
    public void testViewStatement() {}

}
