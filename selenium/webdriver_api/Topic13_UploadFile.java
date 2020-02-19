package webdriver_api;

import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class Topic13_UploadFile {

	String projectPath = System.getProperty("user.dir");

	// Khai báo biến driver đại diện cho selenium webdriver
	WebDriver driver;
	// Lấy ra đường dấn tương đối
	String path1 = projectPath + "\\Uploadfile\\1.jpg";
	String path2 = projectPath + "\\Uploadfile\\2.jpg";
	String path3 = projectPath + "\\Uploadfile\\3.png";

	String chromeAutoIT = projectPath + "\\UploadAutoIT\\chrome.exe";
	String firefoxAutoIT = projectPath + "\\UploadAutoIT\\firefox.exe";

	// Pre-Condition
	@BeforeClass
	public void beforeClass() {
		// Khởi tạo trình duyệt firefox mới nhất
		// set property cho gecko
		 System.setProperty("webdriver.gecko.driver", ".\\libraries\\geckodriver.exe");
		 driver = new FirefoxDriver();

		// Chorme
		//System.setProperty("webdriver.chrome.driver", ".\\libraries\\chromedriver.exe");

		//driver = new ChromeDriver();

		// Chờ chơ element được hiển thị trước khi tương tác trong vòng 30s
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Phóng to trình duyệt
		driver.manage().window().maximize();

	}

	// @Test
	public void TC_01_senKeys() throws InterruptedException {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		WebElement uploadFile = findElement("//input[@type='file']");

		// Upload nhiều file cùng 1 lúc và chỉ chạy trên trình duyệt firefox >=55
		uploadFile.sendKeys(path1 + "\n" + path2 + "\n" + path3);

		// uploadFile.sendKeys(path1);

		// uploadFile = findElement("//input[@type='file']");
		// uploadFile.sendKeys(path2);

		// uploadFile = findElement("//input[@type='file']");
		// uploadFile.sendKeys(path3);

		Thread.sleep(3000);
		List<WebElement> startButtons = findElements("//table//button[@class='btn btn-primary start']");
		for (WebElement start : startButtons) {
			start.click();
			Thread.sleep(2000);
		}

		Assert.assertTrue(isDisplay(("//p[@class='name']//a[@title='1.jpg']")));
		Assert.assertTrue(isDisplay(("//p[@class='name']//a[@title='2.jpg']")));
		Assert.assertTrue(isDisplay(("//p[@class='name']//a[@title='3.png']")));

	}

	//@Test
	public void TC_02_autoIT() throws InterruptedException, IOException {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		WebElement uploadFile = driver.findElement((By.cssSelector(".fileinput-button")));
		uploadFile.click();
		Thread.sleep(2000);

		// Thực thi runtime file (bấm là chạy ví dụ .exe/ .bat/ .sh)
		if (driver.toString().contains("chorme")) {
			Runtime.getRuntime().exec(new String[] { chromeAutoIT, path2 });
		} else {
			Runtime.getRuntime().exec(new String[] { firefoxAutoIT, path2 });
		}
		Thread.sleep(2000);
		findElement("//table//button[@class='btn btn-primary start']").click();

		Assert.assertTrue(isDisplay("//a[contains(text(),'2.jpg')]"));

	}
	@Test
	public void TC_03_robotClass() throws InterruptedException, IOException, AWTException {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		WebElement uploadFile = driver.findElement((By.cssSelector(".fileinput-button")));
		uploadFile.click();
		Thread.sleep(2000);

		StringSelection selection= new StringSelection(path1);
		//Copy the clipboard
		
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
		
		if(driver.toString().contains("chorme") || driver.toString().contains("firefox")) {
			WebElement upload = driver.findElement((By.cssSelector(".fileinput-button")));
			upload.click();
			Thread.sleep(2000);
		}
		else {
			System.out.println("Go to IE");
			WebElement uploadRobot = findElement("//input[@type='file']");
			uploadRobot.click();
			Thread.sleep(2000);

		}
		Robot robot= new Robot();
		Thread.sleep(2000);
		
		//Nhấn phím Enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		//Nhấn Ctrl+V
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);

		//Nhả phím Ctrl+V
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		
		
		//Nhấn phím Enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(2000);
		
		findElement("//table//button[@class='btn btn-primary start']").click();

		Assert.assertTrue(isDisplay("//a[contains(text(),'1.jpg')]"));

	}



	public List<WebElement> findElements(String locator) {
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
