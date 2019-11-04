package System_api;
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

public class Topic_02_WebElement {
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
		driver.get("https://id.zing.vn/v2/register");
		
		//Lấy title trang web
		driver.getTitle();
	}
	

	@Test
	public void TC_01() {
		
		//ID
		driver.findElement(By.id("regacc_fullname")).sendKeys("Nguyễn Thị Lanh");
		driver.findElement(By.id("regacc_account")).sendKeys("nguyenlanhvfu94");
		driver.findElement(By.id("check_account_valid")).click();
		driver.findElement(By.id("regacc_pwd")).sendKeys("123456Aa");
		driver.findElement(By.id("regacc_re_pwd")).sendKeys("123456Aa");
		
		//Chọn Ngày (droplist)
		Select date = new Select(driver.findElement(By.id("dob")));
		date.selectByVisibleText("16");
		date.selectByValue("16");
		
		//Chọn Tháng
		Select month= new Select(driver.findElement(By.id("mob")));
		month.selectByVisibleText("Tháng 12");
		
		//Chọn Năm
		Select year= new Select(driver.findElement(By.id("yob")));
		year.selectByVisibleText("1994");
		
		//Chọn giới tính (radiobutton)
		driver.findElement(By.xpath("//input[@name='gender'][@value='0']")).click();
		
		//Chọn linktext
		driver.findElement(By.linkText("Điều khoản sử dụng")).click();
			
			
		
	}


	//Pro-conditon
	@AfterClass
	public void afterClass() {
		//Tắt trình duyệt
		driver.quit();
	}

}


