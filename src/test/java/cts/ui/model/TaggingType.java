package cts.ui.model;

import static cts.ui.model.VerifyType.*;

public enum TaggingType {
    Insert_Problem_Category("Insert Problem Category", DB),
    Push_Customer_Class("Push Customer Class", DB),
    Push_ABI_Danger("Push ABI Danger", DB),
    General_Info_Get_Classification("General Info - Get Classification", UI),
    General_Info_Get_Dates("Push Customer Class",UI),
    Customer_Classification_Tab_Get_log("Customer Classification Tab - Get log", UI);

    private String taggingName;
    private VerifyType verityType;

    TaggingType(String taggingName, VerifyType verityType) {
        this.taggingName = taggingName;
        this.verityType = verityType;
    }
}
