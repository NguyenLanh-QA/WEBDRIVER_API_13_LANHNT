package webdriver_api;

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

public class Topic14_FindElement_Part_II {

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
		// Chờ chơ element được hiển thị trước khi tương tác trong vòng 30s
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		// Phóng to trình duyệt
		driver.manage().window().maximize();

	}

	//@Test
	public void TC_01_FindElement() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		// Điều kiện 1- Không tìm thấy element --FALSED và trả ra kết quả No Such
		// Element
		// Trước khi đánh falsed: luôn tìm element theo chu kỳ là 0.5s/lần cho đến khi
		// hết timeout của implicitlyWait
		// Nếu như đang còn tìm Element mà chưa hết timeout nhưng Element đã xuất hiện
		// thì vẫn Pass (Trường hợp Page loading)
		// Nếu như chờ hết timeout mà Element chưa xuất hiện thì False testcase
		driver.findElement(By.xpath("//input[@id='id_order']")).sendKeys("12345");

		// Điều kiện 2- Tìm thấy duy nhất 1 element
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("12345");

		// Điều kiện 3- Tìm thấy nhiều hơn 1 element thì luôn thao tác với thằng đầu tiên
		
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}

	@Test
	public void TC_02_FindElements() throws InterruptedException {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");

		// Điều kiện 1- Không tìm thấy element
		elements = driver.findElements(By.xpath("//input[@id='id_order']"));

		// Nếu như đang còn tìm Element mà chưa hết timeout nhưng Element đã xuất hiện
		// thì vẫn Pass
		// Luôn tìm element theo chu kỳ là 0.5s/lần cho đến khi hết timeout của
		// implicitlyWait
		// Không đáng false testcase và trả về 1 empty list (list rỗng không có phần tử
		// nào)
		System.out.println("Size của list= " + elements.size());
		Assert.assertTrue(elements.isEmpty());
		Assert.assertEquals(elements.size(), 0);

		// Điều kiện 2- Tìm thấy duy nhất 1 element
		elements = driver.findElements(By.xpath("//input[@id='email']"));
		System.out.println("Size của list= " + elements.size());
		Assert.assertFalse(elements.isEmpty());
		Assert.assertEquals(elements.size(), 1);
		
		elements.get(0).sendKeys("auto@gmail.com");
		
		// Điều kiện 3- Tìm thấy nhiều hơn 1 element 
		elements = driver.findElements(By.xpath("//button[@type='submit']"));
		System.out.println("Size của list= " + elements.size());
		Assert.assertFalse(elements.isEmpty());
		Assert.assertEquals(elements.size(), 4);

	}

	// Pro-conditon
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.quit();
	}

}
