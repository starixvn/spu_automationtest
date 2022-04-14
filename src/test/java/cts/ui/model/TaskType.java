package cts.ui.model;

import cts.ui.model.external.ExternalPageType;
import cts.ui.model.external.ExternalTargetType;
import static cts.ui.model.external.ExternalTargetType.*;
import static cts.ui.model.external.ExternalPageType.*;

public enum TaskType {
    MarkNewProblemAccount_CreditSites(ManageProblemAccountCreditSites, "Mark New Problem Account"),
    MarkNewProblemAccount_LicenseeSites(ManageProblemAccountLicenseeSites, "Mark New Problem Account"),
    UpdateProblemAccount(ManageProblemAccountCreditSites, "Update Problem Account"),
    Unmark(ManageProblemAccountCreditSites, "Unmark"),
    ReduceBetLimit(ManageProblemAccountCreditSites, "Reduce Bet Limit"),
    RemoveReducedBetLimit(ManageProblemAccountCreditSites, "Remove Reduced Bet Limit");

    private ExternalPageType externalPageType;
    private String actionName;
     TaskType(ExternalPageType externalPageType, String actionName) {
        this.externalPageType = externalPageType;
        this.actionName = actionName;
    }

    public String getActionName() {return actionName;}
    public ExternalPageType getExternalPageType() {return externalPageType;}
}

