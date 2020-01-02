package webdriver_api;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic12_JavaScriptExcutor {
	// Khai báo biến driver đại diện cho selenium webdriver
	WebDriver driver;

	//Pre-Condition
	@BeforeClass
	public void beforeClass() {
		// Khởi tạo trình duyệt firefox
		driver = new FirefoxDriver();
		
		// Chờ chơ element được hiển thị trước khi tương tác trong vòng 30s
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Phóng to trình duyệt
		driver.manage().window().maximize();
		
		//Mở ra 1 trang web 
		driver.get("http://demo.guru99.com/v4/");
	}
	public Object executeForBrowser(String javaSript) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript(javaSript);
	}
	
	public boolean verifyTextInInnerText(String textExpected) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String textActual = (String) js.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
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
		js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 5px solid red; border-style: dashed;");
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

	@Test
	public void TC_01_() {
		
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


