package weakTopics;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.epam.healenium.SelfHealingDriver;

public class WeakTopicsToCover {

    WebDriver driver;
    WebDriverWait wait;
    String parentWin;

    By googleBtn = By.xpath("//span[text()='Continue with Google']");
    By emailField = By.xpath("//input[@type='email']");
    By nextBtn = By.xpath("//span[text()='Next']");
    By fbEmail = By.name("email");

    @BeforeClass
    public void envSetup() {
        WebDriver delegate = new ChromeDriver();
        try {
            driver = SelfHealingDriver.create(delegate);
        } catch (Exception e) {
            com.epam.healenium.SelfHealingEngine engine = new com.epam.healenium.SelfHealingEngine(delegate);
            SelfHealingDriver.setEngineFields(delegate, engine);
            driver = SelfHealingDriver.create(engine);
        }
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.get("https://www.linkedin.com/home");
    }

    // ✅ Screenshot Method
    public void takeScreenshot(String testName) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String path = System.getProperty("user.dir") 
                + "/screenshots/" 
                + testName + "_" + timeStamp + ".png";

        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(src, new File(path));
            System.out.println("Screenshot saved: " + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ✅ Capture screenshot on failure
    @AfterMethod
    public void captureOnFailure(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            takeScreenshot(result.getName());
        }
    }

    @Test(priority = 1)
    public void openNewTab() {
        try {
            WebElement el = driver.findElement(googleBtn); // Triggers healing if broken
            wait.until(ExpectedConditions.elementToBeClickable(el)).click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", driver.findElement(googleBtn));
        }
    }

    @Test(priority = 2)
    public void windowsHandling() {

        parentWin = driver.getWindowHandle();

        wait.until(driver -> driver.getWindowHandles().size() > 1);

        Set<String> allWindows = driver.getWindowHandles();

        for (String win : allWindows) {
            if (!win.equals(parentWin)) {
                driver.switchTo().window(win);
                break;
            }
        }
    }

    @Test(priority = 3)
    public void childWindowActions() {

        WebElement emailEl = driver.findElement(emailField); // Triggers healing if broken
        WebElement email = wait.until(ExpectedConditions.visibilityOf(emailEl));
        email.sendKeys("samroz");

        try {
            WebElement nextEl = driver.findElement(nextBtn); // Triggers healing if broken
            wait.until(ExpectedConditions.elementToBeClickable(nextEl));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", nextEl);
            nextEl.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", driver.findElement(nextBtn));
        }
    }

    // ❌ This will fail intentionally (to test screenshot)
    @Test(priority = 4, dependsOnMethods = "childWindowActions")
    public void producingStaleElement() {

        driver.switchTo().window(parentWin);
        driver.navigate().to("https://www.facebook.com/");

        WebElement fbEl = driver.findElement(fbEmail); // Triggers healing if broken
        WebElement element = wait.until(ExpectedConditions.visibilityOf(fbEl));

        driver.navigate().refresh();

        // ❌ stale exception here → screenshot will be captured
        element.sendKeys("samroz");
    }

    @Test(priority = 5)
    public void handleStaleElement() {

        WebElement fbEl = driver.findElement(fbEmail); // Triggers healing if broken
        WebElement element = wait.until(
                ExpectedConditions.refreshed(
                        ExpectedConditions.visibilityOf(fbEl)
                )
        );

        element.sendKeys("handled successfully");
    }
}