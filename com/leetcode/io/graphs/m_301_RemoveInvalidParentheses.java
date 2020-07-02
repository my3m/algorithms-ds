package io.graphs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import org.junit.Test;

public class m_301_RemoveInvalidParentheses {
	/*
	 * try removing each-bracket, see if it becomes a valid if there are multiple
	 * parenthesis, try doing dfs, on remaining string, perform a
	 * isValidParenthesis() to see if its valid, will give you number of
	 * invalidParenthesis.
	 * 
	 * we can use substring to leave the parenthesis we want to check
	 * 
	 * )())()
	 * 
	 * ab
	 * 
	 */

	int minimumDepth = Integer.MAX_VALUE;
	Set<String> validParentheses = new HashSet<String>();

	public List<String> removeInvalidParentheses(String s) {
		List<String> list = new ArrayList<String>();
		if (isWellFormed(s)) {
			list.add(s);
			return list;
		}
		Set<String> visited = new HashSet<String>();
		int[] lrmin = getminimum(s);
		dfs(s, lrmin[0], lrmin[1], 0, visited);
		// bfs(s);
		if (validParentheses.size() == 0) {
			list.add("");
			return list;
		}
		for (String vs : validParentheses) {
			list.add(vs);
		}
		return list;
	}

	void bfs(String s) {
		Queue<String> queue = new LinkedList<String>();
		queue.offer(s);
		int depth = 0;
		Set<String> visited = new HashSet<String>();
		while (queue.size() > 0) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				String current = queue.poll();
				if (visited.contains(current))
					continue;
				visited.add(current);
				System.out.println(current);
				if (!isWellFormed(current)) {
					if (validParentheses.size() == 0) {
						int left = 0;
						int right = 0;
						for (int j = 0; j < current.length(); j++) {
							char chr = current.charAt(j);
							if (right > left) {
								break;
							}
							if (current.charAt(j) == '(' || current.charAt(j) == ')') {
								String str = current.substring(0, j) + current.substring(j + 1);
								queue.offer(str);
							}
							if (chr == '(')
								left++;
							if (chr == ')')
								right++;
						}
					}
				} else {
					// depth
					System.out.printf("Found valid string at depth=%d\r\n", depth);
					validParentheses.add(current);
				}
			}
			depth += 1;
		}
	}

	int[] getminimum(String s) {
		int left = 0;
		int right = 0;
		//())
		for(int i = 0; i < s.length(); i++) {
			char chr = s.charAt(i);
			if(chr == '(') left++;
			else if(chr == ')') {
				if(left > 0) left--;
				else right ++;
			}
		}
		return new int[] { left, right };
	}
	
	boolean dfs(String s, int rleft, int rright, int n, Set<String> visited) {
		if (s.length() == 0)
			return false;
		if(rleft < 0)
			return false;
		if(rright < 0)
			return false;
		if (visited.contains(s))
			return false;
		visited.add(s);
		if (isWellFormed(s)) {
			if (n == minimumDepth) {
				validParentheses.add(s);
			} else if (n < minimumDepth) {
				validParentheses.clear();
				validParentheses.add(s);
				minimumDepth = n;
			}
		} else {
			int left = 0;
			int right = 0;
			for (int i = 0; i < s.length(); i++) {
				char chr = s.charAt(i);
				// )()
				// )())()
				if (right > left)
					break;
				String ns = s.substring(0, i) + s.substring(i + 1);
				if (chr == '(') {
					dfs(ns, rleft - 1, rright, n + 1, visited);
				}
				if (chr == ')') {
					dfs(ns, rleft, rright - 1, n + 1, visited);
				}
				if (chr == '(')
					left++;
				if (chr == ')')
					right++;
			}
		}
		return false;
	}

	boolean isWellFormed2(String s) {
		if (s.length() == 0)
			return false;
		int left = 0;
		int right = 0;
		for (int i = 0; i < s.length(); i++) {
			char chr = s.charAt(i);
			if (chr == '(')
				left++;
			if (chr == ')') {
				right++;
				if (right > left)
					return false;
			}
		}
		return left == right;
	}

	boolean isWellFormed(String s) {
		Stack<Character> stack = new Stack<Character>();
		// (
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(' || s.charAt(i) == ')') {
				if (s.charAt(i) == '(') {
					stack.push(s.charAt(i));
				} else if (s.charAt(i) == ')') {
					if (stack.size() > 0) {
						if (stack.peek() != '(')
							return false;
						else if (stack.peek() == '(') {
							stack.pop();
						}
					} else {
						return false;
					}
				}
			}
		}
		return stack.size() == 0;
	}

	@Test
	public void Test1() {
		assertFalse(isWellFormed("()())()"));
		assertFalse(isWellFormed("(a)())()"));
		assertFalse(isWellFormed(")"));
		assertFalse(isWellFormed(")("));
		assertFalse(isWellFormed("(()"));
		assertTrue(isWellFormed("()()()"));
		assertTrue(isWellFormed("()()((((((()))))))()"));
	}

	// ()())
	@Test
	public void Test2() {
		List<String> expected = new ArrayList<String>();
		expected.add("()()");
		assertEquals(expected, removeInvalidParentheses("()(((((((()"));
	}

	@Test
	public void Test3() {
		List<String> expected = new ArrayList<String>();
		expected.add("(())");
		assertEquals(expected, removeInvalidParentheses("(())("));
	}

	@Test
	public void Test4() {
		List<String> expected = new ArrayList<String>();
		expected.add("(k())(k)u");
		expected.add("((k())k)u");
		expected.add("((k))(k)u");
		assertEquals(expected, removeInvalidParentheses("((k())(k)u"));
	}
	
	@Test
	public void Test5() {
		List<String> expected = new ArrayList<String>();
		expected.add("(p())");
		expected.add("p(())");
		assertEquals(expected, removeInvalidParentheses("))(p(((())"));
	}
}
