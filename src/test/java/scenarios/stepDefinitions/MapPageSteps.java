package scenarios.stepDefinitions;

import context.TestContext;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.MapPage;

public class MapPageSteps {

    private final MapPage mapPage;
    private Scenario scenario;

    public MapPageSteps(TestContext context) {
        mapPage = context.mapPage;
        scenario = context.scenario;
    }

    @Then("Verify the address {string} is correct")
    public void verify_the_address_is_correct(String expectedAddress) {
        String actualAddress = mapPage.getVehicleAddress();
        scenario.log("Actual address: " + actualAddress);
        Assert.assertNotEquals(actualAddress, "nan");
        Assert.assertNotEquals(actualAddress, "undefined");
        Assert.assertEquals(actualAddress, expectedAddress,
                "Expected address is: " + expectedAddress + " but the actual address is:  " + actualAddress);
    }

    @Then("Verify the odometer reading {string} is correct")
    public void verify_the_odometer_reading_is_correct(String expectedReading) {
        boolean isExpectedReadingDisplayed = mapPage.isExpectedOdometerReadingDisplayed(expectedReading);
        Assert.assertTrue(isExpectedReadingDisplayed);
    }

    @Then("Verify the vehicle specifications {string} are correct")
    public void verify_the_vehicle_specifications_are_correct(String expectedVehicleSpec) {
        String actualVehicleSpec = mapPage.getVehicleSpecs();
        scenario.log("Actual vehicle spec: " + actualVehicleSpec);
        Assert.assertEquals(actualVehicleSpec, expectedVehicleSpec, "Expected vehicle spec is: " + expectedVehicleSpec
                + " but the actual vehicle spec is:  " + actualVehicleSpec);
    }

    @Then("Verify the fuel percentage {string} is correct")
    public void verifyTheFuelPercentageIsCorrect(String fuelPercentage) {
        String actualFuelPercentage = mapPage.getFuelPercentage(fuelPercentage);
        scenario.log("Actual Fuel Percentage" + actualFuelPercentage);
        Assert.assertEquals(actualFuelPercentage, fuelPercentage);
    }

    @When("Search and select a vehicle {string} from the list")
    public void searchAndSelectAVehicleFromTheList(String vehicle) throws InterruptedException {
        mapPage.searchAndSelectVehicle(vehicle);
    }

    @Then("Verify the adblue percentage {string} is correct")
    public void verifyTheAdbluePercentageIsCorrect(String percentage) {
        Assert.assertTrue(mapPage.verifyAdBlue(percentage));
    }

    @Then("Verify the vehicle health status {string} in vehicle details")
    public void verifyTheVehicleStatusIsCorrect(String healthStatus) {
        String status = mapPage.getVehicleHealthStatus(healthStatus);
        scenario.log("Actual health status: " + status);
        Assert.assertEquals(status, healthStatus, "Expected Health status is not displayed");
    }

    @Then("Verify the vehicle moving status {string} in vehicle details")
    public void verifyTheVehicleMovingStatusIsCorrect(String movingStatus) {
        Assert.assertTrue(mapPage.isMovingStatusDisplayed(movingStatus), "Expected moving status is not displayed");
    }

    @Then("Verify the vehicle status count component is displayed on the map")
    public void verifyTheVehicleStatusCountComponentIsDisplayedOnTheMap() {
        Assert.assertTrue(mapPage.isVehicleStatusComponentDisplayed());
    }

    @Then("Verify the vehicle status component {string} data is correct")
    public void verifyTheVehicleStatusComponentDataIsCorrect(String status) {
        int count = mapPage.getVehicleStatusComponentCount(status);
        boolean flag = false;
        scenario.log(status + ": " + count);
        if (count >= 0) {
            flag = true;
        }
        Assert.assertTrue(flag);
    }

    @When("Click on the status {string}")
    public void clickOnTheStatus(String status) {
        mapPage.clickOnStatus(status);
    }

    @Then("Verify that the vehicles are listed as per the status {string} clicked")
    public void verifyThatTheVehiclesAreListedAsPerTheStatus(String status) throws InterruptedException {
        mapPage.verifyVehicleListAsPerStatus(status);
    }

    @Then("Verify that the {string} section is displayed")
    public void verifyThatTheSectionIsDisplayed(String section) {
        Assert.assertTrue(mapPage.isSectionDisplayed(section));
    }

    @Then("Verify the vehicle health status {string} in card")
    public void verifyTheVehicleHealthStatusInCard(String status) {
        String actualStatusInCard = mapPage.getHealthStatusInCard(status);
        scenario.log("Actual health status in card: " + actualStatusInCard);
        Assert.assertEquals(actualStatusInCard, status);
    }

    @Then("Verify the vehicle moving status {string} in card")
    public void verifyTheVehicleMovingStatusInCard(String status) {
        String actualStatusInCard = mapPage.getMovingStatusInCard(status);
        scenario.log("Actual moving status in card: " + actualStatusInCard);
        Assert.assertEquals(actualStatusInCard, status);
    }
}
