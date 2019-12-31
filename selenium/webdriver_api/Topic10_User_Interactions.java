package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic10_User_Interactions {
	// Khai báo biến driver đại diện cho selenium webdriver
	WebDriver driver;
	Actions actions;

	// Pre-Condition
	@BeforeClass
	public void beforeClass() {
		// Khởi tạo trình duyệt firefox
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("dom.webnotifications.enabaled", false);
		driver = new FirefoxDriver(profile);
		actions = new Actions(driver);

		// Chờ chơ element được hiển thị trước khi tương tác trong vòng 30s
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Phóng to trình duyệt
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Hover_Move_Mouse() {
		driver.get("http://www.myntra.com/");
		actions.moveToElement(driver.findElement(By.xpath("//div[@class='desktop-navLink']//a[text()='Men']")))
				.perform();
		driver.findElement(By.xpath("//a[@class='desktop-categoryLink' and text()='Track Pants & Joggers']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Track Pants For Men']")).isDisplayed());

	}

	@Test
	public void TC_02_Click_And_Hold() {

	}

	@Test
	public void TC_03_() {

	}

	// Pro-conditon
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.quit();
	}

}
