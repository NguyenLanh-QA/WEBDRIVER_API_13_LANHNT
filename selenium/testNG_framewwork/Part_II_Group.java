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

public class Part_II_Group {

	@BeforeClass //Before chạy mà fail thì after không chạy. Muốn chạy thì phải thêm alwaysRun=true
	public void beforeClass() {
		System.out.println("Run beforeClass");
	}

	@Test(groups = "user")
	public void TC_01() {
		System.out.println("Run testcase 01");
	}

	@Test(groups = "user")
	public void TC_02() {
		System.out.println("Run testcase 02");
	}

	@Test(groups = "pay")
	public void TC_03() {
		System.out.println("Run testcase 02");
	}

	@Test(groups = "pay")
	public void TC_04() {
		System.out.println("Run testcase 02");
	}

	@Test(groups = "shop")
	public void TC_05() { //TC_05 mà fail thì after vẫn chạy
		System.out.println("Run testcase 02");
	}

	@AfterClass (alwaysRun= true)
	public void afterClass() {
		System.out.println("Run afterClass");
	}

}
