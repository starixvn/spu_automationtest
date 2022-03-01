package cts.ui.tasks.accountProfile;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;

public class AccountProfileForm {
    public static Target CLASS = Target.the("flag evidence number").locatedBy("//*[@id='spanClass']");;
    public static Target FLAG_EVIDENCE = Target.the("flag evidence number").locatedBy("//*[@id='spanFlagEvidence']");
//    public static Target INFOLOADING_ICON = Target.the("InfoLoading icon").locatedBy("//*[@id='spanContentInfoLoading' and @style='visibility: hidden;']");
    public static Target LOADING_ICON = Target.the("loading icon").locatedBy("//*[@id='loading' and @style='display: none;']");
//    public static Target LOADING_ICON = Target.the("loading icon").locatedBy("//*[@id='loading']");
//    public static Target INFOLOADING_ICON = Target.the("InfoLoading icon").locatedBy("//*[@id='spanContentInfoLoading']");
    public static Target SMALL_LOADING_ICON = Target.the("small loading icon").locatedBy("//*[@class='icon-loading']");

    public static Target REMOVE_ICON = Target.the("remove Evidence icon").locatedBy("//span[@title='Remove Evidence']");
    public static Target CATEGORY = Target.the("category icon").locatedBy("//*[@id='divCustomerCategorySummary']//*[starts-with(@class, 'text-status')]");
    public static Target EVIDENCE_TABLE_NAME = Target.the("evidence table name").locatedBy("//*[@id='tblChainToEvidenceFlagged']/tbody/tr/td[1]");
    public static Target EVIDENCE_TABLE_COMMENT = Target.the("evidence table comment").locatedBy("//*[@id='tblChainToEvidenceFlagged']/tbody/tr/td[3]");
    public static Target CUSTOMER_TABLE_CATEGORY = Target.the("customer table category").locatedBy("//*[@id='customer-category-table']/tbody/tr[1]/td[2]");
    public static Target CUSTOMER_TABLE_CUSTOMERCLASS = Target.the("customer table category").locatedBy("//*[@id='customer-category-table']/tbody/tr[1]/td[3]");
    public static Target SEARCH_BOX = Target.the("search box").locatedBy("//*[@id='customer-category-table']/tbody/tr[1]/td[3]");
}
