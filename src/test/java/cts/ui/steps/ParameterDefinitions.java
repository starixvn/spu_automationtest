package cts.ui.steps;

import cts.ui.model.EnvType;
import cts.ui.tasks.database.DataPrepareTask;
import io.cucumber.java.*;
import lib.GetProperties;
import lib.Telegram;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.thucydides.core.util.EnvironmentVariables;

import java.nio.file.Paths;

import static cts.ui.tasks.database.DataPrepareTask.preList;

@Slf4j
public class ParameterDefinitions {
    private static Telegram telegram = new Telegram();
    private static GetProperties prop = new GetProperties();
    public static EnvironmentVariables environmentVariables;
    public static EnvType envType;
    private static int successfulNumber;
    private static int unSuccessfulNumber;

    @ParameterType(".*")
    public Actor actor(String actorName) {
        return OnStage.theActorCalled(actorName);
    }

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
        if (envType == null) {
            setEnvType();
        }
    }

    @After
    public void after(Scenario scenario) {
        switch (scenario.getStatus()) {
            case PASSED:
                successfulNumber++;
                break;
            default:
                unSuccessfulNumber++;
                break;
        }
        preList = null;
    }

    @BeforeAll
    public static void beforeAll() {
        log.info("started BeforeAll1");
    }

    @AfterAll
    public static void afterAll() {
        telegram.sendMessage(String.format("Test cases executed: %d \r\nTest cases passed: %d \r\nTest cases unSuccessful: %d \r\nFull Report: %s \r\nSummary Report: %s",
                successfulNumber + unSuccessfulNumber,
                successfulNumber,
                unSuccessfulNumber,
                Paths.get(System.getProperty("user.dir"), prop.getProperty("fullReportDir")),
                Paths.get(System.getProperty("user.dir"), prop.getProperty("summaryReportDir"))));
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
