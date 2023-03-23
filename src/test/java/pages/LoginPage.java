package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By userNameTextBox = By.id("userName");
    private final By passwordTextBox = By.xpath("//*[@placeholder='Password']");
    private final By loginButton = By.xpath("//*[@type='submit']");
    private final By svgElement = By.xpath("//*[local-name()='svg']/*[local-name()='path']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void loginToIntangles() throws InterruptedException {
        enterTextOnElement(userNameTextBox, "test_user@intangles.com");
        enterTextOnElement(passwordTextBox, "1234");
        clickOnElement(loginButton);
        moveToElementAndClick(svgElement);
        Thread.sleep(5000);
    }
}
