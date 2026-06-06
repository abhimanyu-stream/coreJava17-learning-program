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



		//Alternative: Better Approach
///For a sorted array, you can use Binary Search (more efficient for large arrays):

public static int findFirst(int[] nums, int target) {
    int left = 0, right = nums.length - 1, result = -1;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (nums[mid] == target) {
            result = mid;
            right = mid - 1;  // Keep searching left
        } else if (nums[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    return result;
}

public static int findLast(int[] nums, int target) {
    int left = 0, right = nums.length - 1, result = -1;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (nums[mid] == target) {
            result = mid;
            left = mid + 1;  // Keep searching right
        } else if (nums[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    return result;
}

        

	}

}
/**
 * 
 * Detailed Explanation: FindFirstAndLastOccouranceOfElementGivenArray.java
This program finds the first and last occurrence of a target element (8) in a sorted array using a single pass algorithm.

Problem Statement
Given array: {5, 7, 7, 8, 8, 8, 8, 8, 10}
Find: First and last index of element 8

Algorithm Walkthrough
1. Initialization (Lines 11-12)
int first = -1;  // -1 means "not found yet"
int last = -1;   // -1 means "not found yet"
Both initialized to -1 to handle the case where element doesn't exist
-1 is a sentinel value indicating "not present"
2. Loop Through Array (Lines 14-27)
for(int i = 0; i < nums.length; i++) {
    if(8 != nums[i]) {
        continue;  // Skip elements that are not 8
    }
    if(first == -1) {
        first = i;  // Found first occurrence
    } else {
        last = i;   // Update last occurrence
    }
}
Step-by-Step Execution
Iteration	i	nums[i]	Condition	first	last	Action
0	0	5	8 ≠ 5	-1	-1	Skip (continue)
1	1	7	8 ≠ 7	-1	-1	Skip (continue)
2	2	7	8 ≠ 7	-1	-1	Skip (continue)
3	3	8	Match!	3	-1	first = 3 (first == -1)
4	4	8	Match!	3	4	last = 4 (first != -1)
5	5	8	Match!	3	5	last = 5 (update)
6	6	8	Match!	3	6	last = 6 (update)
7	7	8	Match!	3	7	last = 7 (update)
8	8	10	8 ≠ 10	3	7	Skip (continue)
3. Output Logic (Lines 28-33)
if(first != -1) {
    System.out.println("First: " + first);  // Output: First: 3
    System.out.println("Last: " + last);     // Output: Last: 7
} else {
    System.out.println("Not Found");
}
Key Logic Insights
Why this works:
First occurrence: When first == -1, we know it's the first time we've seen the element
Last occurrence: Every subsequent match updates last, so it always holds the most recent index
Not found case: If element doesn't exist, first stays -1
Edge Case Handling:
// If element appears only once:
// first = 3, last = -1
// Output would show: First: 3, Last: -1
⚠️ BUG ALERT: If the element appears only once, last remains -1, which is incorrect!

Bug Fix
The code has a subtle bug when the element appears only once. Here's the fix:

if(first != -1) {
    System.out.println("First: " + first);
    // Fix: If last is still -1, it means element appeared only once
    System.out.println("Last: " + (last == -1 ? first : last));
} else {
    System.out.println("Not Found");
}
Alternative: Better Approach
For a sorted array, you can use Binary Search (more efficient for large arrays):

public static int findFirst(int[] nums, int target) {
    int left = 0, right = nums.length - 1, result = -1;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (nums[mid] == target) {
            result = mid;
            right = mid - 1;  // Keep searching left
        } else if (nums[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    return result;
}

public static int findLast(int[] nums, int target) {
    int left = 0, right = nums.length - 1, result = -1;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (nums[mid] == target) {
            result = mid;
            left = mid + 1;  // Keep searching right
        } else if (nums[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    return result;
}
Complexity Analysis
Approach	Time Complexity	Space Complexity
Current (Linear)	O(n)	O(1)
Binary Search	O(log n)	O(1)
Summary
✅ What it does: Finds first and last index of target element
✅ Algorithm: Single-pass linear search
⚠️ Bug: Doesn't handle single-occurrence case properly
🎯 Better for sorted arrays: Binary search approach
📊 Output: First: 3, Last: 7

Would you like me to fix the bug or implement the binary search version?

 */