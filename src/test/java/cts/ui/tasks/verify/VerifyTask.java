package cts.ui.tasks.verify;

import cts.ui.actions.Sleep;
import cts.ui.model.Category;
import cts.ui.model.TaggingType;
import cts.ui.model.TargetVerifyType;
import cts.ui.tasks.common.NavigateTo;
import cts.ui.tasks.database.DataPrepareTask;
import io.cucumber.datatable.DataTable;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.targets.Target;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class VerifyTask {
    private Map<TargetVerifyType, String> actionList = new HashMap<>();

    public VerifyTask(TaggingType taggingType, DataTable dataTable) {
        List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> map : list) {
            actionList.put(TargetVerifyType.getTargetVerifyType(map.get("Target"), taggingType), map.get("exp"));
        }
    }

    public Performable performTask(Actor actor, TaggingType taggingType, String baseID) throws InterruptedException {
        List<String> custIDList = DataPrepareTask.getData(baseID);
        switch (taggingType.getPage()) {
            case GeneralInfo, CustomerClassificationTab:
                actor.attemptsTo(NavigateTo.toPage(taggingType.getPage(), custIDList.get(0)));
                actor.attemptsTo(Sleep.By(Category.LONG_WAITING));
                break;
            default:
                actor.attemptsTo(NavigateTo.toPage(taggingType.getPage()));
                break;
        }

        for (Map.Entry<TargetVerifyType, String> map : this.actionList.entrySet()) {
            TargetVerifyType target = map.getKey();
            Object value = map.getValue();
            switch (target.getActionType()){
                case HasText: {
                    actor.attemptsTo(Ensure.that(Target.the(target.getInputIdentify()).locatedBy(target.getTargetLocated())).hasText(value.toString()));
                    break;
                }
                default:
                    break;
            }
        }
        return Task.where("");
    }
}
