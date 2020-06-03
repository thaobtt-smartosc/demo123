package testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import pages.HomePage;
import pages.LoginPage;

public class LoginPageTest extends BaseTest{
	HomePage homePage;
	LoginPage loginPage;
	  @BeforeClass 
	  public void preCondition() {
		  homePage = new HomePage(driver);
		  loginPage = new LoginPage(driver);
	  }
	 
	@Test(dataProvider = "test_successful")
	
	public void testLogInSuccesful(String email, String pass, String message_successful) {
		homePage.open().clickOnSignIn();
		loginPage.login(email, pass);
		Assert.assertEquals(loginPage.checkSuccessful(), message_successful);
	}
	@Test(dataProvider = "test_email")
	@Story("Test invalid Email case")
	@Description("emailInvalid")
	public void testLogInInvalidEmail(String email, String pass, String message_invalid_email)
	{
		homePage.open().clickOnSignIn();
		loginPage.login(email, pass);
		Assert.assertEquals(loginPage.checkEmail(), message_invalid_email);
	}
	
	@Test(dataProvider = "test_password")
	@Story("Test invalid Password case")
	@Description("passwordInvalid")
	public void testLogInInvalidPassword(String email, String pass, String message_invalid_email)
	{
		homePage.open().clickOnSignIn();
		loginPage.login(email, pass);
		Assert.assertEquals(loginPage.checkPassword(pass), message_invalid_email);
	}
	@DataProvider(name = "test_successful")
	public static Object[][] test_successful() {
		return new Object[][] { { "thaobtt@smartosc.com", "Thao123456#", "Welcome, thao btt!" }, };
	}

	@DataProvider(name = "test_email")
	public static Object[][] test_email() {
		return new Object[][] {		
			 
				{"@#$%", "Thao123456#", "Please enter a valid email address (Ex: johndoe@domain.com)." },
				{ "", "Thao123456#", "This is a required field." },
				 };
	}

	@DataProvider(name = "test_password")
	public static Object[][] test_password() {
		return new Object[][] {
				{ "thaobtt@smartosc.com", "@%#", "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later." },
				{ "thaobtt@smartosc.com", "", "This is a required field." }, };
	}
	
	

}
