package io.stack;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import org.junit.Test;

public class m_1249_MinimumRemoveMakeValidParentheses {
	public String minRemoveToMakeValid(String s) {
		int balance = 0, open = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char chr = s.charAt(i);
			if (chr == '(') {
				open++;
				balance++;
			} else if (chr == ')') {
				if (balance == 0)
					continue;
				balance--;
			}
			sb.append(chr);
		}

		
		// ((((()
		//open = 5, balance= 4
		//just keep only 1 "(" through traverse & get ()
		
		//(((**********))
		//if we have "asteriks" > balance, we can cover it
		
		//what if we have *** before (((
		//******((())
		//we cannot make this valid because ) comes after (
		if (balance != 0) {
			int openKeep = open - balance;
			StringBuilder sb2 = new StringBuilder();
			for (int i = 0; i < sb.length(); i++) {
				char chr = sb.charAt(i);
				if (chr == '(') {
					if (--openKeep < 0) {
						continue;
					}
				}
				sb2.append(chr);
			}
			return sb2.toString();
		}
		return sb.toString();
	}

	@Test
	public void Test1() {
		assertEquals("()()", minRemoveToMakeValid("()())))"));
	}

	@Test
	public void Test2() {
		assertEquals("()()", minRemoveToMakeValid("()()(((("));
	}

	@Test
	public void Test3() {
		assertEquals("", minRemoveToMakeValid(")))(((("));
	}

	@Test
	public void Test4() {
		// assertEquals("(())", minRemoveToMakeValid("((((((()()"));
		assertEquals("()()", minRemoveToMakeValid("((((((()()"));
	}
}
