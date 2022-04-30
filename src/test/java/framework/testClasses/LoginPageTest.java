package framework.testClasses;

import org.testng.annotations.Test;

import framework.pages.LoginPage;
import framework.util.BaseClass;

public class LoginPageTest extends BaseClass {
	@Test 
	public void testLogin() {
		LoginPage login = new LoginPage(driver);
		login.login();
	}
}