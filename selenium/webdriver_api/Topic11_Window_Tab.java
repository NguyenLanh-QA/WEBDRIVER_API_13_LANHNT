package webdriver_api;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic11_Window_Tab {
	// Khai báo biến driver đại diện cho selenium webdriver
	WebDriver driver;

	// Pre-Condition
	@BeforeClass
	public void beforeClass() {
		// Khởi tạo trình duyệt firefox
		System.setProperty("webdriver.chrome.driver", ".\\libraries\\chromedriver.exe");
		driver = new ChromeDriver();

		// Chờ chơ element được hiển thị trước khi tương tác trong vòng 30s
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Phóng to trình duyệt
		driver.manage().window().maximize();

	}

	// WINDOW_TAB
	//@Test
	public void TC01_Window_Tab() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		// Trả ra ID của tab đang đứng hay tab đang active
		String parentID = driver.getWindowHandle();
		System.out.println("Parent ID=" + parentID);

		// Click link google
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		// Trả ra ID của tất cả các tab

		switchToWindowByID(parentID);
		Assert.assertEquals(driver.getTitle(), "Google");

		// Quay lại parent window
		driver.switchTo().window(parentID);

		// Click link facebook
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		// Trả ra ID của tất cả các tab

		switchToWindowByTitle(parentID);
		Assert.assertEquals(driver.getTitle(), "Facebook - Đăng nhập hoặc đăng ký");

		// Quay lại parent window
		driver.switchTo().window(parentID);

		// Click tiki, switch qua tab mới
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		switchToWindowByTitle(parentID);
		Assert.assertEquals(driver.getTitle(), "Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");

		closeAllwindowwithoutparent(parentID);
		// Quay lại parent window
		Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");

	}

	// Hàm chạy với 2 cửa sổ (nhiều hơn 2 cửa sổ sẽ false)
	public void switchToWindowByID(String parentID) {
		// Lấy ra tất cả các ID đang có
		Set<String> allWindows = driver.getWindowHandles();
		// Dùng vòng lặp để duyệt qua từng ID
		for (String termID : allWindows) {
			// Kiểm tra ID nào mà khác với parent ID thì switch qua Tab/Window
			if (!termID.equals(parentID))

			{

				// switch qua tab/window đó
				driver.switchTo().window(termID);

				break;
			}
		}

	}

	// Hàm chạy với > 2 cửa sổ( đúng với mọi trường hợp)
	public void switchToWindowByTitle(String expectTitle) {
		// Lấy ra tất cả các ID đang có
		Set<String> allWindows = driver.getWindowHandles();
		// Dùng vòng lặp để duyệt qua từng ID
		for (String termID : allWindows) {
			// Switch vào window trước
			driver.switchTo().window(termID);
			// Get ra title đang có
			String currentWindow = driver.getTitle();
			// Kiểm tra title nào mà bắng với title mong muốn thì break khỏi vòng lắp
			if (currentWindow.equals(expectTitle))
				break;

		}

	}

	// Đóng tất cả các window/tab ngoại trừ parent

	public boolean closeAllwindowwithoutparent(String parentID) {
		// Lấy ra tất cả các ID đang có
		Set<String> allWindows = driver.getWindowHandles();

		// Dùng vòng lặp để duyệt qua từng ID
		for (String runWindows : allWindows) {
			// Nếu ID nào mà khác với parent thì switch qua
			if (!runWindows.equals(parentID)) {
				// switch vào window đó
				driver.switchTo().window(runWindows);
				// close tab/ window đang đứng
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
		if (driver.getWindowHandles().size() == 1)
			return true;
		else
			return false;

	}

	@Test
	public void TC02_Window_Tab() throws InterruptedException {
		driver.get("https://kyna.vn/");
		Thread.sleep(5000);
		// Steps 03: Kiểm tra nếu pop-up xuất hiện thì đóng pop-up
		List<WebElement> fancypopup = driver.findElements(By.xpath("//div[@class='fancybox-inner']"));
		if (fancypopup.size() > 0) {
			Assert.assertTrue(fancypopup.get(0).isDisplayed());
			driver.findElement(By.xpath("//img[@class='fancybox-image']")).click();
		}

		// Steps04- nếu pop-up không hiển thị thì switch đến Iframe
		// Switch vào iframe trước thì mới tương tác được với element trong iframe
		// Theo index
		// driver.switchTo().frame(1);
		// Theo id/ name
		// Theo Webelement

		// Trả ra ID của tab đang đứng hay tab đang active
		String parentID = driver.getWindowHandle();
		System.out.println("Parent ID=" + parentID);

		driver.findElement(By.xpath("//div[@class='hotline']//img[@alt='facebook']")).click();
		switchToWindowByTitle(parentID);
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - Trang chủ");

	}

	// Pro-conditon
	// @AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.quit();
	}

}
