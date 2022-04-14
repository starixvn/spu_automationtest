package cts.ui.model;

import static cts.ui.model.Page.*;
import static cts.ui.model.VerifyType.*;
import static cts.ui.model.TargetVerifyType.*;

public enum TaggingType {
    Insert_Problem_Category("Insert Problem Category", DB, null),
    Push_Customer_Class("Push Customer Class", DB, null),
    Push_ABI_Danger("Push ABI Danger", DB, null),
    General_Info_Get_Classification("General Info - Get Classification", UI, GeneralInfo),
    General_Info_Get_Dates("Push Customer Class", UI, null),
    Customer_Classification_Tab_Get_log("Customer Classification Tab - Get log", UI, CustomerClassificationTab),
    Push_CC_Category_ABI_Danger(" Push CC, Category, ABI Danger", DB, null);

    private String taggingName;
    private VerifyType verityType;
    private Page page;

    TaggingType(String taggingName, VerifyType verityType, Page page) {
        this.taggingName = taggingName;
        this.verityType = verityType;
        this.page = page;
    }

    public String getTaggingName() {
        return taggingName;
    }
    public VerifyType getVerityType() {
        return verityType;
    }

    public Page getPage() {
        return page;
    }
}
