package roughWork;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Interview1 {

     WebDriver driver;
		
		@BeforeTest
		public void envSetup() {
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("https://blazedemo.com/");
		}
		
		@Test(priority = 1)
		public void departureCity() throws InterruptedException {
		    Thread.sleep(5000);
		    // 1. Pehle dropdown element ko WebElement me store karein
		    WebElement fromPortElement = driver.findElement(By.xpath("//select[@name='fromPort']"));
		    
		    // 2. Select class ke constructor me us element ko pass karein (new keyword ke saath)
		    Select selectFrom = new Select(fromPortElement);
		    selectFrom.selectByIndex(3);
		}

		@Test(priority = 2)
		public void destinationCity() throws InterruptedException {
		    Thread.sleep(5000);
		    // 1. Destination dropdown element find karein
		    WebElement toPortElement = driver.findElement(By.xpath("//select[@name='toPort']"));
		    
		    // 2. New Select object banakar pass karein
		    Select selectTo = new Select(toPortElement);
		    selectTo.selectByIndex(3);
		}

		@Test(priority=2)
		public void submitClick() {
			driver.findElement(By.xpath("//input[@type='submit']")).click();
	  
		}
		

		@Test(priority=3)
		public void validateFlightList() {
			System.out.println(driver.getTitle());

		}
		
		@Test(priority=4)
		public void assertValidation() {
			WebElement airline = driver.findElement(By.xpath("//td[text()='Aer Lingus']"));
			System.out.println(airline.getText());
			String expt = "Aer Lingus";
			
			Assert.assertEquals(airline.getText(), expt);
			
			
		}

}
