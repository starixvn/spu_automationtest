package cts.ui.tasks.evidence;

import cts.ui.model.OverWriteType;
import cts.ui.tasks.navigation.NavigateForm;
import lib.GetProperties;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Upload;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Keys;

import java.nio.file.Paths;
import java.util.List;

public class EvidenceTask {
    static GetProperties prop = new GetProperties();

    public static Performable importEvidenceFile(String fileName, OverWriteType override) {
        if (override == OverWriteType.Overwrite) {
            return Task.where("{0} import evidence file name: " + fileName + "with overwrite type",
                    Upload.theFile(Paths.get(System.getProperty("user.dir"), String.format(prop.getProperty("testDataDir") + "%s", fileName))).to(EvidenceForm.UPLOAD_FIELD),
                    Click.on(EvidenceForm.CHECK_BOX),
                    Click.on(EvidenceForm.YES_BUTTON),
                    Click.on(EvidenceForm.IMPORT_BUTTON));
        } else {
            return Task.where("{0} import evidence file name: " + fileName + "with not overwrite type",
                    Upload.theFile(Paths.get(System.getProperty("user.dir"), String.format(prop.getProperty("testDataDir") + "%s", fileName))).to(EvidenceForm.UPLOAD_FIELD),
                    Click.on(EvidenceForm.IMPORT_BUTTON));
        }
    }

    public static Performable removeAllEvidence(List<String> data) {
        String userNameList = StringUtils.join(data.subList(1, data.size()), " ");
        return Task.where("{0} remove all evidence",
                Enter.theValue(userNameList).into(EvidenceForm.USERNAME_REMOVE_BOX).thenHit(Keys.ENTER),
                Click.on(EvidenceForm.USERNAME_REMOVE_BOX),
                Click.on(EvidenceForm.REMOVE_BUTTON).afterWaitingUntilEnabled(),
                Click.on(NavigateForm.CONFIRM_BUTTON).afterWaitingUntilPresent()
        );
    }
}

