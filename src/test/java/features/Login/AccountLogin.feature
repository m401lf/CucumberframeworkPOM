Feature: Login Page

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


  Scenario Outline: User should NOT be able to login with invalid credentials
    Given User is on landing page as "<url>"
    When User clicks the LoginRegister button
    And User enters Login Name as "<LoginName>"
    And User enters Password as "<Password>"
    When User clicks on Login button
    Then User gets a confirmation message
    And User is still in login page as "<Text_Dispalyed>"

    Examples:
      | url                             | LoginName            | Password    | Text_Dispalyed     |
      | https://automationteststore.com | datastudioplaceWrong | Manchester1 | RETURNING CUSTOMER |

