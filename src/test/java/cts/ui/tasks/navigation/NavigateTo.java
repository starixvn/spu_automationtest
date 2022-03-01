package cts.ui.tasks.navigation;

import cts.ui.model.Category;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Open;

public class NavigateTo {
    public static Performable LoginPage() {
        return Task.where("{0} open the login page",
                Open.browserOn().the(LoginPage.class));
    }

    public static Performable evidenceManagementPage() {
        return Task.where("{0} navigate to the Evidence Management page",
                Click.on(NavigateForm.menuItem(Category.CONTROLLER)).afterWaitingUntilEnabled(),
                Click.on(NavigateForm.dropdownList(Category.EVIDENCE_MANAGEMENT_ITEM)).afterWaitingUntilPresent()
        );
    }

    public static Performable HomePage() {
        return Task.where("{0} navigate to Home page",
                Click.on(NavigateForm.HOME_ICON)
        );
    }

    public static Performable tabName(String value) {
        return Task.where("{0} select tab Name",
                Click.on(NavigateForm.tabItem(value)).afterWaitingUntilEnabled());
    }

    public static Performable closeDialog() {
        return Task.where("{0} close the system message dialog",
                Click.on(NavigateForm.DIALOG_ClOSE));
    }
}
