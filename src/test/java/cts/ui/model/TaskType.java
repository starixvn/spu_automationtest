package cts.ui.model;

import static cts.ui.model.ExternalPageType.ManageProblemAccountCreditSites;
import static cts.ui.model.ExternalPageType.ManageProblemAccountLicenseeSites;

public enum TaskType {
    MarkNewProblemAccount_CreditSites(ManageProblemAccountCreditSites, "Mark New Problem Account"),
    MarkNewProblemAccount_LicenseeSites(ManageProblemAccountLicenseeSites, "Mark New Problem Account"),
    UpdateProblemAccount(ManageProblemAccountCreditSites, "Update Problem Account"),
    Unmark(ManageProblemAccountCreditSites, "Unmark"),
    ReduceBetLimit(ManageProblemAccountCreditSites, "Reduce Bet Limit"),
    RemoveReducedBetLimit(ManageProblemAccountCreditSites, "Remove Reduced Bet Limit");

    ExternalPageType externalPageType;
    String actionName;
     TaskType(ExternalPageType externalPageType, String actionName) {
        this.externalPageType = externalPageType;
        this.actionName = actionName;
    }

    public String getActionName() {return actionName;}
    public ExternalPageType getExternalPageType() {return externalPageType;}
}

