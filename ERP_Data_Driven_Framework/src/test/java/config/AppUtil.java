package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class AppUtil {
public static Properties conprop;
public static WebDriver driver;
@BeforeTest
public static void setup() throws Throwable

{
	conprop = new Properties();
	conprop.load(new FileInputStream("./PropertyFiles/Login.properties"));
	
	if(conprop.getProperty("Browser").equalsIgnoreCase("chrome"))
	{
		driver = new ChromeDriver();
	}
	else if(conprop.getProperty("Browser").equalsIgnoreCase("firefox"))
	{
		driver = new FirefoxDriver();
	}
	else
	{
		Reporter.log("Browser value did not matching",true);
	}
		
}
@AfterTest
public static void teardown()
{
	driver.quit();
}
}