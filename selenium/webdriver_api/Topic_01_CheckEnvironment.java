package webdriver_api;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_01_CheckEnvironment {
	// Khai báo biến driver đại diện cho selenium webdriver
	WebDriver driver;

	//Pre-Condition
	@BeforeClass
	public void beforeClass() {
		// Khởi tạo trình duyệt firefox
		driver = new FirefoxDriver();
		
		// Chờ chơ element được hiển thị trước khi tương tác trong vòng 30s
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Phóng to trình duyệt
		driver.manage().window().maximize();
		
		//Mở ra 1 trang web 
		driver.get("http://demo.guru99.com/v4/");
	}

	@Test
	public void TC_01_ValidateCurrentUrl() {
		// Lấy ra Url của page hiện tại và gán vào biến loginPageUrl
		String loginPageUrl = driver.getCurrentUrl();
		
		//Các hàm verify dữ liệu của TestNG(true/false/equals)
		Assert.assertEquals(loginPageUrl, "http://demo.guru99.com/v4/");
	}

	@Test
	public void TC_02_ValidatePageTitle() {
		// Lấy ra title của page hiện tự và gán nó vào biến loginPageTitle
		String loginPageTitle = driver.getTitle();
		
		//Verify dữ liệu của biến loginPageTitle này bằng với giá trị mình mong muón
		Assert.assertEquals(loginPageTitle, "Guru99 Bank Home Page");
	}

	@Test
	public void TC_03_LoginFormDisplayed() {
		// Verify login form được hiển thị ở trang login
		Assert.assertTrue(driver.findElement(By.xpath("//form[@name='frmLogin']")).isDisplayed());
	}

	//Pro-conditon
	@AfterClass
	public void afterClass() {
		//Tắt trình duyệt
		driver.quit();
	}

}


