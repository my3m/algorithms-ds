package io.stack;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Test;

public class BasicCalculator3 {
	//2+2*3
	//2+6
	//2*(5+5*2)/3
	//2*(3+5*2+4/(1+1))/3 => 30/3 => 10
	public int calculate(String s, int start, int[] eidx) {
		char operand = '+';
		int total = 0;
		Stack<Integer> stack = new Stack<Integer>();
		while(start >= 0 && start < s.length()) {
			char curr = s.charAt(start);
			if(isDigit(curr)) {
				int k = curr - '0';
				while(start + 1 < s.length() && isDigit(s.charAt(start + 1))) {
					k = k*10 + (s.charAt(start + 1) - '0');
					start++;
				}
				resolveExpression(stack, operand, k);
			}
			else if(isOperand(curr)) {
				operand = curr;
			}
			else if(curr == '(') {
				int inner = calculate(s, start + 1, eidx);
				resolveExpression(stack, operand, inner);
				start = eidx[0];
			}
			else if(curr == ')') {
				eidx[0] = start;
				break;
			}
			start++;
		}
		while(stack.size() > 0) total += stack.pop();
		return total;
	}
	
	public void resolveExpression(Stack<Integer> stack, char operand, int value) {
		if(operand == '*') {
			stack.push(stack.pop() * value);
		}
		else if(operand == '/') {
			stack.push(stack.pop() / value);
		}
		else if(operand == '-') {
			stack.push(-1 * value);
		}
		else if(operand == '+') {
			stack.push(value);
		}
	}
	
	public boolean isOperand(char c) {
		return c == '+' || c == '-' || c == '/' || c == '*';
	}
	
	public boolean isDigit(char c) {
		return c >= '0' && c <= '9';
	}
	
	@Test
	public void Test1() {
		assertEquals(8, calculate("2+2*3", 0, new int[1]));
	}
	@Test
	public void Test2() {
		assertEquals(8, calculate("2+6", 0, new int[1]));
	}
	@Test
	public void Test3() {
		assertEquals(5, calculate("10-10/2", 0, new int[1]));
	}
	@Test
	public void Test4() {
		assertEquals(25, calculate("(10+30/2)", 0, new int[1]));
	}
	@Test
	public void Test5() {
		assertEquals(50, calculate("(10+30/2)*2", 0, new int[1]));
	}
	//(10+30/2)*2-(50/10)
	//30-
	@Test
	public void Test6() {
		assertEquals(45, calculate("(10+30/2)*2-(50/10)", 0, new int[1]));
	}
	@Test
	public void Test7() {
		assertEquals(-12, calculate("(2+6* 3+5- (3*14/7+2)*5)+3", 0, new int[1]));
	}
}
