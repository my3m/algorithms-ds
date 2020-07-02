package io.primitives;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class m_202_HappyNumber {
	  public boolean isHappy(int n) {
        /**
            Intuiton: Use a set to add all visited numbers. If we re-visit this set, we formed a cycle
                #2, Use fast/slow. 
                    Intuiton: if we are in a cycle, eventually "fast" will meet "slow" in the cycle"
                                if no cycle, "fast" will reach to 1 first
                                "fast" means, twice is many operations than "slow" in same call
        */
		int slow = n;
		int fast = getHappyNumber(n);
		while (slow != 1 && slow != fast) {
			slow = getHappyNumber(slow);
			fast = getHappyNumber(getHappyNumber(fast));
		}
		return fast == 1;
	}

	boolean usingHashMap(int n) {
		int sum = 0;
		Set<Integer> visited = new HashSet<Integer>();
		while (n != 1) {
			n = getHappyNumber(n);
			if (!visited.add(n))
				return false;
			// System.out.println(n);
			// if(n == 4) /* Cycle */
			// return false;
		}
		return true;
	}

	int getHappyNumber(int n) {
		int sum = 0;
		while (n != 0) {
			int mod = n % 10;
			sum += (mod * mod);
			n = n / 10;
		}
		return sum;
	}
	
	@Test
	public void Test1() {
		List<Integer> happyNumbers = Arrays.asList(1, 7, 10, 13, 19, 23, 28, 31, 32, 44, 49, 68, 70, 79, 82, 86, 91, 94,
				97, 100);
		for(int i = 0; i <= 100; i++) 
			assertEquals(happyNumbers.contains(i), isHappy(i));
	}
}
