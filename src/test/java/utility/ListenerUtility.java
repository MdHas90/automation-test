package utility;


import org.apache.log4j.Logger;
import org.testng.IResultMap;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

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
        logger.debug("The name of the testcase passed is : "+Result.getName());
    }
}
