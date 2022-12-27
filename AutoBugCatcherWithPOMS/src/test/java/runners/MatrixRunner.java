package runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.SoftAssertions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/featurefiles/matrix", glue = "stepsfiles/matrix")
public class MatrixRunner{

    public static WebDriver driver;

    public static SoftAssertions matrixSoftAssert = new SoftAssertions();

    @BeforeClass()
    public static void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }
    @AfterClass()
    public static void teardown(){
        driver.quit();
        matrixSoftAssert.assertAll();
    }
}
