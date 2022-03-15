package cts.ui.steps;

import cts.ui.model.*;
import cts.ui.tasks.GeneralForm;
import cts.ui.tasks.external.NapCMTask;
import cts.ui.tasks.external.ServiceTask;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;

import static cts.ui.model.EnvType.*;
import static cts.ui.steps.ParameterDefinitions.getEnvironment;

public class ExternalStep {
    EnvType envType = getEnvironment();

    @Given("{actor} start {} service")
    public void precondition(Actor actor, ServiceType serviceType) {
        if (envType != UAT && envType != Prod) {
            actor.attemptsTo(
                    ServiceTask.loginServicePage(),
                    ServiceTask.startService(serviceType));
        }
    }

    @Given("{actor} prepare data")
    public void prepareData (Actor actor, DataTable dataTable){

    }

    @When("{actor} perform task {}")
    public void action(Actor actor, TaskType taskType, DataTable dataTable) throws InterruptedException {
        SourceType sourceType = taskType.getExternalPageType().getSourceType();
        switch (sourceType) {
            case DW:
                break;
            case NapCM:
                actor.attemptsTo(
                        NapCMTask.loginNapCMPage(),
                        NapCMTask.navigateTo(taskType));
                actor.attemptsTo(NapCMTask.performTask(actor, dataTable));
//                actor.attemptsTo(Ensure.that(GeneralForm.DIALOG_SUCCESS).isDisplayed());
                break;
            case Robot:
                break;
            default:
                break;
        }
    }
}
