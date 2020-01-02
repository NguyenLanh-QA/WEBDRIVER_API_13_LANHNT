package webdriver_api;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic09_User_Interactions {
	// Khai báo biến driver đại diện cho selenium webdriver
	WebDriver driver;
	Actions actions;
	Alert alert;

	//Pre-Condition
	@BeforeClass
	public void beforeClass() {
		// Khởi tạo trình duyệt firefox
		System.setProperty("webdriver.chrome.driver", ".\\libraries\\chromedriver.exe");
		driver = new ChromeDriver();
		
		actions= new Actions(driver);
		
			
		
		// Chờ chơ element được hiển thị trước khi tương tác trong vòng 30s
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Phóng to trình duyệt
		driver.manage().window().maximize();
		
		
	}

	//@Test
	public void TC_01_Hover_Move_Mouse() {
		driver.get("http://www.myntra.com/");
		actions.moveToElement(driver.findElement(By.xpath("//a[@class='desktop-main' and text()='Discover']"))).perform();
		driver.findElement(By.xpath("//a[@class='desktop-categoryLink' and text()='American Eagle']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='breadcrumbs-crumb' and text()='American Eagle']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='title-container']")).isDisplayed());
	}

	//@Test
	public void TC_02_Click_And_Hold() {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");

		List<WebElement> numbers = driver.findElements(By.xpath("//ol[@id='selectable']//li"));
		int beforclicknumbersize = numbers.size();
		System.out.println("Size=" + beforclicknumbersize);
		actions.clickAndHold(numbers.get(0)).moveToElement(numbers.get(7)).release().perform();
		List<WebElement> selectednumbers = driver.findElements(By.xpath("//ol[@id='selectable']//li[contains(@class,'ui-selected')]"));
		int afternumbersize = selectednumbers.size();
		System.out.println("Size=" + afternumbersize);
		for (WebElement number : selectednumbers) {
			System.out.println(number.getText());
		}
		Assert.assertEquals(selectednumbers.size(), 8);

	}

	//@Test
	public void TC_03_Click_And_Hold_Random() {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");

		List<WebElement> numbers = driver.findElements(By.xpath("//ol[@id='selectable']//li"));
		int beforclicknumbersize = numbers.size();
		System.out.println("Size=" + beforclicknumbersize);

		actions.keyDown(Keys.CONTROL).perform();
		actions.click(numbers.get(0)).click(numbers.get(2)).click(numbers.get(4)).click(numbers.get(6)).click(numbers.get(9));
		actions.keyUp(Keys.CONTROL).perform();

		List<WebElement> selectednumbers = driver.findElements(By.xpath("//ol[@id='selectable']//li[contains(@class,'ui-selected')]"));
		int afternumbersize = selectednumbers.size();
		System.out.println("Size=" + afternumbersize);
		for (WebElement number : selectednumbers) {
			System.out.println(number.getText());
		}
		Assert.assertEquals(selectednumbers.size(), 5);
	}

	//@Test
	public void TC_04_Double_Click() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		actions.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		Thread.sleep(3000);
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='demo']")).getText(), "Hello Automation Guys!");

	}

	@Test
	public void TC_05_Right_Click() throws InterruptedException {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		actions.contextClick(findElement("//span[text()='right click me']")).perform();
		actions.moveToElement(findElement("//span[text()='Quit']")).perform();
		Assert.assertTrue((isDisplay("//li[contains(@class,'context-menu-visible') and contains(@class,'context-menu-hover')]/span[text()='Quit']")));
		actions.click(findElement("//span[text()='Quit']")).perform();
		alert=driver.switchTo().alert();
		Thread.sleep(2000);
		Assert.assertEquals(alert.getText(), "clicked: quit");
		alert.accept();

	}

	public WebElement findElement(String locator) {
		return driver.findElement(By.xpath(locator));
		
	}
	public boolean isDisplay(String locator) {
		
		return findElement(locator).isDisplayed();
		
	}
	//Pro-conditon
	@AfterClass
	public void afterClass() {
		//Tắt trình duyệt
		driver.quit();
	}

}


