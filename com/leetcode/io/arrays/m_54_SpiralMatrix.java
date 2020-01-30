/**
 * 
 */
package io.arrays;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.*;

/**
 * @author owner-pc
 * Spiral Matrix II is similar
 */
public class m_54_SpiralMatrix {
    //can you construct 2d array given a spiral traversal list?
    public List<Integer> spiralOrder(int[][] matrix) {
        /*
        [ 1,   2,  3,  4,  5],
        [ 6,   7,  8,  9, 10],
        [ 11, 12, 13, 14, 15],
        [ 16, 17, 18, 19, 20],
        */
        
        /*
        [[],[]
        [0]
        [0,1,2,3]
        */
        
        List<Integer> traversal = new ArrayList<Integer>();
        
        if(matrix.length == 0)
            return traversal;
        if(matrix[0].length == 0)
            return traversal;
            
        int[][] directions = new int[4][];
        directions[0] = new int[] {0, 1};
        directions[1] = new int[] {1, 0};
        directions[2] = new int[] {0, -1};
        directions[3] = new int[] {-1, 0};
        
        boolean visited[][] = new boolean[matrix.length][matrix[0].length];
        spiralTraverse(0, 0, matrix, visited, traversal, directions, 0, 0);
        return traversal;
    }
    
    //idea:dfs,spiral
    //cons:limited stack space, wont work on large data
    //pros:easy to read, simple & works
    public void spiralTraverse(int i, int j, int[][] matrix, boolean[][] visited, List<Integer> traversal
                               , int[][] dir, int cur, int running) {  
    	if(i < 0 || i >= matrix.length)
    		return;
    	if(j < 0 || j >= matrix[i].length)
    		return;
    	if(visited[i][j])
    		return;
        visited[i][j] = true;
        traversal.add(matrix[i][j]);
        if(isValidMove(i + dir[cur][0], j + dir[cur][1], matrix, visited, traversal)) {
            //* If it's a valid move, continue traversing in the current direction
            spiralTraverse(i + dir[cur][0], j + dir[cur][1], matrix, visited, traversal, dir, cur, running + 1);
        } else {
            /* handle special case when we reach boundaries
                just toggle direction
            */         
            if(running < matrix.length * matrix[0].length) {
                cur = (cur + 1) % dir.length;
                //System.out.printf("dir=%d, ", cur);
                //if(isValidMove(i + dir[cur][0], j + dir[cur][1], matrix, visited, traversal)) {
                    spiralTraverse(i + dir[cur][0], j + dir[cur][1], matrix, visited, traversal, dir, cur, running + 1);
                //}
            }
        }
    }
    
    public boolean isValidMove(int i, int j, int[][] matrix, boolean[][] visited, List<Integer> traversal) {
        if(i < 0 || i >= matrix.length)
            return false;
        if(j < 0 || j >= matrix[i].length)
            return false;
        if(visited[i][j])
            return false;
        //System.out.printf("val=%d, ", matrix[i][j], i, j);
        return true;
    }
    
    @Test
    public void Test1() {
    	int[][] matrix = new int[][]
    			{
    				{1 , 2, 3, 4, 5},
    				{6 , 7, 8, 9,10},
    				{11,12,13,14,15},
    				{16,17,18,19,20},
    				{21,22,23,24,25}
    			};
    	int[] result = toIntArray(spiralOrder(matrix));
    	assertEquals(Arrays.toString(new int[] {1,2,3,4,5,10,15,20,25,24,23,22,21,16,11,6,7,8,9,14,19,18,17,12,13}),
    			Arrays.toString(result));
    }
    
    @Test
    public void Test2() {
    	int[][] matrix = new int[0][];
    	int[] result = toIntArray(spiralOrder(matrix));
    	assertEquals(Arrays.toString(new int[0]),
    			Arrays.toString(result));
    }  
    @Test
    public void Test3() {
    	int[][] matrix = new int[0][0];
    	int[] result = toIntArray(spiralOrder(matrix));
    	assertEquals(Arrays.toString(new int[0]),
    			Arrays.toString(result));
    }      
    
    int[] toIntArray(List<Integer> list) {
    	int[] result = new int[list.size()];
    	for(int i=0; i<result.length;i++) {
    		result[i] = list.get(i);
    	}
    	return result;
    }
}
