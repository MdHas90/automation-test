import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import utility.DataReader;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class SauceLabs {



    public class UseDriver {

        static WebDriver driver;

        public static void openUrl() {
            DataReader data = new DataReader();
            String sauceUrl = "https://" + data.getSauceUser() + ":" + data.getSauceKey() + "@ondemand.us-west-1.saucelabs.com:443/wd/hub";

            switch (data.getEnv().toLowerCase()) {
                case "local":
                    switch (data.getBrowserName().toLowerCase()) {
                        case "chrome":
                            System.setProperty("webdriver.chrome.driver", data.getChromeDriverPath());
                            driver = new ChromeDriver();
                            break;
                        case "firefox":
                            System.setProperty("", data.getFirefoxDriverPath());
                            driver = new ChromeDriver();
                            break;
                    }
                    break;
                case "sauce":
                    DesiredCapabilities capabilities = null;
                    switch (data.getBrowserName().toLowerCase()) {
                        case "chrome":
                           // capabilities = DesiredCapabilities.chrome();
                            break;
                        case "firefox":
                          //  capabilities = DesiredCapabilities.firefox();
                            break;
                    }
                    capabilities.setCapability("version", data.getVersion());
                    capabilities.setCapability("platform", data.getPlatform());
                    try {
                        driver = new RemoteWebDriver(new URL(sauceUrl), capabilities);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    break;
            }
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get(data.getUrl());
        }


    }
}
