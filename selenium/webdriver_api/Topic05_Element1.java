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

public class Topic05_Element1 {
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
	By bioghraphyBy=By.xpath("//textarea[@id='bio']");
	By jobRole2=By.xpath("//select[@id='job2']");
	By checkboxDisable=By.xpath("//input[@id='check-disbaled']");
	By slide2By=By.xpath("//input[@id='slider-2']");
	




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
	//Email

		if (IsDisplay(emailTextboxBy)) {
			senKeyToElement(emailTextboxBy, "Automation Testing");
		}
		// Age

		if (IsDisplay(age18RadioBy)) {
			clickToElement(age18RadioBy);
		}
		// Education
		if (IsDisplay(eduTextareaBy)) {
			senKeyToElement(eduTextareaBy, "Automation Testing");
		}
		
	}

	@Test
	public void TC_02_Enable() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		// Email

		Assert.assertTrue(IsEnable(emailTextboxBy));

		// Age

		Assert.assertTrue(IsEnable(age18RadioBy));

		// Education
		Assert.assertTrue(IsEnable(eduTextareaBy));

		// Job Role 1

		Assert.assertTrue(IsEnable(jobRole1By));

		// Development checkbox
		Assert.assertTrue(IsEnable(developmentcheckboxBy));

		// Slide 1
		Assert.assertTrue(IsEnable(slide1By));

		// ----DISABLE
		Assert.assertFalse(IsEnable(passwordBy));
		Assert.assertFalse(IsEnable(ageradioDisable));
		Assert.assertFalse(IsEnable(bioghraphyBy));
		Assert.assertFalse(IsEnable(checkboxDisable));
		Assert.assertFalse(IsEnable(slide2By));
	}
	

	@Test
	public void TC_03_Selected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		// Age
		clickToElement(age18RadioBy);
		Assert.assertTrue(IsSelected(age18RadioBy));

		// Development checkbox
		clickToElement(developmentcheckboxBy);
		Assert.assertTrue(IsSelected(developmentcheckboxBy));

		clickToElement(developmentcheckboxBy);
		Assert.assertFalse(IsSelected(developmentcheckboxBy));

	
	}
	
	public boolean IsDisplay(By by)
	{
		WebElement element= driver.findElement(by);
		if(element.isDisplayed()) {
			return true;
		}
		else
		{
			return false;
		}
	}
	public boolean IsEnable(By by)
	{
		WebElement element= driver.findElement(by);
		if(element.isEnabled()) {
			System.out.println("Element is enable");
			return true;
		}
		else
		{
			System.out.println("Element is disable");
			return false;
		}
	}
	public boolean IsSelected(By by)
	{
		WebElement element= driver.findElement(by);
		if(element.isSelected()) {
			System.out.println("Element is selected");
			return true;
		}
		else
		{
			System.out.println("Element is unselected");
			return false;
		}
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
