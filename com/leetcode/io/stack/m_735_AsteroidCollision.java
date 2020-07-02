package io.stack;

import static org.junit.Assert.assertArrayEquals;

import java.util.LinkedList;

import org.junit.Test;

public class m_735_AsteroidCollision {
    public int[] asteroidCollision(int[] asteroids) {
        LinkedList<Integer> s1 = new LinkedList<Integer>();
        for(int i = 0; i < asteroids.length; i++) {
            if(asteroids[i] < 0) {
                //20,4,-20
                while(s1.size() > 0 && asteroids[s1.getLast()] > 0 &&
                      Math.abs(asteroids[i]) > asteroids[s1.getLast()]) {
                    s1.removeLast();
                    //-12,12,5,2
                    //10,-2
                    //10,-20
                }
                //10,-10
                if(s1.size() > 0) {
                    if(asteroids[s1.getLast()] > 0 
                      && asteroids[s1.getLast()] + asteroids[i] == 0) {
                        s1.removeLast();
                        continue;
                    }
                    //10,-2
                    else if(asteroids[s1.getLast()] > 0 &&
                            Math.abs(asteroids[s1.getLast()]) > Math.abs(asteroids[i])) {
                        continue;
                    }
                }
            }
            s1.add(i);   
        }
        int[] answer = new int[s1.size()];
        for(int i = 0; i < s1.size(); i++) {
            answer[i] = asteroids[s1.get(i)];
        }
        return answer;
    }
    @Test
    public void Test1() {
    	int[] input = new int[] { 3,6,3,2,5,-12,12,5,2 };
    	int[] exp = new int[] { -12,12,5,2 };
    	assertArrayEquals(exp, asteroidCollision(input));
    }
    
    @Test
    public void Test2() {
    	int[] input = new int[] { 5, 10, -5 };
    	int[] exp = new int[] { 5, 10};
    	assertArrayEquals(exp, asteroidCollision(input));
    }
    
    @Test
    public void Test3() {
    	int[] input = new int[] { 10, 2, -5 };
    	int[] exp = new int[] { 10 };
    	assertArrayEquals(exp, asteroidCollision(input));
    }
    
    @Test
    public void Test4() {
    	int[] input = new int[] {-2,1,-2,-1};
    	int[] exp = new int[] { -2,-2,-1 };
    	assertArrayEquals(exp, asteroidCollision(input));
    }
    
    @Test
    public void Test5() {
    	int[] input = new int[] { -2,-1,1,2 };
    	int[] exp = new int[] { -2,-1,1,2 };
    	assertArrayEquals(exp, asteroidCollision(input));
    }
    
    @Test
    public void Test6() {
    	int[] input = new int[] { -2,-2,1,-2 };
    	int[] exp = new int[] { -2,-2,-2 };
    	assertArrayEquals(exp, asteroidCollision(input));
    }
}
