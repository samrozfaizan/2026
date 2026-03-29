package coding;

public class NumbersManipulation {

	public static void main(String[] args) {
		//1. find min number in an array
		
		int num[]= {12,24,3,67,2,23};
		
		int min = num[0];
		
		for (int i=0; i<=num.length-1; i++) {
			if (num[i]<min) {
			min= num[i];
			}
		}
		System.out.println(min);	
		
		//2.find max number in an array.
		
		int num2[]= {23,12,65,56,34,89,78,34,23,9};
		
		int max=num2[0];
		
		for (int i=0; i<=num2.length-1; i++) {
			
			if (num2[i]>max) {
				max=num2[i];
			}
		}
		System.out.println(max);
		
		
		//3.find second lowest number in an array.
		
		int num3[]= {12,24,3,67,2,23};
		
		int minn = Integer.MAX_VALUE;
		int secondMin = Integer.MAX_VALUE ;
		
		for (int i=0; i<=num3.length-1; i++) {
			if (num3[i]<minn) {
				secondMin=minn;
				minn=num3[i];
			}
			
			else if (num3[i]<minn && secondMin>minn) {
				
				secondMin=num3[i];
			}
		}
		System.out.println(secondMin);
		
	//4. find the second highest number in an array.
		
		int num4[]= {23,12,65,56,34,89,78,34,23,9};
		
		int maxx = Integer.MIN_VALUE;
		int secondMax=Integer.MIN_VALUE;
		
		
		for (int i=0; i<=num4.length-1; i++) {
			
			if (num4[i]>maxx) {
				
				secondMax =maxx;
				maxx=num4[i];
			}
			
			else if (num4[i]>secondMax && num4[i]<maxx) {
				
				secondMax=num4[i];
			}
		}
		System.out.println(secondMax);
		
	}
	
	

}
