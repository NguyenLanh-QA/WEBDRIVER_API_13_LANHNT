package testNG_framewwork;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Part_III_Priority_Skip {

	@Test(groups = "user")
	public void TC01_Create_New_Customer() {
		
	}

	@Test(groups = "user", description = "Edit thông tin người dùng")
	public void TC02_Edit_Customer() {
		
	}

	@Test(groups = "pay")
	public void TC03_Create_New_Account() {
		
	}

	@Test(groups = "pay")
	public void TC04_Edit_Account() {
		
	}

	@Test(groups = "shop", enabled = false)
	public void TC05_Delete_Account() { 
		
	}
	@Test(groups = "shop")
	public void TC06_Delete_Customer() { 
		
	}

}
