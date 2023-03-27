package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class MapPage extends BasePage {

    private final By vehicleAddress = By.cssSelector("div[title='Current location'] span[class='d-MuiTypography-root d-MuiTypography-body1']");
    private final By vehicleSpecification = By.cssSelector("div[title='Vehicle specs'] span[class='d-MuiTypography-root d-MuiTypography-body1']");
    private final By searchVehicleField = By.cssSelector("input[placeholder='Search Vehicle...']");
    private final By vehicles = By.xpath("//p[@class='d-MuiTypography-root d-MuiTypography-body1'][@caseformat='upper'][contains(@style,'font-size')]");
    private final By vehicleStatusComponent = By.xpath("//h6[normalize-space()='TOTAL']");
    private final String vehicleStatusComponentName = "//h6[normalize-space()='statusName']";
    private final String vehicleStatusComponentCount = "//h6[normalize-space()='statusName']/preceding-sibling::h6/b";
    private final String section = "//span[normalize-space()='section']";
    private final String healthStatusInCard = "//p[normalize-space()='status']";
    private final String movingStatusInCard = "//p[contains(text(), 'status')]";
    private final String odometerReading = "//b[normalize-space()='odometerReading']";
    private final String fuelPercentage = "//span[normalize-space()='percentageFuel']";
    private final String vehicleHealth = "//span[normalize-space()='healthStatus']";
    private final String vehicleMoving = "//span[contains(@class,'d-MuiChip-label d-MuiChip-labelSmall')][normalize-space()='movingStatus']";
    private final String adBluePercentage = "//span[normalize-space()='percentage']";

    public MapPage(WebDriver driver) {
        super(driver);
    }

    public String getVehicleAddress() {
        return getTextOnElement(vehicleAddress);
    }

    public boolean isExpectedOdometerReadingDisplayed(String reading) {
        return driver.findElement(By.xpath(odometerReading.replace("odometerReading", reading))).isDisplayed();
    }

    public String getVehicleSpecs() {
        return getTextOnElement(vehicleSpecification);
    }

    public boolean isSectionDisplayed(String detailsSection) {
        WebElement element = driver.findElement(By.xpath(section.replace("section", detailsSection.toUpperCase())));
        scrollToElement(element);
        return element.isDisplayed();
    }

    public String getFuelPercentage(String percentage) {
        return driver.findElement(By.xpath(fuelPercentage.replace("percentageFuel", percentage))).getText();
    }

    public void searchAndSelectVehicle(String vehicle) throws InterruptedException {
        enterTextOnElement(searchVehicleField, vehicle);
        Thread.sleep(5000);
        driver.findElement(searchVehicleField).sendKeys(Keys.ENTER);
        Thread.sleep(5000);
    }

    public boolean verifyAdBlue(String percentage) {
        return driver.findElement(By.xpath(adBluePercentage.replace("percentage", percentage))).isDisplayed();
    }

    public void verifyVehicleListAsPerStatus(String status) throws InterruptedException {

        Thread.sleep(3000);
        List<WebElement> scrollToElements;
        scrollToElements = driver.findElements(vehicles);
        System.out.println(scrollToElements.size());
        for (WebElement element : scrollToElements) {
            // Scroll the element into view using JavascriptExecutor
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].scrollIntoView();", element);
            Assert.assertEquals(element.getText(), status);
            System.out.println(element.getText());
        }

    }

    public String getVehicleHealthStatus(String healthStatus) {
        return driver.findElement(By.xpath(vehicleHealth.replace("healthStatus", healthStatus))).getText();
    }

    public boolean isMovingStatusDisplayed(String movingStatus) {
        return driver.findElement(By.xpath(vehicleMoving.replace("movingStatus", movingStatus))).isDisplayed();
    }

    public boolean isVehicleStatusComponentDisplayed() {
        return driver.findElement(vehicleStatusComponent).isDisplayed();
    }

    public int getVehicleStatusComponentCount(String status) {
        String count = driver.findElement(By.xpath(vehicleStatusComponentCount.replace("statusName", status))).getText();
        return Integer.parseInt(count);
    }

    public void clickOnStatus(String status) {
        clickOnElement(By.xpath(vehicleStatusComponentName.replace("statusName", status)));
    }

    public String getHealthStatusInCard(String status) {
        By element = By.xpath(healthStatusInCard.replace("status", status));
        waitUntilElementIsDisplayed(element);
        return driver.findElement(element).getText();
    }

    public String getMovingStatusInCard(String status) {
        By element = By.xpath(movingStatusInCard.replace("status", status));
        waitUntilElementIsDisplayed(element);
        return driver.findElement(element).getText();
    }
}
