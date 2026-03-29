package pom;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestPage extends BaseClass{
	
	public LoginPage loginPage;
	
	@BeforeTest
	public void loginPageSetup() {
		loginPage = new LoginPage(driver);
	} 
	
	@Test
	public void emailFieldTest() {
	loginPage.emailField("samroz.faizan");
		
	}
	@Test
	public void passFieldTest() {
		loginPage.passField("12345");
	}
	@Test
	public void pageTitle() {
		
		System.out.println(driver.getTitle());
	}

}
