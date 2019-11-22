package webdriver_api;


import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic04_Element {
	// Khai báo biến driver đại diện cho selenium webdriver
	WebDriver driver;
	WebElement email;
	WebElement age;
	WebElement education;

	// Pre-Condition
	@BeforeClass
	public void beforeClass() {
		// Khởi tạo trình duyệt firefox
		driver = new FirefoxDriver();

		// Chờ chơ element được hiển thị trước khi tương tác trong vòng 30s
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Phóng to trình duyệt
		driver.manage().window().maximize();

	}

	@BeforeMethod
	public void beforeMethod() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		
	}

	@Test
	public void TC_01_ElementDisplay() {
	email=driver.findElement(By.xpath("//input[@id='mail']"));
	email.isDisplayed();

	
	age=driver.findElement(By.xpath("//label[(text()='Under 18')]"));
	age.isDisplayed();

	education=driver.findElement(By.xpath("//textarea[@id='edu']"));
	education.isDisplayed();



	
	email.sendKeys("Automation Testing");
	age.click();
	education.sendKeys("Automation Testing");
	
	
	}

	//@Test
	public void TC_02_VerifyTitle() {

		
						
	}

	//@Test
	public void TC_03_NavigateFuntion() {
		
		


		
	
	}

	//@Test
	public void TC_04_GetPageSource() {

	driver.getPageSource().contains("Login or Create an Account");
	driver.findElement(By.xpath("//span[(text()='Create an Account')]")).click();
	driver.getPageSource().contains("Create an Account");

	}



	// Pro-conditon
	//@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.close();
	}

}
