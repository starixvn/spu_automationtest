Feature: Import Evidence
  Scenario: Import evidence with invalid data file
    Given user login with account "sa" "1234aa"
    When user upload evidence file "evidence_invalid.xlsx" with Overwrite type on EvidenceManagement page
    Then user verify msg error
      | tagging | TC-01           | EvidenceCode_expect | Error_expect                           | CheckList |
      | ff      | AutoFHUUS01248  | 6.1                 | This Customer does not exist.          | hasText   |
      | 2       | AnvoFHUUS012481 | 6.30                | This Customer does not exist.          |           |
      | 3       | AnvoFHUUS01248  | 1.1                 | This Evidence does not exist.          |           |
      | 4       | AnvoFHUUS01248  | 6.0                 | This Evidence does not exist.          |           |
      | 5       | AnvoFHUUS01248  | 6.31                | This Evidence does not exist.          |           |
      | 6       | AnvoFHUUS01248  | 6.101               | This Evidence does not exist.          |           |
      | 7       | AutoFHUUS01248  | 1.1                 | Both Customer & Evidence do not exist. |           |
      | 8       | AutoFHUUS01248  | 3.1                 | Both Customer & Evidence do not exist. |           |
      | 9       | AutoFHUUS01248  | 6.0                 | Both Customer & Evidence do not exist. |           |
      | 10      | AutoFHUUS01248  | 6.31                | Both Customer & Evidence do not exist. |           |
      | 11      |                 | 6.1                 | This Customer does not exist.          |           |
      | 12      | AnvoFHUUS01248  |                     | This Evidence does not exist.          |           |
      | 13      | AutoFHUUS01248  | 6.31                | This row is already exist.             |           |

  Scenario: Import evidence with valid data file
    When user upload evidence file "evidence_valid.xlsx" with Overwrite type on EvidenceManagement page
    Then user should see upload evidence successful validation
    Then user should see IsProcess is equal to 1 during 1 minute on CustEvidenceFromFile table
      | Username       |
      | MS88UUS0117671 |
      | MS88UUS0117672 |
    And user should see mapping EvidenceID, CategoryID on CustEvidence, CustEvidenceFromFile, CTSCustomerClassification table
      | Username_input | EvidenceID_expect | CategoryID_expect |
      | MS88UUS0117671 | 41                | 72                |
      | MS88UUS0117672 | 52                | 75                |

    And user should see categoriesSummary, Class, Flag Evidence, Customer Classification on Account Profile
      | Username_input | CategoriesSummary_expect | ClassClassification_expect | FlagEvidence_expect | CommentEvidence_expect | Evidence_expect | CategoryCustomerClassification_expect | CustomerClass_expect | checklist | target              |
      | MS88UUS0117671 | Group Betting            | 8                          | 6.1                 | Auto test 6.1          | Group Betting   | Group Betting                         | 8                    | hastext   | CategoriesSummary   |
      | MS88UUS0117672 | Fixed Game               | 10                         | 6.2                 | Auto test 6.2          | Fixed Game      | Fixed Game                            | 10                   |           | ClassClassification |