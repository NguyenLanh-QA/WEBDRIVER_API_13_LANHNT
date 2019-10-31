package webdriver_api;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;

public class Topic_02_Excersie_WebElement2 {
	// Khai báo biến driver đại diện cho selenium webdriver
	WebDriver driver;

	//Pre-Condition
	@BeforeClass
	public void beforeClass() {
		// Khởi tạo trình duyệt firefox
		driver = new FirefoxDriver();
		
		// Chờ chơ element được hiển thị trước khi tương tác trong vòng 30s
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Phóng to trình duyệt
		driver.manage().window().maximize();
		
		//Mở ra 1 trang web 
		driver.get("http://live.demoguru99.com");
		
		//Lấy title trang web
		driver.getTitle();
	}
	

	@Test
	public void TC_01_LoginEmpty() {
		
		//Click link My Account
		driver.findElement(By.linkText("MY ACCOUNT")).click();
		//Click button Login
		driver.findElement(By.id("send2")).click();
		
		//Check verify trường Email
		String notificationEmail=driver.findElement(By.id("advice-required-entry-email")).getText();
		String expectEmail="This is a required field.";
		
		//Check verify trường Password
		String notificationPass=driver.findElement(By.id("advice-required-entry-pass")).getText();
		String expectPass="This is a required field.";
		if((expectEmail.equals(notificationEmail)) && (expectPass.equals(notificationPass)))
		{
			System.out.println("TC01_Passed");
		}
		else
		{
			System.out.println("TC01_Falsed");
		}
		
	}
	@Test
	public void TC_02_LoginWithValueInvalid() {
		
						
		//Check verify trường Email
		driver.findElement(By.id("email")).sendKeys("123434234@12312.123123");
		//Click button Login
		driver.findElement(By.id("send2")).click();
		
		//Check thông báo Email
		String notificationEmail=driver.findElement(By.id("advice-validate-email-email")).getText();
		String expectEmail="Please enter a valid email address. For example johndoe@domain.com.";
		if(expectEmail.equals(notificationEmail)) 
		{
			System.out.println("TC02_Passed");
		}
		else
		{
			System.out.println("TC02_Falsed");
		}
		
		//Xóa dữ liệu tại Email
		driver.findElement(By.id("email")).clear();
	}
	@Test
	public void TC_03_LoginWithPasswordLess6Characters() {
		
						
		//Nhập dữ liệu trường Email
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		
		//Nhập password < 6 ký tự
		driver.findElement(By.id("pass")).sendKeys("123");
		
		//Click button Login
		driver.findElement(By.id("send2")).click();
		
		//Check thông báo Email
		String notificationPass=driver.findElement(By.id("advice-validate-password-pass")).getText();
		String expectPass="Please enter 6 or more characters without leading or trailing spaces.";
		if(expectPass.equals(notificationPass)) 
		{
			System.out.println("TC03_Passed");
		}
		else
		{
			System.out.println("TC03_Falsed");
		}
		
		//Xóa dữ liệu tại Email
		driver.findElement(By.id("email")).clear();
		//Xóa dữ liệu tại Password
		driver.findElement(By.id("pass")).clear();
	}
	@Test
	public void TC_04_LoginWithPasswordIncorrect() {
		
						
		//Nhập dữ liệu trường Email
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		
		//Nhập password < 6 ký tự
		driver.findElement(By.id("pass")).sendKeys("123123123");
		
		//Click button Login
		driver.findElement(By.id("send2")).click();
		
		//Check thông báo Email
		String notificationPass=driver.findElement(By.xpath("//*[@id='top']/body/div/div/div[2]/div/div/div/ul/li/ul/li/span")).getText();
		String expectPass="Invalid login or password.";
		if(expectPass.equals(notificationPass)) 
		{
			System.out.println("TC04_Passed");
		}
		else
		{
			System.out.println("TC04_Falsed");
		}
		
		//Xóa dữ liệu tại Email
		driver.findElement(By.id("email")).clear();
		//Xóa dữ liệu tại Password
		driver.findElement(By.id("pass")).clear();
	}
	
	@Test
	public void TC_05_CreateAnAccount() {
		
		
		//Click button Creat my account
		driver.findElement(By.xpath("//*[@id='login-form']/div/div[1]/div[2]/a/span/span")).click();
						
		//Nhập dữ liệu trường FirsName
		driver.findElement(By.id("firstname")).sendKeys("Lanh");
		
		//Nhập password < 6 ký tự
		driver.findElement(By.id("lastname")).sendKeys("Nguyễn Thị");
		
		//Nhập email
		driver.findElement(By.id("email_address")).sendKeys("a"+Math.floor(Math.random())+"@gmail.com");
		
		
		//Nhập password
		driver.findElement(By.id("password")).sendKeys("Lanh1994");
		
		//Nhập password
		driver.findElement(By.id("confirmation")).sendKeys("Lanh1994");
		
		//Click button Register
		driver.findElement(By.xpath("//*[@id='form-validate']/div[2]/button")).click();
		
		//Check thông báo 
		String notificationPass=driver.findElement(By.xpath("//*[@id='top']/body/div/div/div[2]/div/div/div/ul/li/ul/li/span")).getText();
		String expectPass="Invalid login or password.";
		if(expectPass.equals(notificationPass)) 
		{
			System.out.println("TC04_Passed");
		}
		else
		{
			System.out.println("TC04_Falsed");
		}
		
		//Xóa dữ liệu tại Email
		driver.findElement(By.id("email")).clear();
		//Xóa dữ liệu tại Password
		driver.findElement(By.id("pass")).clear();
	}
	
	//Pro-conditon
	@AfterClass
	public void afterClass() {
		//Tắt trình duyệt
		driver.close();
	}

}


