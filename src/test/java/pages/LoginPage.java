package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


public class LoginPage extends BasePage {

    private final By userNameTextBox = By.id("userName");
    private final By passwordTextBox = By.xpath("//*[@placeholder='Password']");
    private final By loginButton = By.xpath("//*[@type='submit']");
    private final By svgElement = By.xpath("//*[local-name()='svg']/*[local-name()='path']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void loginToIntangles() throws InterruptedException {
        driver.findElement(userNameTextBox).sendKeys("test_user@intangles.com");
        driver.findElement(passwordTextBox).sendKeys("1234");
        driver.findElement(loginButton).click();
        Thread.sleep(5000);
        WebElement element = driver.findElement(svgElement);
        // Action class to move and click element
        Actions act = new Actions(driver);
        act.moveToElement(element).
                click().build().perform();
        Thread.sleep(5000);
    }
}
