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
		
		@Test(priority=1)
		public void departureCity() throws InterruptedException {
			Thread.sleep(5000);
			driver.findElement(By.xpath("//select[@name='fromPort']"));
			Select select = (Select) driver;
			select.selectByIndex(3);
	         
		}
		
		@Test
		public void destinationCity() throws InterruptedException {
			Thread.sleep(5000);
			driver.findElement(By.xpath("//select[@name='toPort']"));
			Select select = (Select) driver;
			select.selectByIndex(3);
	         
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
