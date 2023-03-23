package hooks;

import context.TestContext;
import factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.it.Ma;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.MapPage;
import utilities.ConfigLoader;
import utilities.CredsLoader;

import java.io.File;
import java.io.IOException;

public class Hooks {
    private WebDriver driver;
    private final TestContext context;

    public Hooks(TestContext context) {
        this.context = context;
    }

    @Before
    public void setUp(Scenario scenario) {
        driver = DriverFactory.initializeDriver();
        context.driver = driver;
        context.credsLoader = new CredsLoader();
        context.configLoader = new ConfigLoader();
        context.scenario = scenario;
        context.loginPage = new LoginPage(context.driver);
        context.mapPage = new MapPage(context.driver);
    }

    @After
    public void tearDown(Scenario scenario) throws IOException {
        // Take screenshot if the scenario failed
        if (scenario.isFailed()) {
            File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            byte[] fileContent = FileUtils.readFileToByteArray(sourcePath);
            scenario.attach(fileContent, "image/png", "image");
        }

//        if (driver != null)
//            driver.quit();
    }

}
