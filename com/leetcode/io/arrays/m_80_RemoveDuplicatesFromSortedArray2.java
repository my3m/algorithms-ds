package io.arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class m_80_RemoveDuplicatesFromSortedArray2 {
    public int removeDuplicates(int[] nums) {
        int i = 0, k = 1, left = 1;
		//2,3,5,7,11,13,
		for(;k < nums.length; k++) {
			if(nums[k] != nums[i]) {
                left = 1;
				nums[++i] = nums[k];
			}
            else {
                if(left > 0) {
                    left--;
                    nums[++i] = nums[k];
                }
            }
            
		}
        return i + 1;
    }
    
    @Test
    public void Test1() {
    	int[] input = new int[] {0,0,1,1,1,1,2,3,3};
    	int output = removeDuplicates(input);
    	assertEquals(7, output);
    	int[] actualArray = Arrays.copyOfRange(input, 0, output);
    	assertArrayEquals(new int[] { 0,0,1,1,2,3,3}, actualArray);
    }
    
}
