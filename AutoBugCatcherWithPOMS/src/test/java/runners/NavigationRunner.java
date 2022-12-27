package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.SoftAssertions;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.Scenario;

import java.util.ArrayList;
import java.util.List;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/featurefiles/navigation", glue = "stepsfiles/navigation")
public class NavigationRunner {

    public static WebDriver driver;
    public static SoftAssertions navigationSoftAssert;

    @BeforeClass
    public static void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        navigationSoftAssert = new SoftAssertions();
    }

    @AfterClass
    public static void teardown(){
        driver.quit();
        navigationSoftAssert.assertAll();
    }
}
