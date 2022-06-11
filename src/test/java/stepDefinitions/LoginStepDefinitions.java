package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utility.Utility;

//import utility.Utility;

import java.io.IOException;
import java.time.Duration;

public class LoginStepDefinitions {
    public WebDriver driver;
    @Before
    public void beforeTest(){
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/driver/chromedriver.exe");
//        System.setProperty("webdriver.chrome.driver", "./src/test/resources/grid-2/chromedriver");
        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--headless");
        driver = new ChromeDriver(ops);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
    }
    @After
    public void afterEveryStep(Scenario scenario) throws IOException {
        if(scenario.isFailed()){
            Utility utility = new Utility(driver);
            utility.takeScreenShot(scenario.getName());
        }
        driver.quit();
    }
    @Given("User visits the website for LoginStepDefinitions")
    public void user_visits_the_website_for_login_step_definitions() {
        driver.get("http://automationpractice.com/index.php");
    }
    @Given("clicks on sign-in for LoginStepDefinitions")
    public void clicks_on_sign_in_for_login_step_definitions() {
        driver.findElement(By.xpath("//a[@class='login']")).click();
    }

    @Then("User redirects to Authenticate page")
    public void user_redirects_to_authenticate_page() {
        String text = driver.findElement(By.xpath("//h1[@class='page-heading']")).getText();
        Assert.assertEquals(text,"AUTHENTICATION");
    }
    @When("User enters valid email-address")
    public void user_enters_valid_email_address() {
        driver.findElement(By.id("email")).sendKeys("mercedez@mailinator.com");
    }
    @When("enters valid password")
    public void enters_valid_password() {
        driver.findElement(By.id("passwd")).sendKeys("P@ssword123");
    }
    @When("clicks on Sign in to log in")
    public void clicks_on_sign_in_to_log_in() {
        driver.findElement(By.id("SubmitLogin")).click();
    }
    @Then("User can log in successfully")
    public void user_can_log_in_successfully() {
        String text = driver.findElement(By.xpath("//h1[@class='page-heading']")).getText();
        Assert.assertEquals(text,"MY ACCOUNT");
    }
    @When("enters invalid password")
    public void enters_invalid_password() throws IOException, ParseException {
        driver.findElement(By.id("passwd")).sendKeys("P@ssword12");
    }
    @Then("User cannot login")
    public void user_cannot_login() {
        String errorMessage = driver.findElement(By.xpath("//li[contains(text(),'Authentication failed.')]")).getText();
        Assert.assertEquals(errorMessage,"Authentication failed.");
    }
    @When("User enters invalid email-address")
    public void user_enters_invalid_email_address() {
        driver.findElement(By.id("email")).sendKeys("mercede@mailinator.com");
    }

}
