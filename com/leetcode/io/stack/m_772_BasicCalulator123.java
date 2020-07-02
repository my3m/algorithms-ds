package io.stack;

import java.util.Stack;

public class m_772_BasicCalulator123 {
	// 2+3*(5+9/3);
	// 2+(6+3), prefix=2, opr = '+', ct=9
	// 2+9, prefix=2, ct=9
	// 2+3*(5+9/3)+3; total=2, prefix=3, opr=*, ct=8
	// 2*3, total = 0, prefix=2, ct=3
	public int calculate(String s) {
		Stack<Object[]> s1 = new Stack<>();
		int total = 0, prefix = 0, count = 0;
		char opr = '+';
		for (char c : s.toCharArray()) {
			if (Character.isDigit(c)) {
				count = count * 10 + c - '0';
			} else if (c == ' ')
				continue;
			else if (c == '(') {
				// 2+3*(5+9/3);
				// total=2, prefix=3, opr='*'
				s1.push(new Object[] { total, prefix, opr });
				total = 0;
				prefix = 0;
				opr = '+';
			} else if (c == ')') {
				int[] r = getSums(total, prefix, count, opr);
				Object[] temp = s1.pop();
				total = (int) temp[0];
				prefix = (int) temp[1];
				opr = (char) temp[2];
				count = r[0] + r[1];
			} else if ("+-/*".indexOf(c) != -1) {
				int[] r = getSums(total, prefix, count, opr);
				total = r[0];
				prefix = r[1];
				count = 0;
				opr = c;
			}
		}
		// 2*3
		// 2+3, prefix=2, opr = '+', ct=3
		int[] r = getSums(total, prefix, count, opr);
		return r[0] + r[1];
	}

	public int[] getSums(int total, int prefix, int count, char opr) {
		if (opr == '+') {
			total += prefix;
			prefix = count;
		} else if (opr == '-') {
			total += prefix;
			prefix = -count;
		} else if (opr == '*') {
			prefix = prefix * count;
		} else if (opr == '/') {
			prefix = prefix / count;
		}
		return new int[] { total, prefix };
	}
}
