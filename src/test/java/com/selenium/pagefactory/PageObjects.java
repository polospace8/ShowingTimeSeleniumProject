package com.selenium.pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Utils.BaseClass;

public class PageObjects {
	WebDriver driver = null;	
	public PageObjects(WebDriver driver)
	{
		this.driver = driver;
        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
	}

	//Clicking on Menu items
	@FindBy(xpath="//div[@id='responsive-navbar']/a[1]") public WebElement linkMainMenu;
	@FindBy(xpath="//*[@class='menu treeview']/li[1]/a") public WebElement linkTopNavShowings;
	@FindBy(xpath="//*[@class='menu treeview']/li[2]/a") public WebElement linkTopNavMarketStats;

	@FindBy(xpath="//div[@id='topNav']//a[contains(text(),'Request Demo')]") public WebElement btnTopNavRequestDemo;
    @FindBy(id="edit-submit") public WebElement btnSubmitRequestDemo;
    @FindBy(xpath="//div[@class='messages error']") public WebElement labelErrorMsgRequiredfields;
 
    @FindBy(xpath="//a[@class='button'][contains(text(),'ShowingTime Appointment Center')]") public WebElement linkSTAppointmentCenter;
    @FindBy(className="pageBlock_PlayImage") public WebElement videoSTApptScheduling;
    @FindBy(xpath="//div[@class='pp_overlay']") public WebElement popUpVideoSTApptScheduling;
    
    @FindBy(xpath="//a[@class='billing external']") public WebElement linkTopNavBilling;
    @FindBy(id="ctl00_MainContent_btnLogin") public WebElement btnBillingLogIn;
    @FindBy(id="ctl00_MainContent_pnlMessage") public WebElement errorMsgBillingLogIn;
    
    @FindBy(linkText="FastStats") public WebElement linkMarketStatsFastStats;
    @FindBy(tagName="img") public WebElement imageSimple;
    
    @FindBy(xpath="//a[@id='logo']/img") public WebElement imgShowingTimeLogo;
    @FindBy(xpath="//a[@class='button'][contains(text(),'Manage Showings')]") public WebElement btnManageShowings;
    @FindBy(xpath="//h1/span[text()='ShowingTime Appointment Center']") public WebElement labelSTAppointmentCenter;
}
