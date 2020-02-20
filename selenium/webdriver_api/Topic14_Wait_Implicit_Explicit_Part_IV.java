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

public class Topic14_Wait_Implicit_Explicit_Part_IV {

	// Khai báo biến driver đại diện cho selenium webdriver
	WebDriver driver;
	WebDriverWait waitExplicit;

	By startButtonBy = By.xpath("//div[@id='start']/button");
	By loadingBarBy = By.xpath("//div[@id='loading']//img");
	By heloWorldBy = By.xpath("//h4[text()='Hello World!']");
	By loadingDateBy = By.xpath("//div[contains(@style,'position: absolute;')]//div[@class='raDiv']");

	
	// WebDriverWait waitExplicit;

	// Pre-Condition. Nếu dùng BeforeMethod thì sẽ chạy cho từng testcase
	@BeforeClass
	public void beforeClass() {
		// Khởi tạo trình duyệt firefox mới nhất
		// set property cho gecko
		System.setProperty("webdriver.gecko.driver", ".\\libraries\\geckodriver.exe");
		driver = new FirefoxDriver();
		waitExplicit = new WebDriverWait(driver, 10); // Wait rõ ràng

		// Chờ chơ element được hiển thị trước khi tương tác trong vòng 30s- ko chờ cho
		// element nào có trạng thái cụ thể mà đi tìm element
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Phóng to trình duyệt
		driver.manage().window().maximize();

	}

	// @Test
	public void TC_01_Implicit_Wait() {
		// 1. Mở app
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

		// Set lại 10s cho implicit và 3s bị ghi đè (3s không còn ý nghĩa nữa) và 10s sẽ
		// được set cho tất cả TC từ bước này trở đi
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// 2. Chờ cho Start button hiển thị để thao tác
		WebElement startButton = driver.findElement(startButtonBy);
		Assert.assertTrue(startButton.isDisplayed());
		// 3. Click vào Start button + Hiển thị Loading bar
		startButton.click();

		// Để loading bar biến mất thì mất khoảng 5s dù nhanh hay chậm

		// 5. Kiểm tra HelloWorld text được hiển thị
		WebElement heloWorld = driver.findElement(heloWorldBy);
		Assert.assertTrue(heloWorld.isDisplayed());

	}

	// @Test
	public void TC_02_Explicit_Wait() {
		// 1. Mở app
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		// Chờ cho button có thể click
		waitExplicit.until(ExpectedConditions.elementToBeClickable(startButtonBy));
		// click butotn
		driver.findElement(startButtonBy).click();

		// loading Bar hiển thị 5s và biến mất

		// Chờ cho Hello World được visible trước khi check hiển thị khi
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(heloWorldBy));
		Assert.assertTrue(driver.findElement(heloWorldBy).isDisplayed());

	}

	// Dùng invisiable
	//@Test
	public void TC_03_Explicit_Wait() {
		// 1. Mở app
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		// Chờ cho button có thể click
		waitExplicit.until(ExpectedConditions.elementToBeClickable(startButtonBy));
		// click butotn
		driver.findElement(startButtonBy).click();

		// loading Bar hiển thị 5s và biến mất

		// Chờ cho Loadingbar được invisible trước khi check HelloWorld được hiển thị
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(loadingBarBy));
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(heloWorldBy));

		Assert.assertTrue(driver.findElement(heloWorldBy).isDisplayed());

	}
	// Dùng invisiable
	@Test
	public void TC_04_Implicit_Wait() {
		// 1. Mở app
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		// In ra ngày được chọn
		WebElement daySelected= driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']"));
		System.out.println("Ngày được chọn = "+daySelected.getText());
		Assert.assertEquals(daySelected.getText(), "No Selected Dates to display.");
		driver.findElement(By.xpath("//a[text()='7']")).click();
		//Chờ icon loading biến mất
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(loadingDateBy));
		

		//Check cho ngày  là ngày được chọn
		Assert.assertTrue(driver.findElement(By.xpath("//td[@class='rcSelected']//a[text()='7']")).isDisplayed());
		daySelected= driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']"));

		//Kiểm tra ngày được chọn
		System.out.println("Ngày được chọn = "+daySelected.getText());
		Assert.assertEquals(daySelected.getText(), "Friday, February 07, 2020");
		
		
	}
	// Pro-conditon
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.quit();
	}

}
