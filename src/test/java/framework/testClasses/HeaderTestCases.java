package framework.testClasses;

import org.testng.annotations.Test;

import framework.businessLayer.Header;
import framework.util.BaseClass;
import framework.util.ObjectRepo;

public class HeaderTestCases extends BaseClass {
	
	@Test
	public void testCase1() {
		Header header = new Header(driver, objectRepo);
		header.verifyMenu();
	}
}