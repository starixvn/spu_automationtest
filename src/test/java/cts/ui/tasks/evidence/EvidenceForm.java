package cts.ui.tasks.evidence;

import net.serenitybdd.screenplay.targets.Target;

public class EvidenceForm {
    public static Target CLOSE_BUTTON = Target.the("Close button").locatedBy("//*[@id='btnResultValidationClose']");
    public static Target YES_BUTTON = Target.the("yes button").locatedBy("//*[*[@id='confirmation']]//*[normalize-space(text())='Yes']");
    public static Target CHECK_BOX = Target.the("check box").locatedBy("//*[@id='containerCheckbox']/span");
    public static Target REMOVE_BUTTON = Target.the("remove button").locatedBy("//*[@id='btnRemoveEvidence']");
    public static Target USERNAME_REMOVE_BOX = Target.the("user name evidence remove box").locatedBy("//*[@id='spanAddUserName']/tags/span");
    public static Target UPLOAD_FIELD = Target.the("upload field").locatedBy("#inputFileUpload");
    public static Target IMPORT_BUTTON = Target.the("import button").locatedBy("#btnImport");
    public static Target DIALOG_TITLE_SUCCESS = Target.the("dialog title").locatedBy("//span[normalize-space(text())='Successful Validation']");

    public static Target TABLE_RESULT_LINE(int index) {
        return Target.the(String.format("table result line %s", index) ).locatedBy(String.format("//*[@id='tableResult']/tbody/tr[%s]", index));
    }
}