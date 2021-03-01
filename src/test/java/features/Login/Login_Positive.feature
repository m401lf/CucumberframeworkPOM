Feature: Login Page

  @Validcredentials
  Scenario Outline: User should be able to login with valid credentials
    Given User is on landing page as "<url>"
    When User clicks the LoginRegister button
    And User enters Login Name as "<LoginName>"
    And User enters Password as "<Password>"
    When User clicks on Login button
    Then User is on Dashboard Page
    And User clicks logoff button

    Examples: 
      | url                             | LoginName       | Password    |
      | https://automationteststore.com | datastudioplace | Manchester1 |

