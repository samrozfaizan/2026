package shashiRoughwork;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Demo1 {

	private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	private static final ThreadLocal<WebDriverWait> wait = new ThreadLocal<>();

	public WebDriver getDriver() {
		return driver.get();
	}

	public WebDriverWait getWait() {
		return wait.get();
	}

	@BeforeClass(alwaysRun = true)
	@Parameters("browser")
	public void setup(@Optional("chrome") String browser) {
		String browserName = (browser == null || browser.trim().isEmpty()) ? "chrome" : browser.trim();

		if (browserName.equalsIgnoreCase("chrome")) {
			driver.set(new ChromeDriver());
		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver.set(new FirefoxDriver());
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver.set(new EdgeDriver());
		} else {
			throw new IllegalArgumentException("Invalid browser name: " + browser);
		}

		getDriver().manage().window().maximize();
	}

	@BeforeMethod(alwaysRun = true)
	public void envSetup() {
		wait.set(new WebDriverWait(getDriver(), Duration.ofSeconds(10)));
		getDriver().get("https://qaplayground.com/practice");
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		WebDriver currentDriver = getDriver();
		if (currentDriver != null) {
			currentDriver.quit();
		}
		driver.remove();
		wait.remove();
	}

	@Test(enabled = false)
	public void inputFields() {
		String originalWindow = getDriver().getWindowHandle();
		getWait().until(ExpectedConditions.elementToBeClickable(
				By.xpath("//a[@data-testid='new-practice-card-input-fields']"))).click();
		Set<String> windowHandles = getDriver().getWindowHandles();
		if (windowHandles.size() > 1) {
			for (String handle : windowHandles) {
				if (!handle.equals(originalWindow)) {
					getDriver().switchTo().window(handle);
					break;
				}
			}
		}
	}

	@Test
	public void inputFieldButton() {
		WebElement dropdwonCta = getWait().until(ExpectedConditions.elementToBeClickable(
				By.xpath("//a[@data-testid='new-practice-card-input-fields']")));
		dropdwonCta.click();
		WebElement field = getWait().until(ExpectedConditions.elementToBeClickable(
				By.xpath("//input[@value='Avengers']")));
		field.clear();
		field.sendKeys("Marvel");
	}

	@Test
	public void dropDown() {
		WebElement dropdwonCta = getWait().until(ExpectedConditions.elementToBeClickable(
				By.xpath("//a[@data-testid='new-practice-card-dropdowns']")));
		dropdwonCta.click();
		WebElement dropdwonList = getWait().until(ExpectedConditions.elementToBeClickable(
				By.xpath("//select[@data-testid='fruit-select']")));
		Select select = new Select(dropdwonList);
		select.selectByIndex(2);
		List<WebElement> dropList = select.getOptions();
		for (int i = 0; i < dropList.size(); i++) {
			dropList.get(i).click();
			System.out.println(dropList.get(i).getText());
		}
	}

	@Test
	public void WindowHandles() {
		WebElement dropdwonCta = getWait().until(ExpectedConditions.elementToBeClickable(
				By.xpath("//a[@data-testid='new-practice-card-tabs-windows']")));
		dropdwonCta.click();
		WebElement dropdwonList = getWait().until(ExpectedConditions.elementToBeClickable(
				By.xpath("//button[@data-testid='tw-tab-a']")));
		dropdwonList.click();
		Set<String> allWindows = getDriver().getWindowHandles();
		System.out.println(allWindows);
		Iterator<String> AllWin = allWindows.iterator();
		String tabA = AllWin.next();
		String tabB = AllWin.next();
		getDriver().switchTo().window(tabA);
		getDriver().switchTo().window(tabB);
		WebElement textConfirm = getWait().until(ExpectedConditions.elementToBeClickable(
				By.xpath("(//span[text()='Playground '])[2]"))); // added 9 on index
		System.out.println(textConfirm.getText());
		getDriver().switchTo().window(tabA);
	}

	@Test
	public void iframe() {
		getDriver().get("https://qaplayground.com/practice");
		WebElement dropdwonCta = getWait().until(ExpectedConditions.elementToBeClickable(
				By.xpath("//a[@data-testid='new-practice-card-iframes']")));   // s removed on iframe
		dropdwonCta.click();
		getWait().until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("basic-frame"));
		WebElement inputField = getWait().until(ExpectedConditions.elementToBeClickable(
				By.xpath("//input[@data-testid='iframe-name-input']")));
		inputField.sendKeys("My name is Shashi");
		System.out.println(inputField.getText());
		WebElement sumitButton = getWait().until(ExpectedConditions.elementToBeClickable(
				By.xpath("//button[@data-testid='iframe-submit-btn']")));
		sumitButton.click();
	}

	@Test
	public void assertion() {
		getDriver().get("https://qaplayground.com/practice");
		WebElement dropdwonCta = getWait().until(ExpectedConditions.elementToBeClickable(
				By.xpath("//a[@data-testid='new-practice-card-iframes']")));
		dropdwonCta.click();
		getWait().until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("basic-frame"));
		WebElement inputField = getWait().until(ExpectedConditions.elementToBeClickable(
				By.xpath("//input[@data-testid='iframe-name-input']")));
		inputField.sendKeys("My name is Shashi");
		System.out.println(inputField.getText());
		WebElement sumitButton = getWait().until(ExpectedConditions.elementToBeClickable(
				By.xpath("//button[@data-testid='iframe-submit-btn']")));
		sumitButton.click();
	}
	
	@AfterMethod
	public void screenShot(ITestResult result) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			WebDriver currentDriver = getDriver();
			if (currentDriver != null) {
				TakesScreenshot ts = (TakesScreenshot) currentDriver;
				File source = ts.getScreenshotAs(OutputType.FILE);
				File dir = new File("C:\\Users\\samro\\OneDrive\\Desktop\\ss");
				if (!dir.exists()) {
					dir.mkdirs();
				}
				File destination = new File(dir, result.getName() + "_" + System.currentTimeMillis() + ".png");
				FileHandler.copy(source, destination);
			}
		}
	}

}
