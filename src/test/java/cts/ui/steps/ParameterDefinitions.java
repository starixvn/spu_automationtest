package cts.ui.steps;

import cts.ui.model.EnvType;
import io.cucumber.java.*;
import lib.GetProperties;
import lib.Telegram;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.util.EnvironmentVariables;

import java.nio.file.Paths;

@Slf4j
public class ParameterDefinitions {
    private Telegram telegram = new Telegram();
    private GetProperties prop = new GetProperties();
    public static EnvironmentVariables environmentVariables;
    public static EnvType envType;
//    public

    @ParameterType(".*")
    public Actor actor(String actorName) {
        return OnStage.theActorCalled(actorName);
    }

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
        if(envType == null){setEnvType();}
    }

    @After
    public void after(Scenario scenario) {
//        if (scenario.isFailed()) {
//            telegram.sendMessage(String.format("Scenario \"%s\" is FAILED - Line: %s \r\nFull Report: %s \r\nSummary Report: %s",
//                    scenario.getName(),
//                    scenario.getLine(),
//                    Paths.get(System.getProperty("user.dir"), prop.getProperty("fullReportDir")),
//                    Paths.get(System.getProperty("user.dir"), prop.getProperty("summaryReportDir"))));
//        }
    }

    @BeforeAll
    public static void beforeAll() {
        log.info("started BeforeAll1");
    }

    @AfterAll
    public static void afterAll() {
        log.info("started AfterAll");
    }

    public static void setEnvType() {
        String webserviceEndpoint = EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("webdriver.base.url");
        if (webserviceEndpoint.equals(EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("environments.local1.webdriver.base.url"))) {
            ParameterDefinitions.envType = EnvType.Local1;
        } else if (webserviceEndpoint.equals(EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("environments.local2.webdriver.base.url"))) {
            ParameterDefinitions.envType = EnvType.Local2;
        } else if (webserviceEndpoint.equals(EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("environments.local3.webdriver.base.url"))) {
            ParameterDefinitions.envType = EnvType.Local3;
        } else if (webserviceEndpoint.equals(EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("environments.localmain.webdriver.base.url"))) {
            ParameterDefinitions.envType = EnvType.LocalMain;
        } else if (webserviceEndpoint.equals(EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("environments.uat.webdriver.base.url"))) {
            ParameterDefinitions.envType = EnvType.UAT;
        } else {
            ParameterDefinitions.envType = null;
        }
    }
}
