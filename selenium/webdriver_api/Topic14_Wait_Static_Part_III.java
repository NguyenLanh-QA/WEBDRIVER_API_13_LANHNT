package webdriver_api;

import java.util.Date;
import java.util.List;
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

public class Topic14_Wait_Static_Part_III {

	// Khai báo biến driver đại diện cho selenium webdriver
	WebDriver driver;
	List<WebElement> elements;

	// WebDriverWait waitExplicit;

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

		// waitExplicit = new WebDriverWait(driver, 15);
		// Chờ chO element được hiển thị trước khi tương tác trong vòng 30s
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		// Chờ cho page được load xong
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

		// Phóng to trình duyệt
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Static() throws InterruptedException {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		System.out.println("Start sleep =" + getCurrentTime());
		//1000ms= 1s
		//1. Nếu page được load xong trong 3s thì hết 2s bị lãng phí
		//2. Nếu như page được load xong trong 10s hoặc > thì bị thiếu thời gian --> False TC
		//Nhược điểm: Fix cứng thời gian, không flexible
		//1 testcase có 30 steps, mỗi bước set 1s thì mất 30s
		//300TC trên 1 browser x 30steps x30s = 9000s (lãng phí khoảng 150 phút= 2.5 giờ) 
		//Nếu chạy trên 3 browsers: 9000s x 3 browsers= 7.5 giờ
		//Chỉ sử dụng trong trường hợp đặc biệt là dùng đối với Internet Explorer (bắt buộc phải dùng)
		Thread.sleep(5000);
		System.out.println("End sleep =" + getCurrentTime());
		driver.findElement(By.xpath("//input[@id='search_query_top']")).sendKeys("Automation");
	
	}

	
	public String getCurrentTime() {
		Date date=new Date();  
		return date.toString();
 
	}

	// Pro-conditon
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.quit();
	}

}
