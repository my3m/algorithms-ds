package io.arrays.traversal;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class m_845_LongestMountainInArray {
    public int longestMountain(int[] A) {
        if(A.length < 3)
            return 0;        
        int max = 0;
        for(int i = 1; i < A.length - 1; i++) {
            //1. find a peak A[i-1] < A[i] < A[i + 1]
            //2. expand l to left, r to right
            //3. check for maximum
            if(A[i-1] < A[i] && A[i+1] < A[i]) {
                int l = i, r = i;
                while(l > 0 && A[l-1] < A[l]) {
                    l--;
                }
                while(r + 1 < A.length && A[r + 1] < A[r]) {
                    r++;
                }
                int length = (i-l) + 1 + (r-i);
                max = Math.max(max, length);
            }
        }
        return max;   
    }
    
    /**
		First naive solution I thought off. expand left/right on each A[i]
		We can selectively perform this on peaks, & reduce complexity to O(N) above
     */
    public int longestMountain2(int[] A) {
        //B[0] < B[i-1] < B[i] > B[i+1] > B[i+2]
        
        //[0,1,2,3,4,5,6]
        
        int max = 0;
        for(int i = 1; i < A.length -1; i++) {
            int k = 1;
            int left = i;
            while(left > 0 && A[left-1] < A[left]) {
                left--;
            }
            int right = i;
            while(right + 1 < A.length && A[right+1] < A[right]) {
                right++;
            }
            int length = 1 + (i-left) + (right - i);
            if(i-left > 0 && (right - i) > 0)
                max = Math.max(max, length);
        }
        return max;
    }    
    
    /**
		Naive implementation. Increment k for increasing sequence, then change
		mode to decreasing. Reset after each new climb. O(N)
     */
    public int longestMountain3(int[] A) {
        int max = 0;
        int k = 0;
        boolean increasing = true;
        for(int i = 1; i < A.length; i++) {
            if(increasing && A[i-1] < A[i]) {
                k++;
            }
            else {
                if(k > 0) {
                    if(A[i] < A[i-1]) {
                        k++;
                        if(increasing) {
                            increasing = false;
                            k++;
                        }
                        max = Math.max(k, max);                        
                    }
                    else {
                        increasing = true;
                        k = 0;
                        if(A[i-1] < A[i]) {
                            k++;
                        }                        
                    }
                }
            }
        }
        return max;
    }    
    
    @Test
    public void Test1() {
    	assertEquals(9, longestMountain(new int[] { 10,3,5,7,2,2,1,5,8,12,9,7,5,2,1 }));
    }
    
    @Test
    public void Test2() {
    	assertEquals(3, longestMountain(new int[] { 10,9,8,7,12,4 }));
    }
    
    @Test
    public void Test3() {
    	assertEquals(4, longestMountain(new int[] { 1,6,2,6,8,2 }));
    }
}
