package scenarios.stepDefinitions;

import context.TestContext;
import io.cucumber.java.en.Given;
import pages.LoginPage;

public class LoginPageSteps {
    private final LoginPage loginPage;

    public LoginPageSteps(TestContext context) {
        loginPage = context.loginPage;
    }

    @Given("Logged into intangles")
    public void logged_into_intangles() throws InterruptedException {
        loginPage.goTo("https://inroute.intangles.com/#/");
        loginPage.loginToIntangles();
    }

}
