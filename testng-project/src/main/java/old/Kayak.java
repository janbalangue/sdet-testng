package old;

import java.security.SecureRandom;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

// this class is not used
public class Kayak {

	public WebDriver driver;

	@BeforeSuite
	public void BeforeSuite() {
		System.setProperty("webdriver.chrome.driver", "/opt/homebrew/Caskroom/chromedriver/103.0.5060.53/chromedriver");
	}

	@Test
	public void ChromeTest() {
		driver = new ChromeDriver();
		accessKayak(driver);
	}

//	@Test
//	public void FirefoxTest() {
//		driver = new FirefoxDriver();
//		accessKayak(driver);
//	}

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
//		<input type="text" value="" class="k_my-input" tabindex="0" aria-readonly="false" placeholder="From?" aria-label="Flight origin input" data-test-origin="">
		WebElement originElement = driver.findElement(By.xpath("//input[@aria-label=\"Flight origin input\"]"));
		sleep();
		originElement.sendKeys("ORD");
		sleep();
//		WebElement choiceElement = driver.findElements(By.xpath("//div[@class=\"JyN0-item JyN0-pres-item-mcfly\"]"))
//				.get(0);
//		choiceElement.click();
//		sleep();
//		<input type="text" value="" class="k_my-input" tabindex="0" aria-readonly="false" placeholder="To?" aria-label="Flight destination input" data-test-destination="">
		WebElement destinationElement = driver
				.findElement(By.xpath("//input[@aria-label=\"Flight destination input\"]"));
		sleep();
		destinationElement.sendKeys("BCN");
		sleep();
//		choiceElement = driver.findElements(By.xpath("//div[@class=\"JyN0-item JyN0-pres-item-mcfly\"]")).get(1);
//		choiceElement.click();
//		sleep();
//		<span class="sR_k-value" aria-live="polite" aria-atomic="true" aria-label="Monday July 25th">Mon 7/25</span>
//		<div class="sR_k-input sR_k-mod-responsive"><span class="sR_k-date sR_k-mod-hide-arrows" role="button" tabindex="0" aria-label="End date calendar input"><span class="sR_k-prefixIcon"><span height="20px" cleanup="" class="svg" style="transform:translate3d(0,0,0);vertical-align:middle;-webkit-font-smoothing:antialiased;-moz-osx-font-smoothing:grayscale;height:20px;width:20px"><svg class="svg-image" role="img" style="width:inherit;height:inherit;line-height:inherit;color:inherit;" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 200 200"><path d="M185 33.182v30.909H15V33.182h30.909V10h23.182v23.182h61.818V10h23.182v23.182H185zM15 71.818h170v92.727c0 8.535-6.919 15.455-15.455 15.455H30.455C21.919 180 15 173.081 15 164.545V71.818zm115.909 46.364h30.909v-30.91h-30.909v30.91zm-46.364 0h30.909v-30.91H84.545v30.91zm0 46.363h30.909v-30.909H84.545v30.909zm-46.363-46.363h30.909v-30.91H38.182v30.91zm0 46.363h30.909v-30.909H38.182v30.909z"></path></svg></span></span><span class="sR_k-value" aria-live="polite" aria-atomic="true" aria-label="Tuesday July 26th">Tue 7/26</span></span></div>
		driver.findElement(By.xpath("//span[@aria-label=\"End date calendar input\"]")).click();
		sleep();
//		driver.findElements(By.xpath("//*[text()=\\\"30\\\"]")).get(0).click();
		new Actions(driver).moveToElement(driver.findElements(By.xpath("//*[text()=\"30\"]")).get(0)).click().perform();
		sleep();
		String currentWindowString = driver.getWindowHandle();
		driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();
		sleep();
		Set<String> windowsSet = driver.getWindowHandles();
		for (String window : windowsSet) {
			if (!window.equals(currentWindowString)) {
				driver.switchTo().window(window);
				break;
			}
		}
		sleep();
		List<WebElement> flightsElements = driver.findElements(By.xpath("//span[@class='price-text']"));
		Iterator<WebElement> iterator = flightsElements.iterator();
		if (iterator.hasNext()) {
			WebElement cheapestFlight = iterator.next();
			WebElement currentFlight = cheapestFlight;
			StringBuilder lowestPriceBuilder = new StringBuilder(cheapestFlight.getText());
			System.out.println(lowestPriceBuilder.toString());
			if (lowestPriceBuilder.indexOf("$") != -1) {
				lowestPriceBuilder.deleteCharAt(lowestPriceBuilder.indexOf("$"));
			}
			if (lowestPriceBuilder.indexOf(",") != -1) {
				lowestPriceBuilder.deleteCharAt(lowestPriceBuilder.indexOf(","));
			}
			System.out.println(lowestPriceBuilder.toString());
			int lowestPrice = Integer.parseUnsignedInt(lowestPriceBuilder.toString());
			while (iterator.hasNext()) {
				currentFlight = iterator.next();
				StringBuilder builder = new StringBuilder(currentFlight.getText());
				if (builder.length() > 0) {
					System.out.println(builder);
					if (builder.indexOf("$") != -1) {
						builder.deleteCharAt(builder.indexOf("$"));
					}
					if (builder.indexOf(",") != -1) {
						builder.deleteCharAt(builder.indexOf(","));
					}
					System.out.println(Integer.parseUnsignedInt(builder.toString()));
					int currentPrice = Integer.parseUnsignedInt(builder.toString());
					if (currentPrice < lowestPrice) {
						lowestPrice = currentPrice;
						cheapestFlight = currentFlight;
					}
				}

			}
			cheapestFlight.click();
		}
	}

	private void sleep() {
		try {
			Thread.sleep(new SecureRandom().nextInt(300));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
