Feature: Registration to the website

  Scenario: Verify users can go to registration page
    Given User visits the website for RegistrationStepDefinitions
    And clicks on sign-in for RegistrationStepDefinitions
    When user enters valid email-address for account creation
    And clicks on Create an account
    Then user redirects to Create account page

  Scenario: Verify users cannot do registration without filing any field
    Given User visits the website for RegistrationStepDefinitions
    And clicks on sign-in for RegistrationStepDefinitions
    When user enters valid email-address for account creation
    And clicks on Create an account
    And user redirects to Create account page
    Then user clicks register without filling any fields
    And gets error message

  Scenario: Verify users can do registration
    Given User visits the website for RegistrationStepDefinitions
    And clicks on sign-in for RegistrationStepDefinitions
    When user enters valid email-address for account creation
    And clicks on Create an account
    And user redirects to Create account page
    Then user clicks register after filling all fields
    And user successfully logs in and do sign out