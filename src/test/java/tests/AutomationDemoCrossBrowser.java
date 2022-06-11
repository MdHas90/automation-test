package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AutomationDemoCrossBrowser {

	WebDriver driver;

	/**
	 * This function will execute before each Test tag in testng.xml
	 * 
	 * @param browser
	 * @throws Exception
	 */
	@BeforeTest
	@Parameters("browser")
	public void setup(String browser) throws Exception {

		// Check if parameter passed from TestNG is 'firefox'
		if (browser.equalsIgnoreCase("firefox")) {
			// create firefox instance
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}

		// Check if parameter passed as 'chrome'
		else if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			// create chrome instance
			driver = new ChromeDriver();
		}

		// Check if parameter passed as 'Edge'
		else if (browser.equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();
			// create Edge instance
			driver = new EdgeDriver();
		}

		else if (browser.equalsIgnoreCase("Safari")) {
			// set path to Edge.exe
			WebDriverManager.safaridriver().setup();
			// create Edge instance
			driver = new SafariDriver();
		}

		else {
			// If no browser passed throw exception
			throw new Exception("Browser is not correct");
		}
	}

	@Test
	public void testParameterWithXML() throws InterruptedException {
		driver.get("http://automationpractice.com/index.php");
		Thread.sleep(10000);
		driver.close();
	}
}
