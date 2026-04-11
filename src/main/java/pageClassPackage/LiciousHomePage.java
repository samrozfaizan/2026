package pageClassPackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LiciousHomePage {

	  WebDriver driver;
	  
		
		@FindBy(xpath="//span[text()='Categories']")
	     WebElement category;
		
		@FindBy(xpath="//p[text()='Stores']")
		 WebElement stores;
		
		@FindBy(xpath="//span[text()='Lab Reports']")
		 WebElement  labReports;
		
		@FindBy(xpath="//span[text()='Login']")
		 WebElement  login;
		
		@FindBy(xpath="//span[text()='Cart']")
		 WebElement  cart;
		
		
		 public   LiciousHomePage(WebDriver driver){
			this.driver=driver;
			PageFactory.initElements(driver, this);
		}
		 
		 // display methods
		 public void categoryButtonDisplay() {
			 category.isDisplayed();
		 }
		 
		 public void storesButtonDisplay() {
			 stores.isDisplayed();
		 }
		 
		 public void labReportsButtonDisplay() {
			labReports.isDisplayed();
		 }
		 
		 public void loginButtonDisplay() {
			login.isDisplayed();
		 }
		 
		 public void cartButtonDisplay() {
			 cart.isDisplayed();
		 }
		
		 
		 
		// click methods  
		 public void categoryButtonClick() {
			 category.click();
		 }
		 
		 public void storesButtonClick() {
			 stores.click();
		 }
		 
		 public void labReportsButtonClick() {
			 labReports.click();
		 }
		 
		 public void loginButtonClick() {
			 login.click();
		 }
		 
		 public void cartButtonClick() {
			 cart.click();
		 }
		 
		 //Resuable methods
		 
		 public void forward() {
			 driver.navigate().forward();
		 }
		
		 public void backward() {
			 driver.navigate().back();
		 }

		 public void refresh() {
			driver.navigate().refresh();
		 }
		 
		
}
