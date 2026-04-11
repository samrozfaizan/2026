package roughWork;



import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class RoughWork2 {
       WebDriver driver;
	@BeforeClass()
	public void driversetup () {
		driver = new ChromeDriver();	
	}
	@BeforeMethod
	public void envsetup() {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
	}
	
	@Test(priority=-1)
	public void launchWebsite() {
		driver.get("https://www.licious.in/");
	}
	
	@Test(priority=1)
	public void categroyButtonValidate () {
		WebElement element =driver.findElement(By.xpath("//span[text()='Categories']"));
		String actualText = element.getText();
		String expectedText = "Categories";
		
		Assert.assertEquals(actualText, expectedText);	
		System.out.println(actualText);
	}
	
	@Test(priority=2)
	public void categoryButtonClick () {
		WebElement element =driver.findElement(By.xpath("//span[text()='Categories']"));
		element.click();         	
	}
	
	@Test(priority=3)
	public void optionButtonValidate () {
		WebElement element =driver.findElement(By.xpath("//li[@id='l0_item_7'] [text()='Licious Specials']"));
		String actualText = element.getText();
		String expectedText = "Licious Specials";
		Assert.assertEquals(actualText, expectedText);	
		System.out.println(actualText);
	}
	

	@Test(priority=4)
	public void optionButtonClick () {
		WebElement element =driver.findElement(By.xpath("//li[@id='l0_item_7'] [text()='Licious Specials']"));
		element.click();         	
	}
	
	@AfterClass
	public void tearDown() throws InterruptedException {
		if(driver!=null) {
			Thread.sleep(5000);
			driver.quit();
		}
	}

}
