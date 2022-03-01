package cts.ui.actions;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

public class Sleep implements Task {
    public final long timeMiliSeconds;

    public Sleep(long timeMiliSeconds) throws InterruptedException {
        this.timeMiliSeconds = timeMiliSeconds;
        Thread.sleep(timeMiliSeconds);
    }

    public static Performable By(long timeMiliSeconds) throws InterruptedException {
        return new Sleep(timeMiliSeconds);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

    }
}
