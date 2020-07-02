package io.patterns;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrefixSum {
	
	
	public int[] getPrefixSum(int[] array) {
		//sum[i...j]
		//[1,4,6,11,24,1,5,2,72,12,6,2], i = 5, j = 9, 1....12

		//20,......25
		//lets look for a ps ending at sum = 5
		
		//[1,4], target = 5, sum = 5 at idx = 1, 2
		Map<Integer, Integer> sumIndexes = new HashMap<>();
		int sum = 0;
		for(int i = 0; i < array.length; i++) {
			sum += array[i];
			//int jIdxOffset = sum - k;
			//j.j+1.....i
			//i - (j+1) + 1
			//i-j-1+1
			//i-j
			
			sumIndexes.putIfAbsent(sum, i);
		}
		
		List<Integer> ps = new ArrayList<>();
		ps.add(0);
		for(int i = 0; i < array.length; i++) {
			//p[i] = p[0] + a[i]
			ps.add(ps.get(ps.size()-1)+array[i]);
		}
		int[] result = new int[ps.size()];
		for(int i = 0; i < result.length; i++) {
			result[i] = ps.get(i);
		}
		return result;
	}
}
