package testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class FlightSearchPage {

	WebDriver driver;

	public FlightSearchPage(WebDriver driver) {
		this.driver = driver;
	}

	By defaultOrigin = By.xpath("//div[@class=\"vvTc-item-close\"]");
	By flightOriginInput = By.xpath("//input[@aria-label=\"Flight origin input\"]");
	By flightDestinationInput = By.xpath("//input[@aria-label=\"Flight destination input\"]");
	By startDateInput = By.xpath("//span[@aria-label=\"Start date calendar input\"]");
	By endDateInput = By.xpath("//span[@aria-label=\"End date calendar input\"]");
	By sept10 = By.xpath("//div[@aria-label=\"September 10, 2022\"]");
	By sept15 = By.xpath("//div[@aria-label=\"September 15, 2022\"]");
	By submit = By.xpath("//button[@type=\"submit\"]");

	public void closeDefaultOrigin() {
		driver.findElement(defaultOrigin).click();
	}

	public void setOrigin(String origin) {
		driver.findElement(flightOriginInput).sendKeys(origin);

	}

	public void setDestination(String destination) {
		driver.findElement(flightDestinationInput).sendKeys(destination);
	}

	public void setStartDate() {
		driver.findElement(startDateInput).click();
		new Actions(driver).moveToElement(driver.findElement(sept10)).click().perform();
	}

	public void setEndDate() {
		driver.findElement(endDateInput).click();
		new Actions(driver).moveToElement(driver.findElement(sept15)).click().perform();

	}

	public void submit() {
		driver.findElement(submit).click();
	}

}
