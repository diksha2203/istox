package shopee;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import framework.browserlaunch;
import locators.HomeLocator;
import locators.productLocator;

public class product extends browserlaunch{
	HomeLocator hmLocator = new HomeLocator();
	productLocator pl = new productLocator();

	public ArrayList<ArrayList<String>> getProductDetails(String pagenumber) {
		ArrayList<ArrayList<String>> productDetails = new ArrayList<ArrayList<String>>();
		List<WebElement> products = driver.findElements(By.xpath(pl.productItem));	
		for (WebElement it : products) {
			ArrayList<String> det = new ArrayList<String>();
			String  price = it.findElement(By.xpath(pl.productPrice)).getText();
			String name = it.findElement(By.xpath(pl.productName)).getText();
			List<WebElement> ratings= it.findElements(By.xpath(pl.productRating));
			String productHref = it.findElement(By.xpath(pl.productHref)).getAttribute("href");
			det.add(pagenumber);
			det.add(getProductRating(ratings));
			det.add(name);
			det.add(price);
			det.add(productHref);
			productDetails.add(det);
		}
		return productDetails;
	}

	public String getProductRating(List<WebElement> wb) {	
		double sum = 0.0;
		for (WebElement it : wb) {
			try {
				double rating = Double.parseDouble(it.getAttribute("style").replace("width: ", "").replace("%;", ""));
				sum = sum + rating;
			} catch (java.lang.NumberFormatException e) {
				sum = 0;
			}

		}
		Double tDouble;
		if (sum != 0) {
			tDouble = sum/100;
		}else {
			tDouble = 0.0;
		}
		return tDouble.toString();
	}

	public ArrayList<String> maxPrice(ArrayList<ArrayList<String>> lst) {
		double max=0;
		ArrayList<String> maxPriceProductDetail = new ArrayList<String>();
		for (int i=0; i<lst.size(); i++) {
			double currPrice = Double.parseDouble(lst.get(i).get(3));
			if (currPrice>=max) {
				max=currPrice;
				maxPriceProductDetail = lst.get(i);
			}
		}
		return maxPriceProductDetail;
	}


	public void clickOnProduct (String productName) {
		List<WebElement> products = driver.findElements(By.xpath(pl.productItem));	
		for (WebElement it : products) {
			String name = it.findElement(By.xpath(pl.productName)).getText();
			System.out.println(name);
			if(name.trim().equals(productName.trim())) {
				System.out.println("clicking on product");
				it.findElement(By.xpath(pl.productName)).click();
			}		
		}
	}
}


