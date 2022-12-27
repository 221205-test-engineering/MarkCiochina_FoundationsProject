package stepsfiles.login;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import poms.HomePages;
import poms.LoginPage;
import runners.LoginRunner;

import static stepsfiles.generalfunctions.GeneralFunctions.*;

public class LoginStepsImpl {

    public static WebDriver driver = LoginRunner.driver;

    public LoginPage loginPage;
    public HomePages homePage;
    @Given("The employee is on the login page")
    public void the_employee_is_on_the_login_page() {
        loginPage = new LoginPage(driver,"https://bugcatcher-dan.coe.revaturelabs.com/?dev=24");
        standardWait(driver, loginPage.loginButton);
    }
    @When("The employee types in {word} into the username input")
    public void the_employee_types_in_into_the_username_input(String username) {
         loginPage.usernameInput.sendKeys(username);
    }
    @When("The employee types in {word} into the password input")
    public void the_employee_types_in_into_the_password_input(String password) {
        loginPage.passwordInput.sendKeys(password);
    }
    @When("The employee clicks on the login button")
    public void the_employee_clicks_on_the_login_button() {
        loginPage.loginButton.click();
    }
    @Then("The employee should see an alert saying that {word} was incorrect")
    public void the_employee_should_see_an_alert_saying_that_was_incorrect(String incorrectField) {
        if(alertWait(driver).contains(incorrectField)){
            driver.switchTo().alert().accept();
        } else {
            Assert.fail();
        }
    }
    @When("The employee types {string} into username input")
    public void the_employee_types_into_username_input(String username) {
        loginPage.usernameInput.sendKeys(username);
    }
    @When("The employee types {string} into password input")
    public void the_employee_types_into_password_input(String password) {
        loginPage.passwordInput.sendKeys(password);
    }
    @Then("the employee should be on the {string} page")
    public void the_employee_should_be_on_the_page(String string){
        homePage = new HomePages(driver);
        standardWait(driver, homePage.navBar);
        if(!homePage.returnTitle().contains(string)){
            Assert.fail();
        }
    }
    @Then("The employee should see their name {string} {string} on the home page")
    public void the_employee_should_see_their_name_on_the_home_page(String string, String string2) {
        String welcomeNames = homePage.welcomeNames.getText();
        Assert.assertEquals(welcomeNames.contains(string), welcomeNames.contains(string2)); // should be comparing boolean true to boolean true
    }

}
