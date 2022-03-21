Feature: NAPCM

  Scenario: TC-01
#    Given user start CTS_Problem_Account_Insert service
    Given I prepare data
      | Role   | Site      | Downline  | Existed Association | Existed Association Value | Generate Association | Generate Association Value |
      | Agent  | BEST-ODDS | CMember01 | byDevice            | 3                         | byDevice             | 1                          |
    When I perform task MarkNewProblemAccount_CreditSites
      | Level | Username | Reason        | Preventive Action | Comment                       |
      | Agent |          | Group Betting | None              | Verify insert PA for CAgent01 |
    Then I verify tagging Insert_Problem_Category
    |CTSCustomerClassification_InputDataLog|InputValue|CategoryID|
    |41|102|||||

  Scenario: TC-02
#    Given I start CTS_Customer_Account_Evidence service
