package webdriver_api;





import java.util.ArrayList;
import java.util.List;
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


public class Topic07_Dropdown_I {
	// Khai báo biến driver đại diện cho selenium webdriver
	WebDriver driver;
	By job1Droplist=By.xpath("//select[@id='job1']");
	By job2Droplist=By.id("job2");
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
	Select select;
	int numberSelect;
	
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
		select = new Select(driver.findElement(job1Droplist));
		Assert.assertFalse(select.isMultiple());

		// Kiểm tra chọn giá trị Automation Testing bằng selectByVisibleText
		select.selectByVisibleText("Automation Testing");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Automation Testing");

		// Kiểm tra chọn giá trị Manual Testing bằng selectByValue
		
		select.selectByValue("manual");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Manual Testing");

		// Kiểm tra chọn giá trị Manual Testing bằng selectByIndex
		select.selectByIndex(9);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Functional UI Testing");

		// Kiểm tra số lượng trong droplist
		int numberSelect = select.getOptions().size();
		Assert.assertEquals(numberSelect, 10);
		
		//In ra tất cả các giá trị nằm trong droplist--> Xem tất cả các giá trị trong droplist này sort đúng hay không
		//Sort ASC/DESC
		List <WebElement> allOptions=select.getOptions();
		
		for (WebElement option : allOptions) {
			System.out.println(option.getText());
		}
		//Sort ASC/DESC
		//List<String> arrayList = new ArrayList<String>();
		//for (WebElement option : allOptions) {
			//arrayList.add(option.getText());
		//}
		
		
		// Kiểm tra dropdown có hỗ trợ multi-select
		select = new Select(driver.findElement(job2Droplist));
		Assert.assertTrue(select.isMultiple());
		
		//Chọn nhiều giá trị tròng droplist
		select.selectByVisibleText("Automation");
		select.selectByVisibleText("Mobile");
		select.selectByVisibleText("Desktop");
		
		//Kiểm tra 3 giá trị được chọn thành công
		List <WebElement> optionSelected=select.getAllSelectedOptions();
		Assert.assertEquals(optionSelected.size(), 3);
		List<String> arraySelected = new ArrayList<String>();
		for (WebElement select: optionSelected) {
			arraySelected.add(select.getText());
		}
		Assert.assertTrue(arraySelected.contains("Automation"));
		Assert.assertTrue(arraySelected.contains("Mobile"));
		Assert.assertTrue(arraySelected.contains("Desktop"));
		
		//Bỏ chọn 3 giá trị
		select.deselectAll();
		List <WebElement> optionDeselect=select.getAllSelectedOptions();
		Assert.assertEquals(optionDeselect.size(), 0);

		
		
	}
	
	@Test
	public void TC_02_Droplist_Extend() {
		driver.get("https://demo.nopcommerce.com/register");
		clickToElement(By.xpath("//a[@class='ico-register']"));
		Assert.assertEquals(driver.findElement(By.xpath("//strong[text()='Your Personal Details']")).getText(),"Your Personal Details");
		
		clickToElement(genderRadio);
		senKeyToElement(firtnameBy, firstnameTextbox);
		senKeyToElement(lastnameBy, lastnameTextbox);
		
		
		//Chọn Day=1 và có 32 items trong droplist
		Select select = new Select(driver.findElement(dateBy));
		select.selectByVisibleText("1");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "1");
		numberSelect=select.getOptions().size();
		Assert.assertEquals(numberSelect, 32);
		System.out.println("Day="+numberSelect);


		//Chọn Month=May và có 13 items trong droplist
		select = new Select(driver.findElement(monthBy));
		select.selectByVisibleText("May");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "May");
		numberSelect=select.getOptions().size();
		Assert.assertEquals(numberSelect, 13);
		System.out.println("Month="+numberSelect);
		
		//Chọn Year=1980 và có 112 items trong droplist
		select = new Select(driver.findElement(yearBy));
		select.selectByVisibleText("1980");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "1980");
		numberSelect=select.getOptions().size();
		Assert.assertEquals(numberSelect, 112);
		System.out.println("Year="+numberSelect);
		
		senKeyToElement(emailBy, emailTextbox);
		senKeyToElement(passBy, passTextbox);
		senKeyToElement(confirmpassBy, passTextbox);
		clickToElement((By.xpath("//input[@id='register-button']")));
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result' and text()='Your registration completed']")).getText(), "Your registration completed");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='header-links']//a[contains(text(),'My account')]")).getText(), "My account");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='header-links']//a[contains(text(),'Log out')]")).getText(), "Log out");
		
		

		
	}
			 
	
	public boolean IsDisplay(String xpathLocator)
	{
		WebElement element= driver.findElement(By.xpath(xpathLocator));
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
