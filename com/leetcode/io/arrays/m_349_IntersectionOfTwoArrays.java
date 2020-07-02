package io.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class m_349_IntersectionOfTwoArrays {
	/**
	  1. You could also sort both arrays,
	  	 then for each num in first array
	  	 perform binary search on 2nd array (sorted)
	  	 
	  2. Using a set, add all numbers from first array
	  	 for the 2nd array, if a number exists in set from (1)
	  	 	Add it to list of intersections
	 */
    public int[] intersection(int[] nums1, int[] nums2) {
        //1,1,2,2
        //2,2
        
        int i = 0, j = 0;
        List<Integer> nums = new ArrayList<>();
        
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        
        while(i < nums1.length && j < nums2.length) {
            if(nums1[i] < nums2[j]) {
                i++;
            }
            else if(nums1[i] > nums2[j]) {
                j++;
            }
            else {
                int val = nums1[i];
                nums.add(val);
                while(i < nums1.length && nums1[i] == val) 
                    i++;
                while(j < nums2.length && nums2[j] == val)
                    j++;
            }
        }
        int[] arr = new int[nums.size()];
        for(int k = 0; k < nums.size(); k++)
            arr[k] = nums.get(k);
        return arr;
    }
}
