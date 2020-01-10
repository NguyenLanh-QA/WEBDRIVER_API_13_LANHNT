package webdriver_api;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic11_Window_Tab {
	// Khai báo biến driver đại diện cho selenium webdriver
	WebDriver driver;

	//Pre-Condition
	@BeforeClass
	public void beforeClass() {
		// Khởi tạo trình duyệt firefox
		System.setProperty("webdriver.chrome.driver", ".\\libraries\\chromedriver.exe");
		driver = new ChromeDriver();
		
		// Chờ chơ element được hiển thị trước khi tương tác trong vòng 30s
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Phóng to trình duyệt
		driver.manage().window().maximize();
		

	}

	@Test
	public void TC_01() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//Lấy ra ID của window/ tab đang active
		String parentID=driver.getWindowHandle();
		System.out.println("Parent ID="+ parentID);
		//Click link GG
		driver.findElement(By.xpath("//div[@class='container']//a[text()='GOOGLE']")).click();

		//Lấy ra ID của tất cả các window/ tab
		Set <String> allIDs= driver.getWindowHandles();
		for (String id: allIDs) {
			System.out.println("ID="+ id);
		}
		//So sánh: Nếu cái nào mà khác parent thì switch qua trong trường hợp chỉ có 2 cửa sổ
		
		
	}
	//Theo ID
	public void switchTWindowByID(String parentID) {
		//Lấy ra tất cả ID đang có và lưu vào allWindows
		Set <String> allWindows= driver.getWindowHandles();
		//Dùng vòng lặp duyệt qua từng ID
		for(String runWindow: allWindows) {
			if(!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}
	@Test
	public void TC_02_() {
	
	}

	@Test
	public void TC_03_() {
		
	}

	//Pro-conditon
	@AfterClass
	public void afterClass() {
		//Tắt trình duyệt
		driver.quit();
	}

}


