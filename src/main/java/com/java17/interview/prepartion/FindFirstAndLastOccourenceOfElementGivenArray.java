package com.java17.interview.prepartion;

public class FindFirstAndLastOccourenceOfElementGivenArray {

	public static void main(String[] args) {
		
		
		// count element first index and last index
        
        int [] nums = {5,7,7,8,8,8,8,8,10};
        int first = -1;
        int last = -1;
        
        for(int i = 0; i <nums.length; i++) {
        	//try to find 8's first and last occurrence in the given array
        	if(8 != nums[i]) {
        		continue;
        	}
        	if(first == -1) {
        		
        		first = i;
        	}else {
        		last = i;
        	}
       }
        if(first != -1){
    		System.out.println("First "+ ":" + first);
    		System.out.println("Last "+ ":" + last);
    	}else {
    		System.out.println("Not Found");
    	}
        

	}

}
