package cts.ui.model.database;

public enum InputTaskType {
    RoleType("role"),
    Site("site"),
    Downline("downline"),
    ExistedAssociation("existed association"),
    ExistedAssociationValue("existed association value"),
    GenerateAssociation("generate association"),
    GenerateAssociationValue("generate association value");

    private String dataName;

    InputTaskType(String dataName) {
        this.dataName = dataName;
    }

    public String getDataName() {
        return dataName;
    }

    public static InputTaskType getDataType(String value) {
        for (InputTaskType dataType : InputTaskType.values()) {
            if (dataType.getDataName().equalsIgnoreCase(value)) {
                return dataType;
            }
        }
        return null;
    }
}
