package cts.ui.model;

import java.util.Arrays;

import static cts.ui.model.ActionType.*;
import static cts.ui.model.Page.*;
import static cts.ui.model.TaggingType.*;

public enum TargetVerifyType {
    Category("category", HasText, GeneralInfo, new TaggingType[]{General_Info_Get_Classification},"//*[@id='divCustomerCategorySummary']//*[starts-with(@class, 'text-status')]"),
    Class("class", HasText, GeneralInfo, new TaggingType[]{General_Info_Get_Classification}, "//*[@id='spanClass']"),
    Next_Category_Scan("next category scan", HasText, GeneralInfo, new TaggingType[]{General_Info_Get_Classification}, "//*[@id='spanNextCategoryScanDate']"),
    ABI("abi", HasText, GeneralInfo, new TaggingType[]{General_Info_Get_Classification}, "//*[@id='spanDangerousABI']"),
    Category_Tab("category", HasText, CustomerClassificationTab, new TaggingType[]{Customer_Classification_Tab_Get_log}, "//*[@id='txt-usernames']");

    private String inputIdentify;
    private ActionType actionType;
    private Page page;
    private String targetLocated;
    private TaggingType[] taggingType;

    TargetVerifyType(String inputIdentify, ActionType actionType, Page page, TaggingType[] taggingType, String targetLocated) {
        this.inputIdentify = inputIdentify;
        this.actionType = actionType;
        this.page = page;
        this.taggingType = taggingType;
        this.targetLocated = targetLocated;
    }

    public String getInputIdentify() {
        return inputIdentify;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public Page getPage() {
        return page;
    }

    public String getTargetLocated() {
        return targetLocated;
    }

    public TaggingType[] getTaggingType() {
        return taggingType;
    }

    public static TargetVerifyType getTargetVerifyType(String value, TaggingType taggingType) {
        for (TargetVerifyType targetVerifyType : TargetVerifyType.values()) {
            if (targetVerifyType.inputIdentify.equalsIgnoreCase(value) && (Arrays.stream(targetVerifyType.taggingType).anyMatch(taggingType::equals))) {
                    return targetVerifyType;
            }
        }
        return null;
    }
}
