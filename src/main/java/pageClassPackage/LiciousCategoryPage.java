package pageClassPackage;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LiciousCategoryPage {
	
	  WebDriver driver;
	  
	@FindBy(xpath="//span[text()='Categories']") 
	WebElement categoryButton;
	
	@FindBy(xpath="//ul[@class='CategoryMenu_category_list__czV2_']//li")
     List<WebElement> category;
	
	
	
	
	 public   LiciousCategoryPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	 
	 
	 public void categoryButtonClick() {
		 categoryButton.click();
	 }
	
	public void categoryNameClick () {
		for (int i=0; i<category.size(); i++) {
			WebElement element = category.get(i);
         	String actual=element.getText();
			String expectedText = "Licious Specials";
			if (actual.equalsIgnoreCase(expectedText)) {
			element.click();
			}
		}
	}
	



}
