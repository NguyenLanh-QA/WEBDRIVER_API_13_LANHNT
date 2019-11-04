package System_api;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Login_Logout {
	// Khai báo biến driver đại diện cho selenium webdriver
	WebDriver driver;

	//Pre-Condition
	@BeforeClass
	public void beforeClass() {
		// Khởi tạo trình duyệt firefox
		driver = new FirefoxDriver();
		
		// Chờ chơ element được hiển thị trước khi tương tác trong vòng 30s
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
		// Phóng to trình duyệt
		driver.manage().window().maximize();
		
		//Mở ra 1 trang web 
		driver.get("http://ban-attp.dichvucong.site/");
	}

	@Test
	public void TC_01_ValidateCurrentUrl() {
		// Lấy ra Url của page hiện tại và gán vào biến loginPageUrl
		String loginPageUrl = driver.getCurrentUrl();
		
		//Các hàm verify dữ liệu của TestNG(true/false/equals)
		Assert.assertEquals(loginPageUrl, "http://ban-attp.dichvucong.site/");
	}

	@Test
	public void TC_02_ValidatePageTitle() {
		// Lấy ra title của page hiện tự và gán nó vào biến loginPageTitle
		String loginPageTitle = driver.getTitle();
		
		//Verify dữ liệu của biến loginPageTitle này bằng với giá trị mình mong muón
		Assert.assertEquals(loginPageTitle, "Ban quản lý an toàn thực phẩm");
	}

	@Test
	public void TC_03_LoginFormDisplayed() {
		
		//Click Đăng nhập để chuyển đến trang Login
		
		WebElement ele = driver.findElement(By.xpath("//header[@class='wf100 header']//a[(text()='Đăng nhập')]"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", ele);
	
				
		// Verify login form được hiển thị ở trang login
		Assert.assertTrue(driver.findElement(By.xpath("//form[@class='login-form']")).isDisplayed());
	}
	@Test
	public void TC_04_LoginEmty() {
		
		//Click button Đăng nhập
		driver.findElement(By.xpath("//button[@name='btnLogin']")).click();
		//Kiểm tra thông báo
	    String emailError=driver.findElement(By.xpath("//span[@id='usernameOrEmailAddress-error']")).getText();
		Assert.assertEquals(emailError,"This field is required.");
	    String passError=driver.findElement(By.xpath("//span[@id='password-error']")).getText();
		Assert.assertEquals(passError,"This field is required.");
						
		
	}
	
	//Pro-conditon
	@AfterClass
	public void afterClass() {
		//Tắt trình duyệt
		driver.quit();
	}

}


