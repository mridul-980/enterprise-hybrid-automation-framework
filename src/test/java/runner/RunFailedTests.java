package runner;
import org.testng.TestNG;
import java.util.Collections;

public class RunFailedTests {
	public static void main(String[] args) {
		TestNG testng = new TestNG();
		testng.setTestSuites(Collections.singletonList("test-output/testng-failed.xml"));
		testng.run();
	}
}
