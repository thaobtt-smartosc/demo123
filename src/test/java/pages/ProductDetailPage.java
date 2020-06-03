package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Sleeper;

import data.Product;
import org.openqa.selenium.By;


import base.BasePage;
public class ProductDetailPage extends BasePage {

	String XPATH_CHOOSE_SIZE = "//div[@class='swatch-option text'][@option-label='%s']";
	String XPATH_CHOOSE_COLOR = "//div[@class='swatch-option color'][@option-label='%s']";
	String ID_INPUT_QTY = "qty";
	String ID_BUTTON_ADD_TO_CART = "product-addtocart-button";
	String XPATH_MESSAGE_ADDTOCART_SUCCESS = "//div[@role='alert'][./div[@data-ui-id='message-success']]";
	String XPATH_MESSAGE_ADDWISHLIST_SUCCESS = "//div[@data-bind = 'html: message.text']";
	String XPATH_MESSAGE_ADDCOMPARE_SUCCESS = "//div[@data-bind = 'html: message.text']";
	String XPATH_ADD_TO_WISHLIST = "//div[@class='product-social-links']//span[text()='Add to Wish List']";
	String XPATH_ADD_TO_COMPARE="//div[@class='product-social-links']//span[text()='Add to Compare']";
	public ProductDetailPage(WebDriver driver) {
		super(driver);
	}
	
	public void open(String url) {
		driver.get(url);
	}
	
	public void chooseSize(String size) {
		actionUtility.click(By.xpath(String.format(XPATH_CHOOSE_SIZE, size)));
	}
	
	public void chooseColor(String color) {
		actionUtility.click(By.xpath(String.format(XPATH_CHOOSE_COLOR, color)));
	}
	
	public void inputQty(int qty) {
		actionUtility.sendKeys(By.id(ID_INPUT_QTY), String.valueOf(qty));
	}
	
	public void addToCart() {
		actionUtility.click(By.id(ID_BUTTON_ADD_TO_CART));
	}

	public void addProductToCart(Product product){
		open(product.getUrl());
		chooseSize(product.getSize());
		chooseColor(product.getColor());
		addToCart();
		waitUtility.sleep(6);
	}

	public void addProductToWishList(Product product){
		open(product.getUrl());
		waitUtility.sleep(6);
		actionUtility.click(By.xpath(XPATH_ADD_TO_WISHLIST));
	}
	
	public void addProductToCompareList(Product product) {
		open(product.getUrl());
		waitUtility.sleep(6);
		actionUtility.click(By.xpath(XPATH_ADD_TO_COMPARE));
	}

	public boolean messageSuccessDisplayWhenAddProductToCart() {
		return actionUtility.checkElementDisplay(By.xpath(XPATH_MESSAGE_ADDTOCART_SUCCESS));
	}
	public boolean messageSuccessDisplayWhenAddProductToWishList() {
		return actionUtility.checkElementDisplay(By.xpath(XPATH_MESSAGE_ADDWISHLIST_SUCCESS));
	}

	public boolean messageSuccessDisplayWhenAddProductToCompareList()
	{
		return actionUtility.checkElementDisplay(By.xpath(XPATH_MESSAGE_ADDCOMPARE_SUCCESS));
	}
	
	
}
