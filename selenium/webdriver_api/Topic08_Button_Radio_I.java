package webdriver_api;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic08_Button_Radio_I {
	// Khai báo biến driver đại diện cho selenium webdriver
	WebDriver driver;
	JavascriptExecutor javascript;
	//Select select;
	Alert alert;

	//Input date
	
	
	
	

	// Pre-Condition
	@BeforeClass
	public void beforeClass() {
		// Khởi tạo trình duyệt firefox
		driver = new FirefoxDriver();

		// Chờ chơ element được hiển thị trước khi tương tác trong vòng 30s
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Phóng to trình duyệt
		driver.manage().window().maximize();
		javascript= (JavascriptExecutor) driver;
		//select=new Select (driver.findElement(By.xpath("")));
 		
	}
	//Kiểm tra element enable, disable, selected
	//@Test
	public void TC_01() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		//button enable
		elementEnable("//button[@id='button-enabled']");
				
		//radiobutton
		elementEnable("//input[@id='under_18']");
		elementSelected("//input[@id='under_18']");
		//checkbox
		elementEnable("//input[@id='development']");
		
		//button disable
		elementEnable("//button[@id='button-disabled']");
	}
	
	//Check javascript
	//@Test
	public void TC_02() {

		driver.get("https://demo.nopcommerce.com");
		javascript.executeScript("arguments[0].click()", driver.findElement(By.xpath("//a[text()='Desktops ']")));
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Desktops']")).isDisplayed());
	}
	public void TC_03_CheckandUncheck() {

		driver.get("https://demo.nopcommerce.com");
		javascript.executeScript("arguments[0].click()", driver.findElement(By.xpath("//a[text()='Desktops ']")));
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Desktops']")).isDisplayed());
	}
	//Exercise
	//@Test
	public void TC_03_ButtonJavascriptExecutor() {
		// 1.Truy cập trang
		driver.get("http://live.guru99.com/");
		// 2. Click My Account dưới footer
		javascript.executeScript("arguments[0].click()",
				driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")));
		// 3. Kiểm tra url
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
		// 4. Click Create an account
		javascript.executeScript("arguments[0].click()", driver.findElement(By.xpath("//a[@class='button']")));
		// 5. Kiểm tra url
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
	}
	@Test
	public void TC_04_Checkbox() throws InterruptedException {
		// 1.Truy cập trang
		driver.get("https://demos.telerik.com/kendo-ui/styling/checkboxes");
		String checkbox= "//label[text()='Dual-zone air conditioning']/preceding-sibling::input";
		
		
		// 2. Click checkbox
		clickJS(checkbox);
		
		Thread.sleep(2000);
		// 3. Kiểm tra đã chọn checkbox đó
	    Assert.assertTrue(elementSelected(checkbox));
		// 2. Click vào checkbox
	    clickJS(checkbox);;
		// 3. Kiểm tra checkbox đó được bỏ chọn
		Assert.assertFalse(elementSelected(checkbox));

		
	}
	@Test
	public void TC_05_Radio() throws InterruptedException {
		// 1.Truy cập trang
		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		String radiobutton= "//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input";
	
		// 2. Click radiobutton
		
		clickJS(radiobutton);
		Thread.sleep(2000);
		// 3. Kiểm tra đã chọn hay chưa
	    Assert.assertTrue(elementSelected(radiobutton));
		
	    
	

		
	}
	@Test
	public void TC_01_AceptAlert() throws InterruptedException {

		// Mở ra 1 trang web
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[contains(text(),'Click for JS Alert')]")).click();
		alert=driver.switchTo().alert();
		Thread.sleep(2000);
		Assert.assertEquals(alert.getText(), "I am a JS Alert");	
	    alert.accept();
	    Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You clicked an alert successfully");
		
	}

	@Test
	public void TC_02_DismisAlert() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[contains(text(),'Click for JS Confirm')]")).click();
		alert = driver.switchTo().alert();
		Thread.sleep(2000);
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		alert.dismiss();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You clicked: Cancel");

	}
	@Test
	public void TC_03_Prompt() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[contains(text(),'Click for JS Prompt')]")).click();
		alert = driver.switchTo().alert();
		Thread.sleep(2000);
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		alert.sendKeys("Chào mừng bạn đến với lớp học Autotest");;
		alert.accept();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You entered: Chào mừng bạn đến với lớp học Autotest");

	}
	//Cách 1: Truyền trực tiếp username, pass vào URL
	@Test
	public void TC_04_Authe_1() {
		String usernameandPass="admin";
		String url="http://the-internet.herokuapp.com/basic_auth";
		url="http://"+usernameandPass+":"+ usernameandPass+ "@the-internet.herokuapp.com/basic_auth";
		driver.get(url);

	}
	//Cách 2: Dùng hàm getAttribute
	@Test
	public void TC_04_Authe_2() {
		String username="admin";
		String password="admin";
		driver.get("http://the-internet.herokuapp.com/");
		
		WebElement basicAuthenlink= driver.findElement(By.xpath("//a[text()='Basic Auth']"));
		String url=basicAuthenlink.getAttribute("href");
		System.out.println(url);
		driver.get(byPassAuthenticationAlert(url, username, password));
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
		

	}

	public String byPassAuthenticationAlert(String url, String username, String password) {
		System.out.println("Old url" + url);
		String[] splitUrl = url.split("//");
		url = splitUrl[0] + "//" + username + ":" + password + "@" + splitUrl[1];
		System.out.println("New url=" + url);
		return url;
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

	public void clickJS (String Locator) {
		WebElement element= driver.findElement(By.xpath(Locator));
		javascript.executeScript("arguments[0].click()",element); 
	}
	public boolean elementEnable(String Locator)
	{
		WebElement element= driver.findElement(By.xpath(Locator));
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
	
	public boolean elementSelected(String Locator)
	{
		WebElement element= driver.findElement(By.xpath(Locator));
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

	public void checktoElement(String Locator) {
		WebElement element = driver.findElement(By.xpath(Locator));
		if (!element.isSelected()) {
			element.click();
		}

	}

	public void unchecktoElement(String Locator) {
		WebElement element = driver.findElement(By.xpath(Locator));
		if (element.isSelected()) {
			element.click();
		}

	}

	// Pro-conditon
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.close();
	}

}
