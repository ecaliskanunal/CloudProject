@smoke
Feature: Dashboard functionality
  User Story: As a user I should be able to modify Dashboard page

  Acceptance Criteria
  1. After Login user can see all modules and Username.
  2. User can click on Customize button
     User can see status widgets and select any of them.
     User can see background images and select any of them.
  3. User can click on Set Status button
     User sees Online status options and select any of them
     User sees Status messages and select any of them
     User can Set or Clear status messages
     After these steps user can see his/her selections on dashboard
  4. User can set the location for weather by typing the place
     After his selection, user can see his selection on the dashboard

  Background:
    Given user is logged in

    @CLOUD-1509
  Scenario Outline: user can see all the modules after hovering over them
    When user hovers over a "<module>" button on top left
    Then user should be able to see the "<module>" name highlighted

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

  @CLOUD-1510
  Scenario: user can see his full username when he clicks on the top right menu
    When user hovers over username image on top right menu
    And user clicks on username image
    Then user should be able to see his full username displayed on top of the menu

  @CLOUD-1511
  Scenario Outline: user can click on each module button
    When user hovers over a "<module>" button on top left
    And user clicks on the "<module>"
    Then user should be able to see the "<module>" page
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

  @CLOUD-1512
  Scenario: user can click on customize button
    When user hovers over customize button
    And user clicks on customize button
    Then user should be able to see the customize page

  @CLOUD-1513
  Scenario: user can see status widgets on customize page
    When user hovers over customize button
    And user clicks on customize button
    Then user should be able to see all status widgets on customize page
      | Status            |
      | Weather           |
      | Upcoming events   |
      | Upcoming cards    |
      | Important mail    |
      | Unread mail       |
      | Recommended files |
      | Talk mentions     |
      | Recent statuses   |

  @CLOUD-1514
  Scenario: user can select all status widgets on customize page
    When user clicks on customize button
    And user clicks on all widgets one by one
    Then user should be able to see all widgets selected

  @CLOUD-1515
  Scenario: user can deselect all status widgets on customize page
    When user clicks on customize button
    And user clicks on all widgets one by one to deselect
    Then user should be able to see all widgets deselected

  @CLOUD-1516
  Scenario: user can see background images on customize page
    When user clicks on customize button
    And user scrolls down on customize page
    Then user should be able to see background images

  @CLOUD-1517
  Scenario: user can select any background image on customize page
    When user clicks on customize button
    And user scrolls down on customize page
    Then user should be able to select any background image
      | 3  |
      | 5  |
      | 7  |
      | 13 |
      | 11 |

  @CLOUD-1518
  Scenario Outline: user can select status options
    When user clicks on set status button on dashboard
    And user clicks on a "<status>"
    And user clicks on set status message button on the new opening page
    Then user should be able to see the selected "<status>" message on dashboard
    Examples:
      | status         |
      | Away           |
      | Do not disturb |
      | Invisible      |

  @CLOUD-1519
  Scenario Outline: user can set any status message
    When user clicks on set status button on dashboard
    And user chooses a "<status message>"
    And user clicks on set status message button on the new opening page
    Then Then user should be able to see the selected "<status message>" on dashboard
    Examples:
      | status message   |
      | In a meeting     |
      | Working remotely |
      | Commuting        |
      | Out sick         |
      | Vacationing      |

  @CLOUD-1520
  Scenario: user can clear status messages
    When user clicks on set status button on dashboard
    And user clicks on clear status button on the new opening page
    Then user should be able to see status message cleared from dashboard
      | In a meeting     |
      | Working remotely |
      | Commuting        |
      | Out sick         |
      | Vacationing      |

  @CLOUD-1521
    Scenario Outline: user can set the location for weather by typing the place
      When user clicks on weather widget on dashboard
      And user clicks on address box
      And user types a "<place>" in address box
      Then user should be able to see the weather for the "<place>" typed on dashboard

      Examples:
      |place|
      |Ankara|
      |Boston|
