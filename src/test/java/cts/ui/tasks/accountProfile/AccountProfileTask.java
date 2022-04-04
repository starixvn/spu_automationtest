package cts.ui.tasks.accountProfile;

import cts.ui.model.Category;
import cts.ui.tasks.common.NavigateForm;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

public class AccountProfileTask {
    public static Performable removeEvidence() {
        {
            return Task.where("{0} perform remove evidence in account profile",
                    Click.on(NavigateForm.tabItem(Category.CHAINS_EVIDENCE_EXCEPTIONS)),
                    Click.on(AccountProfileForm.REMOVE_ICON).afterWaitingUntilPresent(),
                    Click.on(NavigateForm.CONFIRM_BUTTON).afterWaitingUntilPresent(),
                    Click.on(NavigateForm.DIALOG_ClOSE).afterWaitingUntilPresent()
            );
        }
    }
}
