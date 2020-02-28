package testNG_framewwork;

import org.testng.annotations.Test;
import org.testng.annotations.Parameters;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Part_V_Multi_Brower {
	WebDriver driver;
	By emailTextbox = By.xpath("//input[@id='email']");
	By passTextbox = By.xpath(" //input[@id='pass']");
	By loginButton = By.xpath("//button[@id='send2']");

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		if (browserName.equals("chorme")) {
			System.setProperty("webdriver.chrome.driver", ".\\libraries\\chromedriver.exe");

			driver = new ChromeDriver();
		} else {
			System.setProperty("webdriver.gecko.driver", ".\\libraries\\geckodriver.exe");
			driver = new FirefoxDriver();
		}

		// Chờ chơ element được hiển thị trước khi tương tác trong vòng 30s
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Phóng to trình duyệt
		driver.manage().window().maximize();

	}

	@Test(dataProvider = "user_pass")
	public void TC_01(String username, String passwword) {
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		driver.findElement(emailTextbox).sendKeys(username);
		driver.findElement(passTextbox).sendKeys(passwword);
		driver.findElement(loginButton).click();

		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains(username));
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");

	}

	@DataProvider(name = "user_pass")
	public Object[][] UserAndPasswordData() {
		return new Object[][] {  {"automation_13@gmail.com", "123123" } };

	}

	@AfterClass()
	public void afterClass() {
		driver.quit();

	}

}
