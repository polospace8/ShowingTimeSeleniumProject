package Utils;

import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;



public class CommonFunctions {
	//constructor
	WebDriver driver = null;
	 ExtentReports extent= null;
	 ExtentTest logger;
	ExtentReportsUtil extentReport = new ExtentReportsUtil();
	
	
	public CommonFunctions(WebDriver driver){
		this.driver=driver;
		extent = new ExtentReports (System.getProperty("user.dir") +"/Reports/extentReport.html", true);
		extent.addSystemInfo("User Name", "Vrunda");
	}
	
//This method is to click a WebElement
	public Boolean clickObject(WebElement objWebElement,String arg) throws Exception
	{
		try
		{
			objWebElement.click();
			String strObjectXPATH = "";
			logger = extent.startTest(arg);
			logger.log(LogStatus.PASS, arg+" is clicked");
			return true;
		}
		catch(Exception objException)
		{
			objException.printStackTrace();
			logger = extent.startTest(arg);
			logger.log(LogStatus.FAIL, arg+" is NOT clicked");
		}
		return false;
	}
	
	
// Method to send text to a webelement	
	public Boolean sendText(WebElement objWebElement, String strText) throws Exception
	{
		try
		{
			objWebElement.sendKeys(strText);;
			String strObjectXPATH = "";
			logger = extent.startTest("passTest");
			logger.log(LogStatus.PASS, "Test Case Passed is passTest");
			return true;
		}
		catch(Exception objException)
		{
			objException.printStackTrace();
			logger = extent.startTest("failTest");
			logger.log(LogStatus.FAIL, "Test Case Status is failed");
		}
		return false;
	}

// Method to verify if the webelement/text is displayed	
public Boolean isDisplayed(WebElement objWebElement,String arg) throws Exception
{
	try
	{	
		if (objWebElement.isDisplayed() == true){
		logger = extent.startTest(arg);
		logger.log(LogStatus.PASS, arg+" is displayed");
		}
		return true;
	}

	catch(Exception objException)
	{
		objException.printStackTrace();
		logger = extent.startTest(arg);
		logger.log(LogStatus.FAIL, arg+" is NOT displayed");
	}
return false;	
}

public String getTextWebElement(WebElement objWebElement,String arg) throws Exception
{
	String actualText="";
	try
	{
		actualText = objWebElement.getText();
		logger = extent.startTest(arg);
		logger.log(LogStatus.PASS, arg+" : Expected text is displayed");
	}
	catch (Exception objException)
	{
		objException.printStackTrace();
		logger = extent.startTest(arg);
		logger.log(LogStatus.FAIL, arg+" :Expected text is NOT displayed");
	}
	return actualText;
}


public void sendKeysAction(Keys Key) throws Exception {
	try {
		Actions action = new Actions(driver);
		action.sendKeys(Key);
		logger = extent.startTest("passTest");
		logger.log(LogStatus.PASS, "Test Case Passed is passTest");
	} catch (Exception exception) {
		logger = extent.startTest("failTest");
		logger.log(LogStatus.FAIL, "Test Case Status is failed");
		throw new Exception(exception);
	}
}

public void hitEscapeKeyForPopups() {
	try {
		if (Boolean.valueOf("HANDLE_POPUP")) {
		sendKeysAction(Keys.ESCAPE);
		logger = extent.startTest("passTest");
		logger.log(LogStatus.PASS, "Test Case Passed is passTest");
		}
	} catch (Exception exception) {
		System.out.println("Error in hitting Escape key for pupup");
		logger = extent.startTest("failTest");
		logger.log(LogStatus.FAIL, "Test Case Status is failed");
	}
}

//method to switch to a  new window
public boolean switchToNewWindow() throws Exception {
	try {

		ArrayList<String> windows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(windows.get(1));
		return true;
	} catch (Exception exception) {
		throw new Exception();
	}
}

//compare and verify 2 strings
public void compareStrings(String arg1, String arg2) throws Exception{
	try{
		if (arg1.equals(arg2)){
			logger = extent.startTest(" String comparison passed");
			logger.log(LogStatus.PASS, arg1+" and "+arg2+" are a match");

		}
	} catch (Exception exception) {
		logger = extent.startTest(" String comparison failed");
		logger.log(LogStatus.FAIL, arg1+" and "+arg2+" are NOT a match");
		throw new Exception();
	}
}


public void endReport(){
	extent.endTest(logger);
    extent.flush();
//    extent.close();
   
}




}


