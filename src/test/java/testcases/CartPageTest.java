package testcases;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import data.DataTest;
import data.Product;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductDetailPage;

public class CartPageTest extends BaseTest{
	HomePage homePage;
	LoginPage loginPage;
	ProductDetailPage productDetailPage;
	CartPage cartPage;
	Product product1, product2;
	List<Product>listProduct;
	@BeforeTest
	public void Data()
	{
		homePage=new HomePage(driver);
		loginPage=new LoginPage(driver);
		productDetailPage=new ProductDetailPage(driver);
		cartPage=new CartPage(driver);
		DataTest dataTest=new DataTest();
		product1=dataTest.getProduct("product1");
		product2=dataTest.getProduct("product2");
		listProduct=new ArrayList<Product>();
		listProduct.add(product1);
		listProduct.add(product2);
		clearAllProductInCart();
	}
	
	
	public void clearAllProductInCart()
	{
		homePage.open().clickOnSignIn();
		loginPage.login("thaobtt@smartosc.com", "Thao123456#");
		cartPage.openCartPage();
		cartPage.removeAllProductInCart();
	}
	public void addProducts()
	{
		for(Product product : listProduct)
		{
			productDetailPage.addProductToCart(product);
			Assert.assertTrue(productDetailPage.messageSuccessDisplayWhenAddProductToCart(), "Add to cart unsucessfully.Plz check!");
		}
	}
	public void verifyProductInfoInCart()
	{
		
		for(Product product : listProduct)
		{
			Assert.assertTrue(cartPage.checkProductNameDisplay(product), "Product" + product.getName()+" is not added to cart");
			Assert.assertEquals(product.getPrice(), cartPage.getSummaryPrice(product),"Price of product "+product.getName()+"is not correct");
		}
	}
	@Test
	public void verifyProductInCart()
	{
		addProducts();
		verifyProductInfoInCart();
	}
	
	
 
	
}
