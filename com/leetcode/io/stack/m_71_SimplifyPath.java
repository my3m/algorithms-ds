package io.stack;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.Stack;

import org.junit.Test;

/**
 * 
	Example 1:
		Input: "/home/"
		Output: "/home"
	Explanation: Note that there is no trailing slash after the last directory name.
	
	Example 2:
		Input: "/../"
		Output: "/"
	Explanation: Going one level up from the root directory is a no-op, as the root level is the highest level you can go.
	
	Example 3:	
		Input: "/home//foo/"
		Output: "/home/foo"
	Explanation: In the canonical path, multiple consecutive slashes are replaced by a single one.

	Example 4:
		Input: "/a/./b/../../c/"
		Output: "/c"
	
	Example 5:
		Input: "/a/../../b/../c//.//"
		Output: "/c"
	
	Example 6:	
		Input: "/a//b////c/d//././/.."
		Output: "/a/b/c"
 */
public class m_71_SimplifyPath {
    public String simplifyPath(String path) {
    	String[] paths = path.split("\\/", Integer.MAX_VALUE);
    	LinkedList<String> s1 = new LinkedList<String>();
    	for(String s : paths) {
    		if(s.equals("") || s.equals("."))
    			continue;
    		else if(s.equals("..")) {
    			if(s1.size() > 0)
    				s1.removeLast();
    		}
    		else {
    			s1.addLast(s);
    		}
    	}
    	StringBuilder sb = new StringBuilder();
    	if(s1.size() == 0)
        	sb.append("/");
    	while(s1.size() > 0) {
    		sb.append("/" + s1.removeFirst());
    	}
    	return sb.toString();
    }
    
    @Test
    public void Test1() {
    	assertEquals("/home", simplifyPath("/home/"));
    }
    
    @Test
    public void Test2() {
    	assertEquals("/", simplifyPath("/../"));	
    }
    
    @Test
    public void Test3() {
    	assertEquals("/home/foo", simplifyPath("/home//foo/"));
    }
    
    @Test
    public void Test4() {
    	assertEquals("/c", simplifyPath("/a/./b/../../c/"));
    }
    
    @Test
    public void Test5() {
    	assertEquals("/c", simplifyPath("/a/../../b/../c//.//"));
    }
    
    @Test
    public void Test6() {
    	assertEquals("/a/b/c", simplifyPath("/a//b////c/d//././/.."));
    }
}
