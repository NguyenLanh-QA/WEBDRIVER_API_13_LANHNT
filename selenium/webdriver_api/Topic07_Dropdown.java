package webdriver_api;




import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sun.xml.internal.ws.client.SenderException;

public class Topic07_Dropdown {
	// Khai báo biến driver đại diện cho selenium webdriver
	WebDriver driver;
	By job1Droplist=By.xpath("//select[@id='job1']");
	By genderRadio=By.xpath("//input[@id='gender-female']");
	By firtnameBy=By.xpath("//input[@id='FirstName']");
	By lastnameBy=By.xpath("//input[@id='LastName']");
	By dateBy=By.xpath("//select[@name='DateOfBirthDay']");
	By monthBy=By.xpath("//select[@name='DateOfBirthMonth']");
	By yearBy=By.xpath("//select[@name='DateOfBirthYear']");
	By emailBy=By.xpath("//input[@id='Email']");
	By passBy=By.xpath("//input[@id='Password']");
	By confirmpassBy=By.xpath("//input[@id='ConfirmPassword']");


	//Input date
	
	String firstnameTextbox="Nguyễn Thị ";
	String lastnameTextbox="Lanh";
	String emailTextbox=randomNumber()+ "@gmail.com";
	String passTextbox="123456a";
	String confirmpassTextbox="123456a";
	
	public int randomNumber() {
	Random rand = new Random();
	return rand.nextInt(1000);
	
	}
	
	

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
	public void TC_01_Droplist() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		// Kiểm tra dropdown không hỗ trợ multi-select
		Select select = new Select(driver.findElement(job1Droplist));
		Assert.assertFalse(select.isMultiple());

		// Kiểm tra chọn giá trị Automation Tester bằng selectByVisibleText
		select.selectByVisibleText("Automation Tester");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Automation Tester");

		// Kiểm tra chọn giá trị Manual Tester bằng selectByValue
		
		select.selectByValue("manual");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Manual Tester");

		// Kiểm tra chọn giá trị Mobile Tester bằng selectByIndex
		select.selectByIndex(3);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Mobile Tester");

		// Kiểm tra số lượng trong droplist
		int numberSelect = select.getOptions().size();
		Assert.assertEquals(numberSelect, 5);
		
	}


	@Test
	public void TC_02_Droplist_Extend() {
		driver.get("https://demo.nopcommerce.com/register");
		clickToElement(By.xpath("//a[@class='ico-register']"));
		Assert.assertEquals(driver.findElement(By.xpath("//strong[text()='Your Personal Details']")).getText(),"Your Personal Details");
		
		clickToElement(genderRadio);
		senKeyToElement(firtnameBy, firstnameTextbox);
		senKeyToElement(lastnameBy, lastnameTextbox);
		
		Select select = new Select(driver.findElement(dateBy));
		select.selectByVisibleText("1");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "1");



		select = new Select(driver.findElement(monthBy));
		select.selectByVisibleText("May");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "May");

		select = new Select(driver.findElement(yearBy));
		select.selectByVisibleText("1980");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "1980");
		
		senKeyToElement(emailBy, emailTextbox);
		senKeyToElement(passBy, passTextbox);
		senKeyToElement(confirmpassBy, passTextbox);
		clickToElement((By.xpath("//input[@id='register-button']")));
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result' and text()='Your registration completed']")).getText(), "Your registration completed");
		IsDisplay(By.xpath("//input[@name='register-continue']"));
		

		
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
