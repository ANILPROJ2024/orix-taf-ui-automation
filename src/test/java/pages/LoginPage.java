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

    public void loginToIntangles(String username, String password) throws InterruptedException {
        enterTextOnElement(userNameTextBox, username);
        enterTextOnElement(passwordTextBox, password);
        clickOnElement(loginButton);
        moveToElementAndClick(svgElement);
        Thread.sleep(5000);
    }
}
