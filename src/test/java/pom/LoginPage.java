package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	 WebDriver driver;
	
	@FindBy(name="email")
	WebElement emailField;
	
	@FindBy(name="pass")
	WebElement passField;
	
	
	public LoginPage (WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements( driver, this);
	}
	
	public void emailField(String email) {
		emailField.sendKeys(email);
	}
	
	public void passField(String pass) {
		passField.sendKeys(pass);
	}
	
	
}
