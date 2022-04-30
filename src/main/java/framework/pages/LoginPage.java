package framework.pages;

import static org.testng.Assert.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import framework.util.Reports;

public class LoginPage {
	public WebDriver driver;
	@FindBy(xpath = "//a[contains(text(),'Log')]")
	WebElement loginLink;
	@FindBy(id = "user")
	WebElement txtEmail;
	@FindBy(id = "login")
	WebElement btnLoginWithAtlassian;
	@FindBy(id = "password")
	WebElement txtPassword;
	@FindBy(id = "login-submit")
	WebElement btnLogin;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void login() {
		WebDriverWait wait = new WebDriverWait(driver, 1);
		loginLink.click();
		SoftAssert softAssert = new SoftAssert();
		softAssert.fail("Fail1");
		Reports.log("INFO","Login link was clicked",true);
		txtEmail.sendKeys("mondaldip27@gmail.com");
		btnLoginWithAtlassian.click();
		Reports.log("INFO","Login With Atlassian button was clicked",true);
		softAssert.fail("Fail2");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("login")));
		txtPassword.sendKeys("Password@Dip27");
		btnLogin.click();
		Reports.log("INFO","Login button was clicked",true);
		//Assert.fail();
		softAssert.assertAll();
	}

}