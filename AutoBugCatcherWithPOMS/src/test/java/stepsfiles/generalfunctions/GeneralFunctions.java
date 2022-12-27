package stepsfiles.generalfunctions;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class GeneralFunctions {

    public static void standardWait(WebDriver driver, WebElement elem) {
        new FluentWait<WebDriver>(driver).withTimeout(Duration.ofMillis(6000)).ignoring(NoSuchElementException.class).until(ExpectedConditions.visibilityOf(elem));
    }

    public static String elemIsDisplayed(WebElement elem){
        try {
            elem.isDisplayed();
            return "yes";
        } catch (Exception e){
            return "no";
        }
    }

    public static String alertWait(WebDriver driver) {
        try {
            new FluentWait<WebDriver>(driver).withTimeout(Duration.ofMillis(6000)).ignoring(NoAlertPresentException.class).until(ExpectedConditions.alertIsPresent());
            return driver.switchTo().alert().getText();
        } catch (Exception e) {
//            System.out.println("I am from the public static alertWait() method : an exception was thrown here. Alert not present. StackTrace below");
//            e.printStackTrace();
            return "Alert not present";
        }
    }
}
