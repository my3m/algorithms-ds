package io.linkedlist;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Test;

/**
	Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
	Output: 7 -> 8 -> 0 -> 7

	Intuiton: Add both inputs to a stack ds. Perform addition using stack.pop() 
				Construct combined sum from tail upwards using references.
 */
public class m_445_AddTwoNumbers2 {
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		Stack<Integer> s1 = new Stack<Integer>(), s2 = new Stack<Integer>();

		while (l1 != null) {
			s1.push(l1.val);
			l1 = l1.next;
		}

		while (l2 != null) {
			s2.push(l2.val);
			l2 = l2.next;
		}
	        
        /*
            s1= 3   s2= 4
                4       6
                2       5
                7
        
        
        */
	        
		// ListNode dummyTail = new ListNode(0);
		// 7->8->0->7
		ListNode current = null;
		int carry = 0;

		while (s1.size() > 0 || s2.size() > 0) {
			int a = s1.size() > 0 ? s1.pop() : 0;
			int b = s2.size() > 0 ? s2.pop() : 0;
			int sum = a + b + carry;
			carry = sum / 10;
			ListNode prev = new ListNode(sum % 10);
			prev.next = current;
			current = prev;
		}
		if (carry > 0) {
			ListNode prev = new ListNode(carry);
			prev.next = current;
			current = prev;
		}
		return current;
	}
	
	@Test
	public void Test1() {
		ListNode l1 = new ListNode(7);
		ListNode l1_ref = l1;
		l1_ref.next = new ListNode(2);
		l1_ref = l1_ref.next;
		l1_ref.next = new ListNode(4);
		l1_ref = l1_ref.next;
		l1_ref.next = new ListNode(3);
		
		ListNode l2 = new ListNode(5);
		ListNode l2_ref = l2;
		l2_ref.next = new ListNode(6);
		l2_ref = l2_ref.next;
		l2_ref.next = new ListNode(4);
		
		ListNode expected = new ListNode(7);
		ListNode ref = expected;
		ref.next = new ListNode(8);
		ref = ref.next;
		ref.next = new ListNode(0);
		ref = ref.next;
		ref.next = new ListNode(7);
		
		assertEquals(expected.toString(), addTwoNumbers(l1, l2).toString());
	}
}
