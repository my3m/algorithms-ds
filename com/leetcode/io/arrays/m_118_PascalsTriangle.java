package io.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class m_118_PascalsTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> pascals = new ArrayList<>();
         if(numRows == 0)
             return pascals;
         pascals.add(Arrays.asList(1));
         if(numRows == 1)
             return pascals;
         pascals.add(Arrays.asList(1,1));
         if(numRows == 2)
             return pascals;        
         for(int i = 3; i <= numRows; i++) {
             List<Integer> prev = pascals.get(pascals.size() - 1);
             List<Integer> curr = new ArrayList<>();
             curr.add(1);
             for(int j = 1; j < i - 1; j++) {
                 curr.add(prev.get(j) + prev.get(j - 1));
             }
             curr.add(1);
             pascals.add(curr);
         }
         return pascals;
     }
}
