package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import utility.Utility;

import java.io.IOException;
import java.time.Duration;

public class DashboardStepDefinitions{
    public WebDriver driver;
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
    @Given("User visits the website for dashboardStepDefinitions")
    public void user_visits_the_website_for_dashboard_step_definitions() {
        driver.get("http://automationpractice.com/index.php");
    }
    @Then("User can get the title of the page")
    public void user_can_get_the_title_of_the_page() {
        String title = driver.getTitle();
//        Assert.assertEquals(title, "My Store");
        Assert.assertEquals(title, "Pant");

    }
    @When("user clicks on contact us")
    public void user_clicks_on_contact_us() throws InterruptedException {
        driver.findElement(By.xpath("//div[@id='contact-link']//a[contains(text(),'Contact us')]")).click();
    }
    @When("user redirects to contact us page")
    public void user_redirects_to_contact_us_page() {
        String text = driver.findElement(By.xpath("//h1[@class='page-heading bottom-indent']")).getText();
        Assert.assertEquals(text,"CUSTOMER SERVICE - CONTACT US");
    }
    @When("user selects a subject")
    public void user_selects_a_subject() {
        Select state = new Select(driver.findElement(By.xpath("//select[@id='id_contact']")));
        state.selectByValue("2");
    }
    @When("user inputs email address")
    public void user_inputs_email_address() {
        driver.findElement(By.id("email")).sendKeys("hector@mailinator.com");
    }
    @When("user inputs a message")
    public void user_inputs_a_message() {
        driver.findElement(By.id("message")).sendKeys("Lorem Ipsum is simply dummy text of the printing and typesetting industry.");
    }
    @When("user clicks send button")
    public void user_clicks_send_button() throws InterruptedException {
        driver.findElement(By.xpath("//span[contains(text(),'Send')]")).click();
    }
    @Then("user gets successful message")
    public void user_gets_successful_message() {
        String text = driver.findElement(By.xpath("//p[@class='alert alert-success']")).getText();
        Assert.assertEquals(text,"Your message has been successfully sent to our team.");
    }
    @Then("user gets empty message box error message")
    public void user_gets_empty_message_box_error_message() {
        String text = driver.findElement(By.xpath("//li[contains(text(),'The message cannot be blank.')]")).getText();
        Assert.assertEquals(text,"The message cannot be blank.");

    }
    @Then("user gets invalid email error message")
    public void user_gets_invalid_email_error_message() {
        String text = driver.findElement(By.xpath("//li[contains(text(),'Invalid email address.')]")).getText();
        Assert.assertEquals(text,"Invalid email address.");
    }
    @After
    public void afterEveryStep(Scenario scenario) throws IOException {
        if(scenario.isFailed()){
            Utility utility = new Utility(driver);
            utility.takeScreenShot(scenario.getName());
        }
        driver.quit();
    }

}
