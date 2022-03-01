package cts.ui.tasks.login;

import net.serenitybdd.screenplay.targets.Target;

public class LoginForm {
    static Target USERNAME_FIELD = Target.the("user name field").locatedBy("#username");
    static Target PASSWORD_FIELD = Target.the("password field").locatedBy("#password");
    static Target LOGIN_BUTTON = Target.the("login button").locatedBy("//*[normalize-space(text())='Login']");
}
