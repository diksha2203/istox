package shopee;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import framework.browserlaunch;
import locators.HomeLocator;

public class home extends browserlaunch {
	HomeLocator hmlocator = new HomeLocator();
	product pd=new product();

	public void clickOnPage(String number) {
		driver.findElement(By.xpath(hmlocator.pageNumber(number))).click();
	}
	public boolean isCurrentPage(String page) {
		try {
			WebElement wb= driver.findElement(By.xpath(hmlocator.selectedPage(page)));
			return wb.isDisplayed();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

}








