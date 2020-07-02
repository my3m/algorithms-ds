package io.stack;

import java.util.Stack;

public class m_394_DecodeString {
	String decodeString(String s) {
		return iterative(s);
	}
    String iterative(String s) {
        //5[ab2[c]xf]
        //"" + 5 ()
        //"ab" + 2
        //], pop "2", repeat sb.toString() 2 times, add to "ab"

        Stack<String> ps1 = new Stack<String>();
        Stack<Integer> cs1 = new Stack<Integer>();
        StringBuilder sb = new StringBuilder();
        int val = 0;
        //"" + 5[ab2[c]xf]
        for(char c : s.toCharArray()) {
            if(Character.isLetter(c)) {
                sb.append(c);
            }
            else if(Character.isDigit(c)) {
                val = val * 10 + c - '0';
            }
            else if(c == '[') {
                cs1.push(Integer.valueOf(val));
                ps1.push(sb.toString());

                sb.setLength(0);
                val = 0;
            }
            else if(c == ']') {
                int x = cs1.pop();
                String temp = sb.toString();
                sb = new StringBuilder(ps1.pop());
                while(x-- != 0) {
                    sb.append(temp);
                    x--;
                }
            }
        }
        return sb.toString();    
    }	
    
    String dfs(StringBuilder sb, int count, String s, int idx) {
        if(idx == s.length())
            return sb.toString();
        char chr = s.charAt(idx);
        if(chr == '[') {
            String temp = dfs(new StringBuilder(), 0, s, idx + 1);
            while(count-- != 0) {
            	sb.append(temp);
            }
            //5[ab4[x]]
            int open = 1;
            idx++;
            while(idx < s.length() && open != 0) {
            	if(s.charAt(idx) == ']')
            		open--;
            	else if(s.charAt(idx) == '[')
            		open++;
            	idx++;
            }
            return dfs(sb, 0, s, idx);
        }
        else if(Character.isLetter(chr)) {
            sb.append(chr);
            return dfs(sb, count, s, idx + 1);
        }
        else if(Character.isDigit(chr)) {
            count = count * 10 + chr - '0';
            return dfs(sb, count, s, idx + 1);
        }
        else { // if(chr == ']') {
//            String temp = sb.toString();
//            sb = new StringBuilder();
//            while(count-- != 0) {
//                sb.append(temp);
//            }
            return sb.toString();
        }        
    }    
    
}
