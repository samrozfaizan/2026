package utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryLogic implements IRetryAnalyzer {
	int count =0;
	int maxTry =3;
	@Override
	public boolean retry(ITestResult result) {
		
		if (count<maxTry) {
			count++;
			return true;
		}
		return false;
	
	}

}
