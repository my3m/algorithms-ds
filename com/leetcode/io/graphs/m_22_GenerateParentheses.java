package io.graphs;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class m_22_GenerateParentheses {
    
    public List<String> generateParenthesis(int n) {
    	List<String> combinations = new ArrayList<String>();
    	dfs(combinations, new ArrayList<Character>(), n, 0, 0);
    	return combinations;
    }
    
    String toString(List<Character> chars) {
		char[] arr = new char[chars.size()];
		for(int i = 0; i < arr.length; i++) {
			arr[i] = chars.get(i);
		}
		return new String(arr);
    }
    
    /*
     * (
     * 		((
     * 
     * 			(((
     * 				((()
     * 					((())
     * 						((()))
     * 			(()
     * 				(()(
     * 					(()())
     * 				(())
     * 					(())(
     * 						(())()
     * 
     * 		() 
     * 			()(
     * 				()((
     * 					()(()
     * 						()(())
     * 		
     * 				()()
     * 					()()(
     * 						()()()
     */
    void dfs(List<String> combinations, List<Character> chars, int n, int x, int y) {
    	if(x == n && y== n) {
    		combinations.add(toString(chars));
    	} else {
    		if(x < n) {
				chars.add('(');
				dfs(combinations, chars, n, x + 1, y);
				chars.remove(chars.size() - 1);
    		}
    		if(y < x && y < n) {
    			chars.add(')');
    			dfs(combinations, chars, n, x, y + 1);
    			chars.remove(chars.size() - 1);
    		}
    	}
    }
    
    @Test
    public void Test1() {
    	List<String> generateParenthesis = generateParenthesis(3);
    	assertEquals(5, generateParenthesis.size());
    }
}
