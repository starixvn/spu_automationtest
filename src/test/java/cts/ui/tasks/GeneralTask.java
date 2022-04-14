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
    private static LinkedHashMap<ExternalTargetType, String> actionList = new LinkedHashMap<>();

    public static void beginPerformTask() {

    }

    public static Performable endPerformTask() {
        return Task.where("end perform task ");
    }

    public static void setActionList(TaskType taskType, DataTable dataTable) {
        List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
        List<ExternalTargetType> closingTargetList = ExternalTargetType.getTargetType(taskType);
        for (Map<String, String> map : list) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                ExternalTargetType target = ExternalTargetType.getTargetType(key, taskType, value);
                if (target.getPrepareMode()) {
                    GeneralTask.actionList.put(target, String.join(",", DataPrepareTask.getData(value.toString())));
                } else {
                    GeneralTask.actionList.put(target, value.toString());
                }
            }
        }

        for (ExternalTargetType closingTarget : closingTargetList) {
            GeneralTask.actionList.put(closingTarget, null);
        }
    }

    public static Performable runGeneralTask(Actor actor, TaskType taskType, DataTable table) {
        ExternalTargetType target;
        Object targetValue;
        setActionList(taskType, table);
        beginPerformTask();
        for (Map.Entry<ExternalTargetType, String> entry : actionList.entrySet()) {
            System.out.println();
            target = entry.getKey();
            targetValue = entry.getValue();
            switch (target.getActionType()) {
                case Select -> Task.where(String.format("perform %s %s", target.getActionType(), target.getTargetName()),
                                Click.on(GeneralForm.dropdown_icon(target)),
                                Click.on(GeneralForm.dropdown_list(targetValue.toString()))).
                        performAs(actor);
                case EnterValue -> Task.where(String.format("perform %s value %s into %s", target.getActionType(), targetValue, target.getTargetName()),
                                Enter.theValue(targetValue.toString()).into(target.getTargetLocated())).
                        performAs(actor);
                case Click -> Task.where(String.format("perform %s %s", target.getActionType(), target.getTargetName()),
                                Click.on(target.getTargetLocated())).
                        performAs(actor);
            }
        }
        return endPerformTask();
    }
}
