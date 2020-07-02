package io.arrays;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class m_560_SubArraySumEqualsK {
	public int subarraySum(int[] nums, int k) {
		//return bruteForce(nums, k);
		return prefixHashSums(nums, k);
	}
	
	/**
	 * !!!IDEA
	 * 	lets assume we have n prefix sums that equal to x
	 *  & we find an x, such that ps[i] - k = x,
	 *  then we found x + 1 new subarrays equal to k
	 */
	int prefixHashSums(int[] nums, int k) {
		Map<Integer, Integer> ps = new HashMap<Integer, Integer>();
		ps.put(0, 1);
		int sum = 0;
		int count = 0;
		for(int i = 0; i < nums.length; i++) {
			sum+=nums[i];
			int offset = sum - k;
			if(ps.containsKey(offset))
				count+= ps.get(offset);
			ps.put(sum, ps.getOrDefault(sum, 0) + 1);
		}
		return count;
	}
	
	int bruteForce(int[] nums, int k) {
		int cnt = 0;
		for(int i = 0; i < nums.length; i++) {
			int sum = 0;
			for(int j = i; j < nums.length; j++) {
				sum+=nums[j];
				if(sum == k) cnt++;
			}
		}
		return cnt;
	}
	
	@Test
	public void Test1() {
		int[] arr = new int[]
				{
						3,4,2,1,5,3
				};
		assertEquals(3, subarraySum(arr, 3));
	}
}
