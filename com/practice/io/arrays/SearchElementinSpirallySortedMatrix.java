package io.arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SearchElementinSpirallySortedMatrix {
	public int[] search(int[][] matrix, int value) {
		//Naive
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[i].length; j++) {
				if(matrix[i][j] == value)
					return new int[] { i, j };
			}
		}
		
		//focus on finding the correct outer layer
		
		return new int[] { -1, -1 };
	}
	
	@Test
	public void Test1() {
		int[][] matrix = new int[][] {
			{1, 2, 3, 4},
			{12, 13, 14, 5},
			{11, 16, 15, 6},
			{10, 9, 8, 7}
		};
		assertArrayEquals(new int[] { 2, 3 }, search(matrix, 6));
	}
}
