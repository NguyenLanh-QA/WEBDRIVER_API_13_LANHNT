package webdriver_api;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic14_Wait_Fluent_Part_V {

	// Khai báo biến driver đại diện cho selenium webdriver
	WebDriver driver;
	WebDriverWait explicitWait;
	Date date = new Date();

	// WebDriverWait waitExplicit;

	// Pre-Condition. Nếu dùng BeforeMethod thì sẽ chạy cho từng testcase
	@BeforeClass
	public void beforeClass() {
		// Khởi tạo trình duyệt firefox mới nhất
		// set property cho gecko
		System.setProperty("webdriver.gecko.driver", ".\\libraries\\geckodriver.exe");
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 10);

		// Chờ chơ element được hiển thị trước khi tương tác trong vòng 30s- ko chờ cho
		// element nào có trạng thái cụ thể mà đi tìm element
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// Phóng to trình duyệt
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Found_Element() {
		driver.get("http://demo.guru99.com/");

		// Implicit
		System.out.println("------STEP 01 - Start TC_01_Found_Element: " + new Date() + "-------");
		try {
			WebElement emailTextbox = driver.findElement(By.xpath("//input[@name='emailid']"));
			Assert.assertTrue(emailTextbox.isDisplayed());
		} catch (NoSuchElementException ex) {
			System.out.println("Switch to catch exception");
		}
		System.out.println("------STEP 01 - End TC_01_Found_Element: " + new Date() + "-------");

		// Explicit
		System.out.println("------STEP 02 - Start TC_01_Found_Element: " + new Date() + "-------");

		try {
			explicitWait
					.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//input[@name='btnLogin']")));
		} catch (Exception ex) {
			System.out.println("Switch to catch exception");
		}
		System.out.println("------STEP 02 - End TC_01_Found_Element: " + new Date() + "-------");
	}

	@Test
	public void TC_02_NotFound_Element() {
		explicitWait = new WebDriverWait(driver, 7);
		driver.get("http://demo.guru99.com/");

		// Implicit
		System.out.println("------STEP 01 - Start TC_Implicit: " + new Date() + "-------");
		try {
			WebElement emailTextbox = driver.findElement(By.xpath("//input[@name='automaition']"));
			Assert.assertTrue(emailTextbox.isDisplayed());
		} catch (NoSuchElementException ex) {
			System.out.println("------STEP 01 - nhảy vào catch: " + new Date() + "-------");
			System.out.println(ex.getMessage());
		}
		System.out.println("------STEP 01 - End Implicit: " + new Date() + "-------");

		// Explicit (Not found)
		System.out.println("------STEP 02 - Start TC_01_Found_Element: " + new Date() + "-------");

		try {
			WebElement emailTextbox = explicitWait
					.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@name='Login']"))));
			Assert.assertTrue(emailTextbox.isDisplayed());
		} catch (Exception ex) {
			System.out.println("------STEP 02 - nhảy vào catch:");
			System.out.println(ex.getMessage());
		}
		System.out.println("------STEP 02 - End TC_02_NotFound_Element: " + new Date() + "-------");

		// Explicit (Not found tham số là By)
		System.out.println("------STEP 03 - Start Explicit (By): " + new Date() + "-------");

		try {
			WebElement emailTextbox = explicitWait
					.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//input[@name='Login']"))));
			Assert.assertTrue(emailTextbox.isDisplayed());
		} catch (Exception ex) {
			System.out.println("------STEP 03 - nhảy vào catch:");
			System.out.println(ex.getMessage());
		}
		System.out.println("------STEP 03 - End TC_02_NotFound_Element: " + new Date() + "-------");

	}

	// Pro-conditon
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.quit();
	}

}
