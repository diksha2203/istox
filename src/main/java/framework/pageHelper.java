package framework;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import tests.TC_AddToCart;

import org.openqa.selenium.support.ui.ExpectedConditions;



public class pageHelper extends browserlaunch {

	public void navigate(String url) throws InterruptedException, IOException {
		driver.navigate().to(url);
		driver.manage().window().maximize();
	}

	public void forward() {
		driver.navigate().forward();  
	}

	public void refresh() {
		driver.navigate().refresh();  
	}
	public WebElement findElementByXpath(String str) {
		return driver.findElement(By.xpath(str));
	}
	@SuppressWarnings("unchecked")
	public List<WebElement> findElementsByXpath(String str) {
		return (List<WebElement>) driver.findElement(By.xpath(str));
	}
	public void dropdownByVisisbleText(String xpath, String value) {
		Select objSelect = new Select(findElementByXpath(xpath));
		objSelect.selectByVisibleText(value);
	}

	public void implicitWait() {
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
	}

	public void waitForElementCount(String locator, int count) throws InterruptedException {
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(locator), count));
	}
}
