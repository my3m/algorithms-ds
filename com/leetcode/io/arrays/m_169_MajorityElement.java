package io.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class m_169_MajorityElement {
	//time=O(nlogn), space=O(1)
    public int majorityElement(int[] nums) {
        int constraint = nums.length / 2, i = 0;
        Arrays.sort(nums);
        while(i < nums.length) {
            //1,1,1,2,2,2,2
            //3
            //6,6,7
            //6,7,7
            int val = nums[i], cnt = 0;
            while(i > 0 && i < nums.length && nums[i] == nums[i - 1]) {
                cnt++;
                i++;
            }
            if(cnt + 1 > constraint)
                return val;
            i++;
        }
        return 0;
    }
    
    public int majorityElement2(int[] nums) {
        int constraint = nums.length / 2, i = 0;
        Map<Integer, Integer> frq = new HashMap<Integer, Integer>();
        for(Integer x : nums) {
            int occurences = frq.getOrDefault(x, 0) + 1;
            if(occurences > constraint)
                return x;
            frq.put(x, occurences);
        }
        return 0;
    }        
}
