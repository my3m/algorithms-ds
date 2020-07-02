package io.dp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class m_256_PaintHouse {
	
    public int minCost(int[][] costs) {
        if(costs.length == 0)
            return 0;
        
        for(int i = 1; i < costs.length; i++) {
            costs[i][0] = Math.min(costs[i - 1][1], costs[i - 1][2]) + costs[i][0];
            costs[i][1] = Math.min(costs[i - 1][0], costs[i - 1][2]) + costs[i][1];
            costs[i][2] = Math.min(costs[i - 1][0], costs[i - 1][1]) + costs[i][2];
        }
        
        return Math.min(costs[costs.length - 1][0],
                        Math.min(costs[costs.length - 1][1], costs[costs.length - 1][2]));
        //return dfs(costs, 0, -1, 0);
    }	
	
    int dfs(int[][] costs, int i, int j, int cost) {
        int length = costs.length;
        if(i == length)
            return cost;
        
        int min = Integer.MAX_VALUE;
        for(int k = 0; k < 3; k++) {
            if(j != k) {
                int tmp = dfs(costs, i + 1, k, cost + costs[i][k]);
                if(tmp < min)
                    min = tmp;
            }
        }
        return min;
    }
    
    @Test
    public void Test1() {
    	int[][] costs = new int[][]
		{
			{17,2,17},
			{16,16,5},
			{14,3,19}
		};
		assertEquals(10, minCost(costs));
    }
}
