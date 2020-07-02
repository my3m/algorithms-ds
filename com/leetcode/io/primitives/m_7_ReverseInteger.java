package io.primitives;

/**
 * Detecting overflow 


    if(reversed > Integer.MAX_VALUE/10 || (reversed == Integer.MAX_VALUE/10 && rem > Integer.MAX_VALUE % 10))
        return 0;
    if(reversed < Integer.MIN_VALUE/10 || (reversed == Integer.MIN_VALUE/10 && rem < Integer.MIN_VALUE % 10))
        return 0;

 *
 */
public class m_7_ReverseInteger {
    public int reverse(int x) {
        //Derive 10-based pow-len
        /*
        0*10 + 3,=> 3
        3*10 + 2 => 32
        32*10 +1 => 321
        */
        int reversed = 0;
        int prev = 0;
        while(x != 0) {
            int tail = (x % 10);
            x/=10;
            //this statement can cause an overflow due to *10
            prev = reversed;
            reversed = reversed * 10 + tail;
            
            //32*10 + 1
            //321
            //320=>32
            if(((reversed-tail)/10) != prev)
                return 0;
        }
        return reversed; //(reversed == (int)reversed) ? (int)reversed : 0;
    }
}
