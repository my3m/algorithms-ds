package io.arrays;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class m_33_SearchRotatedArray1 {
	public int search(int[] nums, int target) {
        if(nums.length == 0)
            return -1;
        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }        
        int pivot = binaryFindPivot(nums);
        //System.out.println(pivot);
        if(nums[pivot] == target)
            return pivot;
        //vanilla binary search
        if(pivot == 0)
            return binarySearch(nums, 0, nums.length - 1, target);
        
        //from pivot onwards is an increasing range, p,.....arr.length - 1
        //from pivot backwards, is a decreasing range
        if(target > nums[pivot] && target <= nums[nums.length - 1])
            return binarySearch(nums, pivot, nums.length - 1, target);
        else if(target >= nums[0] && target <= nums[pivot - 1])
            return binarySearch(nums, 0, pivot -1, target);
        //1,3
        //3,1
        //4,5,6,7,0,1,2,3
        //9,0,1,2,3,4,5,6
        
        
        // if(nums[0] <= nums[pivot]) {
        //     //left-side is sorted
        //     if(target >= nums[0] && target < nums[pivot]) {
        //         return binarySearch(nums, 0, pivot, target);
        //     }
        // }
        // if(nums[pivot] <= nums[nums.length - 1]) {
        //     if(target > nums[pivot] && target <= nums[nums.length - 1])
        //         return binarySearch(nums, pivot + 1, nums.length - 1, target);
        // }
        return -1;
    }
    
    
    /*
        Most intuiton is based on fact arr[mid] > arr[left], => left-side sorted
        4,5,6,0,1,2
        
        3,1
        l=0, r=1, m=0
        
    
    */
    int binaryFindPivot(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        if(arr[left] < arr[right])
            return 0;
        while(left <= right) {
            int mid = left + (right-left)/2;
            if(arr[mid] > arr[mid + 1]) {
                return mid + 1;
            }
            //left-side is sorted
            if(arr[mid] >= arr[left]) {
                left = mid + 1;
            } //right-side is sorted
            else { // if(arr[right] >= arr[mid]) {
                right = mid - 1;
            }
        }
        return 0;
    }
    
    int binarySearch(int[] arr, int left, int right, int target) {
        while(left <= right) {
            int mid = left + (right - left)/2;
            if(target > arr[mid]) {
                left = mid + 1;
            } 
            else if(target < arr[mid]) {
                right = mid - 1;
            }
            else {
                return mid;
            }
        }
        return - 1;
    }
    
    //4,5,6,7,0,1,2
    /*
        
        0,1,2
        l=0, r=2, m=1
        l=0, r=1, m=0
        
    
    */
    int binarySearchRotatedArray(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int pivot = -1;
        while(left <= right) {
            int mid = left + (right-left)/2;
            if(arr[mid] == target) {
                return mid;
            }
            else if(arr[left] <= arr[mid]) {
                //left-side is sorted
                if(target >= arr[left] && target < arr[mid]) {
                    right = mid - 1;
                }
                else {
                    left = mid + 1;
                }
            } else if(arr[right] >= arr[mid]) {
                //right-side is sorted
                if(target > arr[mid] && target <= arr[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
    
    @Test
    public void Test1() {
    	int[] arr = new int[] { 4,5,6,7,8,9,0,1,2,3};
    	assertEquals(0, search(arr, 4));
    	assertEquals(arr.length - 1, search(arr, 3));
    	assertEquals(6, search(arr, 0));
    	assertEquals(-1, search(arr, 20));
    }
    
    //out of bounds mid cases
    @Test
    public void Test2() {
    	int[] arr = new int[] { 1, 3};
    	assertEquals(0, search(arr, 1));
    	assertEquals(1, search(arr, 3));
    	assertEquals(-1, search(arr, 0));
    }
    
    @Test
    public void Test3() {
    	int[] arr = new int[] { 3, 1};
    	assertEquals(0, search(arr, 3));
    	assertEquals(1, search(arr, 1));
    	assertEquals(-1, search(arr, 0));
    }
}
