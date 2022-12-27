package stepsfiles.matrix;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import poms.*;
import runners.MatrixRunner;
import static stepsfiles.generalfunctions.GeneralFunctions.*;

import poms.LoginPage;

public class MatrixStepsImpl {

    public static WebDriver driver = MatrixRunner.driver;
    public LoginPage loginPage = new LoginPage(driver, "https://bugcatcher-dan.coe.revaturelabs.com/?dev=24");
    public HomePages homePage = new HomePages(driver);
    public MatrixPages matrixPage = new MatrixPages(driver);

    @Given("The manager is logged in as a manager")
    public void the_manager_is_logged_in_as_a_manager(){
        standardWait(driver, loginPage.loginButton);
        loginPage.usernameInput.sendKeys("g8tor");
        loginPage.passwordInput.sendKeys("chomp!");
        loginPage.loginButton.click();
    }
    @Given("The manager is on the home page")
    public void the_manager_is_on_the_home_page() {
        standardWait(driver, homePage.welcomeNames);
        if(!homePage.returnUrl().contains("managerhome")){
            MatrixRunner.matrixSoftAssert.fail("Manager Home does not contain 'managerhome' in URL");
            Assert.fail();
        }
    }

    @When("The manager chooses to create a new matrix")
    public void the_manager_chooses_to_create_a_new_matrix(){
        matrixPage.createNewMatrix.click();
    }
    @When("The manager creates a title for the matrix")
    public void the_manager_creates_a_title_for_the_matrix() {
        matrixPage.matrixTitle.sendKeys("new Matrix title");
    }
    @When("The manager adds requirements to the matrix")
    public void the_manager_adds_requirements_to_the_matrix() {
        matrixPage.matrixStory.sendKeys("User needs to do X");
        matrixPage.matrixNote.sendKeys("I am a bot lol");
        matrixPage.addRequirement.click();
    }
    @When("The manager saves the matrix")
    public void the_manager_saves_the_matrix(){
        matrixPage.createMatrix.click();
    }
    @Then("An alert with a success message should appear")
    public void an_alert_with_a_success_message_should_appear(){
        if(alertWait(driver).contains("created")){
            driver.switchTo().alert().accept();
        } else {
            MatrixRunner.matrixSoftAssert.fail("New matrix confirmation alert does not appear");
            Assert.fail();
        }
    }
    @Given("The manager is on the matrix homepage")
    public void the_manager_is_on_the_matrix_homepage(){
        driver.findElement(By.linkText("Matrices")).click();
    }
    @Given("The manager has selected the matrix")
    public void the_manager_has_selected_the_matrix(){
        standardWait(driver, matrixPage.showButton);
        matrixPage.showButton.click();
    }
    @When("The manager adds a defect")
    public void the_manager_adds_a_defect(){
        standardWait(driver, matrixPage.editButton);
        matrixPage.editButton.click();
        matrixPage.defectInput.sendKeys("999");
        matrixPage.defectAddButton.click();
    }
    @When("The manager confirms their changes")
    public void the_manager_confirms_their_changes() {
        matrixPage.saveButton.click();
    }
    @Then("Then the matrix should saved")
    public void then_the_matrix_should_saved(){
        if(!alertWait(driver).contains("Saved")){
            MatrixRunner.matrixSoftAssert.fail("Updating matrix does not produce confirmation alert that changes have been saved");
            Assert.fail();
        } else {
            driver.switchTo().alert().accept();
        }
    }
    @When("The manager adds a Test Cases")
    public void the_manager_adds_a_test_cases(){
        standardWait(driver, matrixPage.editButton);
        matrixPage.editButton.click();
        matrixPage.testcaseInput.sendKeys("777");
        matrixPage.testcaseAddButton.click();
    }
}

