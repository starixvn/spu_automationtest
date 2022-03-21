package cts.ui.model.Database;

public enum AssociationType {
    None("none"),
    ByDevice("byDevice");

    private String associationName;

    AssociationType(String associationName) {
        this.associationName = associationName;
    }

    public static AssociationType getAssociationType(String value) {
        for (AssociationType associationType : AssociationType.values()) {
            if (associationType.associationName.equalsIgnoreCase(value)) {
                return associationType;
            }
        }
        return null;
    }

    public String getAssociationName() {
        return associationName;
    }
}
