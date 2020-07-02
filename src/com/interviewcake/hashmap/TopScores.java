package com.interviewcake.hashmap;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import java.util.*;

import static org.junit.Assert.*;

public class TopScores {

    public static int[] sortScores(int[] unorderedScores, int highestPossibleScore) {

        // sort the scores in O(n) time
        //0,1,2,3,4,5....100
        //37=1,41=1,53=2,65=1,89=1,91=1
        
        //37, 89, 41, 65, 91, 53
        //91, 89, 65, 53, 41, 37
        
        int[] count = new int[101];
        Map<Integer, LinkedList<Integer>> indexes = new HashMap<>();
        for(int i = 0; i < unorderedScores.length; i++) {
            count[unorderedScores[i]]++;
            indexes.putIfAbsent(unorderedScores[i], new LinkedList<>());
            indexes.get(unorderedScores[i]).add(i);
        }
        
        int index = 0;
        int[] ans = new int[unorderedScores.length];
        for(int i = 100; i > -1; i--) {
            while(count[i] > 0) {
            	ans[index++] = i;
//                int curr = unorderedScores[index];
//                if(curr == i) {
//                    index++;
//                    continue;
//                }
//                int to = indexes.get(i).poll();
//                int temp = unorderedScores[index];
//                unorderedScores[index++] = i;
//                unorderedScores[to] = temp;
//                indexes.get(temp).set(0, to);
                count[i]--;
            }
        }
        return ans;
    }


    // tests

    @Test
    public void noScoresTest() {
        final int[] scores = {};
        final int[] expected = {};
        final int[] actual = sortScores(scores, 100);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void oneScoreTest() {
        final int[] scores = {55};
        final int[] expected = {55};
        final int[] actual = sortScores(scores, 100);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void twoScoresTest() {
        final int[] scores = {30, 60};
        final int[] expected = {60, 30};
        final int[] actual = sortScores(scores, 100);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void manyScoresTest() {
        final int[] scores = {37, 89, 41, 65, 91, 53};
        final int[] expected = {91, 89, 65, 53, 41, 37};
        final int[] actual = sortScores(scores, 100);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void repeatedScoresTest() {
        final int[] scores = {20, 10, 30, 30, 10, 20};
        final int[] expected = {30, 30, 20, 20, 10, 10};
        final int[] actual = sortScores(scores, 100);
        assertArrayEquals(expected, actual);
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TopScores.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}