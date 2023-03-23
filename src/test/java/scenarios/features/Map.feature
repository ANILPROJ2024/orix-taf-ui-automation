Feature: Map functionality

  @map @sanity @regression
  Scenario Outline: Select a vehicle from the list in map page and verify the vehicle details
    Given Logged into intangles
    When Select a vehicle "<vehicle>" from the list
    Then Verify the address "<address>" is correct
    And Verify the odometer reading "<odometer_reading>" is correct
    And Verify the vehicle specifications "<vehicle_spec>" are correct

    Examples:
      | vehicle       | address                                                                 | odometer_reading | vehicle_spec              |
      | gobindia_jeep | Vanshaj Prestige, Wakeshwar Rd, Pashan, Pune, Maharashtra 411021, India | 0                | Jeep - Compass petrol bs4 |


  @map @sanity @regression
  Scenario Outline: Select a vehicle from the list in map page and verify the fuel details
    Given Logged into intangles
    When Select a vehicle "<vehicle>" from the list
    Then Verify the fuel percentage "<fuel_percentage>" is correct

    Examples:
      | vehicle       | fuel_percentage |
      | gobindia_jeep | 31%             |


  @map @sanity @regression
  Scenario Outline: Select a vehicle from the list in map page and verify the adblue details
    Given Logged into intangles
    When Search and select a vehicle "<vehicle>" from the list
    Then Verify the adblue percentage "<adblue_percentage>" is correct

    Examples:
      | vehicle            | adblue_percentage |
      | MEV _ test 4_ ARAI | 6%                |


  @map @sanity @regression
  Scenario Outline: Select a vehicle from the list in map page and verify the vehicle health status
    Given Logged into intangles
    When Select a vehicle "<vehicle>" from the list
    Then Verify the vehicle health status "<vehicle_status>" is correct

    Examples:
      | vehicle       | vehicle_status |
      | gobindia_jeep | GOOD           |


  @map @sanity @regression
  Scenario Outline: Select a vehicle from the list in map page and verify the vehicle moving status
    Given Logged into intangles
    When Select a vehicle "<vehicle>" from the list
    Then Verify the vehicle moving status "<vehicle_moving_status>" is correct

    Examples:
      | vehicle       | vehicle_moving_status |
      | gobindia_jeep | OFFLINE               |


  @map @sanity @regression
  Scenario Outline: Select a vehicle from the list in map page and verify the vehicle moving status
    Given Logged into intangles
    When Select a vehicle "<vehicle>" from the list
    Then Verify the vehicle moving status "<vehicle_moving_status>" is correct

    Examples:
      | vehicle       | vehicle_moving_status |
      | gobindia_jeep | OFFLINE               |


  @map @sanity @regression @test
  Scenario Outline: Verify the vehicle status count component for <status>
    Given Logged into intangles
    Then Verify the vehicle status count component is displayed on the map
    Then Verify the vehicle status component "<status>" data is correct
    Then Click on the status "<status>"
    Then Verify that the vehicles are listed as per the status "<status>"

    Examples:
      | status  |
#      | STOPPED |
#      | MOVING  |
#      | IDLING  |
      | PARKED  |
      | NO GPS  |
      | OFFLINE |
#      | TOTAL   |
