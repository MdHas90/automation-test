Feature: Login to the website

  Scenario: Verify users can go to the authentication page
    Given User visits the website for LoginStepDefinitions
    When clicks on sign-in for LoginStepDefinitions
    Then User redirects to Authenticate page

  Scenario: Verify users can login to portal with valid credentials
    Given User visits the website for LoginStepDefinitions
    And clicks on sign-in for LoginStepDefinitions
    When User enters valid email-address
    And enters valid password
    And clicks on Sign in to log in
    Then User can log in successfully

  Scenario: Verify users cannot login to portal with invalid credentials
    Given User visits the website for LoginStepDefinitions
    And clicks on sign-in for LoginStepDefinitions
    When User enters valid email-address
    And enters invalid password
    And clicks on Sign in to log in
    Then User cannot login

  Scenario: Verify users cannot login to portal with invalid credentials
    Given User visits the website for LoginStepDefinitions
    And clicks on sign-in for LoginStepDefinitions
    When User enters invalid email-address
    And enters valid password
    And clicks on Sign in to log in
    Then User cannot login