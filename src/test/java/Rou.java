import java.io.File;

public class Rou {
   
	public static void main(String[] args) {
		
		
		File fis1 = new File ("\"C:\\Users\\samro\\Downloads\\Samroz_Faizan_QA_Automation_Tester_4Plus_Yrs_21Aug2026.pdf\"");
		File fis2 = new File ("\"C:\\Users\\samro\\Downloads\\Samroz_Faizan_QA_Automation_Tester_4Plus_Yrs_21Aug2026.pdf\"");

		System.out.println(fis1.hashCode());
		System.out.println(fis2.hashCode());
		

	}

}
