package stepsfiles.navigation;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import poms.HomePages;
import poms.LoginPage;
import runners.NavigationRunner;
import java.time.Duration;
import static runners.NavigationRunner.navigationSoftAssert;
import static stepsfiles.generalfunctions.GeneralFunctions.standardWait;

public class NavigationStepsImpl{

    public static WebDriver driver = NavigationRunner.driver;
    public LoginPage loginPage;
    public HomePages homePage;

    @Given("The manager is logged in as a manager")
    public void the_manager_is_logged_in_as_a_manager(){
        loginPage = new LoginPage(driver, "https://bugcatcher-dan.coe.revaturelabs.com/?dev=24");
        standardWait(driver, loginPage.loginButton);
        loginPage.usernameInput.sendKeys("g8tor");
        loginPage.passwordInput.sendKeys("chomp!");
        loginPage.loginButton.click();
    }
    @Given("The manager is on the home page")
    public void the_manager_is_on_the_home_page() {
        homePage = new HomePages(driver);
        standardWait(driver, homePage.welcomeNames);
        if(!homePage.returnUrl().contains("managerhome")){
            navigationSoftAssert.fail("Manage home page does not contain 'managerhome' in url");
            Assert.fail();
        }
    }
    @Then("The manager should see links for Matrices, Test Cases, Defect Reporting and Defect Overview")
    public void the_manager_should_see_links_for_matrices_test_cases_defect_reporting_and_defect_overview() {
        String allLinks = "";
        String[] linksToBeVisible = {"Matrices", "Test Cases", "Defect Reporting", "Defect Overview"};
        for(WebElement link : homePage.navLinks){
            allLinks += link.getText() + " ";
        }
        for(int i = 0; i < linksToBeVisible.length; i++){
            if(!allLinks.contains(linksToBeVisible[i])){
                navigationSoftAssert.fail("'" + linksToBeVisible[i] + "' link expected to be visible in navigation bar");
                Assert.fail();
            }
        }
    }
    @When("The manager clicks on Matrices")
    public void the_manager_clicks_on_matrices() {
        homePage.navLinks.get(0).click();
    }
    @Then("The title of the page should be Matrix Page")
    public void the_title_of_the_page_should_be_matrix_page() {
        standardWait(driver, homePage.matrixHeader);
        if(!driver.getTitle().equals("Matrix Page")){
            navigationSoftAssert.fail("Title of " + driver.getCurrentUrl() + " is " + driver.getTitle() + " not 'Matrix Page' on test: ");
        }
    }
    @When("The manager clicks the browser back button")
    public void the_manager_clicks_the_browser_back_button() {
        driver.navigate().back();
    }
    @Then("The manager should be on the home page and the title of page is Home")
    public void the_manager_should_be_on_the_home_page_and_the_title_of_page_is_home() {
        standardWait(driver, homePage.managerHeader);
        if(!driver.getTitle().equals("Home")){
            navigationSoftAssert.fail("Home page title is : " + driver.getTitle() + ". Expected to be 'Home'");
            Assert.fail();
        }
    }
    @When("The manager clicks on Test Cases")
    public void the_manager_clicks_on_test_cases() {
        homePage.navLinks.get(1).click();
    }

    @When("The manager clicks on {string}")
    public void the_manager_clicks_on(String link) {
        for(WebElement navLink : homePage.navLinks){
            if(navLink.getText().equals(link)){
                navLink.click();
                break;
            }
        }
    }
    @Then("The title of page should be {string}")
    public void the_title_of_page_should_be(String string){
        new WebDriverWait(driver, Duration.ofMillis(500)); //ugly but potentially necessary for slow loading scenarios and robust to bad inputs
        if(!string.equals(driver.getTitle())){
            navigationSoftAssert.fail("Title of " + driver.getCurrentUrl() + " is " + driver.getTitle() + " not '" + string + "' on test: AllLinksViable");
            Assert.fail();
        }
    }
}
