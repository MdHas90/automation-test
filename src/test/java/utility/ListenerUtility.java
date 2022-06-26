package utility;


import com.aventstack.extentreports.MediaEntityBuilder;
import main.CrossBrowserExample;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ListenerUtility implements ITestListener {

    public static Logger logger = Logger.getLogger("EasyLogger");

    @Override
    public void onStart(ITestContext Result)
    {
        logger.debug("");
        logger.debug("===============================AUTOMATION TESTING STARTED===============================");
        logger.debug("");
        logger.debug("Started at: " + Result.getStartDate());
    }

    @Override
    public void onFinish(ITestContext Result)
    {
        logger.debug("");
        logger.debug("===============================AUTOMATION TESTING FINISHED===============================");
        logger.debug("");
        logger.debug("Ended at: " + Result.getEndDate());
    }

    // When Test case get failed, this method is called.
    @Override
    public void onTestFailure(ITestResult Result)
    {
        logger.debug("The name of the testcase failed is: " + Result.getName());
        TakesScreenshot takesScreenshot = (TakesScreenshot) CrossBrowserExample.driver;
        String time = new SimpleDateFormat("MM-dd-yyyy-hh-mm-ss-aa").format(new Date());
        File SrcFile=takesScreenshot.getScreenshotAs(OutputType.FILE);
        String fileName = time + "-" + Result.getName() + ".png";
        String filePath = CrossBrowserExample.projectPath + "/reports/" + fileName;
        File DestFile=new File(filePath);
        try {
            FileUtils.copyFile(SrcFile, DestFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        CrossBrowserExample.extentTest.fail("The testcase failed", MediaEntityBuilder.createScreenCaptureFromPath(fileName).build());
    }

    // When Test case get Skipped, this method is called.
    @Override
    public void onTestSkipped(ITestResult Result)
    {
        logger.debug("The name of the testcase Skipped is: " + Result.getName());
    }

    // When Test case get Started, this method is called.
    @Override
    public void onTestStart(ITestResult Result)
    {
        logger.debug(Result.getName()+" test case started");
    }

    // When Test case get passed, this method is called.
    @Override
    public void onTestSuccess(ITestResult Result)
    {
        CrossBrowserExample.extentTest.pass("The testcase passed");
        logger.debug("The name of the testcase passed is : "+Result.getName());
    }
}
