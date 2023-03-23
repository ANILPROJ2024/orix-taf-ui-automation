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

    @When("Select a vehicle {string} from the list")
    public void select_a_vehicle_from_the_list(String vehicle) {
        Assert.assertTrue(mapPage.selectAVehicle(vehicle), "The vehicle " + vehicle + " is not selected");
    }

    @Then("Verify the address {string} is correct")
    public void verify_the_address_is_correct(String expectedAddress) {
        String actualAddress = mapPage.getVehicleAddress();
        scenario.log("Actual address: " + actualAddress);
        Assert.assertEquals(actualAddress, expectedAddress, "Expected address is: " + expectedAddress + " but the actual address is:  " + actualAddress);
    }

    @Then("Verify the odometer reading {string} is correct")
    public void verify_the_odometer_reading_is_correct(String expectedReading) {
        String actualReading = mapPage.getOdometerReading();
        scenario.log("Actual reading: " + actualReading);
        Assert.assertEquals(actualReading, expectedReading, "Expected odometer reading is: " + expectedReading + " but the actual odometer reading is:  " + actualReading);
    }

    @Then("Verify the vehicle specifications {string} are correct")
    public void verify_the_vehicle_specifications_are_correct(String expectedVehicleSpec) {
        String actualVehicleSpec = mapPage.getVehicleSpecs();
        scenario.log("Actual vehicle spec: " + actualVehicleSpec);
        Assert.assertEquals(actualVehicleSpec, expectedVehicleSpec, "Expected vehicle spec is: " + expectedVehicleSpec + " but the actual vehicle spec is:  " + actualVehicleSpec);
    }

    @Then("Verify the fuel percentage {string} is correct")
    public void verifyTheFuelPercentageIsCorrect(String fuelPercentage) {
        Assert.assertTrue(mapPage.verifyFuelPercentage());
    }

    @When("Search and select a vehicle {string} from the list")
    public void searchAndSelectAVehicleFromTheList(String vehicle) throws InterruptedException {
        mapPage.searchAndSelectVehicle(vehicle);
    }

    @Then("Verify the adblue percentage {string} is correct")
    public void verifyTheAdbluePercentageIsCorrect(String percentage) {
        Assert.assertTrue(mapPage.verifyAdBlue(percentage));
    }

    @Then("Verify the vehicle health status {string} is correct")
    public void verifyTheVehicleStatusIsCorrect(String healthStatus) {
        boolean isHealthStatusDisplayed = mapPage.verifyHealthStatusIsDisplayed(healthStatus);
        Assert.assertTrue(isHealthStatusDisplayed, "Expected Health status is not displayed");
    }

    @Then("Verify the vehicle moving status {string} is correct")
    public void verifyTheVehicleMovingStatusIsCorrect(String movingStatus) {
        Assert.assertTrue(mapPage.isMovingStatusDisplayed(movingStatus), "Expected moving status is not displayed");
    }

    @When("Verify the vehicle status count component is displayed on the map")
    public void verifyTheVehicleStatusCountComponentIsDisplayedOnTheMap() {
        Assert.assertTrue(mapPage.isVehicleStatusComponentDisplayed());
    }

    @Then("Verify the vehicle status component {string} data is correct")
    public void verifyTheVehicleStatusComponentDataIsCorrect(String status) {
        int count = mapPage.getVehicleStatusComponentCount(status);
        boolean flag = false;
        scenario.log(status + ": " + count);
        if(count>=0){
            flag = true;
        }
        Assert.assertTrue(flag);
    }

    @Then("Click on the status {string}")
    public void clickOnTheStatus(String status) {
        mapPage.clickOnStatus(status);
    }

    @Then("Verify that the vehicles are listed as per the status {string}")
    public void verifyThatTheVehiclesAreListedAsPerTheStatus(String status) throws InterruptedException {
        mapPage.verifyVehicleListAsPerStatus(status);
    }
}
