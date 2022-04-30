package framework.businessLayer;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import framework.util.ObjectRepo;

public class Header {
	WebDriver driver;
	ObjectRepo objectRepo;

	public Header(WebDriver driver, ObjectRepo objectRepo) {
		this.driver = driver;
		this.objectRepo = objectRepo;
	}

	public void verifyMenu() {
		WebElement menu = driver.findElement(objectRepo.get("menu"));
		assertEquals(menu.getText(), "Workspaces");
	}
}