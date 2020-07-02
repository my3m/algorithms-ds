package io.binarysearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class m_34_FindFirstAndLastPositionOfElementInSortedArray {
    public int[] searchRange(int[] nums, int target) {        
         int start = lower_bound(nums, target);
         if(start == nums.length || nums[start] != target)
             return new int[] { -1, - 1};
         return new int[] { start, lower_bound(nums, target + 1) - 1};
    }
    
    //can coverge to r = nums.length, if target > array
    //Default behavior
    //Finds lowest index that matches target
    //Or if target doesn't exist, will return insert postion
    int lower_bound(int[] nums, int target) {
        int l = 0, r = nums.length;
        int mid = l + (r - l)/2;
        while(l < r) {
            mid = l + (r - l)/2;
            if(nums[mid] < target)
                l = mid + 1;
            else
                r = mid;
        }
        return l;
    }
    
    public String destCity(List<List<String>> paths) {
        Map<String, List<String>> graph = new HashMap<>();
        for(List<String> path : paths) {
            graph.putIfAbsent(path.get(0), new ArrayList<String>());
            graph.get(path.get(0)).add(path.get(1));
        }
        return dfs(graph, paths.get(0).get(0));
    }
    
    public String dfs(Map<String, List<String>> graph, String current) {
        for(String adjacent : graph.get(current)) {
            return dfs(graph, adjacent);
        }
        return current;
    }    
    
    //will return insert position
    //if target exists, will return index + 1 of last match
    int upper_bound(int[] nums, int target) {
        int l = 0, r = nums.length;
        while(l < r) {
            int mid = l + (r - l)/2;
            if(target < nums[mid])
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }    
}
