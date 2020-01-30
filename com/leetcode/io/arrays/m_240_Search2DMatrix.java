package io.arrays;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class m_240_Search2DMatrix {
	public int binarySearchColumn(int[][] matrix, int clmIdx, int rowStartIdx, int rowEndIdx, int target) {
		if(rowEndIdx < rowStartIdx)
			return -1;
		int mid = (rowStartIdx) + ((rowEndIdx-rowStartIdx)/2);
		while(rowStartIdx <= rowEndIdx) {
			mid = (rowStartIdx) + ((rowEndIdx-rowStartIdx)/2);
			if(target < matrix[mid][clmIdx]) {
				rowEndIdx = mid - 1;
			} else if(target > matrix[mid][clmIdx]) {
				rowStartIdx = mid + 1;
			} else {
				break;
			}
		}
		//System.out.println(mid);
		return mid;
	}
	public int binarySearchRow(int[][] matrix, int rowIdx, int colStartIdx, int colEndIdx, int target) {
		if(colStartIdx > colEndIdx)
			return -1;
		int mid = colStartIdx + ((colEndIdx - colStartIdx)/2);
		while(colStartIdx <= colEndIdx) {
			mid = colStartIdx + ((colEndIdx - colStartIdx)/2);
			if(target < matrix[rowIdx][mid]) {
				colEndIdx = mid - 1;
			} else if(target > matrix[rowIdx][mid]) {
				colStartIdx = mid + 1;
			} else {
				break;
			}
		}
		return mid;
	}
	
	public boolean searchMatrixBinarySearch(int[][] matrix, int target) {
		if(matrix.length == 0 || matrix[0].length == 0)
			return false;
		int rows = matrix.length;
		int columns = matrix[0].length;
		
		/*Start bottom-left @ 18*/
		int i = rows - 1;
		int j = 0;
		
		/*
 *     		{1,   4,  7, 11, 15},
    		{2,   5,  8, 12, 19},
    		{3,   6,  9, 16, 22},
    		{10, 13, 14, 17, 24},
    		{18, 21, 23, 26, 30}
		 */
		int steps = -1;
		boolean found = false;
		while((i >= 0 && i < rows) && (j >= 0 && j < columns)) {
			steps++;
			if(target < matrix[i][j]) {
				//search i - 1 to 0
				i = binarySearchColumn(matrix, j, 0, --i, target);
			} else if(target > matrix[i][j]) {
				//search j + 1 to total columns
				j = binarySearchRow(matrix, i, ++j, columns - 1, target);
			}
			else {
				System.out.printf("found @ i=%d,j=%d\r\n", i, j);
				found = true;
				break;
			}
		}
		System.out.println(steps);
		return found;
	}
	
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length == 0 || matrix[0].length == 0)
            return false;
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        int i = 0;
        int j = n - 1;
        
        while((i >= 0 && i < m) && (j >= 0 && j < n)) {
            //System.out.printf("i=%d,j=%s\r\n", i, j);
            if(target < matrix[i][j]) {
                j--;
            }
            else if(target > matrix[i][j]) {
                i++;
            }
            else {
                return true;
            }
        }
        return false;
    }
    
    @Test
    public void BinarySearchColumnTest() {
    	int[][] matrix = new int[][]
		{
    		{1,   4,  7, 11, 15},
    		{2,   5,  8, 12, 19},
    		{3,   6,  9, 16, 22},
    		{10, 13, 14, 17, 24},
    		{18, 21, 23, 26, 30}
		};
		assertEquals(3, binarySearchColumn(matrix, 0, 0, matrix.length - 1, 9));
		//assertEquals(4, binarySearchColumn(matrix, 2, 0, matrix.length - 1, 23));
    }
    @Test
    public void BinarySearchRowTest() {
    	int[][] matrix = new int[][]
		{
    		{1,   4,  7, 11, 15},
    		{2,   5,  8, 12, 19},
    		{3,   6,  9, 16, 22},
    		{10, 13, 14, 17, 24},
    		{18, 21, 23, 26, 30}
		};
		assertEquals(3, binarySearchRow(matrix, 2, 0, matrix[2].length - 1, 16));
		assertEquals(0, binarySearchRow(matrix, 0, 0, matrix[0].length - 1, 1));
		assertEquals(4, binarySearchRow(matrix, 4, 0, matrix[4].length - 1, 30));
    }        
    
    @Test
    public void Test1() {
    	int[][] matrix = new int[][]
		{
    		{1,   4,  7, 11, 15},
    		{2,   5,  8, 12, 19},
    		{3,   6,  9, 16, 22},
    		{10, 13, 14, 17, 24},
    		{18, 21, 23, 26, 30}
		};
		assertEquals(true, searchMatrixBinarySearch(matrix, 9));
    }
    
    @Test
    public void Test2() {
    	int[][] matrix = new int[][]
		{
    		{1,   4,  7, 11, 15},
    		{2,   5,  8, 12, 19},
    		{3,   6,  9, 16, 22},
    		{10, 13, 14, 17, 24},
    		{18, 21, 23, 26, 30}
		};
		assertEquals(true, searchMatrixBinarySearch(matrix, 30));
    }	    
    @Test
    public void Test3() {
    	int[][] matrix = new int[][]
		{
    		{1,   4,  7, 11, 15},
    		{2,   5,  8, 12, 19},
    		{3,   6,  9, 16, 22},
    		{10, 13, 14, 17, 24},
    		{18, 21, 23, 26, 30}
		};
		assertEquals(false, searchMatrixBinarySearch(matrix, 100));
    }
    @Test
    public void Test4() {
    	int[][] matrix = new int[][]
		{
    		{1,   4,  7, 11, 15},
    		{2,   5,  8, 12, 19},
    		{3,   6,  9, 16, 22},
    		{10, 13, 14, 17, 24},
    		{18, 21, 23, 26, 30}
		};
		assertEquals(false, searchMatrixBinarySearch(matrix, 20));
    }
    @Test
    public void Test5() {
    	int[][] matrix = new int[][]
		{
    		{1,   4,  7, 11, 15},
    		{2,   5,  8, 12, 19},
    		{3,   6,  9, 16, 22},
    		{10, 13, 14, 17, 24},
    		{18, 21, 23, 26, 30}
		};
		assertEquals(false, searchMatrixBinarySearch(matrix, 102));
    } 
    
    @Test
    public void Test6() {
    	int[][] matrix = new int[][]
		{
    		{-5}
		};
		assertEquals(false, searchMatrixBinarySearch(matrix, -10));
    }    
}
