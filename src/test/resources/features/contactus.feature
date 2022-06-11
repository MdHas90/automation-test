Feature: Using contact us page

  Scenario: Verify user can go to contact us page
    Given User visits the website for dashboardStepDefinitions
    When user clicks on contact us
    Then user redirects to contact us page

  Scenario: Verify user gets invalid error message
    Given User visits the website for dashboardStepDefinitions
    When user clicks on contact us
    And user redirects to contact us page
    And user clicks send button
    Then user gets invalid email error message

  Scenario: Verify user gets empty message box error message for not filling heading
    Given User visits the website for dashboardStepDefinitions
    When user clicks on contact us
    And user redirects to contact us page
    And user inputs email address
    And user clicks send button
    Then user gets empty message box error message

  Scenario: Verify gets empty message box error message for not filling message box
    Given User visits the website for dashboardStepDefinitions
    When user clicks on contact us
    And user redirects to contact us page
    And user selects a subject
    And user inputs email address
    And user clicks send button
    Then user gets empty message box error message

  Scenario: Verify user gets successful message
    Given User visits the website for dashboardStepDefinitions
    When user clicks on contact us
    And user redirects to contact us page
    And user selects a subject
    And user inputs email address
    And user inputs a message
    And user clicks send button
    Then user gets successful message