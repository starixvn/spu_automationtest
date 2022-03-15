package cts.ui.model;

import static cts.ui.model.MenuItermType.Customer;
import static cts.ui.model.SourceType.NapCM;

public enum ExternalPageType {
    ManageProblemAccountCreditSites(NapCM, Customer, "Manage Problem Account - Credit Sites"),
    ManageProblemAccountLicenseeSites(NapCM, Customer, "Manage Problem Account - Licensee Sites");

    private SourceType sourceType;
    private MenuItermType menuItermType;
    private String externalPageName;

    ExternalPageType(SourceType sourceType, MenuItermType menuItermType, String externalPageName) {
        this.sourceType = sourceType;
        this.menuItermType = menuItermType;
        this.externalPageName = externalPageName;
    }

    public SourceType getSourceType() {
        return sourceType;
    }
    public MenuItermType getMenuItermType() {return menuItermType;}
    public String getExternalPageName() {return externalPageName;}
}
