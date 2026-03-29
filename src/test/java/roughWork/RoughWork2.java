package roughWork;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;



public class RoughWork2 {
       WebDriver driver;
	@BeforeMethod
	public void edgeOpen () {
		 WebDriverManager.edgedriver().setup(); 
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("https://www.facebook.com/");
	}
	
	@Test
	public void test1() {
		
	}

}
