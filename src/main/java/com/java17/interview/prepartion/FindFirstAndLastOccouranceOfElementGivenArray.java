package com.java17.interview.prepartion;

public class FindFirstAndLastOccouranceOfElementGivenArray {

	public static void main(String[] args) {

		//try to find 8's first and last occurrence in the given array

		// count element first index and last index
        
        int [] nums = {5,7,7,8,8,8,8,8,10};
        int first = -1;// consider element not present
        int last = -1;// consider element not present
        
        for(int i = 0; i <nums.length; i++) {
        	//try to find 8's first and last occurrence in the given array
        	if(8 != nums[i]) {
				//if consider element not present
        		continue;
        	}
        	if(first == -1) {
        		//element found
        		first = i;
        	}else {
				//element found
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
