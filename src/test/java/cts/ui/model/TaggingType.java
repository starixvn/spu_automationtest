package cts.ui.model;

public enum TaggingType {
    TG_001("Insert Problem Category"),
    TG_002("Push Customer Class"),
    TG_003("Push ABI Danger"),
    TG_004("Feed for Soon"),
    TG_005("Rollback Association by Check New PA"),
    TG_006("General Info - Get Category"),
    TG_007("General Info - Get Classification"),
    TG_008("General Info - Get Dates"),
    TG_009("Association Accounts Tab - Get Data"),
    TG_010("Customer Classification Tab - Get log"),
    TG_011("Robot Classification Tab - Get Latest Data"),
    TG_012("Get Association Detection List"),
    TG_013("Get Problem Case Monitor List"),
    TG_014("Get Match Monitor Detail List");

    private final String TaggingName;
    TaggingType(String TaggingName) {
        this.TaggingName = TaggingName;
    }
}
