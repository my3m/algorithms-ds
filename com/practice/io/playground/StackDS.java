package io.playground;

import java.util.HashMap;
import java.util.Stack;
import java.util.Map;

public class StackDS {
	public static void main(String[] args) {

	    //System.out.println(
	    //System.out.println(dfs("5[ab4[x]]"));
	    System.out.println(dfs(new StringBuilder(), 0, "5[ab4[x]y]", 0));
	    //System.out.println(dfs("2[a2[c4[x]]fxef]"));
	    /*
	     * 4[x]
	     * 2[cxxxxfxef]
	     * 2[acxxxxfxefcxxxxfxef]
	     * 
	     */
	    //acxxxxfxefcxxxxfxefacxxxxfxefcxxxxfxef
	    
	    //2+9/3+2
	    //2+9+3
	    //2,3
	    System.out.println(3/4);

	    System.out.println(calculator("2-(3+1)*5"));
	    //System.out.println(decompress("3[xf3[bv]le]rt"));
	    //System.out.println(calculateHelper("2+9/3*2*(10/2)+2", 0, 0, 0, 0, '+'));
	    //System.out.println(calculateHelper("2*(1+5)", 0, 0, 0, 0, '+'));
	    System.out.println(calculateHelper("5-(2+3)+2", 0, 0, 0, 0, '+'));
	    //System.out.println(calculateHelper("5-(3+2)+2", 0, 0, 0, 0, '+'));
	    System.out.println(calculateUseAuxiliarySpace("5-5+2", 0));
//	    System.out.println(iterativecalc("100/2+10"));
//	    System.out.println(iterativecalc("2+9/3*2"));
//	    System.out.println(iterativecalcc("2*3+4"));
	    
	    System.out.println(nextGreaterElement(new int[] { 4,1,2 }, new int[] { 1,3,4,2 }));
	}
	
	/***
	 * UBER
		Let there be a simple language like this:
		
		expr ::= int | '(' op expr+ ')'
		op ::= '+' | '*'
		The task is to write a function int evaluate(String expression) that evaluates a given expression. You can assume that expression is valid for this given language.
		
		Examples:
		
		"3" = 3
		"( + 1 2 )" = 3
		"( + 3 4 5 )" = 12
		"( + 7 ( * 8 12 ) ( * 2 ( + 9 4 ) 7 ) 3 )" = 288
	 */
	public static int calculateExpression() {
		return 0;
	}
	
    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        int[]ans = new int[n1];
        if (nums1 == null || n1 == 0) return ans;
        Map<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        for(int j = 0; j < n2; j++) {
            int cur = nums2[j];
                while (!stack.isEmpty() && cur > stack.peek()) {
                    map.put(stack.pop(), cur);
                }
            stack.push(cur);
        }
        for (int i = 0; i < n1; i++) {
            int k = map.getOrDefault(nums1[i], -1);
            ans[i] = k;
        }
        return ans;
    }
	
	static int[] getCurrentTotal(char opr, int total, int prev, int count) {
		if(opr == '+') {
			total += prev;
			prev = count;
		}
		else if(opr == '-') {
			total += prev;
			prev = -count;
		}
		else if(opr == '/') {
			prev = prev / count;
		}
		else if(opr == '*') {
			prev = prev * count;
		}	
		return new int[] { total, prev, count };
	}
	
	//2+2*(9+9/3)
	//2-9/3+0
	//@ '-', total = 0, prev =2
	//@'+', total = 2, prev = -2;
	static int calculator(String s) {
		Stack<Object[]> operations = new Stack<Object[]>();
		int total = 0, prev = 0, count = 0;
		char opr = '+';
		for(char chr : s.toCharArray()) {
			if(Character.isDigit(chr)) {
				count = count * 10 + chr - '0';
			}
			//2+(2+3)+0
			else if(chr == '+' || chr == '-' || chr == '/' || chr == '*') {
				int[] temp = getCurrentTotal(opr, total, prev, count);
				total = temp[0];
				prev = temp[1];
				opr = chr;
				count = 0;
			}
			else if(chr == '(') {
				operations.push(new Object[] { total, prev, opr });
				count = 0;
				opr = '+';
				total = 0;
				prev = 0;
			}
			//"2-(3+1)*5"
			//total = 0, prev=2
			//@ ')', total=4, ct=4
			//tota;=0, prev=2, opr='-', ct=4
			//total=2, prev=-4
			//total=2, prev=-4, opr='*', ct=5
			//total=2, prev=-20
			else if(chr == ')') {
				int[] temp = getCurrentTotal(opr, total, prev, count);
				count = temp[0] + temp[1]; //6
				Object[] state = operations.pop();
				total = (int)state[0]; //t=1
				prev = (int)state[1]; //prev=5
				opr = (char)state[2]; //opr='*'
			}
		}
		int[] temp = getCurrentTotal(opr, total, prev, count);
		return temp[0] + temp[1];
	}
	
	//2+2*(9+9/3)
	//total, prev, opr
	//3[xf3[bv]le]rt
	//xfbvbvbvlexfbvbvbvlexfbvbvbvlert
	static String decompress(String s) {
		//prefix & number
		//each '[' is a recursive call, which means it gets its own instance
		//on '[' means save prefix, + num in stack
		//on ']', pop num + prefix
		//[3[xfe4[a2[fx6[p3[n]]]yy
		//3, ""
		//4, "xfe", xfe + "afxpnnnpnnnpnnnfxpnnnpnnnpnnnafxpnnnpnnnpnnnfxpnnnpnnnpnnnafxpnnnpnnnpnnnfxpnnnpnnnpnnnafxpnnnpnnnpnnnfxpnnnpnnnpnnn"
		//2, "a", "a" + "fxpnnnpnnnpnnnfxpnnnpnnnpnnn"
		//6, "fx", fx + 6pnnn => "fxpnnnpnnnpnnn"
		//3, "p", "p" + 3n => "pnnn"
		//
		Stack<String> prefixs = new Stack<String>();
		Stack<Integer> numberss = new Stack<Integer>();
		StringBuilder sb = new StringBuilder();
		int count = 0;
		for(char chr : s.toCharArray()) {
			if(Character.isDigit(chr)) {
				count = count * 10 + chr - '0';
			}
			else if(chr == '[') {
				//save prefix, + num
				prefixs.push(sb.toString());
				numberss.push(count);
				sb.setLength(0);
				count = 0;
			}
			else if(chr == ']') {
				String temp = sb.toString();
				sb = new StringBuilder(prefixs.pop());
				int repeat = numberss.pop();
				while(repeat > 0) {
					sb.append(temp);
					repeat--;
				}
			}
			else if (Character.isLetter(chr)){
				sb.append(chr);
			}
		}
		return sb.toString();
	}
	
	//2-9 /3
	public static int calculateHelper(String s, int idx, int prev, int count, int running, char opr) {
		if (idx < s.length() && s.charAt(idx) == ' ')
			return calculateHelper(s, idx + 1, prev, count, running, opr);
		char c = s.charAt(idx);
		while (idx < s.length() && Character.isDigit(s.charAt(idx))) {
			count = count * 10 + s.charAt(idx++) - '0';
		}
		while (idx < s.length() && s.charAt(idx) == ' ')
			idx++;
		if (c == '(') {
			count = calculateHelper(s, ++idx, 0, 0, 0, '+');
			int open = 1;
			while (idx < s.length() && (open != 0 || s.charAt(idx) == ' ')) {
				if (s.charAt(idx) == '(')
					open++;
				else if (s.charAt(idx) == ')')
					open--;
				idx++;
			}
		}
		if (opr == '+') {
			running = running + prev;
			prev = count;
		} else if (opr == '-') {
			running = running + prev;
			prev = -1 * count;
		} else if (opr == '/') {
			prev = prev / count;
		} else if (opr == '*') {
			prev = prev * count;
		}
		if (idx == s.length() || s.charAt(idx) == ')')
			return running + prev;
		return calculateHelper(s, idx + 1, prev, 0, running, "+-/*".indexOf(s.charAt(idx)) != -1 ? s.charAt(idx) : opr);
	}
	
	public static int calculateUseAuxiliarySpace(String s, int start) { 
        if(s.length() == 0)
            return 0;
		  char operand = '+';
		  int prev = 0;
		  int total = 0;
		  while(start >= 0 && start < s.length()) {
			  char curr = s.charAt(start);
			  if(isDigit(curr)) {
				  int k = curr - '0';
				  while(start + 1 < s.length() && isDigit(s.charAt(start + 1))) {
					  k = k * 10 + s.charAt(start + 1) - '0';
					  start++;
				  }
				  if(operand == '+') {
					  total+=prev;
					  prev = k;
				  }
				  else if(operand == '-') {
					  total+= prev;
					  prev = -1 * k;
				  }
				  else if(operand == '*') {
					  prev = prev * k;
				  }
				  else if(operand == '/') {
					  prev = prev / k;
				  }
				  else {
					  throw new IllegalArgumentException("invalid operand type. only +, -, /, * allowed.");
				  }
			  }
			  else if(isOperand(curr)) {
				  operand = curr;
			  }
			  start++;
		  }
		  total+=prev;
		  return total;          
    }
	
	public static boolean isOperand(char chr) {
		return chr == '+' || chr == '-' || chr == '*' || chr == '/';
	}

	public static boolean isDigit(char chr) {
		return chr >= '0' && chr <= '9';
	}
	
	static int iterativecalcc(String s) {
		int prefix = 0, val =0, count = 0;
		char op = '+';
		char[] arr = s.toCharArray();
		for(int i = 0; i < arr.length; i++) {
			char chr = arr[i];
			if(Character.isDigit(chr)) {
				count = count * 10 + chr - '0';
			}
			else if(chr == ' ')
				continue;
			else if("+-/*".indexOf(chr) != -1) {
				//0+2-9/3*2
				//2+9/3*2
				//2,9
				//2,3
				
				//prefix=2, op='-'
				//ct=9, chr='/', ct=2=3, 
				//
				if(chr == '+' || chr == '-') {
					if(op == '+') {
						prefix += count;
						op = chr;
					}
					else if(op == '-') {
						prefix -= count;
						op = chr;
					}
					count = 0;
				}
				else {
				//2-3+2
				//2-9/3*2+1
				//2-6
				//2*3
					while(i < arr.length && (arr[i] == '/' || arr[i] == '*')) {
						if(arr[i] == '/') {
							int temp = 0;
							i++;
							while(i < arr.length && arr[i] == ' ') {
								i++;
							}
							while(i < arr.length && Character.isDigit(arr[i])) {
								temp = temp * 10 + arr[i] - '0';
								i++;
							}
							count = count / temp;
							//[1,2,3,4]
							
						}
						else if(arr[i] == '*') {
							int temp = 0;
							//"/ 2
							i++;
							while(i < arr.length && arr[i] == ' ') {
								i++;
							}
							while(i < arr.length && Character.isDigit(arr[i])) {
								temp = temp * 10 + arr[i] - '0';
								i++;
							}
							count = count * temp;
							//"2 * 5  / 2"
						}
						while(i < arr.length && arr[i] == ' ') {
							i++;
						}						
					}
					i--;
				}
			}
		}
		//2*3
		//2-3*2+5*2
		if(count > 0) {
			prefix += op == '+' ? count : (op == '-') ? -count : count;
		}
		return prefix;
	}
	
	static void apply(Stack<Integer> s1, char chr, int count) {
		if(chr == '/') {
			if(s1.size() > 0)
				s1.push(s1.pop() / count);
		}
		else if(chr == '*') {
			if(s1.size() > 0)
				s1.push(s1.pop() * count);
		}
		else if(chr == '+') {
			s1.push(count);
		}
		else if(chr == '-') {
			s1.push(-count);
		}	
	}
	
	static int iterativecalc(String s) {
		int count = 0;
		Stack<Integer> s1 = new Stack<>();
		char op = '+';
		for(char chr : s.toCharArray()) {
			if(Character.isDigit(chr)) {
				count = count * 10 + chr - '0';
			}
			else if("+-*/".indexOf(chr) != -1) {
				apply(s1, op, count);
				op = chr;
				count = 0;
			}
		}
		if(count > 0) {
			apply(s1, op, count);
		}
		count = 0;
		while(s1.size() > 0) {
			count += s1.pop();
		}
		return count;
	}
	
	/**
	 * Understanding that for "[", there is a new instance of sb/count = 0
	 * need to move idx to closing bracket
	 */
    static String dfs(StringBuilder sb, int count, String s, int idx) {
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
	
    static String dfs(String s) {
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
    			StringBuilder prefix = new StringBuilder(ps1.pop());
    			while(x != 0) {
    				prefix.append(sb);
    				x--;
    			}
    			sb = prefix;
    		}
    	}
    	return sb.toString();    
    }	
	
    Map<Integer, String> map = new HashMap<Integer, String>();
    static int dfs(int removed, String str, int idx) 
    {
        if(str.length() == idx)
            return Integer.MAX_VALUE;
        if(isValid(str))
            return removed;
        int minimum = Integer.MAX_VALUE;
        String rem = str.substring(0, idx) + str.substring(idx + 1);
        if(str.charAt(idx) == '(' || str.charAt(idx) == ')') {
            minimum = Math.min(minimum, dfs(removed + 1, rem, idx));
        }
        minimum = Math.min(minimum, dfs(removed, str, idx + 1));
        return minimum;
    }	
	
	static boolean isValid(String str) {
        int open = 0, close = 0;
        for(char c : str.toCharArray()) {
            if(c == '(')
                open++;
            else if(c == ')') {
                close++;
                if(close > open)
                    return false;
            }
        }
        return open == close;
    }	
}
