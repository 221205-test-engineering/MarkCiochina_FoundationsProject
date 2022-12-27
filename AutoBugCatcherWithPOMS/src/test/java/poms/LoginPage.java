package poms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import runners.LoginRunner;

public class LoginPage {

    public WebDriver driver;

    public LoginPage(WebDriver driver, String website){
        this.driver = driver;
        driver.get(website);
        PageFactory.initElements(driver, this); // pre-built class from selenium
    }
    @FindBy(xpath = "//input[@name='username']")
    public WebElement usernameInput;

    @FindBy(xpath = "//input[@name='pass']")
    public WebElement passwordInput;
//
    @FindBy(xpath="//button")
    public WebElement loginButton;



}
