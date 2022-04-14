package cts.ui.tasks.external;

import cts.ui.model.TaggingType;
import cts.ui.model.TaskType;

import cts.ui.tasks.GeneralTask;
import cts.ui.tasks.verify.VerifyTask;
import io.cucumber.datatable.DataTable;
import lib.GetProperties;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.conditions.Check;
import net.serenitybdd.screenplay.questions.Visibility;

public class NapCMTask {

    public static Performable loginNapCMPage() {
        GetProperties prop = new GetProperties();
        return Task.where("{0} open and login service ",
                Open.browserOn().thePageNamed("napcm.base.url"),
                Check.whether(Visibility.of(NapCMForm.LOGIN_BUTTON)).andIfSo(
                        Enter.theValue(prop.getProperty("local.napcm.user")).into(NapCMForm.USERNAME_FIELD),
                        Enter.theValue(prop.getProperty("local.napcm.password")).into(NapCMForm.PASSWORD_FIELD),
                        Click.on(NapCMForm.LOGIN_BUTTON))
        );
    }

    public static Performable navigateTo(TaskType taskType) {
        switch (taskType) {
            case MarkNewProblemAccount_CreditSites, MarkNewProblemAccount_LicenseeSites:
                return Task.where("{0} navigate to page",
                        Click.on(NapCMForm.menuIterm(taskType.getExternalPageType().getMenuItermType().getMenuItermName())),
                        Click.on(NapCMForm.menuDownList(taskType.getExternalPageType().getExternalPageName())),
                        Click.on(NapCMForm.button(taskType.getActionName())));
            default:
                return Task.where("{0} navigate to page",
                        Click.on(NapCMForm.menuIterm(taskType.getExternalPageType().getMenuItermType().getMenuItermName())),
                        Click.on(NapCMForm.menuDownList(taskType.getExternalPageType().getExternalPageName())));
        }
    }
}