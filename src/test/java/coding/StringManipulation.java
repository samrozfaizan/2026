package coding;

public class StringManipulation {

	public static void main(String[] args) {
		//1. Reverse name --> samroz/zormas
		
		String name = "samroz";
		String reverse = "";
		
		for (int i=name.length()-1; i>=0; i--) {
			reverse = reverse + name.charAt(i);
		}
		
    //    System.out.println(reverse);
        
      //2. Reverse word position --> samroz faizan/faizan samroz
    	
    	String name2 = "samroz faizan";
        String arr[]= name2.split(" ");
        
        for (int i=arr.length-1; i>=0; i--) {
        //	System.out.print(arr[i] + " ");
        }
        
     //3. Reverse any specific/alternate word only --> samroz faizan/ zormas faizan
        
        String name3 = "samroz faizan ahmed";
        String arr1[]= name3.split(" ");
        
        for (int i=0; i<arr1.length; i++) {
        	if (i%2==0) {
        		
        		for (int j =arr1[i].length()-1; j>=0; j--) {
        	//		System.out.print(arr1[i].charAt(j));
        		}	
        	}
        	else {
       // 		System.out.print(arr1[i]);
        	}
       // 	System.out.print(" ");
        }
        
        //4. find the occurance --> samroz 
        
        String name4 = "samroz";
        
        for (char letter='a'; letter<='z'; letter++) {
        	
        	int count =0;
        	
        	for (int i=0; i<=name4.length()-1; i++) {
        	
        		if (name4.charAt(i)==letter) {
        			count++;
        		}
        	}
        	if (count>0) {
            	System.out.println(letter + " " + ":" + " " + count);
            }
        }
        
	}
	
	
}
