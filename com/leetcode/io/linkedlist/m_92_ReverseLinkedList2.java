package io.linkedlist;

import org.junit.Test;

public class m_92_ReverseLinkedList2 {
	public ListNode reverseBetween(ListNode head, int m, int n) {
        /**
        Intuiton:
            Pointers=
            	current=set to the mth node
            	prev=ser to mth - 1 node
            	
            	move current to mth node,
            	start reversing till nth node,
            	
            	partition list
            	link tail & connecting
            	
    
         */
    
		if (head == null)
			return null;
		if (m == n)
			return head;
		ListNode current = head, prev = null;
		int k = 1;
		while (k < m) {
			prev = current;
			current = current.next;
			k++;
		}

		ListNode start = prev;

		ListNode tail = current;

		while (k <= n) {
			ListNode next = current.next;
			current.next = prev;
			prev = current;
			current = next;
			k++;
		}

		if (start != null) {
			start.next = prev;
		} else {
			head = prev;
		}

		tail.next = current;

		return head;
    }
	
	@Test
	public void Test1() {
		ListNode head = new ListNode(1);
		ListNode tmp = head;
		tmp.next = new ListNode(2);
		tmp = tmp.next;
		tmp.next = new ListNode(3);
		tmp = tmp.next;
		tmp.next = new ListNode(4);
		tmp = tmp.next;
		tmp.next = new ListNode(5);
		
		System.out.println(head.toString());
		
		reverseBetween(head, 3, 4);
	}
}
