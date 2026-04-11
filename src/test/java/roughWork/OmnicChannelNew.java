package roughWork;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utilities.RetryLogic;

public class OmnicChannelNew {
	//priority
	WebDriver driver;
	@BeforeTest 
	public void envSetup() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(12));
		driver.get("https://www.facebook.com/login/");	
	}
	
	@Test 
	public void test1() {
		driver.findElement(By.name("email")).sendKeys("samroz");;
	}
	
	@Test(retryAnalyzer =RetryLogic.class) 
	public void test2() {
		driver.findElement(By.name("pas")).sendKeys("asdaskj");;
	}
	
	@Test 
	public void test3() {
		driver.findElement(By.xpath("//div[@class='x1ja2u2z x78zum5 x2lah0s x1n2onr6 xl56j7k x6s0dn4 xozqiw3 x1q0g3np x972fbf x10w94by x1qhh985 x14e42zd x9f619 xtvsq51 xqbgfmv xbe3n85 x7a1id4 x1d9i5bo x1xila8y x1bumbmr xc8cyl1']")).click();;
	}
	
	@AfterMethod
	public void screenshots(ITestResult result) throws IOException {
		if (ITestResult.FAILURE==result.getStatus()) {
			
			TakesScreenshot ts = (TakesScreenshot) driver;
		File src =	ts.getScreenshotAs(OutputType.FILE);
			
			File dest = new File("C:\\Users\\samro\\OneDrive\\Pictures\\Screenshots\\FailedSS/" + result.getName() + ".jpeg");
			
			FileHandler.copy(src, dest);
		}
	}
	
}
