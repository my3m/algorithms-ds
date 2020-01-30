package io.arrays;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/*
 * Application: Sliding Window Technique (fast-slow ptr)
 */
public class m_487_MaxConsecutiveOnesII {
    public int findMaxConsecutiveOnes(int[] nums) {
        if(nums.length == 0)
            return 0;
        int k = 1;
        int j = 0;
        //[1]..i => 1, 1-0=> 1
        //[1,1] i=>2, j=0, 
        //[1,0,1,1,0,1,0,0,0]
        int i = 0;
        for(; i<nums.length;i++) {
            if(nums[i] == 0) {
                k--;
            }
            if(k < 0) {
                if(nums[j] == 0) {
                    k++;
                }
                j++;
            }
        }
        return i - j;
    }
    
    @Test
    public void Test1() {
    	assertEquals(5, findMaxConsecutiveOnes(new int[] { 1, 0, 1, 1, 0, 1, 0, 0, 0, 1, 1, 1, 1, 0 }));
    }
    
    @Test
    public void Test2() {
    	assertEquals(1, findMaxConsecutiveOnes(new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}));
    }
    
    @Test
    public void Test3() {
    	assertEquals(2, findMaxConsecutiveOnes(new int[] { 0, 1, 0, 0, 0}));
    }
    
    @Test
    public void Test4() {
    	assertEquals(2, findMaxConsecutiveOnes(new int[] { 0, 0, 0, 0, 1}));
    }
    
    @Test
    public void Test5() {
    	assertEquals(2, findMaxConsecutiveOnes(new int[] { 1, 0, 0, 0, 0}));
    }
    
    @Test
    public void Test6() {
    	assertEquals(3, findMaxConsecutiveOnes(new int[] { 1, 1, 0, 0, 0}));
    }
    @Test
    public void Test7() {
    	assertEquals(4, findMaxConsecutiveOnes(new int[] { 0, 1, 0, 1, 1}));
    }                   
}
