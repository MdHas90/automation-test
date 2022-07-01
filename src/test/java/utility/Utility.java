package utility;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class Utility {
    WebDriver driver;
    String fileName = "./src/test/resources/Users.json";
    public Utility(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void writeInJSON(String email, String password) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(new FileReader(fileName));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email",email);
        jsonObject.put("password", password);
        JSONArray jsonArray = (JSONArray) obj;
        jsonArray.add(jsonObject);
        FileWriter file = new FileWriter(fileName);
        file.write(jsonArray.toJSONString());
        file.flush();
    }
    public String readFromJSON(int position, String key) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        Object object = jsonParser.parse(new FileReader(fileName));
        JSONArray jsonArray = (JSONArray) object;
        JSONObject jsonObject = (JSONObject) jsonArray.get(position);
        String value = (String) jsonObject.get(key);
        return value;
    }
    public void takeScreenShot(String scenarioName) throws IOException {
        File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String time = new SimpleDateFormat("MM-dd-yyyy-hh-mm-ss-aa").format(new Date());
        String fileWithPath = "./src/test/resources/screenshots/" + time + "-" + scenarioName + ".png";
        File DestFile = new File(fileWithPath);
        FileUtils.copyFile(screenshotFile, DestFile);
    }

    public void uploadFile(String fileLocation) {
        try {
            //Setting clipboard with file location
            setClipboardData(fileLocation);
            //native key strokes for CTRL, V and ENTER keys
            Robot robot = new Robot();

            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    public void setClipboardData(String string) {
        //StringSelection is a class that can be used for copy and paste operations.
        StringSelection stringSelection = new StringSelection(string);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
    }

    public String dataDecoder(String string) {
        byte[] decodeBytes = Base64.getDecoder().decode(string.getBytes());
        return new String(decodeBytes);
    }
}
