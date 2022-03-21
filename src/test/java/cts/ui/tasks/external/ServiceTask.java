package cts.ui.tasks.external;

import cts.ui.model.ServiceType;
import lib.GetProperties;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.*;
import net.serenitybdd.screenplay.conditions.Check;
import net.serenitybdd.screenplay.questions.Visibility;
import org.openqa.selenium.Keys;

public class ServiceTask {
    public static Performable loginServicePage() {
        GetProperties prop = new GetProperties();
        return Task.where("{0} open and login service ",
                Open.browserOn().thePageNamed("service.base.url"),
                Check.whether(Visibility.of(ServiceForm.LOGIN_BUTTON)).andIfSo(
                        Enter.theValue(prop.getProperty("local.service.user")).into(ServiceForm.USERNAME_FIELD),
                        Enter.theValue(prop.getProperty("local.service.password")).into(ServiceForm.PASSWORD_FIELD),
                        Click.on(ServiceForm.LOGIN_BUTTON))
        );
    }

    public static Performable startService(ServiceType serviceType) {
        return Task.where("{0} start service",
                Click.on(ServiceForm.menuItem("Triggers")),
                Enter.theValue(serviceType.toString()).into(ServiceForm.SEARCH_FIELD).thenHit(Keys.ENTER),
                HoverOverBy.over(ServiceForm.DOT_ICON).then(Click.on(ServiceForm.dotsIcon(serviceType))),
                Check.whether(Visibility.of(ServiceForm.RESUME_ICON)).
                        andIfSo(Click.on(ServiceForm.RESUME_ICON))
        );
    }
}
