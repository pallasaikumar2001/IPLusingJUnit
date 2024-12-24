import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class maintest {
    public static void main(String[] args) {
        System.out.println("Running JUnit tests for Main class...");
        Result result = JUnitCore.runClasses(Main.class);

        for (Failure failure : result.getFailures()) {
            System.out.println("Test failed: " + failure.toString());
        }

        if (result.wasSuccessful()) {
            System.out.println("All tests passed successfully!");
        } else {
            System.out.println(result.getFailureCount() + " test(s) failed.");
        }
    }
}
