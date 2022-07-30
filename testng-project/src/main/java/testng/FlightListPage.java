package testng;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FlightListPage {

	WebDriver driver;

	public FlightListPage(WebDriver driver) {
		this.driver = driver;
	}

	By resultWrapper = By.xpath("//div[@class='resultWrapper']");

	// doesn't work yet
	public int countResultWrappers() {
		List<WebElement> results = driver.findElements(resultWrapper);
		return results.size();
	}
}
