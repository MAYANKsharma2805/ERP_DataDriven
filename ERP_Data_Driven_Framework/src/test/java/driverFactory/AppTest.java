package driverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunctions.FunctionLibrary;
import config.AppUtil;
import utilities.ExcelFileUtil;

;

public class AppTest extends AppUtil


{
 String inputpath = "./FileInput/LoginData.xlsx";
 String outputpath = "./FileOutput/Datadriven.xlsx";
 ExtentReports reports;
 ExtentTest logger ;
 @Test
 public void startTest() throws Throwable
 {
	 reports = new ExtentReports("./target/Reports/LoginReports.html");
	ExcelFileUtil xl = new ExcelFileUtil(inputpath);
	int rc = xl.rowCount("Login");
	Reporter.log("No of Rows::"+ rc ,true);
	for(int i=1;i<=rc;i++)
	{
		logger = reports.startTest("Validate Login");
		logger.assignAuthor("Mayank Sharma");
		String username = xl.getCellData("Login", i,0);
		String password = xl.getCellData("Login", i,1);
		logger.log(LogStatus.PASS, username+" "+password);
		boolean res = FunctionLibrary.adminLogin(username, password);
		if(res)
		{
			xl.setCellData("Login", i, 2, "Login sucess", outputpath);
			xl.setCellData("Login", i, 3, "Pass", outputpath);
			logger.log(LogStatus.PASS, "Username snd password are valid");
			
		}else
		{
			File screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screen, new File("./Screenshot/Iteration/"+i+"Login"+i+"Login.png"));
			xl.setCellData("Login", i, 2, "Login Fail", outputpath);
			xl.setCellData("Login", i, 3, "Fail", outputpath);
			logger.log(LogStatus.FAIL, "Username snd password are Invalid");
		}
		reports.endTest(logger);
		reports.flush();
	}
 }
 	
}
