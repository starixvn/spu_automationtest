package cts.ui.tasks.external;

import cts.ui.model.ServiceType;
import net.serenitybdd.screenplay.targets.Target;

public class ServiceForm {
    static Target SEARCH_FIELD = Target.the("search field").locatedBy("#search-trigger");
    static Target USERNAME_FIELD = Target.the("user name field").locatedBy("//input[@*='userName']");
    static Target PASSWORD_FIELD = Target.the("password field").locatedBy("//input[@*='password']");
    static Target LOGIN_BUTTON = Target.the("login button").locatedBy("//button[@type='submit']");
    static Target RESUME_ICON = Target.the("resume button").locatedBy("//a[@href='/quartz/Triggers/Resume']");
    static Target DOT_ICON = Target.the("dots icon").locatedBy("//i[@class='dots icon']");

    public static Target menuItem(String value) {
        return Target.the(String.format("%s menu item", value)).locatedBy(String.format("//a[contains(@href,'%s')]", value));
    }

    public static Target pauseIcon(ServiceType serviceType) {
        return Target.the(String.format("%s pause icon", serviceType)).locatedBy(String.format("//a[@*='%s']/preceding-sibling::i[@title='Paused']", serviceType));
    }

    public static Target dotsIcon(ServiceType serviceType) {
        return Target.the(String.format("%s dots icon", serviceType)).locatedBy(String.format("//a[@*='%s']/..//button", serviceType));
    }

    public static Target serviceItem(ServiceType serviceType) {
        return Target.the(String.format("%s service Item", serviceType)).locatedBy(String.format("//a[@*='%s']", serviceType));
    }
}
