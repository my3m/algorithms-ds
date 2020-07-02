package io.arrays;

import java.util.HashSet;
import java.util.Set;

public class m_849_MagicSquaresInGrid {
	
    public int numMagicSquaresInside(int[][] grid) {
        int count = 0;
        for(int i = 0; i < grid.length - 2; i++) {
            //2,2,5,4,5,3
            //2,6,7,7,9,1
            //7,7,2,4,6,1
            //3,6,7,3,8,2
            //7,1,8,5,4,7
            //4,4,5,6,1,9
            for(int j = 0; j < grid[0].length - 2; j++) {
                if(isMagicSquare(grid[i][j], grid[i][j+1], grid[i][j+2],
                            grid[i+1][j], grid[i+1][j+1], grid[i+1][j+2],
                             grid[i+2][j], grid[i+2][j+1], grid[i+2][j+2])) {
                    //System.out.printf("i=%d, j=%d\r\n", i, j);
                    count++;
                }
            }
        }
        return count;
    }
    
    public boolean isMagicSquare(int... values) {
        int[] count = new int[16];
        for(int v : values) {
            count[v]++;
        }
        for(int v = 1; v <=9; v++) {
            if(count[v] != 1)
                return false;
        }
        //0,1,2
        //3,4,5
        //6,7,8
        int[][] rowset = new int[][]
        {
            {0,1,2},
            {3,4,5},
            {6,7,8}
        };
        int[][] clmset = new int[][]
        {
            {0,3,6},
            {1,4,7},
            {2,5,8}
        };       
        int[][] dgset = new int[][] 
        {
            {0,4,8},
            {2,4,6}
        };
        return checkSums(rowset, values) && 
                checkSums(clmset, values) &&
                checkSums(dgset, values);
    }
    
    boolean checkSums(int[][] offset, int[] values) {
        int prev = Integer.MIN_VALUE;
        for(int[] r : offset) {
            int tmp = 0;
            for(int v : r) {
                tmp += values[v];
            }
            if(prev == Integer.MIN_VALUE)
                prev = tmp;
            else {
                if((prev ^ tmp) != 0)
                    return false;
                prev = tmp;
            }  
        }
        return true;
    }	
	
    public int numMagicSquaresInside2(int[][] grid) {
        //how to scan for magic squares coordinates ??
        //how to know if its a magic square given candidate??strategy
        //how to know if they are distinct numbers??
        //You didn't read from 1 to 9 !!
        
        //
        
        //We can compute prefix sums of each row, each column
        //to check if sums are equal
        
        //2,7,6
        //1,5,9
        //4,3,8
        
        //10,3,5
        //1,6,11
        //7,9,2
        
        //7,0,5
        //2,4,6
        //3,8,1
        
        int cnt = 0;
        for(int i = 0; i <= grid.length - 3; i++) {
            for(int j = 0; j <= grid[0].length - 3; j++) {
                if(isMagicSquarePt(grid, i, j))
                    cnt++;
            }
        }
        return cnt;
    }
    
    //Can you consistently code out what you have in your head
    //concise + fast
    boolean isMagicSquarePt(int[][] grid, int i, int j) {
        int srow = Integer.MIN_VALUE;
        boolean isMS = true;
        Set<Integer> values = new HashSet<Integer>();
        //Check for same sum accross all rows
        //Check for uniqueness
        for(int x = i; x <= i + 2; x++) {
            int tmp = 0;
            for(int y = j; y <= j + 2; y++) {
                tmp += grid[x][y];
                if(!values.add(grid[x][y])) {
                    return false;
                }
                if(grid[x][y] < 1 || grid[x][y] > 9)
                    return false;
            }
            if(srow == Integer.MIN_VALUE)
                srow = tmp;
            else {
                if((srow ^ tmp) != 0) {
                    isMS = false;
                    break;
                }
            }
        }
        
        //for(int y = j; y <= grid[0].length - 3; y++) {
        //should it be y <= j + 2 ??
        int sclm = Integer.MIN_VALUE;
        for(int y = j; y <= j + 2; y++) {
            int tmp = 0;
            for(int x = i; x <= i + 2; x++) {
                tmp += grid[x][y];
            }
            if(sclm == Integer.MIN_VALUE)
                sclm = tmp;
            else {
                if((sclm ^ tmp) != 0) {
                    isMS = false;
                    break;
                }
            }
        }
        
        //Check diagonal sums are equal
        int dg1 = grid[i][j] + grid[i+1][j+1]+grid[i+2][j+2];
        int dg2 = grid[i][j+2] + grid[i+1][j+1]+grid[i+2][j];
        
        return ((dg1 ^ dg2) == 0) && isMS;
    }	
	
    public int numMagicSquaresInside3(int[][] grid) {
        //how to scan for magic squares coordinates ??
        //how to know if its a magic square given candidate??strategy
        //how to know if they are distinct numbers??
        
        //
        
        //We can compute prefix sums of each row, each column
        //to check if sums are equal
        
        int[][] prefixRows = new int[grid.length][grid[0].length];
        int[][] prefixColumns = new int[grid.length][grid[0].length];
        
        for(int i = 0; i < grid.length; i++) {
            int rs = 0;
            for(int j = 0; j < grid[0].length; j++) {
                rs += grid[i][j];
                prefixRows[i][j] = rs;
            }
            //System.out.println(Arrays.toString(prefixRows[i]));
        }
        
        for(int i = 0; i < grid[0].length; i++) {
            int rs = 0;
            for(int j = 0; j < grid.length; j++) {
                rs += grid[j][i];
                prefixColumns[j][i] = rs;
            }
        }        
        int ms = 0;
        
        //2,7,6
        //1,5,9
        //4,3,8
        
        //5,5,5
        //5,5,5
        //5,5,5
        
        //2,2,5,4,5,3
        //2,6,7,7,9,1
        //7,7,2,4,6,1
        //3,6,7,3,8,2
        //7,1,8,5,4,7
        //4,4,5,6,1,9        
        
        //1,2,3,4,5,6
        for(int i = 0; i <= grid.length-3; i++) {
            for(int j = 0; j <= grid[0].length - 3; j++) {
                //for point [i][j] check for magic square
                //[i][j] to [i][j+2]
                //[i+1][j] to [i+1][j+2]
                //[i+2][j] to [i+2][j+2]
                
                int rs1 = prefixRows[i][j+2] - (j == 0 ? 0 : prefixRows[i][j-1]);
                int rs2 = prefixRows[i+1][j+2] - (j == 0 ? 0 : prefixRows[i+1][j-1]);
                int rs3 = prefixRows[i+2][j+2] - (j == 0 ? 0 : prefixRows[i+2][j-1]);
                
                int cs1 = prefixColumns[i+2][j] - (i == 0 ? 0 : prefixColumns[i-1][j]);
                int cs2 = prefixColumns[i+2][j+1] - (i == 0 ? 0 : prefixColumns[i-1][j+1]);
                int cs3 = prefixColumns[i+2][j+2] - (i == 0 ? 0 : prefixColumns[i-1][j+2]);
                
                int dg1 = grid[i][j] + grid[i+1][j+1] + grid[i+2][j+2];
                int dg2 = grid[i][j+2] + grid[i+1][j+1] + grid[i+2][j];
                
                // int ts = (rs1 + rs2 + rs3 + cs1 + cs2 + cs3 + dg1 + dg2);
                // System.out.printf("i=%d, j=%d\r\n", i, j);
                // System.out.printf("rs1=%d\r\n", rs1);
                // System.out.printf("rs2=%d\r\n", rs2);
                // System.out.printf("rs3=%d\r\n", rs3);
                // System.out.printf("cs1=%d\r\n", cs1);
                // System.out.printf("cs3=%d\r\n", cs2);
                // System.out.printf("cs3=%d\r\n", cs3);
                
                
                    
                //wrong assumption to think all rows/columns are equal 
                //if their total sum adds to the 8*rs1
                //you also didn't read the question, it said distinct numbers!
                if((rs1 ^ rs2 ^ rs3 ^ cs1 ^ cs2 ^ cs3 ^ dg1 ^ dg2) == 0) {
                    ms++;
                }
            }
        }
        return ms;
    }
}
