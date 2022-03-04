package cts.ui.steps;

import cts.ui.model.TaggingType;
import io.cucumber.java.en.Then;
import lib.Database;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.util.EnvironmentVariables;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaggingStep {
    Database db = new Database();

    @Then("verify tagging {}")
    public void VerifyTagging(TaggingType taggingType) {

        switch (taggingType) {
            case TG_001:
                break;
        }
    }
}
