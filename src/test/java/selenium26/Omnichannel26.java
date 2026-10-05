package selenium26;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Omnichannel26 {

    // ThreadLocal ensures each parallel thread has its own isolated WebDriver instance
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public static void setDriver(WebDriver driver) {
        driverThreadLocal.set(driver);
    }

    public static void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception ignored) {
            } finally {
                driverThreadLocal.remove();
            }
        }
    }

    /**
     * Creates and configures an optimized ChromeDriver instance.
     * Uses PageLoadStrategy.EAGER to avoid waiting for heavy 3rd-party ads and trackers.
     */
    public static WebDriver createDriver() {
        ChromeOptions options = new ChromeOptions();
        // Speeds up page load drastically by not waiting for ads/analytics to finish
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--remote-allow-origins=*");

        // Optional headless mode (e.g. -Dheadless=true)
        if (Boolean.parseBoolean(System.getProperty("headless", "false"))) {
            options.addArguments("--headless=new");
        }

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return driver;
    }

    /**
     * Robust click that scrolls the element to center view and uses JS click fallback
     * if Google ads or sticky headers intercept the standard click.
     */
    public static void safeClick(WebDriver driver, WebElement element) {
        // Scroll into view centered so sticky header/footer or ads don't obscure it
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({behavior: 'instant', block: 'center'});", element);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (Exception e) {
            // Fallback to JavaScript click when intercepted by overlay/ad banner
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    /**
     * Selects a radio button matching the specified label text (e.g. "black", "yellow", "tennis").
     * Returns true if selected, false if disabled or not found.
     */
    public static boolean selectRadioButtonByText(WebDriver driver, String expectedText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='form-check']")));

        List<WebElement> formChecks = driver.findElements(By.xpath("//div[@class='form-check']"));
        for (WebElement container : formChecks) {
            WebElement label = container.findElement(By.xpath(".//label[contains(@class,'form-check-label')]"));
            String labelText = label.getText().trim();

            if (labelText.equalsIgnoreCase(expectedText.trim())) {
                WebElement input = container.findElement(By.xpath(".//input[@type='radio']"));
                if (!input.isEnabled()) {
                    System.out.println("Radio button [" + labelText + "] is DISABLED, cannot click.");
                    return false;
                }
                safeClick(driver, input);
                System.out.println("Selected radio button: " + labelText + " | isSelected: " + input.isSelected());
                return input.isSelected();
            }
        }
        System.out.println("Radio button with text [" + expectedText + "] not found.");
        return false;
    }

    /**
     * Iterates over all radio buttons, skips disabled ones, clicks enabled ones, and prints details.
     */
    public static void iterateAllRadioButtons(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='form-check']")));

        List<WebElement> formChecks = driver.findElements(By.xpath("//div[@class='form-check']"));
        System.out.println("Total radio button containers found: " + formChecks.size());

        for (int i = 0; i < formChecks.size(); i++) {
            WebElement container = formChecks.get(i);
            WebElement label = container.findElement(By.xpath(".//label[contains(@class,'form-check-label')]"));
            WebElement input = container.findElement(By.xpath(".//input[@type='radio']"));

            String text = label.getText().trim();
            boolean enabled = input.isEnabled();

            if (enabled) {
                safeClick(driver, input);
                System.out.println("Clicked [" + text + "] - isSelected: " + input.isSelected());
            } else {
                System.out.println("Skipped [" + text + "] - status is DISABLED");
            }
        }
    }

    // ==========================================
    // TestNG Lifecycle & Parallel Test Methods
    // ==========================================

    @BeforeMethod
    public void setUp() {
        WebDriver driver = createDriver();
        setDriver(driver);
        getDriver().get("https://practice.expandtesting.com/radio-buttons");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        quitDriver();
    }

    @Test(description = "Verify selecting black radio button as originally desired")
    public void testSelectBlackColor() {
        boolean selected = selectRadioButtonByText(getDriver(), "black");
        Assert.assertTrue(selected, "Black radio button should be selected successfully");
    }

    @Test(description = "Verify green radio button is disabled")
    public void testDisabledGreenButton() {
        WebElement greenInput = getDriver().findElement(By.id("green"));
        Assert.assertFalse(greenInput.isEnabled(), "Green radio button should be disabled");
    }

    @Test(description = "Iterate through all radio buttons safely")
    public void testIterateAllButtons() {
        iterateAllRadioButtons(getDriver());
    }

    // Parallel DataProvider to execute multiple options simultaneously
    @DataProvider(name = "radioOptions", parallel = true)
    public Object[][] radioOptionsData() {
        return new Object[][] {
            {"blue"},
            {"red"},
            {"yellow"},
            {"black"},
            {"basketball"},
            {"football"},
            {"tennis"}
        };
    }

    @Test(dataProvider = "radioOptions", description = "Parallel execution for selecting different radio options")
    public void testSelectRadioOptionsParallel(String optionName) {
        System.out.println("[Thread " + Thread.currentThread().getId() + "] Testing option: " + optionName);
        boolean selected = selectRadioButtonByText(getDriver(), optionName);
        Assert.assertTrue(selected, "Radio button [" + optionName + "] should be selected.");
    }

    // ==========================================
    // Standalone main() with Parallel Execution Demo
    // ==========================================

    public static void main(String[] args) {
        System.out.println("=== Starting Omnichannel26 Standalone Runner ===");
        long startTime = System.currentTimeMillis();

        // 1. Single execution test: Select Black and Iterate
        System.out.println("\n--- Step 1: Single Thread Execution ---");
        WebDriver singleDriver = createDriver();
        try {
            singleDriver.get("https://practice.expandtesting.com/radio-buttons");
            System.out.println("Page loaded successfully.");

            String exp = "black";
            selectRadioButtonByText(singleDriver, exp);

            System.out.println("\nIterating through all buttons:");
            iterateAllRadioButtons(singleDriver);
        } finally {
            singleDriver.quit();
            System.out.println("Single execution browser closed.");
        }

        // 2. Parallel multi-thread execution demo: Run 3 threads simultaneously
        System.out.println("\n--- Step 2: Parallel Multi-Thread Execution Demo (3 Concurrent Threads) ---");
        String[] parallelOptions = {"black", "yellow", "tennis"};
        ExecutorService executor = Executors.newFixedThreadPool(parallelOptions.length);
        List<CompletableFuture<Void>> futures = new ArrayList<>();

        for (String option : parallelOptions) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                long threadId = Thread.currentThread().getId();
                System.out.println("[Worker Thread " + threadId + "] Launching browser for: " + option);
                WebDriver threadDriver = createDriver();
                setDriver(threadDriver);
                try {
                    getDriver().get("https://practice.expandtesting.com/radio-buttons");
                    boolean success = selectRadioButtonByText(getDriver(), option);
                    System.out.println("[Worker Thread " + threadId + "] Option [" + option + "] success: " + success);
                } finally {
                    quitDriver();
                    System.out.println("[Worker Thread " + threadId + "] Browser session ended.");
                }
            }, executor);
            futures.add(future);
        }

        // Wait for all parallel tasks to finish
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        executor.shutdown();

        long duration = (System.currentTimeMillis() - startTime) / 1000;
        System.out.println("\n=== All tasks completed successfully in " + duration + "s! ===");
    }
}
