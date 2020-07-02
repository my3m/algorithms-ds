package io.arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

class ElementFrequency implements Comparable<ElementFrequency> {
    public int element;
    public int freq;
    
    public ElementFrequency(int e, int f) {
        this.element = e;
        this.freq = f;
    }
    
    @Override
    public int compareTo(ElementFrequency other) {
        return (this.freq - other.freq);
    }
}


/**
	Time = O(k + nlogn) solution
	Space = O(k + n)
	
	Since k <=n, it meets requirement of nlogn complexity.
 */

public class m_347_TopKFrequentElements {
	
	 public List<Integer> topKFrequent(int[] nums, int k) {
	        Arrays.sort(nums);
			
	        Set<Integer> values = new HashSet<Integer>();        
	        List<ElementFrequency> list = new ArrayList<ElementFrequency>();
	        List<Integer> answer = new ArrayList<>();
	        int i = 0, j = 0;

	        for(;i < nums.length;i++) {
	            int num = nums[i];            
	            if(values.add(num)) {
	                list.add(new ElementFrequency(num, binarySearch(nums, num + 1) - binarySearch(nums, num)));
	            }
	        }
	        Collections.sort(list);
	        for(int m = list.size() - 1; m >= 0 && answer.size() < k; m--) {
	            answer.add(list.get(m).element);
	        }
	        return answer;
	    }
	    
	    int binarySearch(int[] nums, int target) {
	        int l = 0, r = nums.length - 1;
	        while(l < r) {
	            int mid = l + (r - l)/2;
	            if(nums[mid] < target) {
	                l = mid + 1;
	            } else {
	                r = mid;
	            }
	        }
	        return l;
	    }	
	
	public List<Integer> topKFrequent2(int[] nums, int k) {
		Map<Integer, Integer> frequencyCounts = new HashMap<Integer, Integer>();

		for (int i = 0; i < nums.length; i++) {
			int num = nums[i];
			frequencyCounts.put(num, frequencyCounts.getOrDefault(num, 0) + 1);
		}

		List<Integer> values = new ArrayList<>(frequencyCounts.keySet());

		Collections.sort(values, (a, b) -> {
			return frequencyCounts.get(b) - frequencyCounts.get(a);
		});

		return values.subList(0, k);
	}

	@Test
    public void Test1() {
    	int[] nums = new int[]
    			{
    					1,1,1,2,2,3
    			};
    	assertEquals(Arrays.asList(1, 2), topKFrequent(nums, 2));
    }
}
