package poms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class MatrixPages {

    public WebDriver driver;

    public MatrixPages(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this); // pre-built class from selenium
    }

    @FindBy(xpath = "//button[contains(text(), 'Create A new Requirements Matrix')]")
    public WebElement createNewMatrix;

    @FindBy(xpath = "//input[@name='title' and @type='text']")
    public WebElement matrixTitle;

    @FindBy(xpath = "//td/input[@placeholder='User Story' and @type='text']")
    public WebElement matrixStory;

    @FindBy(xpath = "//td/input[@placeholder='Note' and @type='text']")
    public WebElement matrixNote;

    @FindBy(xpath = "//fieldset/button[text()[contains(., 'Add Requirement')]]")
    public WebElement addRequirement;

    @FindBy(xpath = "//button[text()[contains(., 'Create Matrix')]]")
    public WebElement createMatrix;

    @FindBy(xpath = "//li[1]//td[6]/button")
    public WebElement editButton;

    @FindBy(xpath = "//li[1]//div/button")
    public WebElement saveButton;

    @FindBy(xpath = "//li[1]//button")
    public WebElement showButton;

    @FindBy(xpath = "//input[@list='testlist']")
    public WebElement testcaseInput;

    @FindBy(xpath = "//ul[1]//button[text()[contains(.,'Add')]]")
    public WebElement testcaseAddButton;
    @FindBy(xpath = "//input[@list='defectlist']")
    public WebElement defectInput;

    @FindBy(xpath = "//ul[2]//button[text()[contains(.,'Add')]]")
    public WebElement defectAddButton;
}
