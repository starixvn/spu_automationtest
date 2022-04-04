package cts.ui.steps;

import cts.ui.model.*;
import cts.ui.tasks.common.CommonTask;
import cts.ui.tasks.common.NavigateTo;
import cts.ui.tasks.database.DataPrepareTask;
import cts.ui.tasks.database.DatabaseTask;
import cts.ui.tasks.external.NapCMTask;
import cts.ui.tasks.external.ServiceTask;
import cts.ui.tasks.verify.VerifyTask;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;

import static cts.ui.model.EnvType.*;

public class CommonStep {

    @Given("{actor} start {} service")
    public void startService(Actor actor, ServiceType serviceType) {
        if (ParameterDefinitions.envType != UAT && ParameterDefinitions.envType != Prod) {
            actor.attemptsTo(
                    ServiceTask.loginServicePage(),
                    ServiceTask.startService(serviceType));
        }
    }

    @Given("{actor} prepare data")
    public void prepareData(Actor actor, DataTable dataTable) {
        DataPrepareTask data = new DataPrepareTask(dataTable);
    }

    @When("{actor} perform task {}")
    public void performTask(Actor actor, TaskType taskType, DataTable dataTable) {
        SourceType sourceType = taskType.getExternalPageType().getSourceType();
        switch (sourceType) {
            case DW:
                break;
            case NapCM:
                actor.attemptsTo(
                        NapCMTask.loginNapCMPage(),
                        NapCMTask.navigateTo(taskType));
                actor.attemptsTo(NapCMTask.performTask(actor, dataTable, taskType));
//                actor.attemptsTo(Ensure.that(GeneralForm.DIALOG_SUCCESS).isDisplayed());
                break;
            case Robot:
                break;
            default:
                break;
        }
    }

    @When("{actor} verify tagging {} apply for {string}")
    public void verifyTagging(Actor actor, TaggingType taggingType, String baseID, DataTable dataTable) throws InterruptedException {
        VerifyType verifyType = taggingType.getVerityType();
        switch (verifyType) {
            case DB -> {
                DatabaseTask dbTask = new DatabaseTask(dataTable);
                dbTask.performDBTask(baseID);
            }
            case UI -> {
                VerifyTask verifyTask = new VerifyTask(taggingType, dataTable);
                actor.wasAbleTo(NavigateTo.LoginPage());
                actor.attemptsTo(CommonTask.login());
                actor.attemptsTo(verifyTask.performTask(actor, taggingType, baseID));
            }
        }
    }
}
