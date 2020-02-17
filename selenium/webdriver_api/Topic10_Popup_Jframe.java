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

public class Topic10_Popup_Jframe {
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

	// POP-UP_IFRAME
	@Test
	public void TC_01_Popup_Iframe() throws InterruptedException {
		// Mở ra 1 trang web
		driver.get("https://kyna.vn/");
		Thread.sleep(2000);
		// Steps 03: Kiểm tra nếu pop-up xuất hiện thì đóng pop-up
		List<WebElement> fancypopup = driver.findElements(By.xpath("//div[@class='fancybox-inner']"));
		if (fancypopup.size() > 0) {
			Assert.assertTrue(fancypopup.get(0).isDisplayed());
			driver.findElement(By.cssSelector(".fancybox-close")).click();
		}

		// Steps04- nếu pop-up không hiển thị thì switch đến Iframe
		// Switch vào iframe trước thì mới tương tác được với element trong iframe
		// Theo index
		// driver.switchTo().frame(1);
		// Theo id/ name
		// Theo Webelement
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='fanpage']//iframe")));
		boolean iframefacebook = driver.findElement(By.cssSelector("#facebook")).isDisplayed();
		Assert.assertTrue(iframefacebook);
		// Verify lượt like = 170 nghìn
		String likes = driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div"))
				.getText();
		Assert.assertEquals(likes, "170K likes");

		// Đã switch vào iframe rồi thì phải chuyển về main page
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//a[@class='button-login' and text()='Đăng nhập']")).click();
		driver.findElement(By.xpath("//input[@id='user-login']")).sendKeys("automationfc.vn@gmail.com");
		driver.findElement(By.xpath("//input[@id='user-password']")).sendKeys("automationfc.vn@gmail.com");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@id='btn-submit-login']")).click();

		WebElement userLogin = driver
				.findElement(By.xpath("//li[@class='account dropdown wrap']//span[@class='user']"));
		Assert.assertTrue(userLogin.isDisplayed());
		Thread.sleep(2000);
		Assert.assertEquals(userLogin.getText(), "Automation FC");

	}

	
	// Pro-conditon
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.quit();
	}

}
