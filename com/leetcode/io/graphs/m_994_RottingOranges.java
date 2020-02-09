package io.graphs;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class m_994_RottingOranges {
    //"A rotten orange"
    //wrong approach, your answer min + 1 in dfs in returning the depth
    //weakness: computation in wrong state
    //not considering graph only edge state
    
	/*
	 	This question was very difficult using DFS
	 	1. Using a running counter in DFS
	 */
    public int orangesRotting2(int[][] grid) {
        
        if(grid.length == 0)
            return -1;
        
        int numOranges = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                if(grid[i][j] == 1)
                    numOranges++;
            }
        }
        
        int[][] minTimes = new int[grid.length][grid[0].length];
        for(int i = 0; i < minTimes.length; i++){
            Arrays.fill(minTimes[i], Integer.MAX_VALUE);
        }
        
        //System.out.println(fresh);   
        int count = 0;
        int maxPath = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                if(grid[i][j] == 2) {
                    //System.out.printf("dfs @ i={%d}.j={%d}\r\n", i, j);
                    dfs(grid, i, j, minTimes, 0);
                }
            }
        }
        
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                if(grid[i][j] == 1) {
                    if(minTimes[i][j] < Integer.MAX_VALUE) {
                        count++;
                        maxPath = Math.max(maxPath, minTimes[i][j]);
                    }
                }
            }
        }        
        
        if(count == numOranges)
            return maxPath;
        return numOranges == 0 ? 0 : -1;
    }
    
    //Rotten side-by-side
    //[2,2,2,2,1]
    
    //Cannot reach fresh orange
    //[2,2,0,1,1]
    //[2,2,0,0,0]
    //[2,2,1,1,1]
    
    //all fresh
    //[0,0,0,0]
    
    //Deadly case: both 1s get rotten in one minute, you added two?
    //[0,0,1,2]
    //[2,0,1,1]
    
    //[0,1,1,2]
    //[2,1,1,1]
    int dfs(int[][] grid, int i, int j, int[][] minTimes, int min) {
        if(i < 0 || i >= grid.length)
            return 0;
        if(j < 0 || j >= grid[i].length)
            return 0;
        if(grid[i][j] == 0)
            return 0;
        if(min != 0 && grid[i][j] == 2)
            return 0;
        if(min >= minTimes[i][j])
            return 0;
        
        minTimes[i][j] = Math.min(minTimes[i][j], min);
        
        dfs(grid, i - 1, j, minTimes, min + 1);
        dfs(grid, i + 1, j, minTimes, min + 1);
        dfs(grid, i, j + 1, minTimes, min + 1);
        dfs(grid, i, j - 1, minTimes, min + 1);
        
        return min;
    }
    
    public int orangesRotting(int[][] grid) {
    	for(int i = 0; i < grid.length; i++) {
    		for(int j = 0; j < grid[0].length; j++) {
    			if(grid[i][j] == 2)
    				orangesRotting(grid, i, j, 2);
    		}
    	}
    	
    	
    	int max = Integer.MIN_VALUE;
    	for(int i = 0; i < grid.length; i++) {
    		for(int j = 0; j < grid[0].length; j++) {
    			System.out.print(grid[i][j]);
    			if(grid[i][j] == 1)
    				return -1;
    			max = Math.max(max, grid[i][j]);
    		}
    		System.out.println();
    	}
    	return (max > 1) ? (max - 2) : 0;
    }
    
    public void orangesRotting(int[][] grid, int i, int j, int level) {
    	if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length)
    		return;
    	if(grid[i][j] == 1 || (level == 2 || level < grid[i][j])) {
    		grid[i][j] = level;
    		orangesRotting(grid, i - 1, j, level + 1);
    		orangesRotting(grid, i + 1, j, level + 1);
    		orangesRotting(grid, i, j + 1, level + 1);
    		orangesRotting(grid, i, j - 1, level + 1);
    	}
    }
    
    
    @Test
    public void Test1() {
    	int[][] grid = new int[][]
    	{
    		{2,1,1},
    		{1,1,0},
    		{0,1,1}
    	};
    	assertEquals(4, orangesRotting(grid));
    }
    
    @Test
    public void Test2() {
    	int[][] grid = new int[][]
    	{
    		{2,0,1},
    		{1,0,1},
    		{0,0,1}
    	};
    	assertEquals(-1, orangesRotting(grid));
    }
    
    @Test
    public void Test3() {
    	int[][] grid = new int[][]
    	{
    		{2,0,2},
    		{1,0,1},
    		{0,0,1}
    	};
    	assertEquals(2, orangesRotting(grid));
    }
    
    @Test
    public void Test4() {
    	int[][] grid = new int[][]
    	{
    		{2,1,2},
    		{0,1,1},
    		{2,0,0}
    	};
    	assertEquals(2, orangesRotting(grid));
    }
    
    @Test
    public void Test5() {
    	int[][] grid = new int[][]
    	{
    		{2,0,2},
    		{0,0,0},
    		{2,0,0}
    	};
    	assertEquals(0, orangesRotting(grid));
    }
    
    @Test
    public void Test6() {
    	int[][] grid = new int[][]
    	{
    		{2,1,1,1,2,1,1}
    	};
    	assertEquals(2, orangesRotting(grid));
    }
    
    @Test
    public void Test7() {
    	int[][] grid = new int[][]
    	{
    		{2,0,2},
    		{0,0,0},
    		{2,1,0}
    	};
    	assertEquals(1, orangesRotting(grid));
    }
}
