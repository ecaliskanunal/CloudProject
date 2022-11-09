@elif @CLOUD-1383
Feature: Logout functionality

  User Story: As a user, I should be able to log out.

  Acceptance Criteria:
  1-User can log out and ends up in login page
  2-User can not go to home page again by clicking step back button after successfully logged out.

  Background:
    Given user is on dashboard

  @CLOUD-1377
  Scenario: logout button is visible
    When user clicks on dropdown menu
    Then user should be able to see logout button

  @CLOUD-1378
  Scenario: logout button is clickable
    When user clicks on dropdown menu
    Then user should be able to click on logout button

  @CLOUD-1379
  Scenario: user logs out after clicking on logout link
    When user clicks on dropdown menu
    And user clicks logout button
    Then user should be able to log out

  @CLOUD-1380
  Scenario: user can not go to dashboard again by clicking step back button after successfully logged out
    When user clicks on dropdown menu
    And user clicks logout button
    And user navigates back
    Then user should be able to land on login page

  @CLOUD-1381
  Scenario: user is redirected to login page after logging out
    When user clicks on dropdown menu
    And user clicks logout button
    Then user should be able to land on login page

  @CLOUD-1382
  Scenario: username is not stored on login page after logging out
    When user clicks on dropdown menu
    And user clicks logout button
    Then user should not be able to see his username in username box