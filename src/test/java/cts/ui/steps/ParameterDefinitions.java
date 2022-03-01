package cts.ui.steps;

import io.cucumber.java.*;
import lib.GetProperties;
import lib.Telegram;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import java.nio.file.Paths;

@Slf4j
public class ParameterDefinitions {
    Telegram telegram = new Telegram();
    GetProperties prop = new GetProperties();

    @ParameterType(".*")
    public Actor actor(String actorName) {
        return OnStage.theActorCalled(actorName);
    }

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @After
    public void after(Scenario scenario) {
        if (scenario.isFailed()) {
            telegram.sendMessage(String.format("Scenario \"%s\" is FAILED - Line: %s \r\nFull Report: %s \r\nSummary Report: %s",
                    scenario.getName(),
                    scenario.getLine(),
                    Paths.get(System.getProperty("user.dir"), prop.getProperty("fullReportDir")),
                    Paths.get(System.getProperty("user.dir"), prop.getProperty("summaryReportDir"))));
        }
    }
//
//    @BeforeAll
//    public void beforeall() {
//        System.out.println("before_all");
//    }
}
