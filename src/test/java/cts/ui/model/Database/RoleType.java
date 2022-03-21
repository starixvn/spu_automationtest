package cts.ui.model.Database;

import cts.ui.model.LevelType;

import static cts.ui.model.Database.CTSTableType.CTSCustomer;

public enum RoleType {
    Member("Member", 1),
    Agent("Agent", 2),
    Master("Master", 3),
    Super("Super", 4);

    private String roleName;
    private CTSTableType ctsTableType;
    private int roleID;

    RoleType(String roleName, int roleID) {
        this.roleName = roleName;
        this.roleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public int getRoleID() {
        return roleID;
    }

    public static RoleType getRoleType(String value) {
        for (RoleType roleType : RoleType.values()) {
            if (roleType.roleName.equalsIgnoreCase(value)) {
                return roleType;
            }
        }
        return null;
    }
}
