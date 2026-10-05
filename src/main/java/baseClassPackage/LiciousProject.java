package baseClassPackage;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.epam.healenium.SelfHealingDriver;

public class LiciousProject {
	protected static WebDriver driver;
	

	@BeforeSuite
	public void driverSetup() {
		if (driver == null) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			options.addArguments("--remote-allow-origins=*");
			WebDriver rawDriver = new ChromeDriver(options);

			boolean isHealingEnabled = Boolean.parseBoolean(System.getProperty("enableHealing", "false"));

			if (isHealingEnabled) {
				System.out.println(">>> HEALENIUM ENABLED: Running with Self-Healing Proxy <<<");
				try {
					driver = SelfHealingDriver.create(rawDriver);
				} catch (Exception e) {
					com.epam.healenium.SelfHealingEngine engine = new com.epam.healenium.SelfHealingEngine(rawDriver);
					SelfHealingDriver.setEngineFields(rawDriver, engine);
					driver = SelfHealingDriver.create(engine);
				}
			} else {
				System.out.println(">>> HEALENIUM DISABLED: Running with Pure Standard Selenium <<<");
				driver = rawDriver;
			}
		}
	}
	
	@BeforeMethod
	public void browserLaunch() {
		if (driver == null) {
			driverSetup();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get("https://www.licious.in/");
	}

	@AfterSuite(alwaysRun = true)
	public void tearDown() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}

}
