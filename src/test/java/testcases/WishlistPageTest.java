package testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import data.DataTest;
import data.Product;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;


import pages.HomePage;
import pages.LoginPage;
import pages.ProductDetailPage;

import pages.WishlistPage;
import java.util.ArrayList;
import java.util.List;

@Epic("Allure Report")
@Feature("VerifyProducOntWishListPage")
public class WishlistPageTest extends BaseTest {
	HomePage homePage;
	LoginPage loginPage;
	ProductDetailPage productDetailPage;
	WishlistPage wishlistPage;

	Product product1, product2;
	List<Product> listProduct;
	Float subTotal;
	String shippingMethod = "Fixed";

	@BeforeTest
	public void data() {
		homePage = new HomePage(driver);
		loginPage = new LoginPage(driver);
		productDetailPage = new ProductDetailPage(driver);
		wishlistPage = new WishlistPage(driver);

		DataTest dataTest = new DataTest();
		product1 = dataTest.getProduct("product1");
		product2 = dataTest.getProduct("product2");

		listProduct = new ArrayList<Product>();
		listProduct.add(product1);
		listProduct.add(product2);

		// Precondition: clear cart
		clearAllProductInWishlist();
	}

	public void clearAllProductInWishlist() {
		// go to login page
		homePage.open().clickOnSignIn();

		// login
		loginPage.login("thaobtt@smartosc.com", "Thao123456#");

		// check user log in successfully
		Assert.assertTrue(homePage.checkUserLoggedIn(), "Log in unsucessfully.Plz check!");

		// clear cart
		wishlistPage.openWishlist();
		wishlistPage.removeAllProductInWishList();
	}

	public void addProducts() {
		for (Product product : listProduct) {
			productDetailPage.addProductToWishList(product);
			Assert.assertTrue(productDetailPage.messageSuccessDisplayWhenAddProductToWishList(),
					"Add to wishlist unsucessfully.Plz check!");
		}
	}

	public void verifyProductInfoInWishList() {
		for (Product product : listProduct) {
			Assert.assertTrue(wishlistPage.checkProductNameDisplay(product),
					"Product " + product.getName() + " is not added to wishlist!");
			Assert.assertEquals(product.getPrice(), wishlistPage.getSummaryProductPrice(product),
					"Price of product " + product.getName() + " is not correct");
		}
	}
	@Test
	public void verifyProductInWishList() {
		// add products to wishList
		addProducts();
		verifyProductInfoInWishList();

	}
}
