package cts.ui.model;

import static cts.ui.model.SourceType.NapCM;

public enum MenuItermType {
    Customer(NapCM, "Customer"),
    LossControl(NapCM, "Loss Control"),
    TurnoverControl(NapCM, "Turnover Control"),
    VirtualSettingControl(NapCM, "Virtual Setting Control"),
    CreditCollection(NapCM, "Credit Collection");

    private String menuItermName;
    private SourceType sourceType;

    MenuItermType(SourceType sourceType, String menuItermName) {
        this.menuItermName = menuItermName;
        this.sourceType = sourceType;
    }

    public String getMenuItermName() {return menuItermName;}
    public SourceType getSourceType() {return sourceType;}
}
