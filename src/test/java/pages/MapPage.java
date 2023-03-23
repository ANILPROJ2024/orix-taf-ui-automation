package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class MapPage extends BasePage {

    private final By vehicleCard = By.xpath("(//b[contains(text(),'gobinda_jeep')])[1]");
    private final By vehiclePopOver = By.xpath("(//b[contains(text(),'gobinda_jeep')])[3]");
    private final By vehicleAddress = By.cssSelector("div[title='Current location'] span[class='d-MuiTypography-root d-MuiTypography-body1']");
    private final By odometerReading = By.cssSelector("span[class='d-MuiTypography-root d-MuiTypography-h6'] strong");
    private final By vehicleSpecification = By.cssSelector("div[title='Vehicle specs'] span[class='d-MuiTypography-root d-MuiTypography-body1']");
    private final By vehicleState = By.xpath("(//span[@class='d-MuiChip-label d-MuiChip-labelSmall'][normalize-space()='OFFLINE'])[1]");
    private final By vehicleDrivers = By.xpath("//span[normalize-space()='DRIVERS']");
    private final By fuelSection = By.xpath("//span[normalize-space()='FUEL']");
    private final By searchVehicleField = By.cssSelector("input[placeholder='Search Vehicle...']");
    private final By adBlue = By.xpath("//span[normalize-space()='6%']");
    private final By vehicleParkedState = By.xpath("//h6[normalize-space()='PARKED']");
    private final By vehicles = By.xpath("//p[@class='d-MuiTypography-root d-MuiTypography-body1'][@caseformat='upper'][contains(@style,'font-size')]");
    private String vehicleHealth = "//span[normalize-space()='healthStatus']";
    private String vehicleMoving = "//span[contains(@class,'d-MuiChip-label d-MuiChip-labelSmall')][normalize-space()='movingStatus']";
    private final By vehicleStatusComponent = By.xpath("//h6[normalize-space()='TOTAL']");
    private String vehicleStatusComponentName = "//h6[normalize-space()='statusName']";
    private String vehicleStatusComponentCount = "//h6[normalize-space()='statusName']/preceding-sibling::h6/b";

    public MapPage(WebDriver driver) {
        super(driver);
    }

    public boolean selectAVehicle(String vehicle) {
        driver.findElement(vehicleCard).click();
        return driver.findElement(vehiclePopOver).isDisplayed();
    }

    public String getVehicleAddress() {
        WebElement address = driver.findElement(vehicleAddress);
        return address.getText();
    }

    public String getOdometerReading() {
        WebElement odometerRead = driver.findElement(odometerReading);
        return odometerRead.getText();
    }

    public String getVehicleSpecs() {
        WebElement vehicleSpecs = driver.findElement(vehicleSpecification);
        return vehicleSpecs.getText();
    }

    public String getVehicleState() {
        WebElement state = driver.findElement(vehicleState);
        return state.getText();
    }

    public void scrollToDrivers() {
        WebElement scrollToElement = driver.findElement(vehicleDrivers);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView();", scrollToElement);
    }

    public boolean verifyFuelPercentage() {
        driver.findElement(fuelSection).isDisplayed();
        return driver.findElement(By.xpath("//span[normalize-space()='31%']")).isDisplayed();
    }

    public void searchAndSelectVehicle(String vehicle) throws InterruptedException {
        driver.findElement(searchVehicleField).clear();
        driver.findElement(searchVehicleField).sendKeys(vehicle + Keys.ENTER);
        Thread.sleep(5000);
        driver.findElement(searchVehicleField).sendKeys(Keys.ENTER);
        Thread.sleep(5000);
    }

    public boolean verifyAdBlue(String percentage) {
        return driver.findElement(adBlue).isDisplayed();
    }

    public void verifyVehicleListAsPerStatus(String status) throws InterruptedException {

//        driver.findElement(vehicleParkedState).click();

        Thread.sleep(5000);
        List<WebElement> scrollToElements = driver.findElements(vehicles);

        System.out.println(scrollToElements.size());

        for (WebElement element : scrollToElements) {
            Thread.sleep(1000);
            // Scroll the element into view using JavascriptExecutor
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].scrollIntoView();", element);
            Assert.assertEquals(element.getText(), status);
            System.out.println(element.getText());
        }

    }

    public boolean verifyHealthStatusIsDisplayed(String healthStatus) {
        return driver.findElement(By.xpath(vehicleHealth.replace("healthStatus", healthStatus))).isDisplayed();
    }

    public boolean isMovingStatusDisplayed(String movingStatus) {
        return driver.findElement(By.xpath(vehicleMoving.replace("movingStatus", movingStatus))).isDisplayed();
    }

    public boolean isVehicleStatusComponentDisplayed() {
        return driver.findElement(vehicleStatusComponent).isDisplayed();
    }

    public int getVehicleStatusComponentCount(String status){
        String count = driver.findElement(By.xpath(vehicleStatusComponentCount.replace("statusName", status))).getText();
        return Integer.parseInt(count);
    }

    public void clickOnStatus(String status){
        driver.findElement(By.xpath(vehicleStatusComponentName.replace("statusName", status))).click();
    }

}
