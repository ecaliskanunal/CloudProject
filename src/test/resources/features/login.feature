@smoke @login @CLOUD-1191
Feature: Login functionality
  User Story: As a user, I should be able to login so that I can land on the dashboard page.

  Acceptance Criteria:
  1-User can login with valid credentials (either clicking on the "Login button" or hitting the "Enter" key from the keyboard as the last step)
  2-User can not login with any invalid credentials
  "Wrong username or password." should be displayed for invalid credentials
  "Please fill out this field" message should be displayed if the password or username is empty
  3-User can see the password in a form of dots by default
  4-User can see the password explicitly if needed
  5-User can see the "Forgot password?" link on the login page and can see the "Reset Password" button on the next page after clicking on forget password link
  6-User can see valid placeholders on Username and Password fields


  Background:
    Given user is on the login page

  @CLOUD-1180
  Scenario: login with valid credentials after a click on the login button
    When user types username
    And user types password
    And user clicks on login button
    Then user can login successfully and land on dashboard

  @CLOUD-1181
  Scenario: login with valid credentials after hitting on the enter key
    When user types username
    And user types password
    And user hits enter key
    Then user can login successfully and land on dashboard

  @CLOUD-1182
  Scenario Outline: login with invalid credentials
    When user enters invalid username "<invalid username>"
    And user enters invalid password "<invalid password>"
    And user clicks on login button
    Then  user sees error message "Wrong username or password."

    Examples:
      | invalid username | invalid password |
      | Employee1333     | Employee123      |
      | Employee133      | Employee1233     |
      | Employee1333     | Employee1233     |
      | EMPLOYEE133      | Employee123      |
      | employee133      | Employee123      |
      | Employee133      | EMPLOYEE123      |
      | Employee133      | employee123      |

  @CLOUD-1183
  Scenario Outline: login with missing credentials
    When user enters missing credentials username "<username>"
    And user enters missing credentials password "<password>"
    And user clicks on login button
    Then  user cannot login
    Then user sees validation message "<validation message>"

    Examples:
      | username    | password    | validation message          |
      |             | Employee123 | Please fill out this field. |
      | Employee133 |             | Please fill out this field. |
      |             |             | Please fill out this field. |

  @CLOUD-1184
  Scenario: password is typed hidden
    When user types username
    And user types password
    And user sees password hidden

  @CLOUD-1185
  Scenario: password can be made visible
    When user types username
    And user types password
    And user toggles visibility
    Then user sees password visible

  @CLOUD-1186
  Scenario: forgot password link is visible and clickable
    When user sees forgot password link
    And user clicks forgot password link

  @CLOUD-1187
  Scenario: reset password link is visible and clickable
    When user sees forgot password link
    And user clicks forgot password link
    And user types username in reset password link
    Then user clicks reset password

  @CLOUD-1188
  Scenario: placeholder check for username
    When user sees the placeholder for username

  @CLOUD-1189
  Scenario: placeholder check for password
    When user sees the placeholder for password

  @CLOUD-1190
  Scenario: user refreshes and is still logged in
    When user types username
    And user types password
    And user clicks on login button
    And user refreshes the page
    Then user is still logged in

