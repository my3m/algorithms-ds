package io.primitives;

public class m_268_MissingNumber {
    public int missingNumber(int[] nums) {
        //we can sort, check A[i] = A[i - 1] + 1
        //nlogn solution
        
        // sum of series to n, - sum
        //n solution
        
        //[0]
        //
        
        
        int n = nums.length;
        int exp = (n * (n + 1)) / 2;       
        int act = 0;
        for(int x : nums) {
            act += x;
        }
        if(exp == act)
            return 0;
        return exp - act;
    }
}
