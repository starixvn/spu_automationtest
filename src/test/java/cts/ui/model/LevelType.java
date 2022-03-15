package cts.ui.model;

import static cts.ui.model.ActionType.Select;

public enum LevelType {
    Super("Super"),
    Master("Master"),
    Agent("Agent"),
    Member("Member");

    private String levelName;

    public String getLevelName() {
        return levelName;
    }

    LevelType(String levelName) {
        this.levelName = levelName;
    }

    public static LevelType getLevelType(String value) {
        for (LevelType levelType : LevelType.values()) {
            if (levelType.levelName.equalsIgnoreCase(value)) {
                return levelType;
            }
        }
        return null;
    }
}
