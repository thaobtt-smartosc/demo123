package pages;


import data.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class WishlistPage extends BasePage {
   
	String XPATH_EMPTY_WISHLIST = "//div[@class='message info empty']";
    String XPATH_BUTTON_REMOVE_WISHLIST= "//div[@class='products-grid wishlist']//a[@class='btn-remove action delete']";
    String XPATH_SUMMARY_PRODUCT_NAME = "//div[@class='products-grid wishlist']//a[normalize-space(text())='%s']";
    String XPATH_SUMMARY_PRODUCT_PRICE = "//div[@class='products-grid wishlist']//div[@class='product-item-info'][.//a[normalize-space(text())='%s']]//span[@class='price']";
  
 
    public WishlistPage(WebDriver driver) {
        super(driver);
    }
    public void openWishlist() {
    	driver.get("https://demo.smartosc.com/wishlist/");
    }
  
    public boolean checkProductNameDisplay(Product product)
	{
		return actionUtility.checkElementExist(By.xpath(String.format(XPATH_SUMMARY_PRODUCT_NAME, product.getName())), 10);
	}

    public String getSummaryProductPrice(Product product) {
        return actionUtility.getTextByJS(By.xpath(String.format(XPATH_SUMMARY_PRODUCT_PRICE, product.getName())));
    }

    public void removeAllProductInWishList() {
        while (driver.findElements(By.xpath(XPATH_EMPTY_WISHLIST)).size() == 0) {
            actionUtility.clickByJS(By.xpath(XPATH_BUTTON_REMOVE_WISHLIST));
            waitUtility.sleep(5);
        }
    }

}
