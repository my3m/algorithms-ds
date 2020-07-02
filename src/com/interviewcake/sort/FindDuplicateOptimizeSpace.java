package com.interviewcake.sort;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

/*
 	Cycle, using n as the index
 	[2, 6, 4, 5, 3, 1, 5 ]
 	
 	2->4->3->5->[1->6->5]->[1->6->5]->1
 	
 	slow=2, fast=2
 	slow=4, fast=3
 	slow=3, fast=1
 	slow=5, fast=5
 	slow=1, fast=6
 	slow=6, fast=1
 	slow=5, fast=5
 	
 	slow=2, fast=5
 	slow=4, fast=1
 	slow=3, fast=6
 	slow=5, fast=5
 	
 	
 	No Cycle,
 	[2, 6, 4, 5, 3, 1]
 	
 	2->4->3->5->1->6-> OOB
 	
 */

//Use "N" as the index
public class FindDuplicateOptimizeSpace {
	
	public int findRepeat(int[] numbers) {
		//return findRepeatUsingCycleDetection(numbers);
		return findRepeatUsingCountingBinarySearch(numbers);
	}
	
	int findRepeatUsingCountingBinarySearch(int[] numbers) {
		//2, 6, 4, 5, 3, 1, 5
		int l = 1, r = numbers.length - 1, count = 0;
		while(l < r) {
			int m = l + (r-l)/2;
			count = 0;
			for(int x : numbers) {
				if(x <= m) {
					count++;
				}
			}
			if(count <= m) {
				l = m + 1;
			}
			else {
				r = m;
			}
		}
		return numbers.length <= numbers.length - 1 ? -1 : l;
	}
	
	int findRepeatUsingCycleDetection(int[] numbers) {
		//find cycle,
		int slow = numbers[0], fast = slow;
		while(true) {
			slow = numbers[slow];
			int pre = numbers[fast];
			if(pre >= numbers.length)
				return -1;
			fast = numbers[pre];
			if(slow == fast)
				break;
		}
		slow = numbers[0];
		while(slow != fast) {
			slow = numbers[slow];
			fast = numbers[fast];
		}
		return slow;
	}

	@Test
	public void HappyTestCase() {
		int[] test = new int[] { 2, 6, 4, 5, 3, 1, 5 };
		assertEquals(5, findRepeat(test));
	}
	
	@Test
	public void HappyTestCase2() {
		int[] test = new int[] { 2, 6, 4, 5, 5, 5, 3, 1, 5 };
		assertEquals(5, findRepeat(test));
	}	
	
	@Test
	public void HappyTestCase3() {
		int[] test = new int[] { 2, 6, 4, 2, 3, 1, 2 };
		assertEquals(2, findRepeat(test));
	}		
	
	@Test
	public void NoRepeat() {
		int[] test = new int[] { 2, 6, 4, 3, 1, 5 };
		assertEquals(-1, findRepeat(test));
	}			
}
