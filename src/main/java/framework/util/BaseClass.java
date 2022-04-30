package framework.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseClass {
	public WebDriver driver;
	public ChromeOptions options;
	public String url = "https://trello.com";
	public String email = "mondaldip27@gmail.com";
	public String password = "Password@Dip27";
	public String listName;
	public ObjectRepo objectRepo = new ObjectRepo();

	public void launchBrowser() {
		System.setProperty("webdriver.chrome.driver", "src\\main\\java\\resources\\chromedriver.exe");
		options = new ChromeOptions();
		options.addArguments("start-maximized");
		driver = new ChromeDriver(options);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.get(url);
		Reports.driver = driver;
	}

	public void login() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 1);
		WebElement loginLink = driver.findElement(By.xpath("//a[contains(text(),'Log')]"));
		loginLink.click();
		WebElement txtEmail = driver.findElement(By.id("user"));
		txtEmail.sendKeys(email);
		WebElement btnLoginWithAtlassian = driver.findElement(By.id("login"));
		btnLoginWithAtlassian.click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("login")));
		WebElement txtPassword = driver.findElement(By.id("password"));
		txtPassword.sendKeys(password);
		WebElement btnLogin = driver.findElement(By.id("login-submit"));
		btnLogin.click();
	}

	public void logout() {
		WebElement account = driver
				.findElement(By.xpath("//span[@class=\"SETnvPbUKHW-cx _3XZPT267JcxN7w _1hy9FIxNNwKdWB\"]"));
		account.click();
		WebElement menuLogout = driver.findElement(By.xpath("//button[@data-test-id=\"header-member-menu-logout\"]"));
		menuLogout.click();
		WebElement btnLogout = driver.findElement(By.id("logout-submit"));
		btnLogout.click();
		WebElement homeLogo = driver.findElement(By.xpath("//a[@class='global-header-section-logo']"));
		homeLogo.click();
		driver.navigate().refresh();
	}

	public void verifyMenuAndPrint() {
		System.out.println(driver.findElement(By.xpath("//div[@class='_1tvnpiDHjeZfwG']")).getText());
	}

	public void createWorkspace() {
		WebElement btnCreate = driver.findElement(By.xpath("//button[@data-test-id='header-create-menu-button']"));
		btnCreate.click();
		WebElement btnCreateWorkspace = driver.findElement(By.xpath("(//button[@class='_3Qtx4lodxp9J0E'])[3]"));
		btnCreateWorkspace.click();
		WebElement txtWorkspaceName = driver.findElement(By.xpath("//input[@class='_3wY_Q5bDf-T1iP']"));
		txtWorkspaceName.sendKeys("Jayati Inc.");
		WebElement drpWorkspaceTypeClick = driver
				.findElement(By.xpath("//*[@id=\"layer-manager-overlay\"]//div[@class=\" css-191o3mb\"]"));
		drpWorkspaceTypeClick.click();
		WebElement drpWorkspaceTypeSelect = driver.findElement(By.xpath("//li[contains(text(),'Engineer')]"));
		drpWorkspaceTypeSelect.click();
		WebElement txaWorkspaceDescription = driver.findElement(By.xpath("//textarea[@class='_3WDCVZHeu3AAvH']"));
		txaWorkspaceDescription.sendKeys("Learning Selenium");
		WebElement btnContinue = driver.findElement(By.xpath("//button[@type='submit']"));
		btnContinue.click();
		WebElement doLaterLink = driver.findElement(By.xpath("//a[@data-test-id='show-later-button']"));
		doLaterLink.click();
	}

	public void createBoard() {
		WebDriverWait wait = new WebDriverWait(driver, 2);
		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//a[@class='tabbed-pane-nav-item-button js-org-profile active']"))));
		WebElement spanCreateNewBoard = driver.findElement(By.xpath("//span[contains(text(),'Create')]"));
		spanCreateNewBoard.click();
		WebElement txtBoardTitle = driver.findElement(By.xpath("//input[@data-test-id='create-board-title-input']"));
		txtBoardTitle.sendKeys("Selenium Tutorial");
		WebElement drpWorkspaceClick = driver.findElement(By.xpath("(//div[@class=' css-191o3mb'])[3]"));
		drpWorkspaceClick.click();
		WebElement drpWorkspaceSelect = driver.findElement(By.xpath("//li[contains(text(),'Jayati In')]"));
		drpWorkspaceSelect.click();
		WebElement drpVisibilityClick = driver.findElement(By.xpath("(//div[@class=' css-191o3mb'])[4]"));
		drpVisibilityClick.click();
		WebElement drpVisibilitySelect = driver.findElement(By.xpath("//div[contains(text(),'Private')]"));
		drpVisibilitySelect.click();
		WebElement btnCreate = driver.findElement(By.xpath("(//button[contains(text(),'Create')])[2]"));
		btnCreate.click();
	}

	public void openBoard() {
		WebElement boardLink = driver.findElement(By.xpath("(//a[@class='board-tile'])[1]"));
		boardLink.click();
	}

	@DataProvider(name = "DataProvider")
	public Object[][] readData(Method method) {
		Object[][] tempArray = null;
		int testCaseCount = 0;
		try {
			FileInputStream file = new FileInputStream(new File("src\\test\\java\\resources\\testData.xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
//			System.out.println(method.getDeclaringClass().getName());
//			System.out.println(method.getDeclaringClass().getCanonicalName());
			System.out.println(method.getDeclaringClass().getSimpleName());
			XSSFSheet sheet = workbook.getSheet(method.getDeclaringClass().getSimpleName());
			tempArray = new Object[sheet.getLastRowNum()][1];

			Iterator<Row> itr = sheet.iterator();
			Row keys = itr.next();
			while (itr.hasNext()) {
				Row row = itr.next();
				if (row.getCell(0).getStringCellValue().equals(method.getName())) {
					HashMap<String, String> map = new HashMap<>();
					Iterator<Cell> cellIterator = keys.iterator();
					int cellNumber = 1;
					cellIterator.next();
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
//						System.out.println(cell.getStringCellValue());
//						System.out.println(row.getCell(cellNumber).getStringCellValue());
						map.put(cell.getStringCellValue(), row.getCell(cellNumber).getStringCellValue());
						cellNumber++;
					}
//					testData[testCaseCount][0] = new Object[] { map };
					tempArray[testCaseCount][0] = map;
					testCaseCount++;
				}
			}
			workbook.close();
			file.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		Object[][] testData = new Object[testCaseCount][1];
		for (int i = 0; i < testCaseCount; i++) {
			testData[i] = tempArray[i];
		}
		return testData;
	}

	@BeforeMethod
	public void setup() throws Exception {
		launchBrowser();
		//login();
		Reports.startTest(driver);
	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		//logout();
		//driver.quit();
		if (result.getStatus()==1) {
			Reports.log("PASS","Test case has passed",true);
		}
		else {
			Reports.log("FAIL","Test case has failed"+result.getThrowable(),false);
		}			
		Reports.endTest();
	}

	@BeforeSuite
	public void loadObjects() {
		objectRepo.LoadData();
		Reports.createReport();
	}

	@AfterSuite
	public void closeObjects() {
		// objectRepo.close();
		Reports.closeTest();
	}
}
