Feature:

  Scenario: Account is Created
    Given I am using "edge" browser
    * I am currently on the website "https://membership.basketballengland.co.uk/NewSupporterAccount"
    When I enter all required information and click confirm and join
    Then I see "THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND"

  Scenario: Last Name is Missing
    Given I am using "chrome" browser
    * I am currently on the website "https://membership.basketballengland.co.uk/NewSupporterAccount"
    When Last Name is Missing
    Then I see error "Last Name is required"

  Scenario Outline: Passwords Do Not Match
    Given I am using "chrome" browser
    * I am currently on the website "https://membership.basketballengland.co.uk/NewSupporterAccount"
    When I enter <Password> and <ConfirmPassword>:
    Then I should see <message>

    Examples:
      | Password | ConfirmPassword | message                                      |
      | Abcd123  | Abcd12          | Password did not match                       |
      | Abcd123  | A               | Password must be between 5 and 20 characters |

  Scenario: Terms and Conditions Not Accepted
    Given I am using "chrome" browser
    * I am currently on the website "https://membership.basketballengland.co.uk/NewSupporterAccount"
    When Confirm registration without Terms and Conditions accept
    Then I see error message