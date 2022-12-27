package poms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DefectReportPages {

    public WebDriver driver;

    public DefectReportPages(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//th[contains(text(), 'desc')]")
    public WebElement defectDescHeader;

    @FindBy(xpath = "//td")
    public WebElement defectTableDatum;

    @FindBy(xpath = "//td[3]/button[1]")
    public WebElement selectDefect;

    @FindBy(xpath = "//input[@list='employees']")
    public WebElement defectAssignInput;

    @FindBy(xpath = "//button[text()='Assign']")
    public WebElement defectAssignButton;

    @FindBy(xpath = "//td[1]")
    public List<WebElement> defectIDs;

    @FindBy(xpath = "//h3")
    public WebElement myDefectsHeader;

    @FindBy(xpath = "//div/p[5]")
    public List<WebElement> defectAssignments;

    @FindBy(xpath = "//ul/li[1]//p[1]")
    public WebElement expandDefect;

    @FindBy(xpath = "//ul/li[1]//button[text()='Change Status']")
    public WebElement changeStatusButton;

    @FindBy(xpath = "//ul/li[1]//button[5]")
    public WebElement newStatusButton;

    @FindBy(xpath = "//div[@class='Collapsible']//b[2]")
    public WebElement defectStatus;
    @FindBy(xpath = "//input[@name='dateReported']")
    public WebElement dateBox;
    @FindBy(xpath = "//textarea[@name='desc']")
    public WebElement defectDescriptionInput;

    @FindBy(xpath = "//textarea[@name='stepsToReproduce']")
    public WebElement defectStepsInput;

    @FindBy(xpath = "//input[@name='priority']")
    public WebElement prioritySlider;

    @FindBy(xpath = "//input[@name='severity']")
    public WebElement severitySlider;

    @FindBy(xpath = "//button[text()='Report']")
    public WebElement reportButton;

    @FindBy(xpath = "//div[@class='ReactModalPortal']//h4")
    public WebElement modalDefectID;

    @FindBy(xpath = "//div[@class='ReactModalPortal']//button")
    public WebElement modalCloseButton;
}
