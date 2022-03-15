package cts.ui.model;

import static cts.ui.model.ActionType.Select;

public enum PreventiveActionType {
    None("None"),
    VoidWinningTickets("Void Winning Tickets");

    PreventiveActionType(String preventiveActionName) {
        this.preventiveActionName = preventiveActionName;
    }

    private String preventiveActionName;

    public static PreventiveActionType getPreventiveActionType(String value) {
        for (PreventiveActionType preventiveActionType : PreventiveActionType.values()) {
            if (preventiveActionType.preventiveActionName.equalsIgnoreCase(value)) {
                return preventiveActionType;
            }
        }
        return null;
    }

    public String getPreventiveActionName() {
        return preventiveActionName;
    }
}
