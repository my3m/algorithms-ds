package io.arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class m_697_DegreeOfAnArray {
    public int findShortestSubArray(int[] nums) {
        //how to find maximum freq element?
        //how to find subarray containing the freq element number
        
        //compute frequency in hashmap
        //find max freq element
        //another map to keep starting index???
        
        Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
        Map<Integer, List<Integer>> idxs = new HashMap<Integer, List<Integer>>();
        List<Integer> maxFrqElements = new ArrayList<>();
        int maxFrq = 0;
        for(int i = 0; i < nums.length; i++) {
            int x = nums[i];
            counts.put(x, counts.getOrDefault(x, 0) + 1);
            idxs.putIfAbsent(x, new ArrayList<>());
            idxs.get(x).add(i);
            if(counts.get(x) > maxFrq) {
                maxFrq = counts.get(x);
            }
        }
        int slength = Integer.MAX_VALUE;
        for(int value : counts.keySet()) {
            if(counts.get(value) == maxFrq) {
                List<Integer> indexes = idxs.get(value);
                int tmpl = indexes.get(indexes.size() - 1) - indexes.get(0) + 1;
                if(tmpl < slength) {
                    slength = tmpl;
                }
            }
        }
        return slength;
    }
}
