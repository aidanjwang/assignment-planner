import org.junit.Test;
import ucb.junit.textui;
import java.util.LinkedHashSet;

/**
 * JUnit tests for the Assignment Planner system.
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
     * Initializes _APS instance for tests.
     */
    private void initializeTest() {
        LinkedHashSet<Subject> subjects = new LinkedHashSet<>();
        Subject CS = new Subject("CS");
        Subject English = new Subject("English");
        subjects.add(CS);
        subjects.add(English);
        int[] dailyHours = {5, 5, 5, 5, 5, 5, 5};
        _APS = new APS(subjects, dailyHours);
    }

    /**
     * Tests APS.viewCategorical.
     */
    @Test
    public void testViewCategorical() {
        initializeTest();
        _APS.viewCategorical();
    }


}
