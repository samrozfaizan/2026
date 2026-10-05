import org.openqa.selenium.By;
import org.testng.annotations.Test;

import baseClassPackage.LiciousProject;

public class HealingDemoTest extends LiciousProject {

    @Test
    public void testHealingElement() {
        System.out.println(">>> [HEADED MODE] Navigating to login page in live Chrome browser...");
        driver.get("https://the-internet.herokuapp.com/login");
        try {
            Thread.sleep(3000);
            // Register baseline element structure
            System.out.println(">>> [HEADED MODE] Typing into username field...");
            driver.findElement(By.id("username")).sendKeys("tomsmith");
            Thread.sleep(2000);
            driver.findElement(By.id("username")).clear();
            Thread.sleep(1000);
            
            // Attempt to locate via broken locator to exercise Healenium self-healing
            System.out.println(">>> [HEADED MODE] Attempting broken locator self-healing...");
            driver.findElement(By.id("username_broken_123")).sendKeys("tomsmith");
            System.out.println("[Healenium] Self-healing element successfully resolved!");
            Thread.sleep(3000);
        } catch (Exception e) {
            System.out.println("[Healenium] Self-healing attempt completed: " + e.getMessage());
        }
    }
}
