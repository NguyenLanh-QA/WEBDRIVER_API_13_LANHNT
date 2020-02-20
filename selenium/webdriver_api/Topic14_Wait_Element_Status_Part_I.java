package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

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

public class Topic14_Wait_Element_Status_Part_I {

	// Khai báo biến driver đại diện cho selenium webdriver
	WebDriver driver;
	boolean status;
	WebDriverWait waitExplicit;

	// Pre-Condition
	@BeforeClass
	public void beforeClass() {
		// Khởi tạo trình duyệt firefox mới nhất
		// set property cho gecko
		System.setProperty("webdriver.gecko.driver", ".\\libraries\\geckodriver.exe");
		driver = new FirefoxDriver();

		// Chorme
		// System.setProperty("webdriver.chrome.driver",
		// ".\\libraries\\chromedriver.exe");

		// driver = new ChromeDriver();

		waitExplicit = new WebDriverWait(driver, 15);
		// Chờ chơ element được hiển thị trước khi tương tác trong vòng 30s
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Phóng to trình duyệt
		driver.manage().window().maximize();

	}

	//@Test
	public void TC_01_ElementDisplayOrVisible()  {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		// Chờ cho element hiển thị ra
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='SubmitLogin']")));
		// Điều kiện 1- Element có hiển thị trên UI+ có trong DOM - PASSED
		status = driver.findElement(By.xpath("//button[@id='SubmitLogin']")).isDisplayed();// Kiểm tra element hiển thị

		System.out.println("Element có hiển thị trên UI+ có trong DOM = " + status);

		// Điều kiện 2- Element không hiển thị trên UI nhưng có trong DOM - PASSED
		// Chờ cho element không hiển thị ra
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='create_account_error']")));
		status = driver.findElement(By.xpath("//div[@id='create_account_error']")).isDisplayed();
		System.out.println("Element không hiển thị trên UI nhưng có trong DOM = " + status);

		// Điều kiện 3- Element không hiển thị trên UI và không có trong DOM (không check được bằng điều kiện này) --FALSED
		// Chờ cho element không hiển thị ra
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@id='id_order']")));
		status = driver.findElement(By.xpath("//input[@id='id_order']")).isDisplayed();
		System.out.println("Element không hiển thị trên UI và không có trong DOM = " + status);

	}

	@Test
	public void TC_02_ElementUnDisplayOrInVisible() throws InterruptedException {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");

		// Điều kiện 1- Element có hiển thị trên UI+ có trong DOM (không check được bằng điều kiện này) --FALSED
		//waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//button[@id='SubmitLogin']")));


		// Điều kiện 2- Element không hiển thị trên UI nhưng có trong DOM - PASSED
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='create_account_error']")));


		// Điều kiện 3- Element không hiển thị trên UI và không có trong DOM - PASSED
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@id='id_order']")));

	}
	@Test
	public void TC_03_ElementPresence() throws InterruptedException {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		
		// Điều kiện 1- Element có hiển thị trên UI+ có trong DOM (không check được bằng điều kiện này)  - PASSED
		waitExplicit.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='SubmitLogin']")));
		
		
		// Điều kiện 2- Element không hiển thị trên UI nhưng có trong DOM  - PASSED
		waitExplicit.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='create_account_error']")));
		
		
		// Điều kiện 3- Element không hiển thị trên UI và không có trong DOM - --FALSED
		//waitExplicit.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='id_order']")));
		
	}

	public WebElement findElement(String locator) {
		return driver.findElement(By.xpath(locator));

	}

	public boolean isDisplay(String locator) {

		return findElement(locator).isDisplayed();

	}

	// Pro-conditon
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.quit();
	}

}
