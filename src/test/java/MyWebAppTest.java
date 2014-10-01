import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class MyWebAppTest {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://localhost:8080/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testCalc() throws Exception {
		driver.get(baseUrl + "/my-webapp/");
		driver.findElement(By.name("num1")).clear();
		driver.findElement(By.name("num1")).sendKeys("3");
		driver.findElement(By.name("num2")).clear();
		driver.findElement(By.name("num2")).sendKeys("4");
		
		Thread.sleep(1000); // テスト実行時に見やすいように少し待つ。
		
		driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
		
		assertEquals("7", driver.findElement(By.id("answer")).getText());
		
		Thread.sleep(1000); // テスト実行時に見やすいように少し待つ。
		
		driver.findElement(By.id("back")).click();
		driver.findElement(By.name("num1")).clear();
		driver.findElement(By.name("num1")).sendKeys("12");
		driver.findElement(By.name("num2")).clear();
		driver.findElement(By.name("num2")).sendKeys("9");
		
		Thread.sleep(1000); // テスト実行時に見やすいように少し待つ。
		
		driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
		
		assertEquals("21", driver.findElement(By.id("answer")).getText());
		
		Thread.sleep(1000); // テスト実行時に見やすいように少し待つ。
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
