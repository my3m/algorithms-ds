package io.graphs;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/*
 * Idea: standard dfs approach, there are no cycles in this graph
 * 		 so a visited array is not needed.
 * To avoid processing duplicates, think in terms of a graph
 * 		   1 (root)				
 * 		/	 \		\	   \	\
 * 	   2	  2		 2		2 	 5
 * 	/  \  \
 * 2	2  5
 * 
 * dfs=> 1,2,2, 1,2,2, 1,2,5 (2nd one is a duplicate)
 * each level, i=0, what is out starting point for i= index
 * i != state && candidates[i] == candidates[i-1]
 * 
 * or, think in terms of backtracking after first 1,2,2
 * we can write a while loop, while (i < candidates.length - 1 && candidates[i+1] == candidates[i]
 * 
 * using a set is the naive approach
 */
public class CombinationSum2 {
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> allCombinations = new ArrayList<List<Integer>>();
		List<Integer> visitedList = new ArrayList<Integer>();
		Arrays.sort(candidates);
		System.out.println(Arrays.toString(candidates));
		combinationSumHelper(candidates, target, new ArrayList<Integer>(), allCombinations, 0, 0, visitedList, 0);
		return allCombinations;
	}

	public void combinationSumHelper(int[] candidates, int target, List<Integer> combination,
		List<List<Integer>> allCombinations, int running, int index, List<Integer> visited, int frame) {
		if (running == target) {
			allCombinations.add(new ArrayList<Integer>(combination));
		} else if (running < target) {
			//
			System.out.printf("frame=%d, index=%d c=%s\r\n", frame, index, combination);
			for (int i = index; i < candidates.length; i++) {
//				if(i > index && candidates[i - 1] == candidates[i])
//					continue;
				if(running + candidates[i] > target)
					continue;
				combination.add(candidates[i]);
				combinationSumHelper(candidates, target, combination, allCombinations, running + candidates[i], i + 1, visited, frame + 1);
				combination.remove(combination.size() - 1);
				//Another idea to handle duplicates in dfs
				//backtracking, if we processed 1,2,2, then backtrack remove the last 2
				//we go in the upper node
				if(i < candidates.length - 1 && candidates[i + 1] == candidates[i]) {
					i++;
				}
			}
		}
	}
	
//	public static void main(String[] args) {
//		CombinationSum2 instance = new CombinationSum2();
//		//1,1,2,5,6,7,10
//		//1,1
//		
//		/*
//		 	->1
//		 		->3
//		 			->5
//		 */
//		
//		//1,3,5,5,10
//		//1,1,2,2,2,4,4
//		
//		//1,1,1,1,2,2,3
//		List<List<Integer>> result = instance.combinationSum(new int[] { 1,1,1,2,2,3}, 3);
//		for(List<Integer> list : result) {
//			System.out.println(Arrays.toString(list.toArray()));
//		}
//	}

//	@Test
//	public void Test1() {
//		List<List<Integer>> result = combinationSum(new int[] { 10, 1, 2, 7, 6, 1, 5 }, 8);
//		assertEquals("[2, 2, 3]", Arrays.toString(result.get(0).toArray()));
//		assertEquals("[7]", Arrays.toString(result.get(1).toArray()));
//	}
	
	@Test
	public void Test2() {
		List<List<Integer>> result = combinationSum(new int[] { 2,5,2,1,2}, 5);
		assertEquals(Arrays.asList(1,2,2), result.get(0));
		assertEquals(Arrays.asList(5), result.get(1));
	}

}
