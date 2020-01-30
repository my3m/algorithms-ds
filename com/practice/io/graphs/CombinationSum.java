package io.graphs;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> allCombinations = new ArrayList<List<Integer>>();
        combinationSumHelper(candidates, target, new ArrayList<Integer>(), allCombinations, 0, 0);
        return allCombinations;
    }
    
    public void combinationSumHelper(int[] candidates, int target, List<Integer> combination,
		List<List<Integer>> allCombinations, int running, int state) {
        if(running == target) {
            allCombinations.add(new ArrayList<Integer>(combination)); 
        }
        else if (running < target) {
            //
            for(int i = state; i < candidates.length; i++) {
                //List<Integer> combinationNew = new ArrayList<Integer>(combination);
                //combinationNew.add(candidates[i]);
                //combinationSumHelper(candidates, target, combinationNew, allCombinations, running + candidates[i], i);
                if(candidates[i] > target)
                    continue;
                combination.add(candidates[i]);
                combinationSumHelper(candidates, target, combination, allCombinations, running + candidates[i], i);
                combination.remove(combination.size()-1);
            }
            //
        }
    }
    
    @Test
    public void Test1() {
    	List<List<Integer>> result = combinationSum(new int[] {2, 3, 6, 7 }, 7);
    	assertEquals("[2, 2, 3]", Arrays.toString(result.get(0).toArray()));
    	assertEquals("[7]", Arrays.toString(result.get(1).toArray()));
    }
    
}
