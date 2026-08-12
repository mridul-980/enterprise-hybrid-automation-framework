package model;
public class LoginTestData {

    private final String testCaseId;
    private final String username;
    private final String password;
    private final String expectedMessage;

    public LoginTestData(
            final String testCaseId,
            final String username,
            final String password,
            final String expectedMessage) {

        this.testCaseId = testCaseId;
        this.username = username;
        this.password = password;
        this.expectedMessage = expectedMessage;
    }
    @Override
    public String toString() {
        return testCaseId;
    }

    public String getTestCaseId() {
        return testCaseId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getExpectedMessage() {
        return expectedMessage;
    }
}