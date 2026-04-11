package coding;

public class StringManipulation {

	public static void main(String[] args) {
		
		String name = "samroz faizan ahmed";
		String arr[]=name.split(" ");
		
		
		for (int i =0; i<=arr.length-1; i++) {
			if(i==1) {
			for (int j=arr[i].length()-1; j>=0; j--) {
				
				System.out.print(arr[i].charAt(j));
			  }		
			}
			System.out.println(arr[i]);
		}
		
}
}