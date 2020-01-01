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

public class Topic05_Element2 {
	// Khai báo biến driver đại diện cho selenium webdriver
	WebDriver driver;
	By userNameBy=By.xpath("//input[@name='uid']");
	By passWordBy=By.xpath("//input[@name='password']");
	




	// Pre-Condition
	@BeforeClass
	public void beforeClass() {
		// Khởi tạo trình duyệt firefox
		driver = new FirefoxDriver();

		// Chờ chơ element được hiển thị trước khi tương tác trong vòng 30s
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Phóng to trình duyệt
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/v4/");
		senKeyToElement(userNameBy, "mngr233468");
		senKeyToElement(passWordBy, "tyqAhaq");
		clickToElement(By.xpath("//input[@name='btnLogin']"));
		
		String homepageMess1=driver.findElement(By.xpath("//marquee")).getText();
		Assert.assertEquals(homepageMess1, "Welcome To Manager's Page of Guru99 Bank");
		
		String homepageMess2=driver.findElement(By.xpath("//tr[@class='heading3']/td")).getText();
		Assert.assertEquals(homepageMess2, "Manger Id : "+userNameBy);
		

	}

	

	@Test
	public void TC_01_textboxTextarea() {
	
	
	
	
	


	}
	
	public void senKeyToElement(By by, String value)
	{
		WebElement element= driver.findElement(by);
		element.sendKeys(value); 
	
	}
	public void clickToElement(By by)
	{
		WebElement element= driver.findElement(by);
		element.click(); 
	
	}

	

	// Pro-conditon
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.close();
	}

}
