package poms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePages {

    public WebDriver driver;

    public HomePages(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this); // pre-built class from selenium
    }

    public String returnUrl(){
        return this.driver.getCurrentUrl();
    }

    public String returnTitle(){
        return this.driver.getTitle();
    }

    @FindBy(xpath = "//nav/a")
    public WebElement navBar;

    @FindBy(xpath = "//nav/p")
    public WebElement welcomeNames;

    @FindBy(xpath = "//nav/a")
    public List<WebElement> navLinks;

    @FindBy(xpath =  "//h1[text()='Matrices']")
    public WebElement matrixHeader;

    @FindBy(xpath =  "//h1[contains(text(),'Manager')]")
    public WebElement managerHeader;
//    public static By homePageNavBar = By.xpath("//nav/a");
//
//    public static By homePageWelcomeNames = By.xpath("//nav/p");
//
//    public static By managerHomeCreateNewMatrix = By.xpath("//button[contains(text(), 'Create A new Requirements Matrix')]");
//
//    public static By managerHomeMatrixTitle = By.xpath("//input[@name='title' and @type='text']");
//
//    public static By managerHomeMatrixStory = By.xpath("//td/input[@placeholder='User Story' and @type='text']");
//
//    public static By managerHomeMatrixNote = By.xpath("//td/input[@placeholder='Note' and @type='text']");
//
//    public static By managerHomeAddRequirement = By.xpath("//fieldset/button[text()[contains(., 'Add Requirement')]]");
//
//    public static By managerHomeCreateMatrix = By.xpath("//button[text()[contains(., 'Create Matrix')]]");
}
