package selenium26;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Omnichannel26 {
	
public static void main(String[] args) throws InterruptedException {
	
	WebDriver driver = new ChromeDriver();
	
	driver.manage().window().maximize();
	driver.get("https://practice.expandtesting.com/radio-buttons");
	
	Thread.sleep(3000);
    
    List<WebElement> allButtons = driver.findElements(By.xpath("//label[@class='form-check-label']"));
  System.out.println(allButtons.size());
  
  String exp = "black";
  
  for (int i=0; i<allButtons.size();i++) {
	 // if (allButtons.get(i).getText().trim().equalsIgnoreCase(exp)) {
		  allButtons.get(i).click();
		  System.out.println(allButtons.get(i).getText());
		
		 
	//  }
  }
 // driver.close();
    
	
}
	
}
