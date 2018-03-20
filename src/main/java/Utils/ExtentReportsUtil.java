package Utils;

import java.io.File;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
 
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
 
public class ExtentReportsUtil{
 ExtentReports extent;
 ExtentTest logger;
 
 
 
		public void startReport(){

	 		extent = new ExtentReports (System.getProperty("user.dir") +"/Reports/extentReport.html", true);

	 		extent
                .addSystemInfo("Host Name", "ShowingTime")
                .addSystemInfo("Environment", "Automation Testing")
                .addSystemInfo("User Name", "Vrunda");
                extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
 }
 
 		
 		public void passTest(){
// 			 startReport();
			 logger = extent.startTest("passTest");
			 Assert.assertTrue(true);
			 //To generate the log when the test case is passed
			 logger.log(LogStatus.PASS, "Test Case Passed is passTest");
			 }
 
 		
 		public void failTest(){
// 			 startReport();
			 logger = extent.startTest("failTest");
			 Assert.assertTrue(false);
			 logger.log(LogStatus.PASS, "Test Case (failTest) Status is passed");
 		}
 
		 
		 public void skipTest(){
		 logger = extent.startTest("skipTest");
		 throw new SkipException("Skipping - This is not ready for testing ");
		 }
 
		 
		 public void getResult(ITestResult result){
		 if(result.getStatus() == ITestResult.FAILURE){
		 logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
		 logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getThrowable());
		 }else if(result.getStatus() == ITestResult.SKIP){
		 logger.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
		 }

		 extent.endTest(logger);
		 }
		 
		 public void endReport(){
			 			extent.endTest(logger);
		                extent.flush();
		                extent.close();
		    }
}