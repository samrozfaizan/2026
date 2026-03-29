package roughWork;

import org.testng.Assert;
import org.testng.annotations.Test;

public class OmnicChannelNew {
	//priority
	
	
	@Test()
	public void test1 () {
		Assert.assertEquals(1>8, false);
		
	}
	@Test (dependsOnMethods="test1")
	public void test2 () {
		System.out.println("this is test2");
	}
//	@Test()
//	public void test3 () {
//		System.out.println("this is test3");
//	}
//	@Test()
//	public void test4 () {
//		System.out.println("this is test4");
//	}
}
