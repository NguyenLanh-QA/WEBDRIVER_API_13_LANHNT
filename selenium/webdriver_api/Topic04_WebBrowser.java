package webdriver_api;


import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic04_WebBrowser {
	// Khai báo biến driver đại diện cho selenium webdriver
	WebDriver driver;
	String url;
	String title;
	
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
		driver.get("http://live.guru99.com/index.php/");
		driver.findElement(By.xpath("//div[@class='footer']//a[(text()='My Account')]")).click();
		
	}

	@Test
	public void TC_01_VerifyUrl() {

		url=driver.getCurrentUrl();
		Assert.assertEquals(url, "http://live.demoguru99.com/index.php/customer/account/login/");
		System.out.println("Url Login Page="+ url);
		
		driver.findElement(By.xpath("//span[(text()='Create an Account')]")).click();
		url=driver.getCurrentUrl();
		Assert.assertEquals(url, "http://live.demoguru99.com/index.php/customer/account/create/");
		

	}

	@Test
	public void TC_02_VerifyTitle() {

		title= driver.getTitle();
		Assert.assertEquals(title, "Customer Login");
		
		driver.findElement(By.xpath("//span[(text()='Create an Account')]")).click();
		title= driver.getTitle();
		Assert.assertEquals(title, "Create New Customer Account");
						
	}

	@Test
	public void TC_03_NavigateFuntion() {
		
		driver.findElement(By.xpath("//span[(text()='Create an Account')]")).click();
		
		url=driver.getCurrentUrl();
		Assert.assertEquals(url, "http://live.demoguru99.com/index.php/customer/account/create/");
		
		driver.navigate().back();
		url=driver.getCurrentUrl();
		Assert.assertEquals(url, "http://live.demoguru99.com/index.php/customer/account/login/");
		
		driver.navigate().forward();
		title=driver.getTitle();
		Assert.assertEquals(title, "Create New Customer Account");


		
	
	}

	@Test
	public void TC_04_GetPageSource() {

	driver.getPageSource().contains("Login or Create an Account");
	driver.findElement(By.xpath("//span[(text()='Create an Account')]")).click();
	driver.getPageSource().contains("Create an Account");

	}



	// Pro-conditon
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.close();
	}

}
