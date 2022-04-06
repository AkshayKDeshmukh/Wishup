package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class loginPageObjects {

	public WebDriver driver;

	By emailId = By.xpath("//input[@id='email']");
	By password = By.xpath("//input[@type='password']");
	By loginButton = By.xpath("//input[@type='submit']");
	By errorMessage = By.xpath("//div[@class='ui error message']/p");

	public loginPageObjects(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getEmailId() {
		return driver.findElement(emailId);
	}

	public WebElement getPassword() {
		return driver.findElement(password);
	}

	public WebElement getLoginButton() {
		return driver.findElement(loginButton);
	}

	public WebElement getErrorMessage() {
		return driver.findElement(errorMessage);
	}
}
