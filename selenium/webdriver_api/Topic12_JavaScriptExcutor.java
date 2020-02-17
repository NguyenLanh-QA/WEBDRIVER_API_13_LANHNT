package webdriver_api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic12_JavaScriptExcutor {
	// Khai báo biến driver đại diện cho selenium webdriver
	WebDriver driver;
	String userID = "mngr246106";
	String passWord = "AgUtUrY";

	String name = "John Switch";
	String gender="female";
	String date = "1994-01-16";
	String address = "Thanh Xuan Ha Noi";
	String city = "Ha Noi";
	String state = "Viet Nam";
	String pin = "100000";
	String phone = "098765432";
	String email = "autotest_" + randomNumber() + "@gmail.com";
	String pass = "123456";
	String customerID;
	
	String firstName="Automation";
	String lastName="Test";
	String passWordTC03="123456";
	String passConfirm="123456";
	

	public int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(1000);
	}

	// Pre-Condition
	@BeforeClass
	public void beforeClass() {
		// Khởi tạo trình duyệt firefox
		// driver = new FirefoxDriver();

		// Khởi tạo trình duyệt firefox
		System.setProperty("webdriver.chrome.driver", ".\\libraries\\chromedriver.exe");
		driver = new ChromeDriver();

		// Chờ chơ element được hiển thị trước khi tương tác trong vòng 30s
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Phóng to trình duyệt
		driver.manage().window().maximize();

	}

	public Object executeForBrowser(String javaSript) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript(javaSript);
	}

	public boolean verifyTextInInnerText(String textExpected) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String textActual = (String) js
				.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		System.out.println("Text actual = " + textActual);
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.location = '" + url + "'");
	}

	// Element
	public void highlightElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String originalStyle = element.getAttribute("style");
		js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				"border: 5px solid red; border-style: dashed;");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);

	}

	public void clickToElementByJS(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

	public void scrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void sendkeyToElementByJS(WebElement element, String value) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
	}

	public void removeAttributeInDOM(WebElement element, String attributeRemove) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
	}

	// @Test
	public void TC_01() throws InterruptedException {
		// Mở ra 1 trang web
		navigateToUrlByJS("http://live.guru99.com/");
		// Lấy ra domain
		String domain = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(domain, "live.demoguru99.com");
		// Lấy ra URL
		String URL = (String) executeForBrowser("return document.URL");
		Assert.assertEquals(URL, "http://live.demoguru99.com/");

		// Click Mobile App
		clickToElementByJS(driver.findElement(By.xpath("//a[text()='Mobile']")));
		clickToElementByJS(driver.findElement(
				By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//button")));

		verifyTextInInnerText("Samsung Galaxy was added to your shopping cart.");

		clickToElementByJS(driver.findElement(By.xpath("//a[text()='Customer Service']")));

		String title = (String) executeForBrowser("return document.title");
		Assert.assertEquals(title, "Customer Service");

		scrollToElement(driver.findElement(By.xpath("//input[@id='newsletter']")));
		Thread.sleep(2000);

		verifyTextInInnerText("Praesent ipsum libero, auctor ac, tempus nec, tempor nec, justo.");

		navigateToUrlByJS("http://demo.guru99.com/v4/");
		String newdomain = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(newdomain, "demo.guru99.com");
	}

	//@Test
	public void TC_02_removeAttribute() throws InterruptedException {
		driver.get("http://demo.guru99.com/v4/");
		findElement("//input[@name='uid']").sendKeys(userID);
		findElement("//input[@name='password']").sendKeys(passWord);

		clickToElementByJS(findElement("//input[@name='btnLogin']"));

		clickToElementByJS(findElement("//a[text()='New Customer']"));

		findElement("//input[@name='name']").sendKeys(name);
		removeAttributeInDOM(findElement("//input[@id='dob']"), "type");
		findElement("//input[@id='dob']").sendKeys(date);

		

		clickToElementByJS(findElement("//input[@value='f']"));
		findElement("//textarea[@name='addr']").sendKeys(address);
		findElement("//input[@name='city']").sendKeys(city);
		findElement("//input[@name='state']").sendKeys(state);
		findElement("//input[@name='pinno']").sendKeys(pin);
		findElement("//input[@name='telephoneno']").sendKeys(phone);
		findElement("//input[@name='emailid']").sendKeys(email);
		findElement("//input[@name='password']").sendKeys(passWord);
	
		clickToElementByJS(findElement("//input[@name='sub']"));
		Thread.sleep(5000);

	}

	public WebElement findElement(String locator) {
		return driver.findElement(By.xpath(locator));
		
	}
	public boolean isDisplay(String locator) {
		
		return findElement(locator).isDisplayed();
		
	}
	@Test
	public void TC_03_crearanAccount() {
		navigateToUrlByJS("http://live.guru99.com/");
		clickToElementByJS(findElement("//div[@id='header-account']//a[text()='My Account']"));
		clickToElementByJS(findElement("//span[text()='Create an Account']"));
		sendkeyToElementByJS(findElement("//input[@id='firstname']"), firstName);
		sendkeyToElementByJS(findElement("//input[@id='lastname']"), lastName);
		sendkeyToElementByJS(findElement("//input[@id='email_address']"), email);
		sendkeyToElementByJS(findElement("//input[@id='password']"), passWordTC03);
		sendkeyToElementByJS(findElement("//input[@id='confirmation']"), passConfirm);
		
		clickToElementByJS(findElement("//span[contains(text(),'Register')]"));
		Assert.assertTrue(verifyTextInInnerText("Thank you for registering with Main Website Store."));
		clickToElementByJS(findElement("//a[text()='Log Out']"));
		Assert.assertTrue(isDisplay("//div[@class='page-title']"));

		


	}

	// Pro-conditon
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.quit();
	}

}
