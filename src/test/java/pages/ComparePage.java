package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;
import data.Product;

public class ComparePage extends BasePage {
	String XPATH_EMPTY_COMPARE="//div[@class='message info empty']";
	String XPATH_BUTTON_DELETE_COMPARE="//a[@class='action delete']";
	String XPATH_BUTTON_DELETE_CONFIRM="//span[text()='OK']";
	String XPATH_SUMMARY_PRODUCT_NAME="//div[@class='table-wrapper comparison']//a[normalize-space(text())='%s']";
	String XPATH_SUMMARY_PRODUCT_PRICE="//td[@class='cell product info'][.//a[normalize-space(text())='%s']]//span[@class='price']";
	

	public ComparePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	public void openComparePage()
	{
		driver.get("https://demo.smartosc.com/catalog/product_compare/index/");
	}
	
	public boolean checkProductNameDisplay(Product product)
	{
		return actionUtility.checkElementExist(By.xpath(String.format(XPATH_SUMMARY_PRODUCT_NAME, product.getName())), 10);
	}
	public String getSummaryProductPrice(Product product)
	{
		return actionUtility.getTextByJS(By.xpath(String.format(XPATH_SUMMARY_PRODUCT_PRICE, product.getName())));
	}
	public void removeAllProductInComparePage()
	{
		 while (driver.findElements(By.xpath(XPATH_EMPTY_COMPARE)).size() == 0)
		{
			actionUtility.clickByJS(By.xpath(XPATH_BUTTON_DELETE_COMPARE));
			actionUtility.clickToConfirm(By.xpath(XPATH_BUTTON_DELETE_CONFIRM));
			waitUtility.sleep(6);
		}
	}

}
