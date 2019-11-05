package webdriver_api;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic02_WebElement {
	// Khai báo biến driver đại diện cho selenium webdriver
	WebDriver driver;
	String firstName = "Selenium";
	String lastName = "WebDriver";
	String passWord = "123123";
	String passConfirm = "123123";
	String emailValid = "automation_" + randomNumber() + "@gmai.com";

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

	@BeforeMethod
	public void beforeMethod() {
		driver.get("http://live.demoguru99.com");

		// Click MY ACCOUNT
		driver.findElement(By.xpath("//div[@class='footer-container']//a[(text()='My Account')]")).click();
	}

	@Test
	public void TC_01_LoginEmpty() {

		// Click button Login
		driver.findElement(By.xpath("//button[@id='send2']")).click();

		// Check verify trường Email
		String emailError = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
		Assert.assertEquals(emailError, "This is a required field.");

		// Check verify trường Password
		String passError = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText();
		Assert.assertEquals(passError, "This is a required field.");

	}

	@Test
	public void TC_02_LoginWithValueInvalid() {

		// Check verify trường Email
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("123434234@12312.123123");
		// Click button Login
		driver.findElement(By.xpath("//button[@id='send2']")).click();

		// Check Email Error
		String emailError = driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText();
		Assert.assertEquals(emailError, "Please enter a valid email address. For example johndoe@domain.com.");

	}

	@Test
	public void TC_03_LoginWithPasswordLessThan6Characters() {

		// Nhập dữ liệu trường Email
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");

		// Nhập password < 6 ký tự
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");

		// Click button Login
		driver.findElement(By.xpath("//button[@id='send2']")).click();

		// Check thông báo Email
		String passError = driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText();
		Assert.assertEquals(passError, "Please enter 6 or more characters without leading or trailing spaces.");

	}

	@Test
	public void TC_04_LoginWithPasswordIncorrect() {

		// Nhập dữ liệu trường Email
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");

		// Nhập password < 6 ký tự
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123123123");

		// Click button Login
		driver.findElement(By.xpath("//button[@id='send2']")).click();

		// Check thông báo Email
		String passError = driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText();
		Assert.assertEquals(passError, "Invalid login or password.");

	}

	@Test
	public void TC_05_CreateAnAccount() {
		
		System.out.println("emailValid:"+ emailValid+"");

		// Click button Creat my account
		driver.findElement(By.xpath("//span[(text()='Create an Account')]")).click();

		// Nhập dữ liệu trường FirsName
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys(firstName);

		// Nhập password < 6 ký tự
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys(lastName);

		// Nhập email
		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(emailValid);

		// Nhập password
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(passWord);

		// Nhập password
		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys(passConfirm);

		// Click button Register
		driver.findElement(By.xpath("//button[@title='Register']")).click();

		// Check thông báo
		Assert.assertTrue(driver.findElement(By.xpath("//span[(text()='Thank you for registering with Main Website Store.')]")).isDisplayed());

		// Logout
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[(text()='Account')]")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();

	}

	@Test
	public void TC_06_LoginCorrect() {

		// Nhập dữ liệu trường Email
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(emailValid);

		// Nhập password < 6 ký tự
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(passWord);

		// Click button Login
		driver.findElement(By.xpath("//button[@id='send2']")).click();

		// Check hiển thị form Dashboard
		Assert.assertTrue(driver.findElement(By.xpath("//strong[text()='Hello, " + firstName + " " + lastName + "!']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']//p[contains(text(), '" + firstName + " " + lastName + "')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']//p[contains(., '" + emailValid + "')]")).isDisplayed());

	}

	// Pro-conditon
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.close();
	}

}
