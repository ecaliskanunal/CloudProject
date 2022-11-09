@smoke
Feature: Dashboard functionality
  User Story: As a user I should be able to modify Dashboard page

  Acceptance Criteria
  1. After Login user can see all modules and Username.
  2. User can click on Customize button and,
  User can see status widgets and select any of them.
  User can see background images and select any of them.
  3. User can click on Set Status button and
  User sees Online status options and select any of them
  User sees Status messages and select any of them
  User can Set or Clear status messages
  After these steps user can see his/her selections on dashboard
  4. User can set the location for weather
  User can locate the place by map
  User can type the location
  After his selection, user can see his selection on the dashboard

  Background:
    Given user is logged in


  Scenario Outline: user can see all the modules after hovering over them
    When user hovers over a module "<module>"
    Then user should be able to see the selected module "<module>"

    Examples:
      | module    |
      | Dashboard |
      | Files     |
      | Photos    |
      | Activity  |
      | Talk      |
      | Mail      |
      | Contacts  |
      | Circles   |
      | Calender  |
      | Deck      |


  Scenario: user can see username image
    When user hovers over username image
    And user clicks username image
    Then user should be able to see username "username"


  Scenario Outline: user can click each module button
    When user hovers over a module "<module>"
    And user clicks on the module "<module>"
    Then user should be able to see the module page "<module>"

    Examples:
      | module    |
      | Dashboard |
      | Files     |
      | Photos    |
      | Activity  |
      | Talk      |
      | Mail      |
      | Contacts  |
      | Circles   |
      | Calender  |
      | Deck      |


  Scenario: user can click on customize button
    When user hovers over customize button
    And user clicks on customize button
    Then user should be able to customize options


  Scenario: user can see status widgets
    When user hovers over customize button
    And user clicks on customize button
    Then user should be able to see all status widgets following
      | Status            |
      | Weather           |
      | Upcoming events   |
      | Upcoming cards    |
      | Important mail    |
      | Unread mail       |
      | Recommended files |
      | Talk mentions     |
      | Recent statuses   |


  Scenario: user can select all status widgets
    When user clicks on customize button
    And user clicks on all widgets
    Then user should be able to see all widgets selected


  Scenario: user can deselect all status widgets
    When user clicks on customize button
    And user clicks on all widgets to deselect
    Then user should be able to see all widgets deselected


  Scenario: user can see background images
    When user clicks on customize button
    And user scrolls down on customize page
    Then user should be able to see background images


  Scenario: user can select any background image
    When user clicks on customize button
    And user scrolls down on customize page
    Then user should be able to select any background image 5


  Scenario Outline: user can select status options
    When user clicks on set status button on dashboard
    And user clicks on a status "<status>"
    And user clicks on set status message button
    Then user should be able to see the selected message on dashboard "<status>"
    Examples:
      | status         |
      | Online         |
      | Away           |
      | Do not disturb |
      | Invisible      |

  @wip
  Scenario Outline: user can select any status message
    When user clicks on set status button on dashboard
    And user chooses a status message "<status message>"
    And user scrolls down on status page
    And user clicks on set status message button
    Then Then user should be able to see the selected message on dashboard "<status message>"
    Examples:
      | status message   |
      | In a meeting     |
      | Working remotely |
#      | Commuting        |
#      | Out sick         |
#      | Vacationing      |

  Scenario: user can clear status messages
    When user clicks on set status button on dashboard
#    And user clicks on clear status button

  Scenario: user can set status messages


  fter these steps user can see his/her selections on dashboard
