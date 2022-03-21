package cts.ui.steps;

import cts.ui.model.*;
import cts.ui.tasks.DataPrepare;
import cts.ui.tasks.external.NapCMTask;
import cts.ui.tasks.external.ServiceTask;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;

import static cts.ui.model.EnvType.*;

public class ExternalStep {

    @Given("{actor} start {} service")
    public void precondition(Actor actor, ServiceType serviceType) {
        if (ParameterDefinitions.envType != UAT && ParameterDefinitions.envType != Prod) {
            actor.attemptsTo(
                    ServiceTask.loginServicePage(),
                    ServiceTask.startService(serviceType));
        }
    }

    @Given("{actor} prepare data")
    public void prepareData(Actor actor, DataTable dataTable) {
        DataPrepare data = new DataPrepare(dataTable);
    }

    @When("{actor} perform task {}")
    public void performTask (Actor actor, TaskType taskType, DataTable dataTable) {
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

    @When("{actor} verify tagging {}")
    public void verifyTagging(Actor actor, TaggingType taggingType) {

    }
}
