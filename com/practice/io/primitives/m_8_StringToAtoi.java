package io.primitives;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class m_8_StringToAtoi {
    public int myAtoi(String str) {
        int s_idx = 0;
        while(s_idx < str.length() && str.charAt(s_idx) == ' ')
            s_idx++;
        int operator = 1;       
        //300, 290
        //300, i=29
        //300-29=>271
        if(s_idx < str.length()) {
        	//importance of if-else branch?
            if(str.charAt(s_idx) == '-') {
                operator = -1;
                s_idx++;
            }
            else if(str.charAt(s_idx) == '+') {
                s_idx++;
            }
                       
            //-20 -3 => -23 => -23 + 3
            //20 + 3 => 23 => 23 - 3
            
            //-20 - 3
            //-23 + (3)
            //10s + x
            //-23 - (-3_
            //23 - 3 => 30
            //297
            //297/10=> 29
            //292
            //29 8
            int value = 0;
            int tail = 0;
            while(s_idx < str.length() && (str.charAt(s_idx) >= '0' && str.charAt(s_idx) <= '9')) {
            	tail = operator * (str.charAt(s_idx) - '0');
            	if(isOverflowCandidate(value, tail))
                	return operator == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            	value = (value* 10) + tail;
                s_idx++;
            }
            return value;
        }
        return 0;
    }
    
    boolean isOverflowCandidate(int value, int tail) {
        if(value > Integer.MAX_VALUE / 10 || value == Integer.MAX_VALUE/10  && tail > Integer.MAX_VALUE %10) {
        	return true;
        }
        else if (value < Integer.MIN_VALUE / 10 || value == Integer.MIN_VALUE/10 && tail < Integer.MIN_VALUE%10) {
        	return true;
        }
        return false;
    }
    
    @Test
    public void Test1() {
    	assertEquals(2147483647, myAtoi("2147483648"));
    }
    
    @Test
    public void Test2() {
    	assertEquals(-42, myAtoi("        -42"));
    }
    
    @Test
    public void Test3() {
    	assertEquals(1, myAtoi("        +1"));
    }
    
    @Test
    public void Test4() {
    	assertEquals(2147483646, myAtoi("2147483646"));
    }
    
    @Test
    public void Test5() {
    	assertEquals(2147483647, myAtoi("2147483648"));
    }
    
    @Test
    public void Test6() {
    	assertEquals(-2147483648, myAtoi("-91283472332"));
    }
    
    @Test
    public void Test7() {
    	assertEquals(4193, myAtoi("4193 with words"));
    }
    
    @Test
    public void Test8() {
    	assertEquals(0, myAtoi("-"));
    }
    
    @Test
    public void Test9() {
    	assertEquals(0, myAtoi("          -+-+12"));
    }
    
    @Test
    public void Test10() {
    	assertEquals(0, myAtoi("-+1"));
    }
}
