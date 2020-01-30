package io.stack;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Test;

public class BasicCalculator2 {
	
	  /*
	   * lp=2, fp=4, 
	   * 
	   * 
	   */
	  //2+4+2*4 => 6+2*4
	  //2+4*2+9/3
	  //2+8+3
	  public int calculate(String s) {
		  Stack<Integer> stack = new Stack<Integer>();
		  int start = 0;
		  int k = 0;
		  char operand = '+';
		  while(start >= 0 && start < s.length()) {
			  char curr = s.charAt(start);
			  if(isDigit(curr)) {
				  k = curr - '0';
				  while(start + 1 < s.length() && isDigit(s.charAt(start + 1))) {
					  k = k*10 + (s.charAt(start + 1) - '0');
					  start++;
				  }
				  if(operand == '*') {
					  stack.push(stack.pop()*k);
				  }
				  else if (operand == '/') {
					  stack.push(stack.pop()/k);
				  }
				  else if(operand == '+') {
					  stack.push(k);
				  }
				  else if(operand == '-') {
					  stack.push(-1 * k);
				  }
			  }
			  else if(isOperand(curr)) {
				  operand = curr;
			  }
			  start++;
		  }
		  int total = 0;
		  while(stack.size() > 0) total += stack.pop();
		  return total;
	  }
	  
	  public boolean isOperand(char chr) {
		  return chr == '+' || chr == '-' || chr == '*' || chr == '/';
	  }
	  public boolean isDigit(char chr) {
		  return chr >= '0' && chr <= '9';
	  }
	  @Test
	  public void Test1() {
		  assertEquals(13, calculate("2+4*2+9/3"));
	  }
}
