package com.interviewcake.greedy;

import java.util.Arrays;
import java.util.Random;

public class UniformRandomArrayShuffle {

    private static Random rand = new Random();

    private static int getRandom(int lower, int upper) {
    	return lower + rand.nextInt(upper - lower + 1);
    }

    public static void shuffle(int[] array) {

        // shuffle the input in place
        //[1,2,3,4,5,6,7,8,9,10]
        //for i=0, we have 10 choices, 1,2,3,4,5,6,7,8,9,10
    	//for i=1, we have 9 choices, 2,3,4,5,6,7,8,9,10
    	//for i=2, we have 8 choices, 3,4,5,6,7,8,9,10
    	
    	//if we had 10 choices for each i = 0...10, then its not uniform distribution
    	
        for(int i = 0; i < array.length; i++) {
            int rand = getRandom(i, array.length - 1);
            if(rand == i)
                continue;
            int temp = array[rand];
            array[rand] = array[i];
            array[i] = temp;
        }
    }

    public static void main(String[] args) {
        //final int[] initial = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    	final int[] initial = {1,2,3,4,5,5,5,5,5,6,7,8,9};
        final int[] shuffled = Arrays.copyOf(initial, initial.length);
        shuffle(shuffled);
        System.out.printf("initial array: %s\n", Arrays.toString(initial));
        System.out.printf("shuffled array: %s\n", Arrays.toString(shuffled));
    }
}