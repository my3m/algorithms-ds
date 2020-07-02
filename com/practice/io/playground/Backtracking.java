package io.playground;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Backtracking {
	public static void main(String[] args) {
//		int[] nums = new int[] { 1,2,3 };
//	    List<List<Integer>> list = new ArrayList<>();
//	    Arrays.sort(nums);
//	    backtrack(list, new ArrayList<>(), nums, 0);
//	    list.toString();
	    //System.out.println(leastInterval(new char[] { 'A','A','A','B','B','B' }, 2));
	   // System.out.println(leastIntervalBFS(new char[] { 'A','A','A','B','B','B' }, 2));
	    int[][] matrix = new int[][] {
	    	{1, 1, 1, 0, 0},
	    	{1, 1, 1, 0, 0},
	    	{1, 1, 1, 0, 0}
	    };
	    int[][] dp = new int[matrix.length][matrix[0].length];
	    System.out.println(getLargestSquareAtEntry(matrix, dp, 2, 2));
//	    int max = 0;
//	    for(int i = 0; i < matrix.length; i++) {
//	    	for(int j = 0; j < matrix[0].length; j++) {
//	    		max = Math.max(max, getLargestSquareAtEntry(matrix, i , j));
//	    	}
//	    }
	    dp.toString();
	    //System.out.println(max*max);
	}
	
	public static int getLargestSquareAtEntry(int[][] matrix, int[][] dp, int i, int j) {
		if(i < 0 || j < 0 || matrix[i][j] == 0)
			return 0;
		else if(dp[i][j] != 0) {
			return dp[i][j];
		}
		else {
			dp[i][j] = 1 + Math.min(getLargestSquareAtEntry(matrix, dp, i - 1, j),
			Math.min(getLargestSquareAtEntry(matrix, dp, i - 1, j - 1), getLargestSquareAtEntry(matrix, dp, i, j - 1)));
			return dp[i][j];
		}
	}
	
    static int leastIntervalBFS(char[] tasks, int n) {
        Map<Integer, Integer> map = new HashMap<>();
        for(char c : tasks) {
        	map.put(c - 'A', map.getOrDefault(c - 'A', 0) + 1);
        }
        Queue<Integer> queue = new LinkedList<>();
        //return dfs(tasksLst, new int[26], 1, n) - 1;
        return bfs(map, queue, new int[26], n) ;
    }
	
    static int bfs(Map<Integer, Integer> map, Queue<Integer> queue, 
            int[] visited, int cooldown) {
      int interval = 1;
      int next = map.keySet().iterator().next();
      map.put(next, map.get(next)-1);
      queue.offer(next);
      Arrays.fill(visited, -1);
      visited[next] = 1;
      
      while(queue.size() > 0) {
          int chr = queue.poll();
          System.out.println(((char)(chr + 'A')));
		  if(chr != Integer.MAX_VALUE && map.get(chr) == 0) {
			  map.remove(chr);
		  }    
          if(map.size() == 0) {
              return interval;
          }
          boolean busy = true;
          for(Map.Entry<Integer, Integer> e : map.entrySet()) {
        	  if(e.getValue() == 0)
        		  continue;
        	  if(visited[e.getKey()] == -1) {
        		  visited[e.getKey()] = ++interval;
        		  map.put(e.getKey(), map.get(e.getKey()) - 1);
        		  queue.offer(e.getKey());
        		  busy = false;
        	  }
        	  else if(interval + 1 - visited[e.getKey()] > cooldown) {
        		  visited[e.getKey()] = ++interval;
        		  map.put(e.getKey(), map.get(e.getKey()) - 1);   		
        		  queue.offer(e.getKey());
        		  busy = false;
              }
          }
          if(busy) {
        	  queue.offer(Integer.MAX_VALUE);
          }
      }
      return 0;
  }
	
    static int leastInterval(char[] tasks, int n) {
        List<Character> tasksLst = new ArrayList<>();
        for(char c : tasks) {
        	tasksLst.add(c);
        }
        return dfs(tasksLst, new int[26], 1, n+1) - 1;
    }
    
    
    /**
     * 	
     */
    static int dfs(List<Character> tasks, int[] visited, int interval, int cooldown) {
        if(tasks.size() == 0)
            return interval;
        int min = Integer.MAX_VALUE;
        int sc = 0;
        for(int i = 0; i < tasks.size(); i++) {
            //2, 4, c=2
        	int last = interval - visited[tasks.get(i) - 'A'];
            if(visited[tasks.get(i) - 'A'] == 0 || last >= cooldown) {
                List<Character> tasksx = new ArrayList<Character>(tasks);
                tasksx.remove(i);
                int lastVisited = visited[tasks.get(i) - 'A'];
                visited[tasks.get(i) - 'A'] = interval;
                sc++;
                min = Math.min(min, dfs(tasksx, visited, interval + 1, cooldown));
                visited[tasks.get(i) - 'A'] = lastVisited;
            }
        }
        if(sc == 0)
        	min = Math.min(min, dfs(tasks, visited, interval + 1, cooldown));
        return min;
    }	

	static void backtrack(List<List<Integer>> list , List<Integer> tempList, int [] nums, int start){
	    list.add(new ArrayList<>(tempList));
	    for(int i = start; i < nums.length; i++){
	        tempList.add(nums[i]);
	        backtrack(list, tempList, nums, i + 1);
	        tempList.remove(tempList.size() - 1);
	    }
	}   
	
    static void getPermutationsV2(List<String> results, StringBuilder sb, boolean[] used, char[] chars) {
        if(sb.length() == chars.length) {
            results.add(sb.toString());
            //return;
        }
        for(int i = 0; i < chars.length; i++) {
        	if(used[i])
        		continue;
        	if(i > 0 && chars[i] == chars[i-1] && !used[i-1])
        		continue;
            sb.append(chars[i]);
            used[i] = true;
            getPermutationsV2(results, sb, used, chars);
            used[i] = false;
            sb.deleteCharAt(sb.length() - 1);
        }
    }	
	
    static void getPermutations(List<String> results, String str, String prefix) {
        if(str.length() == 0) {
            results.add(prefix);
        }
        for(int i = 0; i < str.length(); i++) {
            String rem = str.substring(0, i) + str.substring(i + 1);
            getPermutations(results, rem, prefix + str.charAt(i));
        }
    }

	
	static int numDecodingsHelper(String s, int i) {
        if(i == s.length())
            return 1;
        else if(i > s.length())
            return 0;
        int ways = 0, val = s.charAt(i) - '0';
        if(val > 0 && val <= 9)
            ways += numDecodingsHelper(s, i + 1);
        if(i + 2 <= s.length()) {
            int val2 = Integer.parseInt(s.substring(i, i + 2));
            if(val2 > 0 && val2 < 27) 
                ways += numDecodingsHelper(s, i + 2);
        }
        return ways;
	}	
	
	static int recursiveSum(String s, int idx) {
		if(s.length() == 0)
			return 1;
		if(idx >= s.length())
			return 0;
		int ways = 0;
			int val = Integer.parseInt(s.substring(0, idx + 1));
			if(val > 0 && val < 27) {
				String substr1 = s.substring(idx + 1);
				ways+= recursiveSum(substr1, 0);
			}			
			else {
				return 0;
			}
			ways += recursiveSum(s, idx + 1);
		return ways;
	}
}
