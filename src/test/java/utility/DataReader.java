package utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DataReader {

    private String url = "";
    private String chromeDriverPath = "";
    private String firefoxDriverPath = "";
    private String browserName = "";
    private String env = "";
    private String sauceUser = "";
    private String sauceKey = "";
    private String version = "";
    private String platform = "";


    public DataReader() {

        Properties prop = new Properties();
        try {
            InputStream inputStream = new FileInputStream("Data.properties");

            prop.load(inputStream);

            url = prop.getProperty("url");
            chromeDriverPath = prop.getProperty("chromeDriverPath");
            firefoxDriverPath = prop.getProperty("firefoxDriverPath");
            browserName = prop.getProperty("browserName");
            env = prop.getProperty("env");
            sauceUser = prop.getProperty("sauceUser");
            sauceKey = prop.getProperty("sauceKey");
            version = prop.getProperty("version");
            platform = prop.getProperty("platform");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public String getUrl() {
        return url;
    }

    public String getChromeDriverPath() {
        return chromeDriverPath;
    }

    public String getFirefoxDriverPath() {
        return firefoxDriverPath;
    }

    public String getBrowserName() {
        return browserName;
    }

    public String getEnv() {
        return env;
    }

    public String getSauceUser() {
        return sauceUser;
    }

    public String getSauceKey() {
        return sauceKey;
    }

    public String getVersion() {
        return version;
    }

    public String getPlatform() {
        return platform;
    }

}
