package roughWork;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class Roughwork1 {
	
	WebDriver driver;
	
		@BeforeTest
	public void envSetup() {
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.globalsqa.com/demo-site/draganddrop/#google_vignette");
	}
	
	@Test
	public void verifyLinks() throws IOException {
		List<WebElement> allLinks = driver.findElements(By.tagName("link"));
		System.out.println(allLinks.size());
		
		for (int i=0; i<allLinks.size(); i++) {
			String url = allLinks.get(i).getAttribute("href");
			verifyUrl(url);
			
		}
	}
	
	public void verifyUrl(String url) throws IOException {
		
		URL links = new URL(url);
		
		HttpURLConnection connection = (HttpURLConnection) links.openConnection();
		connection.setRequestMethod("HEAD");
		connection.connect();
		
		int responseCode = connection.getResponseCode();
		String responseMessage = connection.getResponseMessage();
		
		if (responseCode>300) {
			System.out.println("brokenLinks: " + url + " =>" + responseCode + responseMessage);
		}
		else {
			System.out.println("validLinks: " + url + " =>" + responseCode + responseMessage);
		}
		
	}
	
	
		
}
