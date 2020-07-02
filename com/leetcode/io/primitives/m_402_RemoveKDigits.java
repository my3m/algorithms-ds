package io.primitives;

import org.junit.Test;

public class m_402_RemoveKDigits {
    public String removeKdigits(String num, int k) {
        if(k == 0)
            return num;
        Integer d = removeKdigitsHelper(num, k);
        return d.toString();
    }
    
    int removeKdigitsHelper(String num, int K) {
        int smallest = Integer.MAX_VALUE;
        if(K == 0) {
            return toInteger(num);
        }
        for(int i = 0; i < num.length(); i++) {
        	String substr = num.substring(0, i) + num.substring(i + 1);
            smallest = Math.min(smallest, removeKdigitsHelper(substr, K - 1));
        }
        return smallest;
    }
    
    int toInteger(String num) {
        if(num == "")
            return 0;
        //"123"
        int sum = 0;
        int j = 0;
        while(j < num.length()) {
            sum = sum*10 + num.charAt(j) - '0';
            j++;
        }
        return sum;
    }
    
    @Test
    public void Test1() {
    	removeKdigits("10200", 1);
    }
}
