package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeSuite;

import com.epam.healenium.SelfHealingDriver;

public class BaseClass {
	
	public WebDriver driver;
	
	@BeforeSuite
	public void envSetup() {
		WebDriver rawDriver = new ChromeDriver();
		rawDriver.manage().window().maximize();

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

		driver.get("https://www.facebook.com/login/");
	}

}
