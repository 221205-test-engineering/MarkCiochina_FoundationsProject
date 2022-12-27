package stepsfiles.testcases;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import poms.*;
import runners.TestCasesRunner;
import java.time.Duration;
import static stepsfiles.generalfunctions.GeneralFunctions.*;

public class TestCasesStepsImpl {

    public static WebDriver driver = TestCasesRunner.driver;
    LoginPage loginPage = new LoginPage(driver, "https://bugcatcher-dan.coe.revaturelabs.com/?dev=24");
    HomePages homePage = new HomePages(driver);
    TestCasePages testCasePage = new TestCasePages(driver);

    //track values across multiple steps
    public static String[] description0steps1caseID2 = new String[3];
    public static String originalDescriptionText = "";
    public static String originalStepsDescriptionText = "";
    public static String originalSummaryText = "";

    @Given("The tester is on the test case dashboard")
    public void the_tester_is_on_the_test_case_dashboard(){
        driver.get("https://bugcatcher-dan.coe.revaturelabs.com/?dev=24");
        standardWait(driver, loginPage.loginButton);
        loginPage.usernameInput.sendKeys("ryeGuy");
        loginPage.passwordInput.sendKeys("coolbeans");
        loginPage.loginButton.click();
        standardWait(driver, homePage.navBar);
        driver.findElement(By.xpath("//nav/a[text()='Test Cases']")).click();
    }
    @When("The tester types Description into input with")
    public void the_tester_types_description_into_input_with(String docString) {
        testCasePage.descriptionInput.sendKeys(docString);
        description0steps1caseID2[0] = docString; // running variable to track testcase inputs
    }
    @When("The tester types Steps into input with")
    public void the_tester_types_steps_into_input_with(String docString) {
        testCasePage.stepsInput.sendKeys(docString);
        description0steps1caseID2[1] = docString;
    }
    @When("The tester presses the submit button")
    public void the_tester_presses_the_submit_button() {
        testCasePage.submitButton.click();
    }
    @Then("The test case should appear at the bottom of the table")
    public void the_test_case_should_appear_at_the_bottom_of_the_table(){
        String newEntry = "//tr[last()]//td[text()[contains(.,'" + description0steps1caseID2[0] + "')]]";
        try{
            new FluentWait<WebDriver>(driver).withTimeout(Duration.ofMillis(6000)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(newEntry)));
        } catch (Exception e){
            TestCasesRunner.testCasesSoftAssert.fail("New test case does not appear at bottom of table");
        }
    }
    @Then("The test case result should say UNEXECUTED")
    public void the_test_case_result_should_say_unexecuted(){
        String unex = driver.findElement(By.xpath("//td[text()[contains(.,'"+ description0steps1caseID2[0] + "')]]/../td[3]")).getText(); // find next td in row
        if(!unex.equals("UNEXECUTED")){
            TestCasesRunner.testCasesSoftAssert.fail("New test case result does not say 'UNEXECUTED'");
            Assert.fail();
        }
    }

    @When("The tester presses on details")
    public void the_tester_presses_on_details() {
        standardWait(driver, testCasePage.detailsButton);
        description0steps1caseID2[2] = driver.findElement(By.xpath("//td/../td[1]")).getText();
        description0steps1caseID2[0] = driver.findElement(By.xpath("//td/../td[2]")).getText();
        testCasePage.detailsButton.click();
    }

    @When("The tester presses on details of {string} defect")  //additional variation on starting test to check first and last test case details
    public void the_tester_presses_on_details_of_defect(String position) {
        standardWait(driver, testCasePage.detailsButton);
        description0steps1caseID2[2] = driver.findElement(By.xpath("//tr[" + position + "]//td/../td[1]")).getText();
        description0steps1caseID2[0] = driver.findElement(By.xpath("//tr[" + position + "]//td/../td[2]")).getText();
        driver.findElement(By.xpath("//tr[" + position + "]//button")).click();
    }
    @Then("A test case modal should appear showing the case ID")
    public void a_test_case_modal_should_appear_showing_the_case_id(){
        standardWait(driver, testCasePage.modal);
        if(!testCasePage.modalCaseID.getText().contains(description0steps1caseID2[2])){
            TestCasesRunner.testCasesSoftAssert.fail("Test case modal does not show correct ID. Found '" + driver.findElement(By.xpath(("//h3"))).getText() +
                    "' instead of '" + description0steps1caseID2[2] +"'");
            //Assert.fail(); // replaced with soft assert to test remaining functionality
        }
    }
    @Then("The performed by field should say No One")
    public void the_performed_by_field_should_say_no_one() {
        if(!testCasePage.modalPerformedBy.getText().equals("No One")){
            TestCasesRunner.testCasesSoftAssert.fail("Performed by field does not say 'No One'");
            Assert.fail(); //hard stop because it will not mark the test as wrong if the remaining steps are correct, and they are
        }
    }
    @When("The tester presses the close button")
    public void the_tester_presses_the_close_button() {
        testCasePage.modalCloseButton.click();
    }
    @Then("The Modal Should be closed")
    public void the_modal_should_be_closed() {
        new FluentWait<WebDriver>(driver).withTimeout(Duration.ofMillis(5000)).until(ExpectedConditions.invisibilityOf(testCasePage.modalPerformedBy));
        try{
            if(testCasePage.modalPerformedBy.isDisplayed()){
                TestCasesRunner.testCasesSoftAssert.fail("Modal is displayed when it should be closed");
                Assert.fail();
            }
        } catch (NoSuchElementException e){
            Assert.assertTrue(true);
        }
    }

    @When("The Tester clicks on edit within the modal")
    public void the_tester_clicks_on_edit_within_the_modal() {
        testCasePage.modalEditButton.click();
    }
    @Then("The Tester should be on the case editor for that case")
    public void the_tester_should_be_on_the_case_editor_for_that_case(){
        standardWait(driver, testCasePage.casePageCaseID);
        if(!testCasePage.casePageCaseID.getText().contains(description0steps1caseID2[2])){
            TestCasesRunner.testCasesSoftAssert.fail("The wrong case ID is displayed. Should be : '" + description0steps1caseID2[2] +
                    "' instead of : '" + testCasePage.casePageCaseID + "'");
            Assert.fail();
        }
    }
    @When("The tester clicks on the edit button")
    public void the_tester_clicks_on_the_edit_button() {
        standardWait(driver, testCasePage.casePageEditButton);
        testCasePage.casePageEditButton.click();
    }
    @When("The tester types in {string} into the description text area")
    public void the_tester_types_in_into_the_description_text_area(String string) {
        originalDescriptionText = testCasePage.casePageDescriptionInput.getText();
        testCasePage.casePageDescriptionInput.clear();
        testCasePage.casePageDescriptionInput.sendKeys(string);
    }
    @When("The tester types in {string} into the steps text area")
    public void the_tester_types_in_into_the_steps_text_area(String string) {
        originalStepsDescriptionText = testCasePage.casePageStepsInput.getText();
        testCasePage.casePageStepsInput.clear();
        testCasePage.casePageStepsInput.sendKeys(string);
    }
    @When("The tester clicks on the automated check mark")
    public void the_tester_clicks_on_the_automated_check_mark() {
        testCasePage.casePageCheckbox.click();
    }
    @When("The tester selects {string} for performed from drop down")
    public void the_tester_selects_for_performed_from_drop_down(String string) {
        Select dropdown = new Select(driver.findElement(testCasePage.performedByPath));
        dropdown.selectByVisibleText(string);

    }
    @When("The tester selects {string} for test result from drop down")
    public void the_tester_selects_for_test_result_from_drop_down(String string) {
        Select dropdown = new Select(driver.findElement(testCasePage.testResultPath));
        dropdown.selectByVisibleText(string);
    }
    @When("The tester types in {string} into the summary text area")
    public void the_tester_types_in_into_the_summary_text_area(String string) {
        originalSummaryText = testCasePage.casePageSummary.getText();
        testCasePage.casePageSummary.clear();
        testCasePage.casePageSummary.sendKeys(string);
    }
    @When("The tester clicks save on test case page")
    public void the_tester_clicks_save_on_test_case_page() {
        testCasePage.casePageSaveButton.click();

    }
    @Then("A confirmation prompt should appear")
    public void a_confirmation_prompt_should_appear() {
        if(alertWait(driver).equals("Alert not present")){
            TestCasesRunner.testCasesSoftAssert.fail("Confirmation prompt should have appeared but did not");
            Assert.fail();
        }
    }
    @When("The tester clicks on Ok")
    public void the_tester_clicks_on_ok() {
        driver.switchTo().alert().accept();
    }
    @Then("An alert says the test case has been saved")
    public void an_alert_says_the_test_case_has_been_saved(){
        if(!alertWait(driver).contains("Saved")){
            TestCasesRunner.testCasesSoftAssert.fail("Missing alert confirming test case has been saved");
            Assert.fail();
        } else {
            driver.switchTo().alert().accept();
        }
    }

    @When("The tester clicks on the reset button")
    public void the_tester_clicks_on_the_reset_button() {
        testCasePage.casePageResetButton.click();
    }
    @Then("The fields should be populated to their old values")
    public void the_fields_should_be_populated_to_their_old_values() {
        if(testCasePage.casePageDescriptionInput.getText().equals(originalDescriptionText)
                && testCasePage.casePageStepsInput.getText().equals(originalStepsDescriptionText)
                && testCasePage.casePageSummary.getText().equals(originalSummaryText)){
        } else {
            TestCasesRunner.testCasesSoftAssert.fail("Old values did not reset");
            Assert.fail();
        }

    }
}
