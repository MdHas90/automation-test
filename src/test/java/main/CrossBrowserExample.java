package main;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
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
import org.testng.annotations.*;
import utility.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;

@Listeners(ListenerUtility.class)
public class CrossBrowserExample {

    public static Logger logger;

    public static WebDriver delegate;
    public static SelfHealingDriver driver;

    public static Utility utility;

    public static WebDriverWait wait;
    public static String projectPath;

    DataDriven objExcelFile;

    String filePath;
    String reportPath;

    public static ExtentReports extentReports;
    public static ExtentTest extentTest;
    public static ExtentSparkReporter extentSparkReporter;

    @BeforeClass
    public void setup() {
        projectPath = System.getProperty("user.dir");
        filePath = projectPath+"/assets/excel/";

        reportPath = projectPath+"/reports/";
        extentSparkReporter = new ExtentSparkReporter(reportPath + "TestReport.html");
        extentSparkReporter.config().setDocumentTitle("Web Automation Report");
        extentSparkReporter.config().setReportName("Test Report");
        extentSparkReporter.config().setTheme(Theme.STANDARD);
        extentSparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
        extentReports.setSystemInfo("Tester", "Hassan");

        logger = Logger.getLogger("EasyLogger");
    }

    @BeforeMethod
    public void beforeTest() throws MalformedURLException {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(CapabilityType.BROWSER_NAME, "firefox");
        logger.debug("The current active browser: " + caps.getBrowserName());
        delegate = new RemoteWebDriver(new URL("http://localhost:4444"), caps);
        driver = SelfHealingDriver.create(delegate);
        utility = new Utility(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        objExcelFile = new DataDriven();
    }

    @Test
    public void homePageCheck()
    {
        extentTest = extentReports.createTest("homePageCheck");

        extentTest.info("Opening the website");
        logger.debug("Opening the website");
        driver.get("http://automationpractice.com/index.php");

        extentTest.info("Wait until Sign In Button is clickable");
        logger.debug("Wait until Sign In Button is clickable");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='login']")));

        extentTest.info("Assert the Website Title");
        logger.debug("Assert the Website Title");
        Assert.assertEquals(driver.getTitle(), "My Store");

    }

    @Test
    public void signInCheck() throws IOException, ParseException {
        extentTest = extentReports.createTest("signInCheck");
        int row = 1;
        ArrayList<String> data = objExcelFile.readExcel(filePath,"Book1.xlsx","credentials", logger, row);

        extentTest.info("Opening the website");
        logger.debug("Opening the website");
        driver.get("http://automationpractice.com/index.php");

        extentTest.info("Wait until Sign In Button is clickable");
        logger.debug("Wait until Sign In Button is clickable");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='login']")));

        extentTest.info("Click button login");
        logger.debug("Click button login");
        driver.findElement(By.xpath("//a[@class='login']")).click();

        extentTest.info("Wait until Email Field clickable");
        logger.debug("Wait until Email Field clickable");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("email")));

        extentTest.info("Input the Email based on Users.json file");
        logger.debug("Input the Email based on Users.json file");
//        driver.findElement(By.id("email")).sendKeys(utility.readFromJSON(0, "email"));
        driver.findElement(By.id("email")).sendKeys(data.get(0));

        extentTest.info("Wait until Password Field clickable");
        logger.debug("Wait until Password Field clickable");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("passwd")));

        extentTest.info("Input the Password based on Users.json file");
        logger.debug("Input the Password based on Users.json file");
//        driver.findElement(By.id("passwd")).sendKeys(utility.readFromJSON(0, "password"));
        driver.findElement(By.id("passwd")).sendKeys(data.get(1));

        extentTest.info("Wait until Submit Button clickable");
        logger.debug("Wait until Submit Button clickable");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("SubmitLogin")));

        extentTest.info("Click the Submit Button");
        logger.debug("Click the Submit Button");
        driver.findElement(By.id("SubmitLoginn")).click();

        extentTest.info("Wait until the page is successfully loaded");
        logger.debug("Wait until the page is successfully loaded");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='page-heading']")));

        extentTest.info("Assert after the login is successful");
        logger.debug("Assert after the login is successful");
        String text = driver.findElement(By.xpath("//h1[@class='page-heading']")).getText();
        AssertJUnit.assertEquals(text,"MY ACCOUNT");
    }

    @Test
    public void signInCheckWithBadCredential() throws IOException, ParseException
    {
        extentTest = extentReports.createTest("signInCheckWithBadCredential");
        int row = 2;
        ArrayList<String> data = objExcelFile.readExcel(filePath,"Book1.xlsx","credentials", logger, row);

        logger.debug("Opening the website");
        driver.get("http://automationpractice.com/index.php");

        logger.debug("Wait until Sign In Button is clickable");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='login']")));

        logger.debug("Click button login");
        driver.findElement(By.xpath("//a[@class='login']")).click();

        logger.debug("Wait until Email Field clickable");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("email")));

        logger.debug("Input the Email based on Users.json file");
        driver.findElement(By.id("email")).sendKeys(data.get(0));

        logger.debug("Wait until Password Field clickable");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("passwd")));

        logger.debug("Input the Password based on Users.json file");
        driver.findElement(By.id("passwd")).sendKeys(data.get(1));

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

    @Test
    public void fileUploadUsingSeleniumCheck() {

        extentTest = extentReports.createTest("fileUploadUsingSeleniumCheck");
        String fileName = "example-file.txt";

        logger.debug("Opening the website");
        driver.get("https://filebin.net/");

        logger.debug("Click the upload button");
        driver.findElement(By.id("fileField")).sendKeys(projectPath + "/assets/txt-files/" + fileName);

        logger.debug("Assert the File Name");
        Assert.assertEquals(driver.findElement(By.xpath("(//a[@class='link-primary link-custom'][normalize-space()='"+ fileName +"'])[1]")).getText(), fileName);
    }

    @Test(enabled = false)
    public void fileUploadUsingRobotCheck() {
        extentTest = extentReports.createTest("fileUploadUsingRobotCheck");

        String fileName = "example-file.txt";

        logger.debug("Opening the website");
        driver.get("https://filebin.net/");

        logger.debug("Click the upload button");
        driver.findElement(By.xpath("(//span[@class='fileUpload btn btn-primary mt-2 mb-2'])[1]")).click();

        utility.uploadFile(projectPath + "/assets/txt-files/" + fileName);

        logger.debug("Assert the File Name");
        Assert.assertEquals(driver.findElement(By.xpath("(//a[@class='link-primary link-custom'][normalize-space()='"+ fileName +"'])[1]")).getText(), fileName);
    }

    @AfterClass
    public void endReport(){
        extentReports.flush();
    }

    @AfterMethod
    public void afterTest() throws MalformedURLException {
        driver.quit();
    }

}
