package shopee;

import org.openqa.selenium.By;

import framework.pageHelper;
import locators.CartLocator;

public class cart {

	CartLocator cart = new CartLocator();
	pageHelper ph=new pageHelper();

	public void cartEntry(String num) {
		ph.findElementByXpath(cart.addToCart).click();
		ph.findElementByXpath(cart.quantity).sendKeys(num);
	}

}
