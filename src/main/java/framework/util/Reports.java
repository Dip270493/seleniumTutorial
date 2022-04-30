package framework.util;

import static org.testng.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Reports {
	static ExtentReports reports;
	static ExtentTest test;
	public static WebDriver driver;

	public static void createReport() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");
		String date = format.format(new Date());
		reports = new ExtentReports(System.getProperty("user.dir") + "\\testReports\\ExtentReports-" + date + ".html");
		System.out.println("Report has been generated at: " + System.getProperty("user.dir")
				+ "\\testReports\\ExtentReports" + date + ".html");
	}

	public static void startTest(WebDriver driver2) {
		driver=driver2;
		test = reports.startTest("TestName");
	}

	public static void log(String status, String message, boolean isScreenShot) {
		test.log(LogStatus.valueOf(status), message);
		takeScreenshot(isScreenShot);
		
	}

	public static void takeScreenshot(boolean isScreenShot) {
		try {
		if(isScreenShot) {
			TakesScreenshot scrShot =((TakesScreenshot)driver);
			File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
			byte[] fileContent = FileUtils.readFileToByteArray(SrcFile);
			String encodedString = "data:image/png;base64,"+Base64.getEncoder().encodeToString(fileContent);
			System.out.println(encodedString);
			test.log(LogStatus.INFO,"Adding screenshot"+test.addBase64ScreenShot(encodedString));
		}
	}
	catch(IOException e)
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println("Failed to take screenshot");
	}
	}

	public static void endTest() {
		reports.endTest(test);
	}

	public static void closeTest() {
		reports.flush();
	}
}
