package shopee;

import org.openqa.selenium.Keys;

import framework.browserlaunch;
import framework.pageHelper;
import locators.loginLocator;

public class login extends browserlaunch {
	pageHelper ph=new pageHelper();

	loginLocator ll= new loginLocator();

	public void loginToShoppee(String un, String pwd) {
		//ph.findElementByXpath(ll.LogInLink).click();
		ph.findElementByXpath(ll.loginname).sendKeys(un);
		ph.findElementByXpath(ll.password).sendKeys(pwd);
		ph.findElementByXpath(ll.password).sendKeys(Keys.ENTER);

	}
}
