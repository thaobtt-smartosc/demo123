package testcases;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import data.DataTest;
import data.Product;
import pages.ComparePage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductDetailPage;



public class ComparePageTest extends BaseTest {
	HomePage homePage;
	LoginPage loginPage;
	ProductDetailPage productDetailPage;
	ComparePage comparePage;
	Product product1, product2;
	List<Product>listProduct;
	
	@BeforeTest
	public void Data()
	{
		homePage=new HomePage(driver);
		loginPage=new LoginPage(driver);
		productDetailPage=new ProductDetailPage(driver);
		comparePage=new ComparePage(driver);
		
		DataTest dataTest=new DataTest();
		product1=dataTest.getProduct("product1");
		product2=dataTest.getProduct("product2");
		listProduct=new ArrayList<Product>();
		listProduct.add(product1);
		listProduct.add(product2);
		clearAllProductInCompareList();
		
	}
	
	public void clearAllProductInCompareList()
	{
		homePage.open().clickOnSignIn();
		loginPage.login("thaobtt@smartosc.com", "Thao123456#");
		comparePage.openComparePage();
		comparePage.removeAllProductInComparePage();
		
	}
	public void addProducts()
	{
		for(Product product:listProduct)
		{
			productDetailPage.addProductToCompareList(product);
			Assert.assertTrue(productDetailPage.messageSuccessDisplayWhenAddProductToCompareList(), "Add to compare unsucessfully.Plz check!");
		}
	}
	
	public void verifyProductInfoInComparePage()
	{
		for(Product product : listProduct)
		{
			Assert.assertTrue(comparePage.checkProductNameDisplay(product),"Product "+product.getName()+" is not added to compare page!");
			Assert.assertEquals(comparePage.getSummaryProductPrice(product), product.getPrice());
		}
	}
	@Test
	public void verifyProductInComparePage()
	{
		addProducts();
		comparePage.openComparePage();
		verifyProductInfoInComparePage();
	}
	
	
	

}
