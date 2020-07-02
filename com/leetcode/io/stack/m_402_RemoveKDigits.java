package io.stack;

import java.util.LinkedList;

public class m_402_RemoveKDigits {
    public String removeKdigits(String num, int k) {
        //"827482", k=3
        
        //s="8,7", "82"
        
        //"827482"
        //"242"
        
        //"112"
        //"1"
        if(num.length() == k)
            return "0";
        
        LinkedList<Character> s1 = new LinkedList<>();
        for(char chr : num.toCharArray()) {
            while(s1.size() > 0 && k > 0 && chr < s1.getLast()) {
                s1.removeLast();
                k--;
            }
            s1.add(chr);
        }
        //"112", k=1
        while(s1.size() > 0 && k > 0) {
            s1.removeLast();
            k--;
        }
        //System.out.println(s1);
        StringBuilder sb = new StringBuilder();
        boolean l = true;
        for(char c : s1) {
            if(l && c == '0') continue;
            l = false;
            sb.append(c);
        }
        return sb.length() == 0 ? "0" : sb.toString();
        
        //return Integer.valueOf(dfs(num, k)).toString();
    }
    
    //"apple", "0,1"
    int dfs(String s, int k) {
        if(k == 0)
            return Integer.parseInt(s);
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < s.length(); i++) {
            min = Math.min(min, dfs(s.substring(0, i) + s.substring(i+1), k - 1));
        }
        return min;
    }
}
