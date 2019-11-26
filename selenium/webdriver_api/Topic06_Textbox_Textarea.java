package webdriver_api;



import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic06_Textbox_Textarea {
	// Khai báo biến driver đại diện cho selenium webdriver
	WebDriver driver;
	By userNameBy=By.xpath("//input[@name='uid']");
	By passWordBy=By.xpath("//input[@name='password']");
	By customernameBy=By.xpath("//input[@name='name']");
	By genderBy=By.xpath("//input[@value='f']");
	By dateofbirthBy=By.xpath("//input[@name='dob']");
	By addressBy=By.xpath("//textarea[@name='addr']");
	By cityBy=By.xpath("//input[@name='city']");
	By stateBy=By.xpath("//input[@name='state']");
	By pinoBy=By.xpath("//input[@name='pinno']");
	By telephoneoBy=By.xpath("//input[@name='telephoneno']");
	By emailBy=By.xpath("//input[@name='emailid']");
	By passwordBy=By.xpath("//input[@name='password']");
	By submitButton=By.xpath("//input[@value='Submit']");
	By genderTextbox=By.xpath("//input[@name='gender']");
	
	
	String userName="mngr233468";
	String passWord="tyqAhaq";
	//New
	String customerName="Lanh Nguyen";
	String gender="female";
	String dateofBirth="1994-01-16";
	String address="HoangMai ThanhTri HaNoi";
	String city="Ha Noi";
	String state="Thanh Tri";
	String pin="100001";
	String telephone="0358238398";
	String email="automation_" + randomNumber() + "@gmai.com";
	String pass="16011994";
	
	//Edit
	String editaddress="NguyenTrai ThanhXuan HaNoi";
	String editcity="Ha Noi";
	String editstate="ThanhXuan";
	String editpin="100005";
	String edittelephone="0358238399";
	String editemail="automation_" + randomNumber() + "@hotmail.com";
	
	String customerId;
	
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
		driver.get("http://demo.guru99.com/v4");
		senKeyToElement(userNameBy,userName);
		senKeyToElement(passWordBy, passWord);
		clickToElement(By.xpath("//input[@name='btnLogin']"));
		String homepageMess1= driver.findElement(By.xpath("//marquee")).getText();
		Assert.assertEquals(homepageMess1, "Welcome To Manager's Page of Guru99 Bank");
		
		Assert.assertTrue(IsDisplay(By.xpath("//td[(text()='Manger Id : "+userName+"')]")));
		

	}

	
	@Test
	public void TC_01_NewCustomer() {
		clickToElement(By.xpath("//a[text()='New Customer']"));
		//Input date
		senKeyToElement(customernameBy, customerName);
		clickToElement(genderBy);
		senKeyToElement(dateofbirthBy, dateofBirth);
		senKeyToElement(addressBy, address);
		senKeyToElement(cityBy, city);
		senKeyToElement(stateBy, state);
		senKeyToElement(pinoBy, pin);
		senKeyToElement(telephoneoBy, telephone);
		senKeyToElement(emailBy, email);
		senKeyToElement(passwordBy, pass);
		//click button Submit
		clickToElement(submitButton);
		
		//Check mess susscesfull
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='heading3' and text()='Customer Registered Successfully!!!']")).isDisplayed());

		// Get CustomerId
		customerId=driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		System.out.println(customerId);
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']//following-sibling::td")).getText(),customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']//following-sibling::td")).getText(),gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']//following-sibling::td")).getText(),dateofBirth);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']//following-sibling::td")).getText(),address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']//following-sibling::td")).getText(),city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']//following-sibling::td")).getText(),state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']//following-sibling::td")).getText(),pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']//following-sibling::td")).getText(),telephone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']//following-sibling::td")).getText(),email);

		
	}


	@Test
	public void TC_02_EditCustomer() {
		clickToElement(By.xpath("//a[text()='Edit Customer']"));
		
		driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerId);
		
		clickToElement(By.xpath("//input[@name='AccSubmit']"));
		//Check mess susscesfull
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='heading3' and text()='Edit Customer']")).isDisplayed());
		Assert.assertEquals(driver.findElement(customernameBy).getAttribute("value"),customerName);
		Assert.assertEquals(driver.findElement(genderTextbox).getAttribute("value"),gender);
		Assert.assertEquals(driver.findElement(dateofbirthBy).getAttribute("value"),dateofBirth);
		Assert.assertEquals(driver.findElement(addressBy).getText(),address);
		Assert.assertEquals(driver.findElement(cityBy).getAttribute("value"),city);
		Assert.assertEquals(driver.findElement(stateBy).getAttribute("value"),state);
		Assert.assertEquals(driver.findElement(telephoneoBy).getAttribute("value"),telephone);
		Assert.assertEquals(driver.findElement(pinoBy).getAttribute("value"),pin);
		Assert.assertEquals(driver.findElement(emailBy).getAttribute("value"),email);
		//Verify field disbale
		Assert.assertFalse(driver.findElement(customernameBy).isEnabled());
		Assert.assertFalse(driver.findElement(By.xpath("//input[@value='female']")).isEnabled());
		Assert.assertFalse(driver.findElement(dateofbirthBy).isEnabled());


		//Input date
		clearToElement(addressBy);
		senKeyToElement(addressBy, editaddress);
		clearToElement(cityBy);
		senKeyToElement(cityBy, editcity);
		clearToElement(stateBy);
		senKeyToElement(stateBy, editstate);
		clearToElement(pinoBy);
		senKeyToElement(pinoBy, editpin);
		clearToElement(telephoneoBy);
		senKeyToElement(telephoneoBy, edittelephone);
		clearToElement(emailBy);
		senKeyToElement(emailBy, editemail);
	
		
		//click button Submit
		clickToElement(submitButton);
		
		//Check mess susscesfull
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='heading3' and text()='Customer details updated Successfully!!!']")).isDisplayed());

		// Get CustomerId
		String customerId=driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		System.out.println(customerId);
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']//following-sibling::td")).getText(),customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']//following-sibling::td")).getText(),gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']//following-sibling::td")).getText(),dateofBirth);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']//following-sibling::td")).getText(),editaddress);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']//following-sibling::td")).getText(),editcity);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']//following-sibling::td")).getText(),editstate);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']//following-sibling::td")).getText(),editpin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']//following-sibling::td")).getText(),edittelephone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']//following-sibling::td")).getText(),editemail);

		
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
	public void clearToElement(By by)
	{
		WebElement element= driver.findElement(by);
		element.clear();
	
	}


	// Pro-conditon
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.close();
	}

}
