package io.graphs;

import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.Test;

/*
    /*
            75
            / \   
           []  99
           / \
          /   3
          4    \3
         /
         3
        /
        2
        
        Ideas: BFS(level)
        Case 1. Left subtree is smaller than right subtree, returns depths 1, 2 (wrong)
                skewed trees to left/right
        
               []
         /      |      \
        []      1        []         List=[1], Size=0, depth > count
       /                /   \
      2                4     []     List[[1],[2,4]], Size=1, Depth=2, Count=1, List.Add(), Count=2, list.get(depth)
                           /
                          6
    
    [1],[6],[6]
    */
public class m_364_NestedListWeigtSum2 {
	public int depthSumInverse(Object[] nestedList) {
		//return depthSumInverseUsingList(nestedList);
		List<Object> values = new ArrayList<>();
		for(int i = 0; i < nestedList.length; i++) {
			values.add(nestedList[i]);
		}
		return depthSumInverse2(values);
	}
	
	public int depthSumInverse2(List<Object> nestedList) {
	    int unweighted = 0, weighted = 0;
	    while (nestedList.size() > 0) {
	        List<Object> nextLevel = new ArrayList<>();
	        for (Object ni : nestedList) {
	            if (ni instanceof Integer)
	                unweighted += (Integer)ni;
	            else
	                nextLevel.addAll((List<Object>)ni);
	        }
	        weighted += unweighted;
	        nestedList = nextLevel;
	    }
	    return weighted;
	}	

	int depthSumInverseUsingList(Object[] nestedList) {
		List<Integer> levels = new ArrayList<>();
		depthSumInverseUsingList(nestedList, levels, 1);
		// [1],[6],[6]
		int counter = 1;
		int sum = 0;
		for (int i = levels.size() - 1; i >= 0; i--) {
			sum += (counter++) * levels.get(i);
		}
		return sum;
	}
	
	void depthSumInverseUsingList(Object[] values, List<Integer> levels, int depth) {
		if (depth > levels.size())
			levels.add(0);
		for (Object i : values) {
			if (i instanceof Integer) {
				levels.set(depth - 1, levels.get(depth - 1) + (Integer)i);
			} else {
				depthSumInverseUsingList((Object[])i, levels, depth + 1);
			}
		}
	}

	int depthSumInverseUsingHashMap(Object[] nestedList) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		int d = depthSumInverseUsingHashMap(nestedList, map, 1);
		// {3:6, 2:4, 1:1}
		// System.out.println(d);
		int sum = 0;
		for (Integer key : map.keySet()) {
			// System.out.printf("key=%d, sum=%d\r\n", key, map.get(key));
			// System.out.println(((d+1)-key)*map.get(key));
			sum += ((d + 1) - key) * map.get(key);
		}
		return sum;
	}

	int depthSumInverseUsingHashMap(Object[] values, Map<Integer, Integer> map, int depth) {
		int levelsum = 0;
		int maxd = depth;
		boolean containsList = false;
		// char[] spaces = new char[depth*3];
		for (Object i : values) {
			if (i instanceof Integer) {
				// System.out.printf("%s ->%d (%d)\r\n", new String(spaces), i.getInteger(),
				// depth);
				levelsum += (Integer) i;
			} else {
				// for a root, with multiple children. the sutree with max depth will get
				// overwritten
				// by one with lesser depth, undless you math.max(maxd, func())
				containsList = true;
				maxd = Math.max(maxd, depthSumInverseUsingHashMap((Object[]) i, map, depth + 1));
				// System.out.println(maxd);
			}
		}
		map.putIfAbsent(depth, 0);
		map.put(depth, map.get(depth) + levelsum);
		return Math.max(maxd, depth);
	}
	
	@Test
	public void Test1() {
		Object[] values = new Object[]
				{
						new Object[] { new Object[] { new Object[] { 55 } } },
						new Object[] { new Object[] { 31 } },
						new Object[] { 99 },
						new Object[] { },
						75
				};
		//[[[[55]]],[[31]],[99],[],75]
		assertEquals(714, depthSumInverse(values));
	}
	
}
