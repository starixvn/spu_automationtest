package cts.ui.model;

import static cts.ui.model.ActionType.Select;

public enum ReasonType {
    GroupBetting("Group Betting"),
    Hedging("Hedging"),
    Arbitrage("Hedging"),
    FixedGame("Fixed Game"),
    ABBet("ABBet");


    private String reasonName;
    ReasonType(String reasonName) {
        this.reasonName = reasonName;
    }

    public static ReasonType getReasonType(String value) {
        for (ReasonType reasonType : ReasonType.values()) {
            if (reasonType.reasonName.equalsIgnoreCase(value)) {
                return reasonType;
            }
        }
        return null;
    }

    public String getReasonName() {
        return reasonName;
    }
}
