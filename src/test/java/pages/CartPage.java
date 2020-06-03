package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;
import data.Product;

public class CartPage extends BasePage {
	String XPATH_UPDATE_CART = "//button[@class='action update']";
	String XPATH_INPUT_QTY = "//input[@class='input-text qty'][@data-cart-item-id='WT09-XS-Purple']";
	String XPATH_INVALID_QTY = "//div[@class='mage-error']";
	String XPATH_EMPTY_CART="//div[@class='cart-empty']";
	String XPATH_BUTTON_DELETE_CART="//tr[@class='item-actions']//a[@class='action action-delete']";
	String XPATH_SUMMARY_PRODUCT_NAME="//tr[@class='item-info']//a[text()='%s']";
	String XPATH_SUMMARY_PRODUCT_SIZE="//tr[@class='item-info'][.//a[text()='%s']]//dd[1]";
	String XPATH_SUMMARY_PRODUCT_COLOR="//tr[@class='item-info'][.//a[text()='%s']]//dd[2]";
	String XPATH_SUMMARY_PRODUCT_PRICE="//tr[@class='item-info'][.//a[text()='%s']]//td[@class='col price']//span[@class='price']";
	String XPATH_SUMMARY_PRODUCT_TOTAL="//tr[@class='item-info'][.//a[text()='%s']]//td[@class='col subtotal']//span[@class='price']";
	public CartPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	public void openCartPage()
	{
		driver.get("https://demo.smartosc.com/checkout/cart/");
	}
	public void chageQty(String qty)
	{
		actionUtility.checkElementDisplay(By.xpath(XPATH_INPUT_QTY));
		actionUtility.clear(By.xpath(XPATH_INPUT_QTY));
		actionUtility.sendKeys(By.xpath(XPATH_INPUT_QTY), qty);
		actionUtility.click(By.xpath(XPATH_UPDATE_CART));
	}
	public String getQty()
	{
		return actionUtility.getValueByJS(By.xpath(XPATH_INPUT_QTY));
	}
	public String checkQty(String qty)
	{
		actionUtility.checkElementDisplay(By.xpath(XPATH_INVALID_QTY));
		return actionUtility.getText(By.xpath(XPATH_INVALID_QTY));
	}
	public void removeAllProductInCart()
	{
		while(driver.findElements(By.xpath(XPATH_EMPTY_CART)).size()==0) {
			driver.findElement(By.xpath(XPATH_BUTTON_DELETE_CART)).click();
			waitUtility.sleep(6);
		}
	}
	
	public boolean checkProductNameDisplay(Product product)
	{
		return actionUtility.checkElementExist(By.xpath(String.format(XPATH_SUMMARY_PRODUCT_NAME, product.getName())), 10);
	}
	public String getSummaryPrice(Product product)
	{
		return actionUtility.getTextByJS(By.xpath(String.format(XPATH_SUMMARY_PRODUCT_PRICE, product.getName())));
	}
	 
	public String getSummaryColor(Product product)
	{
		return actionUtility.getTextByJS(By.xpath(String.format(XPATH_SUMMARY_PRODUCT_COLOR, product.getName())));
	}
	public String getSummarySize(Product product)
	{
		return actionUtility.getTextByJS(By.xpath(String.format(XPATH_SUMMARY_PRODUCT_SIZE, product.getName())));
	}
	
	
	
	

}
