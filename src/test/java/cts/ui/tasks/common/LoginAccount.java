package cts.ui.tasks.common;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

public class LoginAccount {
    public static Performable with(String userName, String password) {
        return Task.where("{0} login with user name: '" + userName + "'" + " password: '" + password + "'",
                Enter.theValue(userName)
                        .into(LoginForm.USERNAME_FIELD),
                Enter.theValue(password)
                        .into(LoginForm.PASSWORD_FIELD),
                Click.on(LoginForm.LOGIN_BUTTON)
        );
    }
}
