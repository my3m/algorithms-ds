package com.interviewcake.hashmap;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import java.util.*;

import static org.junit.Assert.*;

public class PalindromePermutation {

    public static boolean hasPalindromePermutation(String theString) {

        // check if any permutation of the input is a palindrome
        //racecar
        //civic
        Map<Character, Integer> frq = new HashMap<>();
        for(char chr : theString.toCharArray()) {
            frq.put(chr, frq.getOrDefault(chr, 0) + 1);
        }
        //abab
        boolean isOdd = false;
        for(char key : frq.keySet()) {
            if(frq.get(key) % 2 != 0) {
                if(isOdd)
                    return false;
                isOdd = true;
            }
        }
        return true;
    }

    
    // tests

    @Test
    public void permutationWithOddNumberOfCharsTest() {
        final boolean result = hasPalindromePermutation("aabcbcd");
        assertTrue(result);
    }

    @Test
    public void permutationWithEvenNumberOfCharsTest() {
        final boolean result = hasPalindromePermutation("aabccbdd");
        assertTrue(result);
    }

    @Test
    public void noPermutationWithOddNumberOfChars() {
        final boolean result = hasPalindromePermutation("aabcd");
        assertFalse(result);
    }

    @Test
    public void noPermutationWithEvenNumberOfCharsTest() {
        final boolean result = hasPalindromePermutation("aabbcd");
        assertFalse(result);
    }

    @Test
    public void emptyStringTest() {
        final boolean result = hasPalindromePermutation("");
        assertTrue(result);
    }

    @Test
    public void oneCharacterStringTest() {
        final boolean result = hasPalindromePermutation("a");
        assertTrue(result);
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(PalindromePermutation.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}