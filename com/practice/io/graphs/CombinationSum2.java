package io.graphs;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class CombinationSum2 {
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> allCombinations = new ArrayList<List<Integer>>();
		List<Integer> visitedList = new ArrayList<Integer>();
		Arrays.sort(candidates);
		combinationSumHelper(candidates, target, new ArrayList<Integer>(), allCombinations, 0, 0, visitedList);
		return allCombinations;
	}
	
	public boolean binarySearch(List<Integer> list, int left, int right, int target) {
		if(left > right)
			return false;
		int mid = left + ((right-left)/2);
		while(left <= right) {
			mid = left + ((right-left)/2);
			if(list.get(mid) == target)
				return true;
			else if(list.get(mid) > target) {
				right = mid - 1;
			}
			else if(list.get(mid) < target) {
				left = mid + 1;
			}
		}
		return false;
	}

	public void combinationSumHelper(int[] candidates, int target, List<Integer> combination,
		List<List<Integer>> allCombinations, int running, int index, List<Integer> visited) {
		if (running == target) {
			allCombinations.add(new ArrayList<Integer>(combination));
/*			int sum = 0;
			for(int s : combination) {
				sum = (sum*10) + s;
			}
			if(!visited.contains(sum)) {
				allCombinations.add(new ArrayList<Integer>(combination));
				visited.add(sum);
			}*/
/*			Binary Search does not work on unsorted lists			
//			if(!binarySearch(visited, 0, visited.size() -1 , sum)) {
//				System.out.println(sum);
//				allCombinations.add(new ArrayList<Integer>(combination));
//				visited.add(sum);
//			}
 * 
 */
		} else if (running < target) {
			//
			for (int i = index; i < candidates.length; i++) {
				if(i > index && candidates[i - 1] == candidates[i])
					continue;
				if(running + candidates[i] > target)
					continue;
				combination.add(candidates[i]);
				combinationSumHelper(candidates, target, combination, allCombinations, running + candidates[i], i + 1, visited);
				combination.remove(combination.size() - 1);
			}
			//
		}
	}
	
	public static void main(String[] args) {
		CombinationSum2 instance = new CombinationSum2();
		//1,1,2,5,6,7,10
		//1,1
		
		/*
		 	->1
		 		->3
		 			->5
		 */
		
		//1,3,5,5,10
		//1,1,2,2,2,4,4
		
		//1,1,1,1,2,2,3
		List<List<Integer>> result = instance.combinationSum(new int[] { 1,1,1,2,2,3}, 3);
		for(List<Integer> list : result) {
			System.out.println(Arrays.toString(list.toArray()));
		}
	}

//	@Test
//	public void Test1() {
//		List<List<Integer>> result = combinationSum(new int[] { 10, 1, 2, 7, 6, 1, 5 }, 8);
//		assertEquals("[2, 2, 3]", Arrays.toString(result.get(0).toArray()));
//		assertEquals("[7]", Arrays.toString(result.get(1).toArray()));
//	}

}
