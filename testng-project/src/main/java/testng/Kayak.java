package testng;

import static org.testng.Assert.assertEquals;

import java.security.SecureRandom;
import java.time.Duration;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Kayak {

	public WebDriver driver;

	@BeforeSuite
	public void BeforeSuite() {
		System.setProperty("webdriver.chrome.driver", "/opt/homebrew/Caskroom/chromedriver/103.0.5060.53/chromedriver");
		System.setProperty("webdriver.gecko.driver", "/opt/homebrew/Cellar/geckodriver/0.31.0/bin/geckodriver");
	}

	@Test
	public void ChromeTest() {
		driver = new ChromeDriver();
		accessKayak(driver);
	}

	@Test
	public void FirefoxTest() {
		driver = new FirefoxDriver();
		accessKayak(driver);
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

	private void accessKayak(WebDriver driver) {
		driver.get("https://www.kayak.com/");
		driver.manage().window().maximize();
		sleep();
		driver.findElement(By.xpath("//div[@class=\"vvTc-item-close\"]")).click();
		sleep();
		driver.findElement(By.xpath("//*[@placeholder=\"From?\"]")).sendKeys("ORD" + Keys.ENTER);
		sleep();
		driver.findElement(By.xpath("//*[@placeholder=\"To?\"]")).sendKeys("BCN" + Keys.ENTER);
		sleep();
		driver.findElements(By.xpath("//*[@class=\"sR_k-value\"]")).get(1).click();
		sleep();
		new Actions(driver).moveToElement(driver.findElement(By.xpath("//*[text()=\"31\"]"))).click().perform();
		sleep();
		driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();
		sleep();
		new WebDriverWait(driver, 10L).pollingEvery(Duration.ofSeconds(1L))
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[@class='price-text']")));
		assertEquals(driver.findElements(By.xpath("//div[@class='resultInner']")).size(), 15);
		sleep();
		Iterator<WebElement> iterator = driver.findElements(By.xpath("//span[@class='price-text']")).iterator();
		WebElement element = iterator.next();
		String priceString = element.getText().substring(1, 2).concat(element.getText().substring(3));
		System.out.println("Price: " + priceString);

	}

	private void sleep() {
		try {
			Thread.sleep(new SecureRandom().nextInt(1000) + 2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
