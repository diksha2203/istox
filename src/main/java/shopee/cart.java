package shopee;


import framework.pageHelper;
import locators.CartLocator;

public class cart {

	CartLocator cart = new CartLocator();
	pageHelper ph=new pageHelper();

	public void cartEntry(String num) {
		ph.findElementByXpath(cart.quantity).clear();
		Integer availableQty=Integer.parseInt(ph.findElementByXpath(cart.qtyAvailable).getText().replace(" piece available", "").trim());
		if (availableQty>8) {
			ph.findElementByXpath(cart.quantity).sendKeys(num);
		}else {
			ph.findElementByXpath(cart.quantity).sendKeys(String.valueOf(availableQty));
		}
		
		ph.findElementByXpath(cart.addToCart).click();
		
	}
	
	public String discountedPrice() {
		try {
		Double originalPrice=Double.parseDouble(ph.findElementByXpath(cart.originalPrice).getText().replace("$", "").replace(",","").trim());
		Double currPrice=Double.parseDouble(ph.findElementByXpath(cart.currPrice).getText().replace("$", "").trim());
		Double discountedPrice = originalPrice - currPrice;
		return String.valueOf(discountedPrice);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return "No Discount Available.";
		}
	}
	
	public String quantitySold() {
		String sold=ph.findElementByXpath(cart.qtySold).getText().trim();
		return sold;	
	}
	
	public String currentPrice() {
		String currPrice=ph.findElementByXpath(cart.currPrice).getText().replace("$", "").trim();
		return currPrice;	
	}
	
	public String ratings() {
		try {
			String rating=ph.findElementByXpath(cart.ratings).getText().trim();
			return rating;	
		}catch (org.openqa.selenium.NoSuchElementException e) {
			return "No one rated this yet.";
		}
	}
	
	

}
