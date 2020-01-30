package io.arrays;

import static org.junit.Assert.assertArrayEquals;

import java.util.HashSet;

import org.junit.Test;

public class m_73_SetMatrixZeroes {
	
   //Time=O(m*n), space=O(m+n)
    //Use constant space by modifying the original array    
    public void setZeroes2(int[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0)
            return;
        //find all indexes with 0
        HashSet<Integer> rows = new HashSet<Integer>();
        HashSet<Integer> columns = new HashSet<Integer>();
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                if(matrix[i][j] == 0) {
                    rows.add(i);
                    columns.add(j);
                }
            }
        }
        
        //0,1
        //1,1
        //2,1
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                if(rows.contains(i) || columns.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }        
    }	
	
	//Time=O(m*n), Space=O(1)
    public void setZeroes1(int[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0)
            return;
        //find all indexes with 0
        boolean isFirstRowZero = false;
        boolean isFirstColumnZero = false;
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                if(matrix[i][j] == 0) {
                    if(i == 0) 
                        isFirstRowZero = true;
                    if(j == 0)
                        isFirstColumnZero = true;
                    if(i > 0 && j > 0) {
                        matrix[i][0] = 0;
                        matrix[0][j] = 0;
                    }
                }
            }
        }
        
        //0,1
        //1,1
        //2,1
        for(int i = 1; i < matrix.length; i++) {
            for(int j = 1; j < matrix[i].length; j++) {
                if(matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }    
        
        if(isFirstRowZero) {
            for(int i =0; i < matrix[0].length; i++) {
                matrix[0][i] = 0;
            }           
        }

        if(isFirstColumnZero) {
            for(int i =0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }     
    }
    
    @Test
    public void Test1() {
    	int[][] matrix = new int[][] 
    			{
    	    		{1, 1, 1, 2},
    	    		{2, 5, 0, 2},
    	    		{2, 4, 1, 5},
    	    		{1, 2, 2, 9}   		
    			}; 	
    	setZeroes1(matrix);
    	assertArrayEquals(new int[] { 1, 1, 0, 2}, matrix[0]);
    	assertArrayEquals(new int[] { 0, 0, 0, 0}, matrix[1]);
    	assertArrayEquals(new int[] { 2, 4, 0, 5}, matrix[2]);
    	assertArrayEquals(new int[] { 1, 2, 0, 9}, matrix[3]);
    }
    
    @Test
    public void Test2() {
    	int[][] matrix = new int[][] 
    			{
    	    		{0, 1, 1, 2},
    	    		{2, 5, 0, 2},
    	    		{2, 4, 1, 5},
    	    		{1, 2, 2, 9}   		
    			}; 	
    	setZeroes1(matrix);
    	assertArrayEquals(new int[] { 0, 0, 0, 0}, matrix[0]);
    	assertArrayEquals(new int[] { 0, 0, 0, 0}, matrix[1]);
    	assertArrayEquals(new int[] { 0, 4, 0, 5}, matrix[2]);
    	assertArrayEquals(new int[] { 0, 2, 0, 9}, matrix[3]);
    }  
    
    @Test
    public void Test3() {
    	int[][] matrix = new int[][] 
    			{
    	    		{0, 1, 1, 2},
    	    		{2, 5, 0, 2},
    	    		{2, 4, 0, 5},
    	    		{1, 2, 2, 9}   		
    			}; 	
    	setZeroes1(matrix);
    	assertArrayEquals(new int[] { 0, 0, 0, 0}, matrix[0]);
    	assertArrayEquals(new int[] { 0, 0, 0, 0}, matrix[1]);
    	assertArrayEquals(new int[] { 0, 0, 0, 0}, matrix[2]);
    	assertArrayEquals(new int[] { 0, 2, 0, 9}, matrix[3]);
    }    
}
