Feature: NAPCM

  Scenario: TC-01
    Given user start CTS_Problem_Account_Insert service
    Given user prepare data
      | Role  | Site      | Downline  | Association |
      | Agent | BEST-ODDS | CMember01 | Not         |
    When user perform task MarkNewProblemAccountCreditSites
      | Level | Username | Reason        | Preventive Action | Comment                       |
      | Agent | agent01  | Group Betting | None              | Verify insert PA for CAgent01 |
