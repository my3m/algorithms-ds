package io.arrays;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class ArrayRotation {
	/** This is the O(1) cyclical implementation for array rotation.
	 		Certain indices when jumped by k=2
	 		0->2->4->0 (indices 1,3,5 are skipped)
	 		To take this into account, we implement for(int start = 0;)
	 		
			For O(n), simply map the array
						arr[(i + k) % len] = arr[i] for all indices
	 */
	public void rotate(int[] nums, int k) {
		// [1,2,3,4,5,6], k=2
		//
		int len = nums.length;
		k = k % len;
		int count = 0;

		for (int i = 0; count < nums.length; i++) {
			// 4->2->0->3->1->4(loop)
			// 0->3->6->2->5->1->4->(0)
			// nums[4] = nums[0]
			int start = i;
			int next = (start + k) % len;
			int previous = nums[start];
			int prev = -1;
			while (prev != start) {
				int temp = nums[next];
				nums[next] = previous;
				prev = next;
				next = (next + k) % len;
				previous = temp;
				count++;
			}
		}
	}

	@Test
	public void Test1() {
		int[] arr = new int[] { 1, 2, 3, 4, 5, 6 };
		rotate(arr, 2);
		assertArrayEquals(new int[] { 5, 6, 1, 2, 3, 4 }, arr);
	}
}
