package framework.testClasses;

import org.testng.annotations.Test;

public class WorkspaceTestCases {
	@Test
	public void createWorkspace() {

	}

	@Test(dependsOnMethods = "createWorkspace")
	public void deleteWorkspace() {

	}
}