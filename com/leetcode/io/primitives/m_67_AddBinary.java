package io.primitives;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class m_67_AddBinary {
	
	/***
        Intuition: Observe that carry can be determined by sum/2 AND 
        			bit can be determined by sum % 2
        
		sum=3, carry=1, e= 1
		sum=2, carry=1, e= 0
		sum=1, carry=0, e= 1
		sum=0, carry=0, e= 0
		
		e = sum % 2
		carry = sum / 2;

	 */
	public String addBinary(String a, String b) {

		StringBuilder sb = new StringBuilder();
		int i = a.length() - 1, j = b.length() - 1, carry = 0;

		while (i >= 0 || j >= 0) {
			int sum = carry;
			if (i >= 0)
				sum += a.charAt(i) - '0';
			if (j >= 0)
				sum += b.charAt(j) - '0';
			sb.append(sum % 2);
			carry = sum / 2;
			i--;
			j--;
		}
		if (carry == 1) {
			sb.append('1');
		}
		return sb.reverse().toString();
	}
	
    public String addBinaryFirstAttempt(String a, String b) {

        StringBuilder a1 = new StringBuilder(a);
        StringBuilder b1 = new StringBuilder(b);
        if(a.length() > b.length()) {
            //add leading zero to b
            while(a1.length() != b1.length()) {
                b1.insert(0, '0');
            }
        } else if(b.length() > a.length()) {
            while(a1.length() != b1.length()) {
                a1.insert(0, '0');
            }
        }
    
        a = a1.toString();
        b = b1.toString();
        
        StringBuilder sb = new StringBuilder();
        
        int i = a.length() - 1;
        
        int carry = 0;
        
        while(i >= 0) {
            int r = carry + getBit(a.charAt(i)) + getBit(b.charAt(i));
            if(r >= 2) {
                carry = 1;
                sb.append((char)('0' + (r - 2)));
            } else {
                carry = 0;
                sb.append((char)('0' + r));
            }
            i--;
        }
        if(carry > 0) {
            sb.append((char)(carry + '0'));
        }
        return sb.reverse().toString();
    }
    
    int getBit(char c) {
        return c == '1' ? 1 : 0;
    }
    
    @Test
    public void Test1() {
        assertEquals("11110", addBinary("1111","1111"));
    }
    
    @Test
    public void Test2() {
        assertEquals("10101", addBinary("1010","1011"));
    }
    
    @Test
    public void Test3() {
        assertEquals("100", addBinary("11","1"));
    }
}
