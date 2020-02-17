package webdriver_api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic13_UploadFile {
	
	String projectPath = System.getProperty("user.dir");
	// Khai báo biến driver đại diện cho selenium webdriver
	WebDriver driver;
	// Lấy ra đường dấn tương đối
	String path1 = projectPath + "\\Uploadfile\\1.jpg";
	String path2 = projectPath + "\\Uploadfile\\2.jpg";
	String path3 = projectPath + "\\Uploadfile\\3.png";

	// Pre-Condition
	@BeforeClass
	public void beforeClass() {
		// Khởi tạo trình duyệt firefox
		//set property cho gecko

		System.setProperty("webdriver.gecko.driver", ".\\libraries\\geckodriver.exe");

		driver = new FirefoxDriver();
		
		

		// Chờ chơ element được hiển thị trước khi tương tác trong vòng 30s
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Phóng to trình duyệt
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_senKeys() throws InterruptedException {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		WebElement uploadFile = findElement("//input[@type='file']");
		
		//Upload nhiều file cùng 1 lúc và chỉ chạy trên trình duyệt firefox >=55
		uploadFile.sendKeys(path1 +"\n"+path2+"\n"+path3);

		//uploadFile.sendKeys(path1);
		
		//uploadFile = findElement("//input[@type='file']");
		//uploadFile.sendKeys(path2);
		
		//uploadFile = findElement("//input[@type='file']");
		//uploadFile.sendKeys(path3);
		
		Thread.sleep(3000);
		List <WebElement> startButtons= findElements("//table//button[@class='btn btn-primary start']");
		for(WebElement start:startButtons) {
			start.click();
			Thread.sleep(2000);
		}
		
		Assert.assertTrue(isDisplay(("//p[@class='name']//a[@title='1.jpg']")));
		Assert.assertTrue(isDisplay(("//p[@class='name']//a[@title='2.jpg']")));
		Assert.assertTrue(isDisplay(("//p[@class='name']//a[@title='3.png']")));

	}

	@Test
	public void TC_02_autoIT() {

	}

	@Test
	public void TC_03_() {

	}
	public List <WebElement> findElements(String locator) {
		return driver.findElements(By.xpath(locator));

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
