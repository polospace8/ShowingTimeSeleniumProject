package Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;

public class BaseClass {
	
	public static WebDriver driver = null;
	
	
	public static WebDriver launchDriver()
	{
		PropertyUtils property = new PropertyUtils("src/test/resources/config/Config.properties");
		
		System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(property.getProperty("URL"));
		return driver;
	}
	
	
}
