package testcasePackage;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import baseClassPackage.LiciousProject;
import pageClassPackage.LiciousHomePage;

public class LiciousHomePageTest extends LiciousProject {
	
	LiciousHomePage LiciousHomePage;
	@BeforeMethod
	public void initpages() {
		LiciousHomePage = new LiciousHomePage(driver);
	}
	//  Display test
	@Test(priority=1)
	public void categoryDisplayTest() {
		LiciousHomePage.categoryButtonDisplay();
		LiciousHomePage.refresh();
	}
	
	@Test(priority=2)
	public void storesDisplayTest() {
		LiciousHomePage.storesButtonDisplay();
		LiciousHomePage.refresh();
	}
	
	@Test(priority=3)
	public void labReportsDisplayTest() {
		LiciousHomePage.labReportsButtonDisplay();
		LiciousHomePage.refresh();
	}
	
	@Test(priority=4)
	public void loginDisplayTest() {
		LiciousHomePage.loginButtonDisplay();
		LiciousHomePage.refresh();
	}
	
	@Test(priority=5)
	public void cartButtonDisplayTest() {
		LiciousHomePage.cartButtonDisplay();
		LiciousHomePage.refresh();
	}
    // click test
	
	@Test(priority=6)
	public void cartButtonclickTest() {
		LiciousHomePage.cartButtonClick();
		LiciousHomePage.refresh();
	}
	
	@Test(priority=7)
	public void categoryButtonclickTest() {
		LiciousHomePage.categoryButtonClick();
		LiciousHomePage.refresh();
	}
	
	@Test(priority=8)
	public void storesButtonclickTest() {
		LiciousHomePage.storesButtonClick();
		LiciousHomePage.refresh();
	}
	
	@Test(priority=9)
	public void labReportsButtonclickTest() {
		LiciousHomePage.labReportsButtonClick();
		LiciousHomePage.refresh();
	}
	
	@Test(priority=10)
	public void loginButtonclickTest() {
		LiciousHomePage.loginButtonClick();
		LiciousHomePage.refresh();
	}

}
