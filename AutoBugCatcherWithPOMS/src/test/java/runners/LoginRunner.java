package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/featurefiles/login", glue = "stepsfiles/login", monochrome = true)
public class LoginRunner {

    public static WebDriver driver;

    @BeforeClass
    public static void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //create a csv file to update with RTM/defect reports
        //update at the end of each Asserts statement corresponding to the feature cases/scenarios
    }

    @AfterClass
    public static void teardown(){
        driver.quit();
    }

}
