package poms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TestCasePages {

    public WebDriver driver;

    public TestCasePages(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this); // pre-built class from selenium
    }

    @FindBy(xpath = "//textarea[@name='desc']")
    public WebElement descriptionInput;

    @FindBy(xpath = "//textarea[@name='steps']")
    public WebElement stepsInput;

    @FindBy(xpath = "//button")
    public WebElement submitButton;

    @FindBy(xpath = "//tr/td[last()]/button")
    public WebElement detailsButton;

    @FindBy(xpath = "//div[@class='ReactModalPortal']//p")
    public WebElement modal;
    @FindBy(xpath = "//h3")
    public WebElement modalCaseID;

    @FindBy(xpath = "//button/a[text()='Edit']")
    public WebElement modalEditButton;

    @FindBy(xpath = "//div[3]//button[1]")
    public WebElement modalCloseButton;

    @FindBy(xpath = "//textarea[1]")
    public WebElement casePageDescriptionInput;

    @FindBy(xpath = "//textarea[2]")
    public WebElement casePageStepsInput;

    @FindBy(xpath = "//fieldset[2]/textarea")
    public WebElement casePageSummary;

    @FindBy(xpath = "//button")
    public WebElement casePageEditButton;
    @FindBy(xpath = "//h1")
    public  WebElement casePageCaseID;

    @FindBy(xpath = "//div[3]/div/div/p[6]")
    public WebElement modalPerformedBy;

    @FindBy(xpath = "//input[@type='checkbox']")
    public WebElement casePageCheckbox;

    @FindBy(xpath = "//div[@id='root']/button[2]")
    public WebElement casePageSaveButton;

    @FindBy(xpath = "//button[text()='Reset']")
    public WebElement casePageResetButton;

    public By performedByPath = By.xpath("//fieldset[1]/select");

    public By testResultPath = By.xpath("//fieldset[2]/select");



}
