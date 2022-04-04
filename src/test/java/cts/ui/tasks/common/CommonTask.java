package cts.ui.tasks.common;

import cts.ui.model.TaggingType;
import cts.ui.steps.ParameterDefinitions;
import cts.ui.tasks.external.NapCMForm;
import cts.ui.tasks.verify.VerifyTask;
import io.cucumber.datatable.DataTable;
import lib.GetProperties;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.conditions.Check;
import net.serenitybdd.screenplay.questions.Visibility;

import java.util.Locale;
import java.util.Properties;

public class CommonTask {
    public static Performable login() {
        GetProperties prop = new GetProperties();
        return Task.where("{0} login CTS page",
                Check.whether(Visibility.of(LoginForm.LOGIN_BUTTON)).andIfSo(
                        Enter.theValue(prop.getProperty(String.format("%s.cts.user", ParameterDefinitions.envType.toString().toLowerCase(Locale.ROOT)))).into(LoginForm.USERNAME_FIELD),
                        Enter.theValue(prop.getProperty(String.format("%s.cts.password", ParameterDefinitions.envType.toString().toLowerCase(Locale.ROOT)))).into(LoginForm.PASSWORD_FIELD),
                        Click.on(LoginForm.LOGIN_BUTTON))
        );
    }
}
