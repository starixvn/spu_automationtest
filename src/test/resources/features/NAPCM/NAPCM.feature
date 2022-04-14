Feature: NAPCM

  Scenario: TC-01
    Given I start CTS_Problem_Account_Insert service
    Given I prepare data
      | Role  | Site      | Downline  | Existed Association | Existed Association Value | Generate Association | Generate Association Value |
      | Agent | BEST-ODDS | CMember01 | byDevice            | 3                         | byDevice             | 1                          |
    When I perform task MarkNewProblemAccount_CreditSites
      | Level | Username | Reason        | Preventive Action | Comment                       |
      | Agent | Username | Group Betting | None              | Verify insert PA for CAgent01 |
    Then I verify tagging Insert_Problem_Category apply for "MemberCustID"
      | Table                                  | Column       | exp |
      | CTSCustomerClassification_InputDataLog | FunctionID   | 41  |
      | CTSCustomerClassification_InputDataLog | InputValue   | 102 |
      | CTSCustomerClassification              | CategoryID   | 152 |
      | CTSCustomerClassification_History      | CategoryID   | 152 |
      | CTSCustomerClassification_History      | SourceTypeID | 6   |
      | CTSCustomerClassification_Log          | CategoryID   | 152 |
      | CTSCustomerClassification_Log          | SourceTypeID | 6   |
#    Then I verify tagging Push_CC_Category_ABI_Danger apply for "MemberCustID"
#      | Table                            | Column        | exp |
#      | dbo.custinfo                     | CustomerClass | 8   |
#      | dbo.custinfo                     | Danger2       | 1   |
#      | dbo.CustCategoryHistory          | CategoryID    | 52  |
#      | dbo.ProblemAccountClassification | InputValue    | 102 |
    Then I verify tagging General_Info_Get_Classification apply for "MemberUsername"
      | Target             | exp           |
      | Category           | GROUP BETTING |
      | Class              | 8             |
      | Next Category Scan | None          |
      | ABI                | 11            |
    Then I verify tagging Customer_Classification_Tab_Get_log apply for "MemberUsername"
      | Target   | exp           |
      | Category | GROUP BETTING |
  Scenario: TC-02
