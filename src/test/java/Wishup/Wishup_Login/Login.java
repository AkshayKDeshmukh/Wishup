package Wishup.Wishup_Login;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.loginPageObjects;
import resources.Base;

public class Login extends Base {

	private static Logger log = LogManager.getLogger(Login.class.getName());

	@BeforeMethod
	public void openBrowser() throws IOException {
		driver = intializeDriver();
		log.info("Driver is initialized");
		driver.manage().window().maximize();
		log.info("Browser is maximized");
		driver.get(prop.getProperty("url"));
		log.info("Navigate to Wish up login Page");
	}

	@Test(dataProvider = "getData")
	public void loginFunctionality(String Scenario, String userName, String Password) {

		loginPageObjects loginObjects = new loginPageObjects(driver);
		loginObjects.getEmailId().sendKeys(userName);
		log.info("Email id entered");

		loginObjects.getPassword().sendKeys(Password);
		log.info("Password entered");

		loginObjects.getLoginButton().click();
		log.info("Login button Clicked");

		if (Scenario.equals("Valid Credentials")) {
			log.info("Login is Successful");
		} else if (Scenario.equals("Invalid Credentials")) {
			String errorMessage = loginObjects.getErrorMessage().getText();
			log.info("Both email and Password are incorrect");
			Assert.assertEquals(errorMessage, "Account does not exist. Click here to signup");

		} else if (Scenario.equals("onlyCorrectEmail")) {
			String errorMessage = loginObjects.getErrorMessage().getText();
			log.info("Email is correct and Password is incorrect");
			Assert.assertEquals(errorMessage,
					"Your credentials do not match. Forgot password? Click here to reset your password");

		} else if (Scenario.equals("onlyCorrectPassword")) {

			String errorMessage = loginObjects.getErrorMessage().getText();
			log.info("Email is incorrect and Password is correct");
			Assert.assertEquals(errorMessage, "Account does not exist. Click here to signup");
		}

	}

	@AfterMethod
	public void closeBrowser() {
		driver.close();
	}

	@DataProvider
	public Object[][] getData() {

		return new Object[][] { { "Valid Credentials", "wishup_testuser1@gmail.com", "testpw1" },
				{ "Invalid Credentials", "wishup_testuser10@gmail.com", "testpw20" },
				{ "onlyCorrectEmail", "wishup_testuser2@gmail.com", "testpw2" },
				{ "onlyCorrectPassword", "wishup_testuser10@gmail.com", "testpw1" },

		};

	}
}
