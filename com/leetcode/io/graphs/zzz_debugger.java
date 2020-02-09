package io.graphs;

import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.Test;

public class zzz_debugger {
	
	// case 1. all zero's
	// case 2. interweaving minutes
	// case 3. cannot reach fresh orange

	// [2,1,1]
	// [1,1,0]
	// [0,1,1]

	// [2,3,4]
	// [3,3,2]
	// [0,4,3]
	
	public int orangesRotting(int[][] grid) {

		Queue<Integer> queue = new LinkedList<Integer>();
		Map<Integer, Integer> depth = new HashMap<Integer, Integer>();

		int rows = grid.length;
		int columns = grid[0].length;

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == 2)
					queue.offer(i * columns + j);
				int index = i * columns + j;
				depth.putIfAbsent(index, grid[i][j]);
			}
		}

		// [0,1,2,3,4] => 0 * 5 + 4 => 4
		// [5,6,7,8,9] => 1 * 5 + 4 => 9

		int[][] offset = new int[][] { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
		int max = Integer.MIN_VALUE;

		while (queue.size() > 0) {
			int size = queue.size();
			
			for (int k = 0; k < size; k++) {
				int index = queue.poll();
				int i = index / columns;
				int j = index % columns;

				for (int[] off : offset) {
					int ni = i + off[0];
					int nj = j + off[1];

					int nx = ni * rows + nj;

					if (ni < 0 || ni >= rows || nj < 0 || nj >= columns)
						continue;

					if (grid[ni][nj] == 0)
						continue;

					// [2,1,1],
					// [0,1,1]
					// [1,0,1]
					
					if (grid[ni][nj] == 1 || depth.get(index) + 1 < depth.get(nx)) {

						/* mark as rotten */
						grid[ni][nj] = 2;

						depth.put(nx, depth.get(index) + 1);

						max = Math.max(max, depth.get(nx));

						queue.offer(nx);
					}
				}
			}
		}
		
		max = Integer.MIN_VALUE;
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[0].length; j++) {
				if(grid[i][j] == 1)
					return - 1;
				//max = Math.max(max, depth.get(i * columns + j));
			}
		}
		if(max == 0)
			return 0;
		else if(max == 1)
			return 0;
		else return max - 2;
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
