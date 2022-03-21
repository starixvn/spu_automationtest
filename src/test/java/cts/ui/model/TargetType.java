package cts.ui.model;

import static cts.ui.model.ActionType.EnterValue;
import static cts.ui.model.ActionType.Select;

public enum TargetType {
    LevelType("level", Select, null,"(//*[@id='ddl-level']/a/span[1])[2]"),
    ReasonType("reason", Select, null, "(//*[@id='ddl-reason']/a/span[1])[1]"),
    PreventiveActionType("preventive Action", Select, null,"(//*[@id='ddl-preventiveAction']/a/span[1])[1]"),
    Username("username", EnterValue, "Username", "//*[@id='txt-usernames']"),
    Comment("comment", EnterValue, null,"//*[@id='txt-comment']");

    private String targetName;
    private ActionType actionType;
    private String targetLocated;
    private String prepareValue;

    public String getTargetLocated() {
        return targetLocated;
    }

    public String getTargetName() {
        return targetName;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public String getPrepareValue() {
        return prepareValue;
    }

    TargetType(String targetName, ActionType actionType, String prepareValue, String targetLocated) {
        this.targetName = targetName;
        this.actionType = actionType;
        this.prepareValue = prepareValue;
        this.targetLocated = targetLocated;
    }

    public static TargetType getTargetType(String value) {
        for (TargetType targetType : TargetType.values()) {
            if (targetType.targetName.equalsIgnoreCase(value)) {
                return targetType;
            }
        }
        return null;
    }
}
