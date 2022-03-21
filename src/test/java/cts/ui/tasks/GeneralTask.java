package cts.ui.tasks;

import cts.ui.model.*;
import io.cucumber.datatable.DataTable;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

import java.util.*;

public class GeneralTask {
    public Map<TargetType, String> actionList = new HashMap<>();

    public GeneralTask(DataTable dataTable) {
        List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> map : list) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                switch (TargetType.getTargetType(key)) {
                    case LevelType -> {
                        this.actionList.put(TargetType.LevelType, LevelType.getLevelType((String) value).getLevelName());
                        break;
                    }
                    case ReasonType -> {
                        this.actionList.put(TargetType.ReasonType, ReasonType.getReasonType((String) value).getReasonName());
                        break;
                    }
                    case PreventiveActionType -> {
                        this.actionList.put(TargetType.PreventiveActionType, PreventiveActionType.getPreventiveActionType((String) value).getPreventiveActionName());
                        break;
                    }
                    case Comment, Username -> {
                        this.actionList.put(TargetType.getTargetType(key), (String) value);
                        break;
                    }
                    default -> {
                        break;
                    }
                }
            }
        }
    }

    public Performable runGeneralTask(Actor actor) {
        TargetType target;
        Object targetValue;
        for (Map.Entry<TargetType, String> entry : actionList.entrySet()) {
            target = entry.getKey();
            if (target.getPrepareValue() == null){
                targetValue = entry.getValue();
            }
            else {
                targetValue = String.join(",", DataPrepare.getData(target.getPrepareValue()));
            }
            switch (target.getActionType()) {
                case Select -> actor.attemptsTo(
                        Task.where(String.format("perform %s %s", target.getActionType(), target.getTargetName())),
                        Click.on(GeneralForm.dropdown_icon(target)),
                        Click.on(GeneralForm.dropdown_list(targetValue.toString())));
                case EnterValue -> {
                    actor.attemptsTo(
                            Task.where(String.format("perform %s value %s into %s", target.getActionType(), targetValue.toString(), target.getTargetName())),
                            Enter.theValue(targetValue.toString()).into(target.getTargetLocated()));
                }
            }
        }
        return Task.where("Click on save button",
                Click.on(GeneralForm.SAVE_BUTTON));
    }
}
