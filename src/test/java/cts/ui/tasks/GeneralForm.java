package cts.ui.tasks;

import cts.ui.model.external.ExternalTargetType;
import net.serenitybdd.screenplay.targets.Target;

public class GeneralForm {
    public static Target SAVE_BUTTON = Target.the("save button").locatedBy("//button[text()='Save']");
    public static Target DIALOG_SUCCESS = Target.the("message dialog").locatedBy("//*[@id='div-successInlineMessage']");

    public static Target dropdown_icon(ExternalTargetType targetType) {
        return Target.the(String.format("target %s", targetType.getTargetName())).locatedBy(targetType.getTargetLocated());
    }

    public static Target dropdown_list(String value) {
        return Target.the(String.format("dropdown item %s", value)).locatedBy(String.format("//a[contains(text(),'%s')]", value));
    }
}
