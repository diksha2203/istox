package framework;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import dataProvider.configFileReader;

public class browserlaunch {
	public static WebDriver driver;
	public static String url;


	@BeforeTest
	public static void launchBrowser() throws InterruptedException, IOException {
		configFileReader cf = new configFileReader();
		pageHelper phHelper = new pageHelper();
		System.setProperty("webdriver.chrome.driver", cf.getDriverPath());
		url = cf.getApplicationUrl();
		driver=new ChromeDriver();
		System.out.println("chrome");
		Thread.sleep(500);
		phHelper.implicitWait();

	}

	@AfterTest
	public static void teardown() {
		//driver.quit();
	}
}







