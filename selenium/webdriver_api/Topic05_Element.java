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

public class Topic05_Element {
	// Khai báo biến driver đại diện cho selenium webdriver
	WebDriver driver;
	By emailTextboxBy=By.xpath("//input[@id='mail']");
	By age18RadioBy=By.xpath("//input[@id='under_18']");
	By eduTextareaBy=By.xpath("//textarea[@id='edu']");
	By jobRole1By=By.xpath("//select[@id='job1']");
	By developmentcheckboxBy=By.xpath("//input[@id='development']");
	By slide1By=By.xpath("//input[@id='slider-1']");
	By passwordBy=By.xpath("//input[@id='password']");
	By ageradioDisable=By.xpath("//input[@id='radio-disabled']");
	By slide1By=By.xpath("//input[@id='slider-1']");
	By slide1By=By.xpath("//input[@id='slider-1']");
	By slide1By=By.xpath("//input[@id='slider-1']");
	
	




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

	

	@Test
	public void TC_01_Display() {
	driver.get("https://automationfc.github.io/basic-form/index.html");
	WebElement emailTextbox=driver.findElement(emailTextboxBy);
	if(emailTextbox.isDisplayed())
	{
		emailTextbox.sendKeys("Automaiton Testing");
	}
	WebElement age18Radio=driver.findElement(age18RadioBy);
	if(age18Radio.isDisplayed())
	{
		age18Radio.click();
	}
	WebElement eduTextarea=driver.findElement(eduTextareaBy);
	if(eduTextarea.isDisplayed())
	{
		eduTextarea.sendKeys("Automaiton Testing");
	}
		
	}

	@Test
	public void TC_02_Enable() {
	driver.get("https://automationfc.github.io/basic-form/index.html");
	//Email
	WebElement emailTextbox=driver.findElement(emailTextboxBy);
	if(emailTextbox.isEnabled())
	{
		System.out.println("Element ["+emailTextbox+"] is enabled");
	}
	else
	{
		System.out.println("Element ["+emailTextbox+"] is disabled");
	}
	//Age
	WebElement age18Radio=driver.findElement(age18RadioBy);
	if(age18Radio.isEnabled())
	{
		System.out.println("Element ["+age18Radio+"] is enabled");
	}
	else
	{
		System.out.println("Element ["+age18Radio+"] is disabled");
	}
	//Education
	WebElement eduTextarea=driver.findElement(eduTextareaBy);
	if(eduTextarea.isEnabled())
	{
		System.out.println("Element ["+eduTextarea+"] is enabled");
	}
	else
	{
		System.out.println("Element ["+eduTextarea+"] is disabled");
	}
	}
	
	
	//@Test
	public void TC_03_Selected() {
		
		


		
	
	}





	// Pro-conditon
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.close();
	}

}
