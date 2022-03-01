package cts.ui.tasks.navigation;

import net.serenitybdd.screenplay.targets.Target;

public class NavigateForm {

    public static Target HOME_ICON = Target.the(String.format("Home item")).locatedBy("//*[@id='menu-home']/a/span");

    public static Target dropdownList(String value) {
        return Target.the(String.format("%s item", value)).locatedBy(String.format("//*[text() = '%s']", value));
    }

    public static Target menuItem(String value) {
        return Target.the(String.format("%s menu item", value)).locatedBy(String.format("#%s", value));
    }

    public static Target searchBox() {
        return Target.the(String.format("search box")).locatedBy("//*[@id='txtInput']");
    }

    public static Target tabItem(String value) {
        return Target.the(String.format(" %s tab item", value)).locatedBy(String.format("//*[text() = '%s']", value));
    }

    /* General Target */
    public static Target CONFIRM_BUTTON = Target.the("confirm button").locatedBy("//button[normalize-space(text())='Confirm']");
    public static Target DIALOG_SUCCESS = Target.the("system message dialog").locatedBy("//*[contains(@class, 'toast') and normalize-space(text())='Successful!']");
    public static Target DIALOG_ClOSE = Target.the("close the system message dialog").locatedBy("//*[@class='close-jq-toast-single']");
}
