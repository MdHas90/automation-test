import com.epam.healenium.SelfHealingDriver;
import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utility.Utility;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class CrossBrowserExample {

    public static Logger logger;

    public static WebDriver delegate;
    public static SelfHealingDriver driver;

    public static Utility utility;

    public static WebDriverWait wait;

    @BeforeMethod
    public void beforeTest() throws MalformedURLException {
        logger = Logger.getLogger("EasyLogger");
        logger.debug("--Automation Start--");
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(CapabilityType.BROWSER_NAME, "firefox");
        logger.debug("The current active browser: " + caps.getBrowserName());
        delegate = new RemoteWebDriver(new URL("http://localhost:4444"), caps);
        driver = SelfHealingDriver.create(delegate);
        utility = new Utility(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Test
    public void HomePageCheck()
    {
        logger.debug("Opening the website");
        driver.get("http://automationpractice.com/index.php");

        logger.debug("Wait until Sign In Button is clickable");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='login']")));

        logger.debug("Assert the Website Title");
        Assert.assertEquals(driver.getTitle(), "My Store");

    }

    @Test
    public void SignInCheck() throws IOException, ParseException {
        logger.debug("Opening the website");
        driver.get("http://automationpractice.com/index.php");

        logger.debug("Wait until Sign In Button is clickable");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='login']")));

        logger.debug("Click button login");
        driver.findElement(By.xpath("//a[@class='login']")).click();

        logger.debug("Wait until Email Field clickable");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("email")));

        logger.debug("Input the Email based on Users.json file");
        driver.findElement(By.id("email")).sendKeys(utility.readFromJSON(0, "email"));

        logger.debug("Wait until Password Field clickable");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("passwd")));

        logger.debug("Input the Password based on Users.json file");
        driver.findElement(By.id("passwd")).sendKeys(utility.readFromJSON(0, "password"));

        logger.debug("Wait until Submit Button clickable");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("SubmitLogin")));

        logger.debug("Click the Submit Button");
        driver.findElement(By.id("SubmitLogin")).click();

        logger.debug("Wait until the page is successfully loaded");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='page-heading']")));

        logger.debug("Assert after the login is successful");
        String text = driver.findElement(By.xpath("//h1[@class='page-heading']")).getText();
        AssertJUnit.assertEquals(text,"MY ACCOUNT");

    }

    @Test
    public void SignInCheckWithBadCredential() throws IOException, ParseException
    {
        logger.debug("Opening the website");
        driver.get("http://automationpractice.com/index.php");

        logger.debug("Wait until Sign In Button is clickable");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='login']")));

        logger.debug("Click button login");
        driver.findElement(By.xpath("//a[@class='login']")).click();

        logger.debug("Wait until Email Field clickable");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("email")));

        logger.debug("Input the Email based on Users.json file");
        driver.findElement(By.id("email")).sendKeys(utility.readFromJSON(0, "email"));

        logger.debug("Wait until Password Field clickable");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("passwd")));

        logger.debug("Input the Password based on Users.json file");
        driver.findElement(By.id("passwd")).sendKeys("wrong password");

        logger.debug("Wait until Submit Button clickable");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("SubmitLogin")));

        logger.debug("Click the Submit Button");
        driver.findElement(By.id("SubmitLogin")).click();

        logger.debug("Wait until the page is successfully loaded");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='page-heading']")));

        logger.debug("Assert after the login is failed");
        String text = driver.findElement(By.xpath("//p[normalize-space()='There is 1 error']")).getText();
        AssertJUnit.assertEquals(text,"There is 1 error");

    }

    @AfterMethod
    public void afterTest() throws MalformedURLException {
        driver.quit();
    }
}
