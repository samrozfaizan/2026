package baseClassPackage;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

public class LiciousProject {
	protected WebDriver driver;
	

	@BeforeSuite
	public void driverSetup() {
	
			driver = new ChromeDriver();
	
	}
	
	@BeforeMethod
	public void browserLaunch() {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get("https://www.licious.in/");
	}
//	@AfterSuite
//	public void tearDown() {
//		if (driver!=null) {
//			driver.quit();
//		}
//	}

}
