package commonFunctions;

import java.time.Duration;

import javax.print.DocFlavor.STRING;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import config.AppUtil;

public class FunctionLibrary extends AppUtil


{
	public static boolean adminLogin(String user , String pass) throws Throwable
	{
		driver.get(conprop.getProperty("Url"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath(conprop.getProperty("Objresetbutton"))).click();
		driver.findElement(By.xpath(conprop.getProperty("Objuser"))).sendKeys(user);
		driver.findElement(By.xpath(conprop.getProperty("Objpass"))).sendKeys(pass);
		driver.findElement(By.xpath(conprop.getProperty("Objlogin"))).click();
		Thread.sleep(3000);
		String Expected ="dashboard";
		String Actual = driver.getCurrentUrl();
		
		if(Actual.contains(Expected))
		{
			Reporter.log("Valid username and password",true);
			driver.findElement(By.xpath(conprop.getProperty("objlogout"))).click();
			return true;
		}
		else
		{
			String message = driver.findElement(By.xpath(conprop.getProperty("Objmessage"))).getText();
			Thread.sleep(3000);
			driver.findElement(By.xpath(conprop.getProperty("ObjOkbutton"))).click();
	        Reporter.log(message+  "Invalid username or password",true);
	        return false;
		}
		
		
		
		
	}

	}
