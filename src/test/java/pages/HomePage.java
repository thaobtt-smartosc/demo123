package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class HomePage extends BasePage {

	String XPATH_LINK_SIGN_IN = "//div[@class='panel header']//li[@class='authorization-link']/a";
	String XPATH_LOGGED_IN = "//div[@class='panel header']//span[@class='logged-in']";

	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	public HomePage open() {
		driver.get("https://demo.smartosc.com/");
		return this;
	}

	public HomePage clickOnSignIn() {
		actionUtility.click(By.xpath(XPATH_LINK_SIGN_IN));
		return this;
	}

	public boolean checkUserLoggedIn() {
		return actionUtility.checkElementDisplay(By.xpath(XPATH_LOGGED_IN));
	}
	
}
