package testcasePackage;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import baseClassPackage.LiciousProject;
import pageClassPackage.LiciousCategoryPage;

public class LiciousCategoryTest extends LiciousProject {
	LiciousCategoryPage liciousCategoryPage;
	@BeforeMethod
	public void initpages() {
	 liciousCategoryPage = new LiciousCategoryPage(driver);
	}
	
	@Test(priority=1)
	public void categoryButtonClickTest() {
		liciousCategoryPage.categoryButtonClick();
	}
	
	
	
	@Test(priority=2)
	public void validateCategory() {
		liciousCategoryPage.categoryNameClick();
		
	}
	
	

}
