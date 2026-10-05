package utilities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Healenium-friendly explicit wait utilities.
 * Triggers driver.findElement(locator) FIRST so Healenium's healing proxy
 * can intercept and heal broken locators, followed by waiting on the returned WebElement.
 */
public class WaitUtils {

    /**
     * Waits for an element to be visible after triggering Healenium self-healing.
     */
    public static WebElement waitForVisibility(WebDriver driver, By locator, Duration timeout) {
        WebElement element = driver.findElement(locator); // Triggers healing if broken
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits for an element to be clickable after triggering Healenium self-healing.
     */
    public static WebElement waitForClickable(WebDriver driver, By locator, Duration timeout) {
        WebElement element = driver.findElement(locator); // Triggers healing if broken
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Retrieves an element with self-healing triggered directly.
     */
    public static WebElement waitForPresence(WebDriver driver, By locator) {
        return driver.findElement(locator); // Triggers healing if broken
    }

    /**
     * Safe click with explicit wait and fallback JavascriptExecutor for timeouts or click interception.
     */
    public static void safeClick(WebDriver driver, By locator, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        try {
            WebElement el = driver.findElement(locator); // Triggers healing if broken
            wait.until(ExpectedConditions.elementToBeClickable(el)).click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", driver.findElement(locator));
        }
    }
}
