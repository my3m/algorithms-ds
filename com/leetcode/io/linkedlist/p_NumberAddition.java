package io.linkedlist;

import java.util.Stack;

public class p_NumberAddition {
	public static void main(String[] args) {

		ListNode first = new ListNode(new int[] { 2, 4, 3 });
		ListNode second = new ListNode(new int[] { 5, 6, 4 });

		addTwoNumbers(first, second).print();
		
		first = new ListNode(new int[] { 9, 7 });
		second = new ListNode(new int[] { 6, 3 });
		addTwoNumbers2(first, second).print();
	}
	
	static ListNode addTwoNumbers2(ListNode first, ListNode second) {
		Stack<ListNode> s1 = new Stack<ListNode>();
		Stack<ListNode> s2 = new Stack<ListNode>();

		while (first != null) {
			s1.push(first);
			first = first.next;
		}

		while (second != null) {
			s2.push(second);
			second = second.next;
		}

		ListNode previous = null, current = null;
		int carry = 0;
		while (s1.size() > 0 || s2.size() > 0) {
			int a = s1.size() > 0 ? s1.pop().val : 0;
			int b = s2.size() > 0 ? s2.pop().val : 0;

			int sum = carry + a + b;

			current = new ListNode(sum % 10);
			//no need for null-check, last digit will always point to null
			if (previous != null) {
				current.next = previous;
			}
			carry = sum / 10;

			previous = current;
		}
		//DONT FORGET CARRY
		if(carry > 0) {
			current = new ListNode(carry);
			current.next = previous;
		}
		return current;
	}
	
	static ListNode addTwoNumbers(ListNode first, ListNode second) {
		// 342
		// 465
		// 807
		
		//0->7
		
		ListNode dummy = new ListNode(0);
		ListNode current = dummy;
		
		int carry = 0;
		while(first != null || second != null) {
			
			int a = first == null ? 0 : first.val;
			int b = second == null ? 0 : second.val;
			
			int sum = carry + a + b;
			
			current.next = new ListNode(sum % 10);
			carry = sum / 10;
			
			current = current.next;
			first = first.next;
			second = second.next;
		}
		
		if(carry > 0)
			current.next = new ListNode(carry);
		
		return dummy.next;		
	}
}
