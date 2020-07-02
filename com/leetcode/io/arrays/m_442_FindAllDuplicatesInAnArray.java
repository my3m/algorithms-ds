package io.arrays;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
	The key here is that values in "nums" will be in range 1 <= x <= len(A)
	if we take the abs value, subtract one, and make that idx -ve
	if we reach that value again, idx will be -ve, so we know we've seen that value before
	
	careful adding nums[i] to duplicate set, it may be -ve due to a number before at its index
 */
public class m_442_FindAllDuplicatesInAnArray {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<Integer>();
        for(int i = 0; i < nums.length; i++) {
            int absValue = Math.abs(nums[i]);
            int idxValue = absValue - 1;
            if(nums[idxValue] < 0) {
                result.add(absValue);
            }
            else {
                nums[idxValue] = nums[idxValue] * -1;
            }
        }
        return result;
    }
    
    @Test
    public void Test1() {
    	int[] nums = new int[] { 4,3,2,7,8,2,3,1};
    	List<Integer> expected = new ArrayList<>();
    	expected.add(2);
    	expected.add(3);
    	
    	assertEquals(expected, findDuplicates(nums));
    }
}
