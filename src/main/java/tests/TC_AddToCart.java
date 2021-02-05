package tests;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import dataProvider.configFileReader;
import framework.browserlaunch;
import framework.pageHelper;
import locators.HomeLocator;
import locators.loginLocator;
import shopee.product;
import shopee.cart;
import shopee.home;
import shopee.login;


public class TC_AddToCart extends browserlaunch {
	product pd = new product();
	HomeLocator hmlocator =new HomeLocator();
	home hm = new home();
	pageHelper ph=new pageHelper();
	login lg=new login();
	configFileReader cf = new configFileReader();
	loginLocator ll= new loginLocator();
	cart cr=new cart();

	@Test
	public void addHighestPriceProductToCart() throws InterruptedException, IOException {
		ph.navigate(cf.getApplicationUrl());
		WebElement wb= ph.findElementByXpath(hmlocator.SearchTools);
		wb.sendKeys("\n");
		Thread.sleep(500);
		//Searching for toys
		WebElement wb1= ph.findElementByXpath(hmlocator.SearchTools);
		wb1.sendKeys("Toy"+"\n");
		Thread.sleep(5000);
		
		//Scroll down for all products
		JavascriptExecutor js = (JavascriptExecutor) driver;
		ArrayList<ArrayList<String>> allProductDetail=new ArrayList<ArrayList<String>>();
		//Navigating till 5 pages of search results.
		for (int page=1; page<=5;page++) {
			if(!hm.isCurrentPage(String.valueOf(page))) {
				System.out.println("Clicking on Page Number: "+ page);
				hm.clickOnPage(String.valueOf(page));
			}	
			for (int i=1; i <= 7; i++) {
				js.executeScript("window.scrollBy(0,1000)");
				Thread.sleep(1500);
			}
			ArrayList<ArrayList<String>> ar=pd.getProductDetails(String.valueOf(page));
			allProductDetail.addAll(ar);	
		}
		ArrayList<String> maxProductDet=pd.maxPrice(allProductDetail);
		System.out.println(maxProductDet);
		String productName=maxProductDet.get(2);

		js.executeScript("window.scrollBy(0,-7000)");
		System.out.println("Navigating to product url");
		String url = maxProductDet.get(2);
		System.out.println(url);
		driver.navigate().to(url);
		System.out.println("Product details: " + productName);
		System.out.println("Discounted Price is: " + cr.discountedPrice().toString());
		System.out.println("Quantity Sold is: " + cr.quantitySold());
		System.out.println("Current price is: " + cr.currentPrice());
		System.out.println("Rating is: " + cr.ratings());
		cr.cartEntry("8");
		lg.loginToShoppee(cf.properties.getProperty("Username"), cf.properties.getProperty("password"));
	}
}


