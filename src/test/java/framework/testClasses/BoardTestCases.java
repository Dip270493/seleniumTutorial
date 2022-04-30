package framework.testClasses;

import java.util.HashMap;

import org.testng.annotations.Test;

import framework.util.BaseClass;

public class BoardTestCases extends BaseClass{
	@Test(dataProvider = "DataProvider")
	public void createBoard(HashMap<Object, Object> testData) {
		System.out.println(testData.get("WorkspaceName"));
	}
	
	@Test
	public void editBoard() {

	}
	
	@Test
	public void deleteBoard() {

	}
}