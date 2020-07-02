package io.stack;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Test;

/**
	Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
	
	An input string is valid if:
		Open brackets must be closed by the same type of brackets.
		Open brackets must be closed in the correct order.
		Note that an empty string is also considered valid.
 */
public class m_20_ValidateParenthesis {
    public boolean isValid(String s) {
    	Stack<Integer> s1 = new Stack<Integer>();
        for(char c : s.toCharArray()) {
        	if(c == '(' || c == '[' || c == '{') {
        		s1.push((int)c);
        	}
        	else if(c == ')' || c == ']' || c == '}') {
        		if(s1.size() == 0)
        			return false;
        		if(s1.peek() == '(' && c != ')' || s1.peek() == '[' && c != ']' 
        				|| s1.peek() == '{' && c != '}')
        			return false;
        		s1.pop();
        	}
        }
        return s1.size() == 0;
    }
    
    boolean isValidRecursive(String s) {
    	return isValidRecursiveHelper(s, 0, new StringBuilder());
    }
    
    //(]{}{{}}
    boolean isValidRecursiveHelper(String s, int idx, StringBuilder sb) {
    	if(s.length() == idx)
    		return sb.length() == 0;
    	char chr = s.charAt(idx);
    	if(chr == '(' || chr == '[' || chr == '{') {
    		sb.append(chr);
    	}
    	else if(chr == ')') {
    		if(sb.length() == 0 || sb.charAt(sb.length() - 1) != '(') 
    			return false;
    		sb.delete(sb.length() -1, sb.length());
    	}
    	else if(chr == ']') {
    		if(sb.length() == 0 || sb.charAt(sb.length() - 1) != '[') 
    			return false;
    		sb.delete(sb.length() -1, sb.length());
    	}
    	else if(chr == '}') {
    		if(sb.length() == 0 || sb.charAt(sb.length() - 1) != '{') 
    			return false;
    		sb.delete(sb.length() -1, sb.length());
    	}
		return isValidRecursiveHelper(s, idx + 1, sb);
    }
    
    @Test
    public void Test1() {
    	assertEquals(false, isValidRecursive("{}{)"));
    	assertEquals(true, isValidRecursive("{}{}"));
    	assertEquals(true, isValidRecursive(""));
    	assertEquals(true, isValidRecursive("(({}){})"));
    	assertEquals(false, isValidRecursive("}}}"));
    	assertEquals(false, isValidRecursive("(}[}{}"));
    	assertEquals(false, isValidRecursive("}}}{{{"));
    	//{}(())[{[]}]
    	
    }
}
