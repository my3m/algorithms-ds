package io.graphs;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Amazon_ZombieInMatrix {
	
	/*
	 
 			{0, 1, 1, 0, 1},		{1, 1, 1, 1, 1},		{1, 1, 1, 1, 1}				
			{0, 1, 0, 1, 0},		{1, 1, 1, 1, 1},		{1, 1, 1, 1, 1},
			{0, 0, 0, 0, 1},		{0, 1, 0, 1, 1},		{1, 1, 1, 1, 1},
			{0, 1, 0, 0, 0}			{1, 1, 1, 0, 1}			{1, 1, 1, 1, 1}
	 
	 */
			
	
	
	
	public int solve(int[][] grid) {
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[0].length; j++) {
				if(grid[i][j] == 1)
					dfs(grid, i , j, 1);
			}
		}
		
		int max = Integer.MIN_VALUE;
		for(int i =0; i < grid.length; i++) {
			for(int j = 0; j < grid[0].length; j++) {
				if(grid[i][j] == 0)
					return - 1;
				max = Math.max(max, grid[i][j]);
			}
		}
		
		if(max == 0)
			return 0;
		else if (max == 1) 
			return 0;
		else 
			return max - 1;
	}
	
	private void dfs(int[][] grid, int i, int j, int level) {
		if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length)
			return;
		//if 0, or lower cost
		if(grid[i][j] == 0 || level <= grid[i][j]) {
			grid[i][j] = level;
			dfs(grid, i - 1, j, level + 1);
			dfs(grid, i + 1, j, level + 1);
			dfs(grid, i, j + 1, level + 1);
			dfs(grid, i, j - 1, level + 1);
		}
	}

	@Test
	public void Test1() {
		int[][] grid = new int[][] 
		{
			{0, 1, 1, 0, 1},
			{0, 1, 0, 1, 0},
			{0, 0, 0, 0, 1},
			{0, 1, 0, 0, 0}
		};
		assertEquals(2, solve(grid));
	}
	
	@Test
	public void Test2() {
		int[][] grid = new int[][]
		{
			{1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1},
            {0, 1, 1, 1, 1}
        };
        assertEquals(1, solve(grid));
	}
	
	@Test
	public void Test3() {
		int[][] grid = new int[][]
		{
			{1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1}
        };
        assertEquals(0, solve(grid));
	}
}
