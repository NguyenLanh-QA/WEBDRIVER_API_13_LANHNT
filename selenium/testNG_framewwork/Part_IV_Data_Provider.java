package testNG_framewwork;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Part_IV_Data_Provider {
	WebDriver driver;
	By emailTextbox = By.xpath("//[@id='email']");
	By passTextbox = By.xpath("//[@id='pass']");
	By loginButton = By.xpath("//[@id='send2']");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();

		// Chờ chơ element được hiển thị trước khi tương tác trong vòng 30s
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Phóng to trình duyệt
		driver.manage().window().maximize();

	}

	@Test(dataProvider = "user_pass")
	public void TC_01(String username, String passwword) {
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		driver.findElement(emailTextbox).sendKeys(username);
		driver.findElement(passTextbox).sendKeys(passwword);
		driver.findElement(loginButton).click();

		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains(username));
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");

	}

	@DataProvider(name = "user_pass")
	public Object[][] UserAndPasswordData() {
		return new Object [][] {
			{"selenium1_@gmail.com", "123456"},
			{"selenium2_@gmail.com", "123456"},
			{"selenium3_@gmail.com", "123456"}};
			
		
	}

	@AfterClass()
	public void afterClass() {

	}

}
