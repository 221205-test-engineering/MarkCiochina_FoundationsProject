package stepsfiles.defectreport;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import poms.*;
import runners.DefectReportRunner;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static runners.DefectReportRunner.defectSoftAssert;
import static stepsfiles.generalfunctions.GeneralFunctions.standardWait;
import static stepsfiles.generalfunctions.GeneralFunctions.elemIsDisplayed;
import static stepsfiles.generalfunctions.GeneralFunctions.alertWait;


public class DefectReportImpl {

    public static WebDriver driver = DefectReportRunner.driver;
    public static String[] defectIdAndDesc = new String[2];
    LoginPage loginPage = new LoginPage(driver, "https://bugcatcher-dan.coe.revaturelabs.com/?dev=24");
    HomePages homePage = new HomePages(driver);
    DefectReportPages defectReportPage = new DefectReportPages(driver);

    @Given("The manager is logged in as a manager")  //REDUNDANT WITH OTHER STEPS, REMOVE FOR FINAL PRODUCT
    public void the_manager_is_logged_in_as_a_manager(){
        driver.get("https://bugcatcher-dan.coe.revaturelabs.com/?dev=24");
        standardWait(driver, loginPage.loginButton);
        loginPage.usernameInput.sendKeys("g8tor");
        loginPage.passwordInput.sendKeys("chomp!");
        loginPage.loginButton.click();
        standardWait(driver, homePage.navBar);
    }
    @Given("The manager is on the home page")
    public void the_manager_is_on_the_home_page() {
        if(!driver.getCurrentUrl().contains("managerhome")){
            defectSoftAssert.fail("Manager home page is incorrect: does not contain 'managerhome' in url");
             Assert.fail();
        }
    }
    @Then("The manager should see pending defects")
    public void the_manager_should_see_pending_defects(){
        if(elemIsDisplayed(defectReportPage.defectDescHeader) == "yes" &&
                elemIsDisplayed(defectReportPage.defectTableDatum) == "yes"){
        } else if(elemIsDisplayed(defectReportPage.defectDescHeader) == "yes" &&
                elemIsDisplayed(defectReportPage.defectTableDatum) == "no"){
            defectSoftAssert.fail("No defects in pending defects table - code will not properly execute");
            Assert.fail(); //can leave in place becase soft assert will produce errors later, not due to lack of functionality
        } else {
            defectSoftAssert.fail("Pending defects table not visible");
            Assert.fail();
        }
    }
    @When("The manager clicks on the select button for a defect")
    public void the_manager_clicks_on_the_select_button_for_a_defect() {
        defectReportPage.selectDefect.click();
        defectIdAndDesc[0] = driver.findElement(By.xpath("//td[1]")).getText(); //store id
        defectIdAndDesc[1] = driver.findElement(By.xpath("//td[2]")).getText(); //store description
    }
    @Then("The defect description should appear in bold")
    public void the_defect_description_should_appear_in_bold() {
        new FluentWait<WebDriver>(driver).withTimeout(Duration.ofMillis(6000)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4")));
        if(driver.findElement(By.xpath("//h4")).getText() //search path checks if bold through 'h4' tag
                .equals(defectIdAndDesc[1])){
        } else {
            defectSoftAssert.fail("Defect description not visible or not bold");
            //Assert.fail();
        }
    }
    @When("The manager selects a tester from the drop down list")
    public void the_manager_selects_a_tester_from_the_drop_down_list() {
        defectReportPage.defectAssignInput.sendKeys("ryeGuy");
    }
    @When("The manager clicks assign")
    public void the_manager_clicks_assign() {
        defectReportPage.defectAssignButton.click();
    }
    @Then("The defect should disappear from the list")
    public void the_defect_should_disappear_from_the_list(){
        new FluentWait<WebDriver>(driver).withTimeout(Duration.ofMillis(6000)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//h4")));
        for(WebElement id : defectReportPage.defectIDs){
            if(id.getText().equals(defectIdAndDesc[0])){ //check by ID
                defectSoftAssert.fail("Pending defect did not disappear from list");
                Assert.fail();
            }
        }
    }
    @Given("The tester is on the Home Page")
    public void the_tester_is_on_the_home_page(){
        driver.get("https://bugcatcher-dan.coe.revaturelabs.com/?dev=24");
        standardWait(driver, loginPage.loginButton);
        loginPage.usernameInput.sendKeys("ryeGuy");
        loginPage.passwordInput.sendKeys("coolbeans");
        loginPage.loginButton.click();
    }
    @Then("The tester can can see only defects assigned to them")
    public void the_tester_can_can_see_only_defects_assigned_to_them() {
        standardWait(driver, defectReportPage.myDefectsHeader);
        for(WebElement id : defectReportPage.defectAssignments){
            if(!id.getAttribute("innerText").contains("ryeGuy")){
                defectSoftAssert.fail("tester can see additional defects no assigned to them");
                Assert.fail();
            }
        }
    }
    @When("The tester changes to defect to any status")
    public void the_tester_changes_to_defect_to_any_status() {
        standardWait(driver, defectReportPage.expandDefect);
        defectReportPage.expandDefect.click();
        standardWait(driver, defectReportPage.changeStatusButton);
        defectReportPage.changeStatusButton.click();
        standardWait(driver, defectReportPage.newStatusButton);
        defectReportPage.newStatusButton.click();
    }
    @Then("The tester should see the defect has a different status")
    public void the_tester_should_see_the_defect_has_a_different_status() {
        if(!defectReportPage.defectStatus.getText().equals("Shelved")){
            defectSoftAssert.fail("Defect status did not change");
            Assert.fail();
        }
    }
    @Given("The employee is on the Defect Reporter Page")
    public void the_employee_is_on_the_defect_reporter_page() {
        driver.get("https://bugcatcher-dan.coe.revaturelabs.com/?dev=24");
        standardWait(driver, loginPage.loginButton);
        loginPage.usernameInput.sendKeys("g8tor");
        loginPage.passwordInput.sendKeys("chomp!");
        loginPage.loginButton.click();
        standardWait(driver, homePage.navBar);
        driver.findElement(By.xpath("//nav/a[3]")).click();
    }

    @When("The employee selects todays date")
    public void the_employee_selects_todays_date() {
        standardWait(driver, defectReportPage.dateBox);
        LocalDate now = LocalDate.now();
        DateTimeFormatter mdy = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String today = now.format(mdy);
        defectReportPage.dateBox.sendKeys(today);
    }
    @When("The employee types in Description with")
    public void the_employee_types_in_description_with(String docString) {
        defectReportPage.defectDescriptionInput.sendKeys(docString);
    }
    @When("The employee types in Steps with")
    public void the_employee_types_in_steps_with(String docString) {
        defectReportPage.defectStepsInput.sendKeys(docString);
    }
    @When("The employee selects {string} priority")
    public void the_employee_selects_priority(String string) {
        if(string.equals("LOW")){
            defectReportPage.prioritySlider.sendKeys(Keys.ARROW_LEFT);
            defectReportPage.prioritySlider.sendKeys(Keys.ARROW_LEFT);
        } else if(string.equals("Medium")){
            defectReportPage.prioritySlider.sendKeys(Keys.ARROW_LEFT);
        } else if(string.equals("High")){
        }
    }
    @When("The employee selects {string} severity")
    public void the_employee_selects_severity(String string){
        if(string.equals("LOW")){
            defectReportPage.severitySlider.sendKeys(Keys.ARROW_LEFT);
            defectReportPage.severitySlider.sendKeys(Keys.ARROW_LEFT);
        } else if(string.equals("Medium")){
            defectReportPage.severitySlider.sendKeys(Keys.ARROW_LEFT);
        } else if(string.equals("High")){
        }
    }
    @When("The employee clicks the report button")
    public void the_employee_clicks_the_report_button() {
        defectReportPage.reportButton.click();
    }
    @Then("There should be a confirmation box")
    public void there_should_be_a_confirmation_box() {
        if(alertWait(driver).equals("Alert not present")){
            defectSoftAssert.fail("Expected confirmation box did not appear");
            Assert.fail();
        }
    }
    @When("The employee clicks Ok")
    public void the_employee_clicks_ok() {
        driver.switchTo().alert().accept();
    }
    @Then("A modal should appear with a Defect ID")
    public void a_modal_should_appear_with_a_defect_id() {
        try {
            standardWait(driver, defectReportPage.modalDefectID);
        } catch (Exception e){
            defectSoftAssert.fail("Modal did not appear");
            Assert.fail();
        }
    }
    @When("The employee clicks close")
    public void the_employee_clicks_close() {
        defectReportPage.modalCloseButton.click();
    }
    @Then("The modal should disappear")
    public void the_modal_should_disappear(){
        try {
            standardWait(driver, defectReportPage.reportButton);
        } catch (Exception e){
            defectSoftAssert.fail("Modal did not disappear");
            Assert.fail();
        }
    }
    @When("The employee selects high priority")
    public void the_employee_selects_high_priority() {
    }
    @When("The employee selects low severity")
    public void the_employee_selects_low_severity() {
        defectReportPage.severitySlider.sendKeys(Keys.ARROW_LEFT);
        defectReportPage.severitySlider.sendKeys(Keys.ARROW_LEFT);
    }
    @Then("No confirmation dialog appears")
    public void no_confirmation_dialog_appears() {
        if(!alertWait(driver).equals("Alert not present")){
            driver.switchTo().alert().accept(); //clear alerts and modal for next tests
            standardWait(driver, defectReportPage.modalCloseButton);
            defectReportPage.modalCloseButton.click();
            defectSoftAssert.fail("Confirmation dialog should not appear");
            Assert.fail(); // confirmation should not appear
        }
    }
}
