package cts.ui.tasks.external;

import net.serenitybdd.screenplay.targets.Target;

public class NapCMForm {
    static Target USERNAME_FIELD = Target.the("user name field").locatedBy("#txtUsername");
    static Target PASSWORD_FIELD = Target.the("password field").locatedBy("#txtPassword");
    static Target LOGIN_BUTTON = Target.the("login button").locatedBy("#btnSubmit");

    public static Target menuIterm(String menuItermName) {
        return Target.the(String.format("menu item %s", menuItermName)).locatedBy(String.format("//a[text()='%s']", menuItermName));
    }

    public static Target menuDownList(String externalPageName) {
        return Target.the(String.format("dropdown list item %s", externalPageName)).locatedBy(String.format("//a[text()='%s']", externalPageName));
    }

    public static Target button(String actionName) {
        return Target.the(String.format("button %s", actionName)).locatedBy(String.format("//button[text()='%s']", actionName));
    }
}
