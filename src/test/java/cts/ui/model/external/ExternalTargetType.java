package cts.ui.model.external;

import cts.ui.model.ActionType;
import cts.ui.model.TargetVerifyType;
import cts.ui.model.TaskType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static cts.ui.model.ActionType.*;
import static cts.ui.model.TaskType.*;

public enum ExternalTargetType {
    LevelType("level", Select, new String[]{"Super", "Master", "Agent", "Member"}, new TaskType[]{MarkNewProblemAccount_CreditSites, MarkNewProblemAccount_LicenseeSites}, false, false, "(//*[@id='ddl-level']/a/span[1])[2]"),
    ReasonType("reason", Select, new String[]{"Group Betting", "Hedging", "Fixed Game", "ABBet"}, new TaskType[]{MarkNewProblemAccount_CreditSites, MarkNewProblemAccount_LicenseeSites}, false, false, "(//*[@id='ddl-reason']/a/span[1])[1]"),
    PreventiveActionType("preventive Action", Select, new String[]{"None", "Void Winning Tickets"}, new TaskType[]{MarkNewProblemAccount_CreditSites, MarkNewProblemAccount_LicenseeSites}, false, false, "(//*[@id='ddl-preventiveAction']/a/span[1])[1]"),
    Username("username", EnterValue, null, new TaskType[]{MarkNewProblemAccount_CreditSites, MarkNewProblemAccount_LicenseeSites}, true,false, "//*[@id='txt-usernames']"),
    Comment("comment", EnterValue, null, new TaskType[]{MarkNewProblemAccount_CreditSites, MarkNewProblemAccount_LicenseeSites}, false, false,"//*[@id='txt-comment']"),

    Save("save", Click, null, new TaskType[] {MarkNewProblemAccount_CreditSites, MarkNewProblemAccount_LicenseeSites}, false, true,"//button[text()='Save']");

    private String targetName;
    private ActionType actionType;
    private String targetLocated;
    private boolean prepareMode;
    private TaskType[] taskTypes;
    private String[] selectList;
    private boolean closingMode;

    public String getTargetLocated() {
        return targetLocated;
    }

    public String getTargetName() {
        return targetName;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public boolean getPrepareMode() {
        return prepareMode;
    }

    public TaskType[] getTaskTypes() {
        return taskTypes;
    }

    public String[] getSelectList() {
        return selectList;
    }

    public boolean isClosingMode() {
        return this.closingMode;
    }

    ExternalTargetType(String targetName, ActionType actionType, String[] selectList, TaskType[] taskTypes, boolean prepareMode, boolean closingMode, String targetLocated) {
        this.targetName = targetName;
        this.actionType = actionType;
        this.selectList = selectList;
        this.taskTypes = taskTypes;
        this.prepareMode = prepareMode;
        this.closingMode = closingMode;
        this.targetLocated = targetLocated;
    }

    public static ExternalTargetType getTargetType(String targetName, TaskType taskType, Object selectValue) {
        for (ExternalTargetType targetType : ExternalTargetType.values()) {
            if (targetType.actionType == Select) {
                if (targetType.targetName.equalsIgnoreCase(targetName) && (Arrays.stream(targetType.taskTypes).anyMatch(taskType::equals)) && (Arrays.stream(targetType.selectList).anyMatch(selectValue.toString()::equalsIgnoreCase))) {
                    return targetType;
                }
            } else {
                if (targetType.targetName.equalsIgnoreCase(targetName) && (Arrays.stream(targetType.taskTypes).anyMatch(taskType::equals))) {
                    return targetType;
                }
            }
        }
        return null;
    }

    public static List<ExternalTargetType> getTargetType(TaskType taskType) {
        List<ExternalTargetType> closingTarget = new ArrayList<>();
        for (ExternalTargetType targetType : ExternalTargetType.values()) {
            if ((Arrays.stream(targetType.taskTypes).anyMatch(taskType::equals)) && (targetType.isClosingMode())) {
                closingTarget.add(targetType);
            }
        }
        return closingTarget;
    }
}
