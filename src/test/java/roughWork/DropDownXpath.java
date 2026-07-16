package roughWork;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DropDownXpath {
	public static void main(String[] args) {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://demoqa.com/select-menu");
		
		   
             driver.findElement(By.xpath("(//div[@class='css-1xc3v61-indicatorContainer'])[1]")).click();
             
			List<WebElement> droplist = driver.findElements(By.xpath("//div[@role='option']"));
		
			System.out.println(droplist.get(0).getText());
			System.out.println(droplist.get(1).getText());
			System.out.println(droplist.get(2).getText());
			System.out.println(droplist.get(3).getText());
			System.out.println(droplist.get(4).getText());
			System.out.println(droplist.get(5).getText());
			
		
		
	}
	
	

}
