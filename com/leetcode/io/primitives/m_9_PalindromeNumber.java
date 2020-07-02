package io.primitives;

public class m_9_PalindromeNumber {
    public boolean isPalindrome(int x) {
        //reverse integer
        
        //1,2,1
        //3,6,5
        //5,6,3
        //0*10 + 5
        //50+6
        //560+3
        
        //how to reverse integer?
        //how to handle negatives?
        //-121
        
        //feedback?
        //did I handle overflow?
        //we could handle negatives much better if x < 0, return false
        
        int ri = 0;
        int num = x;
        while(num != 0) {
            int mod = num % 10;
            ri = ri * 10 + mod;
            num = num / 10;
        }
        //System.out.println(ri);
        return ri == (x < 0 ? x * - 1 : x);
    }
}
