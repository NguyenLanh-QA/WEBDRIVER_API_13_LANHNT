package webdriver_api;
import java.util.List;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic07_Dropdown_II {
	// Khai báo biến driver đại diện cho selenium webdriver
	WebDriver driver;



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
		
		
		
	}

	

	
	// JQUERY
	@Test
	public void TC_01_Droplist_Jquery() {
		//Kiểm tra cái item được chọn thành công
		selectItemJqueryDroplist("//span[@id='number-button']", "//ul[@id='number-menu']/li", "19");
		boolean status=IsDisplay("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='19']");
		Assert.assertTrue(status);
		System.out.println("Status" +status);

		
	}
	public void selectItemJqueryDroplist( String parentXpath, String allItemsXpath, String expectedText )
	{
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		driver.findElement(By.xpath(parentXpath)).click();;
		WebDriverWait waitExplicit;
		waitExplicit = new WebDriverWait(driver,10);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		
		// Khai báo 1 list WebElement chứa items bên trong
		List <WebElement> allItems=driver.findElements(By.xpath(allItemsXpath));
		//Đợi cho tất cả các item được xuất hiện
		waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemsXpath)));
		// Get text từng item sau đó so sánh với item cần chọn
		for(WebElement item :allItems) {
			System.out.println(item.getText());
		
		if(item.getText().equals(expectedText))
		{
			item.click();
			break;
		}
		}
	}
		// ANGULAR
		@Test
		public void TC_02_Droplist_Angular() {
			//Kiểm tra cái item được chọn thành công
			selectItemAngularDroplist("//ejs-dropdownlist[@id='games']/span", "//ul[@id='games_options']/li", "Football");
			boolean status=IsDisplay("//select[@id='games_hidden']//option[text()='Football']");
			Assert.assertTrue(status);
			System.out.println("Status" +status);

			
		}
		public void selectItemAngularDroplist( String parentXpath, String allItemsXpath, String expectedText )
		{
			driver.get("https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
			driver.findElement(By.xpath(parentXpath)).click();;
			WebDriverWait waitExplicit;
			waitExplicit = new WebDriverWait(driver,10);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			
			// Khai báo 1 list WebElement chứa items bên trong
			List <WebElement> allItems=driver.findElements(By.xpath(allItemsXpath));
			//Đợi cho tất cả các item được xuất hiện
			waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemsXpath)));
			// Get text từng item sau đó so sánh với item cần chọn
			for(WebElement item :allItems) {
				System.out.println(item.getText());
			
			if(item.getText().equals(expectedText))
			{
				item.click();
				break;
			}
			}
		 
	
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
	

	




	// Pro-conditon
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.close();
	}

}
