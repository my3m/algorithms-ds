package io.arrays;

import java.util.Arrays;

public class m_628_MaximumProductThreeNumbers {
    public int maximumProduct(int[] nums) {
        //There can be negative integers
        //Sort array, and multiple last 3 entries?
        //if > 2 negative values, multiple first two, then multiple with positive
        //how to handle 0 values???
        //[-10,5,-2]
        
        //[-50,-20,-10,0,100,100,101]
        Arrays.sort(nums);
//        int numNegatives = 0, numPositives = 0, max = 0;
//        for(int x : nums) {
//            if(x < 0)
//                numNegatives++;
//            else if(x > 0) {
//                numPositives++;
//                break;
//            }
//        }
//        int left = nums[nums.length -1] * nums[nums.length -2] * nums[nums.length -3];
//        if(numNegatives > 1 && numPositives > 0) {
//            left = Math.max(left, nums[0] * nums[1] * nums[nums.length - 1]);
//        }
//        return left;
        return Math.max(nums[nums.length -1] * nums[nums.length -2] * nums[nums.length -3],
        		nums[0] * nums[1] * nums[nums.length - 1]);
    }
}
