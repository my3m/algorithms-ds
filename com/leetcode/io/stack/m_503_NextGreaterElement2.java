package io.stack;

import static org.junit.Assert.assertArrayEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.junit.Test;

public class m_503_NextGreaterElement2 {
    public int[] nextGreaterElements(int[] nums) {
        //[8,9,10,2,1,0]
        //idx=6
        
        //10,2,1,0,8
        
        //[2,2,7,6,5,4,3,2]
        //[10,8,12,7,9] => [12,9], firstIdx = 2, [12,12]
        //[1,2,1]
        //2,1,
        
        //[1,2,1]
        //[2,1,1,2]
        
        //[2,2,7,6,5,4,3,2]
        
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        Stack<Integer> s1 = new Stack<Integer>();
        int firstIdx = -1;
        for(int i = 0; i < nums.length; i++) {
            while(s1.size() > 0 && nums[i] > nums[s1.peek()]) {
                map.put(s1.pop(), nums[i]);
            }
            if(s1.size() == 0) 
                firstIdx = i;
            s1.push(i);
        }
        
        for(int i = 0; s1.size() > 0 && i < nums.length; i++) {
            while(s1.size() > 0 && nums[i] > nums[s1.peek()]) {
                map.put(s1.pop(), nums[i]);
            }
        }
        
        // for(int i = 0; firstIdx != -1 && i <= firstIdx; i++) {
        //     while(s1.size() > 0 && nums[i] > nums[s1.peek()]) {
        //         if(!map.containsKey(s1.peek()))
        //             map.put(s1.pop(), nums[i]);
        //     }            
        // }
        
        int[] answer = new int[nums.length];
        for(int i = 0; i < answer.length; i++) {
            answer[i] = map.getOrDefault(i, -1);
        }
        return answer;
    }
    
    @Test
    public void Test1() {
    	int[] input = new int[] { 2,2,7,6,5,4,3,2 };
    	int[] exp = new int[] {7,7,-1,7,7,7,7,7};
    	assertArrayEquals(exp, nextGreaterElements(input));
    }
    
    @Test
    public void Test2() {
    	int[] input = new int[] { 10,8,12,7,9 };
    	int[] exp = new int[] {12,12,-1,9,10};
    	assertArrayEquals(exp, nextGreaterElements(input));
    }
}
