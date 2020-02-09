package io.graphs;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class CombinationSum3 {
	public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> all = new ArrayList<>();
        dfs(all,new ArrayList<>(), 1, n, k);
        return all;
    }
    
    void dfs(List<List<Integer>> all, List<Integer> path, int index, int target, int k) {
        if(target == 0) {
            if(path.size() == k)
                all.add(new ArrayList<>(path));
            return;
        }
        else if(target  > 0 && path.size() < k) {
            for(int i = index; i <= 9; i++) {
                path.add(i);
                dfs(all, path, i + 1, target - i, k);
                path.remove(path.size() - 1);
            }
        }
    }
    
    @Test
    public void Test1() {
    	List<List<Integer>> expected = new ArrayList<>();
    	expected.add(Arrays.asList(1,2,4));
    	assertEquals(expected, combinationSum3(3, 7));
    }
    
    @Test
    public void Test2() {
    	List<List<Integer>> expected = new ArrayList<>();
    	expected.add(Arrays.asList(1,2,6));
    	expected.add(Arrays.asList(1,3,5));
    	expected.add(Arrays.asList(2,3,4));
    	assertEquals(expected, combinationSum3(3, 9));
    }
}
