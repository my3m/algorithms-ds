package io.arrays;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class m_81_SearchRotatedArray2 {
	  public boolean search(int[] nums, int target) {
	        return searchRotatedArray(nums, target);
	    }
	    
	    /**
	        Attempt- w/o finding pivot
	        
	        4,5,6,7,8,9,0,0,1,2
	        
	        4,5,6,6,6,6,7,8,9,0,0,1,2,3,3
	        l=0, r=14, m=7, arr[m] = 8, left-side sorted, l=m+1, l=8
	        l=8, r=14, m=11, arr[m]= 1, arr[l]=9, r=10
	        l=8, r=10, m=9, arr[m]
	        
	        
	        1,3,1
	        
	        1,3,1,1,1
	        l=0, r=4, m=2, arr[m]=1, arr[l] = 1, arr[r] ==
	        
	        
	    */
	    boolean searchRotatedArray(int[] arr, int target) {
	        int left = 0;
	        int right = arr.length - 1;
	        while(left <= right) {
	            int mid = left + (right - left)/2;
	            if(arr[mid] == target)
	                return true;
	            //if( (arr[left] == arr[mid]) && (arr[right] == arr[mid]) ) {++left; --right;}
	            //1,1,1,1,3,1
	            if(arr[left] == arr[mid] && arr[left]!= target) left++;
	            else if(arr[left] <= arr[mid]) {
	                //our left-side is sorted
	                if(target >= arr[left] && target < arr[mid]) {
	                    right = mid - 1;
	                } else {
	                    left = mid + 1;
	                }
	            }
	            else if(arr[right] >= arr[mid]) {
	                //right-side is sorted
	                if(target > arr[mid] && target <= arr[right]) {
	                    left = mid + 1;
	                } else {
	                    right = mid - 1;
	                }
	            }
	            // if(arr[mid] > arr[mid + 1])
	            //     return mid + 1;
	            // else if(arr[mid] < arr[mid - 1])
	            //     return mid;
	        }
	        return false;
	    }
	    
	    @Test
	    public void Test1() {
	    	int[] arr = new int[] { 4,5,6,7,8,9,0,1,2,3,3,3,3,3,};
	    	assertEquals(true, search(arr, 2));
	    	assertEquals(true, search(arr, 3));
	    	assertEquals(true, search(arr, 4));
	    	assertEquals(true, search(arr, 5));
	    	assertEquals(true, search(arr, 6));
	    	assertEquals(true, search(arr, 7));
	    	assertEquals(false, search(arr, 10));
	    }
	    
	    
	    @Test
	    public void Test2() {
	    	/**
			nums=[1,3,1,1,1,1], target=3
			left=0, right=5, mid=2
			Our code gets confused, because here the left-side appears sorted nums[0] < nums[mid]
			, and since target=3 > nums[mid], we scan to our right, we don't find 3 so test case is failed.
			We can fix this by skipping left by 1 each time from left,
			 so our nums becomes = [3,1,1,1,1], but we need to ensure 
			 that nums[0] != target. We know that is not the case, 
			 since target > nums[2] (mid), Because of this, the worst case complexity is O(n).
	    	 */
	    	int[] arr = new int[] { 1,3,1,1,1 };
	    	assertEquals(true, search(arr, 3));
	    	assertEquals(true, search(arr, 1));
	    	assertEquals(false, search(arr, 0));
	    }
}
