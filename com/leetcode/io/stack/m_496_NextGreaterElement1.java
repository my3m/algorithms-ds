package io.stack;

import static org.junit.Assert.assertArrayEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.junit.Test;

public class m_496_NextGreaterElement1 {
	 public int[] nextGreaterElement(int[] nums1, int[] nums2) {
		 Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		 Stack<Integer> s1 = new Stack<Integer>();
		 for(int i = 0; i < nums1.length; i++) {
			 while(s1.size() > 0 && nums1[i] > nums1[s1.peek()]) {
				 map.put(s1.pop(), i);
			 }
			 s1.push(i);
		 }
		 int[] answer = new int[nums2.length];
		 for(int i = 0; i < nums2.length; i++) {
			 answer[i] = map.getOrDefault(nums2[i], -1);
		 }
		 return answer;
	 }
	 
	 @Test
	 public void Test1() {
		 int[] nums1 = new int[] { 1,2,3,4};
		 int[] nums2 = new int[] { 2, 4 };
		 int[] exp = new int[] { 3, -1 };
		 assertArrayEquals(exp, nextGreaterElement(nums1, nums2));
	 }
}
