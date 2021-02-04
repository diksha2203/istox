package locators;

public class HomeLocator {
	public String SearchTools = "//input[@class='shopee-searchbar-input__input']";
	public String pageNumber(String number) {
		String productPagination = String.format("//button[@class='shopee-button-no-outline' and text()='%s']",number);
		return productPagination;

	}
	public String selectedPage(String page) {
		String selectedPage = String.format("//button[@class='shopee-button-solid shopee-button-solid--primary ' and text()='%s']",page);
		return selectedPage;

	}

}


