package io.arrays;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;

import org.junit.Test;

public class m_88_MergeSortedArray {
	
	  public void merge(int[] nums1, int m, int[] nums2, int n) {
	        /*
	            We can utilize the empty space @ end of nums1 & nums2 array
	            Start from right to left, overriting largest val into nums1
	            
	            take care of edge case where i == 0 & j != 0         
	        */
	        
	        int i = m - 1, j = nums2.length - 1, k = nums1.length - 1;
	        while(i >= 0 && j >= 0) {
	            if(nums1[i] >= nums2[j]) {
	                nums1[k--] = nums1[i--];
	            }
	            else {
	                nums1[k--] = nums2[j--];
	            }
	        }
	        //10,11,12,0,0,0
	        //7,8,13
	        
	        //1,2,7,8,9,10
	        while(j >= 0) {
	            nums1[k--] = nums2[j--];
	        }
	    }
	
	/**
		Create a copy of nums1 O(m) space
		initialize i,j,k ptr's
		Time = O(n + m)
		Space = O(m)
	 */
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int[] nums1copy = new int[m];
        for(int i = 0; i < m; i++) {
            nums1copy[i] = nums1[i];
        }
        int i = 0, j = 0, k = 0;
        while(i < nums1copy.length && j < nums2.length) {
            if(nums1copy[i] <= nums2[j]) {
                nums1[k++] = nums1copy[i++];
            }
            else {
                nums1[k++] = nums2[j++];
            }
        }
        while(i != nums1copy.length) {
            nums1[k++] = nums1copy[i++];
        }
        while(j != nums2.length) {
            nums1[k++] = nums2[j++];
        }        
    }
	
    /**        
	    iterate i index
	    iterate j index
	    when nums[i] >= nums[j]
	        put nums[j] in nums[i],
	        shift all numbers in nums[i] 1 to the right
	        
	    1. execution of logic is slow
	    2. Multiple bugs
	    3. Wrong to assume all zero's are replacements
	       Can use m .... nums1.length to set Integer.MAX_VALUE;
	        
	    [-1,0,0,3,3,3,0,0,0]
	    6
	    [1,2,2]
	    3                
        
     */
    public void merge3(int[] nums1, int m, int[] nums2, int n) {
    	
        for(int i = m; i < nums1.length; i++){
            if(nums1[i] == 0)
                nums1[i] = Integer.MAX_VALUE;
        }
    	
        int i = 0, j = 0;
        while(i < nums1.length && j < nums2.length) {
            if(nums1[i] >= nums2[j]) {
                //shift all elements to right 1 by 1
                int num3 = nums2[j];
                int tmp = nums1[i];
                int k = i;
                while(k < nums1.length) {
                    tmp = nums1[k];
                    nums1[k] = num3;
                    num3 = tmp;
                    k++;
                }
                i++;
                j++;
            }
            else if(nums1[i] < nums2[j]) {
                i++;
            }
        }
    }
    
    @Test
    public void Test1() {
		int[] nums1 = new int[] { -1, 0, 0, 3, 3, 3, 0, 0, 0 };
		int[] nums2 = new int[] { 1, 2, 2 };
		merge(nums1, 6, nums2, 3);
		System.out.println(Arrays.toString(nums1));
		assertArrayEquals(new int[] { 1, 2, 2, 3, 5, 6 }, nums1);
    }
}
