package stepDefinitions;

import com.github.javafaker.Faker;
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
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.Login;
import pages.Registration;
import utility.Utility;

import java.io.IOException;
import java.time.Duration;

public class RegistrationStepDefinitions {
    Faker faker = new Faker();
    WebDriver driver;
    WebDriverWait wait;
    Login login;
    Registration registration;
    String email = "";

    @Before
    public void beforeTest(){
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/driver/chromedriver.exe");
        //System.setProperty("webdriver.chrome.driver", "./src/test/resources/grid-2/chromedriver");
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
    @Given("User visits the website for RegistrationStepDefinitions")
    public void user_visits_the_website_for_registration_step_definitions() {
        driver.get("http://automationpractice.com/index.php");
    }
    @Given("clicks on sign-in for RegistrationStepDefinitions")
    public void clicks_on_sign_in_for_registration_step_definitions() {
        driver.findElement(By.className("login")).click();
    }
    @When("user enters valid email-address for account creation")
    public void user_enters_valid_email_address_for_account_creation() {
        String firstName = faker.name().firstName();
        email= firstName.toLowerCase()+"@mailinator.com";
        driver.findElement(By.id("email_create")).sendKeys(email);

    }
    @When("clicks on Create an account")
    public void clicks_on_create_an_account() throws InterruptedException {
        driver.findElement(By.id("SubmitCreate")).click();
        Thread.sleep(7000);
    }
    @Then("user redirects to Create account page")
    public void user_redirects_to_create_account_page() {
        String text = driver.findElement(By.xpath("//h1[@class='page-heading']")).getText();
        Assert.assertEquals(text,"CREATE AN ACCOUNT");
    }
    @Then("user clicks register without filling any fields")
    public void user_clicks_register_without_filling_any_fields() {
        driver.findElement(By.id("submitAccount")).click();
    }
    @Then("gets error message")
    public void gets_error_message() {
        String text = driver.findElement(By.xpath("//p[contains(text(),'There are 8 errors')]")).getText();
        Assert.assertEquals(text,"There are 8 errors");
    }
    @Then("user clicks register after filling all fields")
    public void user_clicks_register_after_filling_all_fields() throws IOException, ParseException, InterruptedException {
        registration = new Registration(driver);
        registration.doRegistration();
    }
    @Then("user successfully logs in and do sign out")
    public void user_successfully_logs_in_and_do_sign_out() {
        driver.findElement(By.xpath("//a[@class='logout']"));
    }
}
