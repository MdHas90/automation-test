package Web;

public class UseDriver {

    //import io.cucumber.java.After;
//import io.cucumber.java.Before;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.remote.RemoteWebDriver;
//
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.concurrent.TimeUnit;
//
//public class UseDriver {
//
//    static WebDriver driver;
//
//   public static void openUrl() {
//        Helper.DataReader data = new Helper.DataReader();
//        String sauceUrl = "https://"+data.getSauceUser()+":"+data.getSauceKey()+"@ondemand.us-west-1.saucelabs.com:443/wd/hub";
//
//        switch (data.getEnv().toLowerCase()) {
//            case "local":
//                switch (data.getBrowserName().toLowerCase()) {
//                    case "chrome":
//                        System.setProperty("webdriver.chrome.driver" , data.getChromeDriverPath());
//                        driver = new ChromeDriver();
//                        break;
//                    case "firefox":
//                        System.setProperty("" , data.getFirefoxDriverPath());
//                        driver = new ChromeDriver();
//                        break;
//                }
//                break;
//            case "sauce":
//                DesiredCapabilities capabilities = null;
//                switch (data.getBrowserName().toLowerCase()) {
//                    case "chrome":
//                        capabilities = DesiredCapabilities.chrome();
//                        break;
//                    case "firefox":
//                        capabilities = DesiredCapabilities.firefox();
//                        break;
//                }
//                capabilities.setCapability("version", data.getVersion());
//                capabilities.setCapability("platform", data.getPlatform());
//                try {
//                    driver = new RemoteWebDriver(new URL(sauceUrl), capabilities);
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                }
//                break;
//        }
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        driver.get(data.getUrl());
//    }

//Comments

//    /**
//     * Any method with @Before annotation will execute before every Scenario
//     */
//    @Before
//    public static void openUrl() {
//        System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
//        driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        driver.get("https://www.facebook.com/");
//        // Misc.sleep(2);
//    }
//
//    public static WebDriver getDriver() {
//        return driver;
//    }
//
//    /**
//     * Any method with @After annotation will execute after every Scenario
//     */
//    @After
//    public static void quitWebPages() {
//        driver.quit();
//    }



}
