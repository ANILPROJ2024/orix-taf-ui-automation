package scenarios.stepDefinitions;

import context.TestContext;
import io.cucumber.java.en.Given;
import pages.LoginPage;
import utilities.ConfigLoader;
import utilities.CredsLoader;

public class LoginPageSteps {
    private final LoginPage loginPage;
    private final CredsLoader credsLoader;
    private final ConfigLoader configLoader;

    public LoginPageSteps(TestContext context) {
        this.loginPage = context.loginPage;
        this.credsLoader = context.credsLoader;
        this.configLoader = context.configLoader;
    }

    @Given("Logged into intangles")
    public void logged_into_intangles() throws InterruptedException {
        String username = credsLoader.getProperty("intangles_login_username");
        String password = credsLoader.getProperty("intangles_login_password");
        loginPage.goTo(configLoader.getProperty("url"));
        loginPage.loginToIntangles(username, password);
    }

}
