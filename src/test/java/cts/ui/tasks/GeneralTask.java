package cts.ui.tasks;

import cts.ui.model.*;
import cts.ui.model.external.ExternalTargetType;
import cts.ui.tasks.database.DataPrepareTask;
import io.cucumber.datatable.DataTable;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

import java.util.*;

public class GeneralTask {
    private Map<ExternalTargetType, List<String>> actionList = new HashMap<>();

    public GeneralTask(DataTable dataTable, TaskType taskType) {
        List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> map : list) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                ExternalTargetType target = ExternalTargetType.getTargetType(key, taskType, value);
                if (target.getPrepareMode()) {
                    this.actionList.put(target, DataPrepareTask.getData(value.toString()));
                }
                else {
                    this.actionList.put(target, Collections.singletonList(value.toString()));
                }
            }
        }
    }

    public Performable runGeneralTask(Actor actor) {
        ExternalTargetType target;
        Object targetValue;
        for (Map.Entry<ExternalTargetType, List<String>> entry : actionList.entrySet()) {
            target = entry.getKey();
            targetValue = String.join(",", new ArrayList<>(new HashSet<>(entry.getValue())));
            switch (target.getActionType()) {
                case Select -> actor.attemptsTo(
                        Task.where(String.format("perform %s %s", target.getActionType(), target.getTargetName())),
                        Click.on(GeneralForm.dropdown_icon(target)),
                        Click.on(GeneralForm.dropdown_list(targetValue.toString())));
                case EnterValue -> {
                    actor.attemptsTo(
                            Task.where(String.format("perform %s value %s into %s", target.getActionType(), targetValue, target.getTargetName())),
                            Enter.theValue(targetValue.toString()).into(target.getTargetLocated()));
                }
            }
        }
        return Task.where("Click on save button",
                Click.on(GeneralForm.SAVE_BUTTON));
    }
}
