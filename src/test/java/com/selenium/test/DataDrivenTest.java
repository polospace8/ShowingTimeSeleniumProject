package com.selenium.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.selenium.pagefactory.PageObjects;

import Utils.BaseClass;
import Utils.CommonFunctions;
import Utils.ExcelFunctions;
import Utils.ExtentReportsUtil;
import Utils.PropertyUtils;

public class DataDrivenTest extends BaseClass{
WebDriver driver = null;
CommonFunctions commonfunction = null;	
PageObjects pageObject = null;
ExcelFunctions excel = new ExcelFunctions();
ExtentReportsUtil reports = new ExtentReportsUtil();
ITestResult result;
ExtentReports extent;
ExtentTest logger;
PropertyUtils property = new PropertyUtils("src/test/resources/config/Config.properties");


//TC01:Verify validation error message is displayed after clicking request demo with no info added to form.
@Test(priority=1, enabled = true) 
public void requestDemo() throws Exception
{
	try{
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		commonfunction.clickObject(pageObject.btnTopNavRequestDemo,"TC01_Step1: RequestDemo button");
		commonfunction.clickObject(pageObject.btnSubmitRequestDemo,"TC01_Step2: Submit Request Demo Button");
		String errorMsg=commonfunction.getTextWebElement(pageObject.labelErrorMsgRequiredfields,"TC01_Step3: Request Demo Page- Required fields Error message");
		commonfunction.isDisplayed(pageObject.labelErrorMsgRequiredfields,"TC01_Step4: Request Demo Page- Required fields Error message -"+errorMsg);
	}catch(Exception objException)
	{
		objException.printStackTrace();
	}
}
	
//TC02:Verify video popup is displayed
@Test(priority=2, enabled=true)
public void verifyVideoPopUpIsDisplayed() throws Exception
{
	try{
		driver.navigate().to(property.getProperty("URL"));;
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		commonfunction.clickObject(pageObject.linkSTAppointmentCenter, "TC02_Step1: Showing Time Appointment Center Link");
		commonfunction.clickObject(pageObject.videoSTApptScheduling, "TC02_Step2: Showing Time Appointment Scheduling Video");
		commonfunction.isDisplayed(pageObject.popUpVideoSTApptScheduling, "TC02_Step3: Showing Time Appointment Scheduling Video PopUp");
	}catch(Exception objException)
	{
		objException.printStackTrace();
	}
}

//TC03:Verify that Url matches http://showingtime.com/marketstats
@Test(priority=3, enabled=true)
public void verifyURLsMatch() throws Exception
{
	try{
		driver.navigate().to(property.getProperty("URL"));
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);	
		commonfunction.clickObject(pageObject.linkMainMenu,"TC03_Step1: Main Menu");
		Thread.sleep(3000);
		commonfunction.clickObject(pageObject.linkTopNavMarketStats,"TC03_Step2: Market Stats Main Menu Option");
		String urlActual=driver.getCurrentUrl();
		String urlExpected= ExcelFunctions.getCellValueforOneRow();
		commonfunction.compareStrings(urlActual,urlExpected);

	}catch(Exception objException)
	{
		objException.printStackTrace();
	}
}

//TC04:Verify validation error message is displayed after clicking Log in to Showing time billing with no info added to form.
@Test(priority=4, enabled=true)
public void verifyBillingLogInErrorMsg() throws Exception
{
	try{
		driver.navigate().to(property.getProperty("URL"));;
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		commonfunction.clickObject(pageObject.linkMainMenu,"TC04_Step1: Main Menu");
		commonfunction.clickObject(pageObject.linkTopNavBilling,"TC04_Step2: Billing Main Menu option ");
		commonfunction.switchToNewWindow();
		commonfunction.clickObject(pageObject.btnBillingLogIn,"TC04_Step3: Billing Log In Button");
		String errorMsg=commonfunction.getTextWebElement(pageObject.errorMsgBillingLogIn,"TC04_Step4: Billing LogIn- Required Fields Error Message");
		commonfunction.isDisplayed(pageObject.errorMsgBillingLogIn,"TC04_Step5: Billing LogIn- Required Fields Error Message:"+errorMsg );
	}catch(Exception objException)
	{
		objException.printStackTrace();
	}
}

//TC05:Verify that Simple image is displayed on the page.
@Test(priority=5, enabled=true)
public void verifySimpleImageIsDisplayed() throws Exception
{
	try{
		driver.navigate().to(property.getProperty("URL"));;
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		commonfunction.clickObject(pageObject.linkMainMenu,"TC05_Step1: Main Menu ");
		Thread.sleep(3000);
		commonfunction.clickObject(pageObject.linkTopNavMarketStats,"TC05_Step2: Market Stats Main Menu option");
		commonfunction.clickObject(pageObject.linkMarketStatsFastStats,"TC05_Step3: Fast Stats Sub Menu option");
		commonfunction.isDisplayed(pageObject.imageSimple, "TC05_Step4: Simple Image");
	}catch(Exception objException)
	{
		objException.printStackTrace();
	}
}
	
//TC06:Verify validation error message is displayed after clicking Log in to Showing time billing with no info added to form.
@Test(priority=6, enabled=true)
public void verifySTAppointementCenterIsDisplayed() throws Exception
{
	try{
		driver.navigate().to(property.getProperty("URL"));;
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		commonfunction.clickObject(pageObject.imgShowingTimeLogo, "TC06_Step1: Manage Showings Logo");
		commonfunction.clickObject(pageObject.btnManageShowings, "TC06_Step2: Manage Showings button");
		commonfunction.clickObject(pageObject.linkSTAppointmentCenter,"TC06_Step3: ShowingTime Appointment Center link");
		commonfunction.isDisplayed(pageObject.labelSTAppointmentCenter,"TC06_Step4: ShowingTime Appointment Center Text");
	}catch(Exception objException){
	objException.printStackTrace();
	}
}

	 
@BeforeTest 
public void TestSetup() throws Exception
	{   	
		driver = BaseClass.launchDriver();
		pageObject = new PageObjects(driver);
		commonfunction=new CommonFunctions(driver);
    }


@AfterTest
public void tearDown() throws Exception
{
	commonfunction.endReport();
	driver.quit();
}	   

}
		



