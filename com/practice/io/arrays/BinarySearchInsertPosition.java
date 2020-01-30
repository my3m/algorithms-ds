package io.arrays;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BinarySearchInsertPosition {
    int binarySearch(int[] array, int left, int right, int target) {
        if(left > right)
            return -1;
        if(array[0] > target)
            return 0;
        if(array[array.length -1] < target) //1, 4, 6, 9
        	return array.length;
        int mid = left + ((right-left)/2);
        int insertPosition = -1;
        while(left <= right) {
            mid = left + ((right-left)/2);
            if(target > array[mid]) {
                left = mid + 1;
                //keep moving to the right
                insertPosition = mid + 1;
            }
            else if(target < array[mid]) {
                right = mid - 1;
            }
            else {
                return mid;
            }
        }
        return insertPosition;
    }
    @Test
    public void Test1() {
    	int[] array = new int[] { 3, 4, 9, 12, 16 };
    	assertEquals(0, binarySearch(array, 0, array.length-1, 2));
    }
    @Test
    public void Test2() {
    	int[] array = new int[] { 3, 4, 9, 12, 16 };
    	assertEquals(2, binarySearch(array, 0, array.length-1, 9));
    }
    @Test
    public void Test3() {
    	int[] array = new int[] { 1, 3, 6, 9, 11, 15, 17, 20 ,22, 26, 29, 33, 39, 42, 50 };
    	assertEquals(8, binarySearch(array, 0, array.length-1, 21));
    }
    @Test
    public void Test4() {
    	int[] array = new int[] { 1, 3, 6, 9, 11, 15, 17, 20 ,22, 26, 29, 33, 39, 42, 50 };
    	assertEquals(15, binarySearch(array, 0, array.length-1, 56));
    }
    @Test
    public void Test5() {
    	int[] array = new int[] { 1, 3, 6, 9, 11, 15, 17, 20 ,22, 26, 29, 33, 39, 42, 50 };
    	assertEquals(13, binarySearch(array, 0, array.length-1, 41));
    }
}
