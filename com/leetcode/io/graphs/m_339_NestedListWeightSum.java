package io.graphs;

import static org.junit.Assert.assertEquals;


import org.junit.Test;

public class m_339_NestedListWeightSum {
	public int depthSum(Object[] values) {
		return depthSumHelper(values, 1);
	}
	
	int depthSumHelper(Object[] values, int depth) {
		int levelsum = 0;
		for(Object o : values) {
			if(o instanceof Integer) {
				levelsum += (Integer)o;
			}
			else if(o instanceof Object[]) {
				levelsum += depthSumHelper((Object[])o, depth + 1);
			}
		}
		return depth * (levelsum);
	}

	//[1, [2, [1, 1], 1], [3]]
	@Test
	public void Test1() {
		//[1, [2, [1, 1], 1], [3]]
		Object[] values = new Object[] { 1, new Object[] { 2, new Object[] { 1, 1 }, 1 }, new Object[] { 3 } };
		assertEquals(25, depthSum(values));
	}
}
