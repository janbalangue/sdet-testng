package testng;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class FlightTest {

	WebDriver driver;
	FlightSearchPage flightSearchPage;
	FlightListPage flightListPage;

	@BeforeSuite
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "/opt/homebrew/Caskroom/chromedriver/103.0.5060.53/chromedriver");
		driver = new ChromeDriver();
		driver.get("https://www.kayak.com/");
		driver.manage().window().maximize();
	}

	@BeforeClass
	public void createPages() {
		flightSearchPage = new FlightSearchPage(driver);
		flightListPage = new FlightListPage(driver);
	}

	@Test
	public void closeDefaultOrigin() {
		flightSearchPage.closeDefaultOrigin();
	}

	@Test
	public void setOrdOrigin() {
		flightSearchPage.setOrigin("ORD");
	}

	@Test
	public void setDestination() {
		flightSearchPage.setDestination("BCN");
	}

	@Test
	public void setStartDate() {
		flightSearchPage.setStartDate();
	}

	@Test
	public void setEndDate() {
		flightSearchPage.setEndDate();
	}

	@Test
	public void submit() {
		flightSearchPage.submit();
	}

	@AfterSuite
	public void tearDown() {
		driver.quit();
	}

}
