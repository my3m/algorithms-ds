package com.interviewcake.greedy;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

public class ProductOfOtherNumbers {

    public static int[] getProductsOfAllIntsExceptAtIndex(int[] intArray) {

        // make an array of the products
        //       [1, 7, 3, 4]
        //left //[1, 1, 7, 21]
        //right//[84,12 4  1,]
        
        //       [84,12,28,21]
        
        return UsingConstantMemory(intArray);
    }
    
    static int[] UsingMemory(int[] intArray) {    
        int len = intArray.length;
        if(len == 1)
            throw new IllegalArgumentException("one number");
        int[] left = new int[len];
        left[0] = 1;
        for(int i = 1; i < len; i++) {
            left[i] = left[i-1]*intArray[i-1];
        }
        int[] right = new int[len];
        right[len-1] = 1;
        for(int i = len -2; i > -1; i--) {
            right[i] = right[i+1]*intArray[i+1];
        }
        int[] result = new int[len];
        for(int i = 0; i < len; i++) {
            result[i] = left[i] * right[i];
        }
        return result;
    }    

    static int[] UsingConstantMemory(int[] intArray) {
        int len = intArray.length;
        if(len == 1)
            throw new IllegalArgumentException("one number");
        int[] left = new int[len];
        left[0] = 1;
        for(int i = 1; i < len; i++) {
            left[i] = left[i-1]*intArray[i-1];
        }
        int right = 1;
        for(int i = len -2; i > -1; i--) {
            right = right * intArray[i+1];
            left[i] = left[i] * right;
        }
        return left;
    }

    // tests

    @Test
    public void smallArrayTest() {
        final int[] actual = getProductsOfAllIntsExceptAtIndex(new int[] {1, 2, 3});
        final int[] expected = new int[] {6, 3, 2};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void longArrayTest() {
        final int[] actual = getProductsOfAllIntsExceptAtIndex(new int[] {8, 2, 4, 3, 1, 5});
        final int[] expected = new int[] {120, 480, 240, 320, 960, 192};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void arrayHasOneZeroTest() {
        final int[] actual = getProductsOfAllIntsExceptAtIndex(new int[] {6, 2, 0, 3});
        final int[] expected = new int[] {0, 0, 36, 0};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void arrayHasTwoZerosTest() {
        final int[] actual = getProductsOfAllIntsExceptAtIndex(new int[] {4, 0, 9, 1, 0});
        final int[] expected = new int[] {0, 0, 0, 0, 0};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void oneNegativeNumberTest() {
        final int[] actual = getProductsOfAllIntsExceptAtIndex(new int[] {-3, 8, 4});
        final int[] expected = new int[] {32, -12, -24};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void allNegativeNumbersTest() {
        final int[] actual = getProductsOfAllIntsExceptAtIndex(new int[] {-7, -1, -4, -2});
        final int[] expected = new int[] {-8, -56, -14, -28};
        assertArrayEquals(expected, actual);
    }

    @Test(expected = Exception.class)
    public void exceptionWithEmptyArrayTest() {
        getProductsOfAllIntsExceptAtIndex(new int[] {});
    }

    @Test(expected = Exception.class)
    public void exceptionWithOneNumberTest() {
        getProductsOfAllIntsExceptAtIndex(new int[] {1});
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(ProductOfOtherNumbers.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}