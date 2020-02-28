package testNG_framewwork;

import org.testng.annotations.Test;
import org.testng.annotations.Parameters;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Part_VII_Dependencies {
	WebDriver driver;
	By emailTextbox = By.xpath("//input[@id='email']");
	By passTextbox = By.xpath(" //input[@id='pass']");
	By loginButton = By.xpath("//button[@id='send2']");

	@BeforeClass
	public void beforeClass(String browserName) {

		System.setProperty("webdriver.gecko.driver", ".\\libraries\\geckodriver.exe");
		driver = new FirefoxDriver();

		// Chờ chơ element được hiển thị trước khi tương tác trong vòng 30s
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Phóng to trình duyệt
		driver.manage().window().maximize();

	}

	@Test()
	public void TC01_Create_New_Customer() {

	}

	// Testcase 02 phụ thuộc TC01
	@Test(dependsOnMethods = "TC01_Create_New_Customer")
	public void TC02_Edit_Customer() {

	}

	@Test(dependsOnMethods = "TC01_Create_New_Customer")
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

	@AfterClass()
	public void afterClass() {
		driver.quit();

	}

}
