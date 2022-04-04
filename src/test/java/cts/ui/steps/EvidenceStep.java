package cts.ui.steps;

import cts.ui.actions.Sleep;
import cts.ui.model.Category;
import cts.ui.model.OverWriteType;
import cts.ui.model.Page;
import cts.ui.questions.DisplayedArticle;
import cts.ui.tasks.accountProfile.AccountProfileTask;
import cts.ui.tasks.accountProfile.AccountProfileForm;
import cts.ui.tasks.evidence.EvidenceForm;
import cts.ui.tasks.evidence.EvidenceTask;
import cts.ui.tasks.common.LoginAccount;
import cts.ui.tasks.common.NavigateForm;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lib.Database;
import net.serenitybdd.screenplay.Actor;
import cts.ui.tasks.common.NavigateTo;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.conditions.Check;
import net.serenitybdd.screenplay.ensure.Ensure;
import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matchers;
import org.openqa.selenium.Keys;

import java.sql.SQLException;
import java.time.Duration;
import java.util.List;
import java.util.Locale;

public class EvidenceStep {

    @Given("{actor} login with account {string} {string}")
    public void login(Actor actor, String username, String password) {
        actor.wasAbleTo(NavigateTo.LoginPage());
        actor.attemptsTo(LoginAccount.with(username, password));
    }

    @Given("{actor} navigate to the Evidence Management page")
    public void userNavigateToThePage(Actor actor) {
        actor.attemptsTo(NavigateTo.evidenceManagementPage());
    }

    @When("{actor} upload evidence file {string} with {} type on {} page")
    public void importEvidence(Actor actor, String fileName, OverWriteType overwrite, Page page) {
        actor.attemptsTo(
                Check.whether(page == Page.EvidenceManagement).andIfSo(NavigateTo.evidenceManagementPage()),
                EvidenceTask.importEvidenceFile(fileName, overwrite));
    }

    @Then("{actor} should see upload evidence message error")
    public void shouldSeeUploadEvidenceMessage(Actor actor, DataTable dataTable) {
        List<List<String>> data = dataTable.asLists(String.class);
        for (int index = 1; index < data.size(); index++) {
            actor.attemptsTo(Ensure.that(EvidenceForm.TABLE_RESULT_LINE(index)).hasText(StringUtils.join(data.get(index), " ").replace("  ", " ")));
        }
    }

    @Then("{actor} should see upload evidence successful validation")
    public void userShouldSeeEvidenceMessageSuccessful(Actor actor) {
        actor.attemptsTo(
                Ensure.that(EvidenceForm.DIALOG_TITLE_SUCCESS.waitingForNoMoreThan(Duration.ofMillis(Category.MEDIUM_WAITING))).isDisplayed()
        );
    }

    @And("{actor} remove Evidence")
    public void userRemoveEvidence(Actor actor, DataTable dataTable) {
        List<String> data = dataTable.asList(String.class);
        for (int index = 1; index < data.size(); index++) {
            actor.attemptsTo(
                    Enter.theValue(String.valueOf(data.get(index))).into(NavigateForm.searchBox()).thenHit(Keys.ENTER),
//                    Ensure.that(AccountProfileForm.INFOLOADING_ICON.waitingForNoMoreThan(Duration.ofMillis(Category.MEDIUM_WAITING))).isNotDisplayed(),
                    Ensure.that(AccountProfileForm.LOADING_ICON.waitingForNoMoreThan(Duration.ofMillis(Category.MEDIUM_WAITING))).isNotDisplayed(),
                    Check.whether(DisplayedArticle.articleText(AccountProfileForm.FLAG_EVIDENCE), Matchers.equalTo("None")).otherwise(AccountProfileTask.removeEvidence().then(NavigateTo.HomePage()))
            );
        }
    }

    @And("{actor} remove all Evidence")
    public void userRemoveAllEvidence(Actor actor, DataTable dataTable) throws InterruptedException {
        List<String> data = dataTable.column(0);
        actor.attemptsTo(
                NavigateTo.evidenceManagementPage(),
                NavigateTo.tabName(Category.REMOVE_EVIDENCE),
                EvidenceTask.removeAllEvidence(data),
                Ensure.that(NavigateForm.DIALOG_SUCCESS.waitingForNoMoreThan(Duration.ofMillis(Category.MEDIUM_WAITING))).isDisplayed()
        );
        actor.attemptsTo(Sleep.By(Category.LONG_WAITING * 6));
    }

    @Then("{actor} should see IsProcess is equal to 1 during 1 minute on CustEvidenceFromFile table")
    public void weShouldSeeIsProcessIsEqualToAfterWaitingTimeSeconds(Actor actor, DataTable dataTable) throws SQLException, InterruptedException {
        Database db = new Database();
        List<List<String>> data = dataTable.asLists(String.class);
        String CTSCustID_CTSCustomer = null;
        String IsProcessed_CustEvidenceFromFile;

        actor.attemptsTo(Sleep.By(6000));
        for (int index = 1; index < data.size(); index++) {
            CTSCustID_CTSCustomer = db.getData(String.format("SELECT CTSCustID FROM CTS_DataCenter.CTSCustomer where UserName = '%s'", data.get(index).get(0)));
            IsProcessed_CustEvidenceFromFile = db.getData(String.format("SELECT IsProcessed FROM CTS_DataCenter.CustEvidenceFromFile where CTSCustID = '%s'", CTSCustID_CTSCustomer));
            actor.attemptsTo(Ensure.that(IsProcessed_CustEvidenceFromFile).isEqualTo("0"));
        }

        for (int time = 0; time <= (60000 - 6000) / Category.SHORT_WAITING; time++) {
            actor.attemptsTo(Sleep.By(Category.SHORT_WAITING));
            IsProcessed_CustEvidenceFromFile = db.getData(String.format("SELECT IsProcessed FROM CTS_DataCenter.CustEvidenceFromFile where CTSCustID = '%s'", CTSCustID_CTSCustomer));
            if (IsProcessed_CustEvidenceFromFile.equals("1")) {
                break;
            }
        }

        for (int index = 1; index < data.size(); index++) {
            CTSCustID_CTSCustomer = db.getData(String.format("SELECT CTSCustID FROM CTS_DataCenter.CTSCustomer where UserName = '%s'", data.get(index).get(0)));
            IsProcessed_CustEvidenceFromFile = db.getData(String.format("SELECT IsProcessed FROM CTS_DataCenter.CustEvidenceFromFile where CTSCustID = '%s'", CTSCustID_CTSCustomer));
            actor.attemptsTo(Ensure.that(IsProcessed_CustEvidenceFromFile).isEqualTo("1"));
        }
    }

    @And("{actor} should see mapping EvidenceID, CategoryID on CustEvidence, CustEvidenceFromFile, CTSCustomerClassification table")
    public void weShouldSeeMappingCorrect(Actor actor, DataTable dataTable) throws SQLException {
        Database db = new Database();
        String CTSCustID_CTSCustomer;
        String EvidenceID_CustEvidence;
        String EvidenceID_CustEvidenceFromFile;
        String CategoryID_CTSCustomerClassification;

        List<List<String>> data = dataTable.asLists(String.class);
        for (int index = 1; index < data.size(); index++) {
            CTSCustID_CTSCustomer = db.getData(String.format("SELECT CTSCustID FROM CTS_DataCenter.CTSCustomer where UserName = '%s'", data.get(index).get(0)));
            EvidenceID_CustEvidence = db.getData(String.format("SELECT EvidenceID FROM CTS_DataCenter.CustEvidence where CTSCustID = '%s'", CTSCustID_CTSCustomer));
            EvidenceID_CustEvidenceFromFile = db.getData(String.format("SELECT EvidenceID FROM CTS_DataCenter.CustEvidenceFromFile where CTSCustID = '%s'", CTSCustID_CTSCustomer));
            CategoryID_CTSCustomerClassification = db.getData(String.format("SELECT CategoryID FROM CTS_DataCenter.CTSCustomerClassification where CTSCustID = '%s' and Remark = 'Synced by Evidence'", CTSCustID_CTSCustomer));
            actor.attemptsTo(Ensure.that(EvidenceID_CustEvidence).isEqualTo(data.get(index).get(1)));
            actor.attemptsTo(Ensure.that(EvidenceID_CustEvidenceFromFile).isEqualTo(data.get(index).get(1)));
            if (data.get(index).get(2) != null) {
                actor.attemptsTo(Ensure.that(CategoryID_CTSCustomerClassification).isEqualTo(data.get(index).get(2)));
            } else {
                actor.attemptsTo(Ensure.that(CategoryID_CTSCustomerClassification).isNullOrEmpty());
            }
        }
    }

    @And("{actor} should see categoriesSummary, Class, Flag Evidence, Customer Classification on Account Profile")
    public void weShouldSeeCategoriesClassIDFlagEvidenceAreCorrect(Actor actor, DataTable dataTable) throws InterruptedException {
        List<List<String>> data = dataTable.asLists(String.class);
        actor.attemptsTo(Click.on(EvidenceForm.CLOSE_BUTTON));

        for (int index = 1; index < data.size(); index++) {
            actor.attemptsTo(Enter.theValue(String.valueOf(data.get(index).get(0))).into(NavigateForm.searchBox()).thenHit(Keys.ENTER));
            actor.attemptsTo(Sleep.By(Category.MEDIUM_WAITING));
            /*
            verify category, class, flag evidence
            * */
            if (data.get(index).get(1) != null) {
                actor.attemptsTo(Ensure.that(AccountProfileForm.CATEGORY.waitingForNoMoreThan(Duration.ofMillis(Category.LONG_WAITING))).isDisplayed());
                Ensure.that(AccountProfileForm.CATEGORY.waitingForNoMoreThan(Duration.ofMillis(Category.SHORT_WAITING))).hasText(data.get(index).get(1).toUpperCase(Locale.ROOT));
            } else {
                actor.attemptsTo(Ensure.that(AccountProfileForm.CATEGORY).isNotDisplayed());
            }

            if (data.get(index).get(2) != null) {
                actor.attemptsTo(Ensure.that(AccountProfileForm.CLASS.waitingForNoMoreThan(Duration.ofMillis(Category.MEDIUM_WAITING))).hasText(data.get(index).get(2)));
            } else {
                actor.attemptsTo(Ensure.that(AccountProfileForm.CLASS).text().isEqualTo(""));
            }

            actor.attemptsTo(
                    Ensure.that(AccountProfileForm.FLAG_EVIDENCE).hasText(data.get(index).get(3)),
                    NavigateTo.tabName(Category.CHAINS_EVIDENCE_EXCEPTIONS));
            actor.attemptsTo(Sleep.By(Category.SHORT_WAITING * 2));
            /*
            verify Flagged Evidence table name, comment
            * */
            actor.attemptsTo(
                    Ensure.that(AccountProfileForm.EVIDENCE_TABLE_NAME.waitingForNoMoreThan(Duration.ofMillis(Category.MEDIUM_WAITING))).isDisplayed(),
                    Ensure.that(AccountProfileForm.EVIDENCE_TABLE_NAME).hasText(data.get(index).get(3) + " " + data.get(index).get(5)),
                    Ensure.that(AccountProfileForm.EVIDENCE_TABLE_COMMENT).hasText(data.get(index).get(4)),
                    NavigateTo.tabName(Category.CUSTOMER_CLASSIFICATION));
            actor.attemptsTo(Sleep.By(Category.SHORT_WAITING * 2));
            /*
            verify Customer Classification category, Classification
            * */
            if (data.get(index).get(6) != null) {
                actor.attemptsTo(
                        Ensure.that(AccountProfileForm.CUSTOMER_TABLE_CATEGORY).text().isEqualTo(data.get(index).get(6)),
                        Ensure.that(AccountProfileForm.CUSTOMER_TABLE_CUSTOMERCLASS).text().isEqualTo(data.get(index).get(7))
                );
            } else {
                actor.attemptsTo(Ensure.that(AccountProfileForm.CUSTOMER_TABLE_CATEGORY).isNotDisplayed());
            }
        }
    }

    @And("{actor} should see mapping EvidenceID on CustEvidence table with {string}")
    public void userShouldSeeMappingEvidenceIDOnCustEvidenceTable(Actor actor, String userName, DataTable dataTable) throws SQLException {
        Database db = new Database();
        String CTSCustID_CTSCustomer;
        List<List<String>> data = dataTable.asLists(String.class);
        CTSCustID_CTSCustomer = db.getData(String.format("SELECT CTSCustID FROM CTS_DataCenter.CTSCustomer where UserName = '%s'", userName));
//        ResultSet evidence_CustEvidence = db.exeQuery(String.format("SELECT EvidenceID FROM CTS_DataCenter.CustEvidence where CTSCustID = '%s'", CTSCustID_CTSCustomer));
//        ResultSet evidence_CustEvidenceFromFile = db.exeQuery(String.format("SELECT EvidenceID FROM CTS_DataCenter.CustEvidenceFromFile where CTSCustID = '%s'", CTSCustID_CTSCustomer));
//        for (int index = 1; index < data.size(); index++) {
//            evidence_CustEvidence.next();
//            actor.attemptsTo(Ensure.that(evidence_CustEvidence.getString(1)).isEqualTo(data.get(index).get(0)));
//        }
    }

    @Then("{actor} verify msg error")
    public void verifyMsgError(Actor actor, DataTable dataTable) {
        List<List<String>> data = dataTable.asLists(String.class);
        boolean hasChecklist = false;
        int Checklist_indexCol = 0;

        for (int i = 0; i < data.get(0).size(); i++) {
            if(data.get(0).get(i).equalsIgnoreCase("checklist")) {
                hasChecklist = true;
                Checklist_indexCol = i;
            }
        }

        for (int index = 1; index < data.size(); index++) {
            if (hasChecklist) {
                switch (data.get(1).get(Checklist_indexCol).toLowerCase()) {
                    case "hastext":
                        actor.attemptsTo(Ensure.that(EvidenceForm.TABLE_RESULT_LINE(index)).hasText(StringUtils.joinWith(" ", data.get(index).get(0), data.get(index).get(1), data.get(index).get(2), data.get(index).get(3)).replace("  ", " ")));
                    default:
                        actor.attemptsTo(Ensure.that(EvidenceForm.TABLE_RESULT_LINE(index)).hasText(StringUtils.joinWith(" ", data.get(index).get(0), data.get(index).get(1), data.get(index).get(2), data.get(index).get(3)).replace("  ", " ")));
                }
            }
        }
    }
}
