package io.linkedlist;

import java.util.Stack;

public class p_ReversalPractice {
	public static void main(String[] args) {
		ListNode x = new ListNode(new int[] { 1, 2, 3, 4, 5});
		//x.print();
		//reverseLinkedListIterative(x).print();
		//reverseLinkedListIterativeStack(x).print();
		//reverseLinkedListRecursive(x).print();
		//reverseLinkedListRecursive2(x, null).print();
		ListNode y = new ListNode(new int[] { 1, 2, 3, 4, 5, 6, 7});
		y.print();
		//reversePartialLinkedList(y, 2, 4).print();
		//reversePartialLinkedList(y, 1, 4).print();
		removeKthNodeFromEnd(y, 1);
		y.print();
	}
	
	//1->2, k=2
	//1->2->3->4->5, k=2
	static int removeKthNodeFromEnd(ListNode current, int k) {
		if(current.next != null) {
			k = removeKthNodeFromEnd(current.next, k);
			if(k == 1) {
				ListNode ref = current.next.next;
				current.next = ref;
			}
			return k - 1;
		}
		return k;
	}
	
	//1->2->3->4-5->6->7    m=2, n=4
	//1->4->3->2->5->6->7
	static ListNode reversePartialLinkedList(ListNode head, int m, int n) {		
		ListNode current = head, prev = null;
		//Move current ptr to the mth node
		int k = 1;
		while(k < m) {
			prev = current;
			current = current.next;
			k++;
		}
		
		ListNode connectingNode = prev, tail = current; //conn=1, tail=2
		
		//reverse from mth node to nth node
		while(k <= n) {
			ListNode next = current.next;
			current.next = prev;
			prev = current;
			current = next;
			k++;
		}
		
		//4->3->2->1
		if(connectingNode != null) {
			connectingNode.next = prev;
		} else {
			head = prev;
		}
		tail.next = current;
		
		return head;
	}
	
	static ListNode reverseLinkedListRecursive2(ListNode current, ListNode previous) {
		ListNode next = current.next;
		current.next = previous;
		if(next != null) {
			current = reverseLinkedListRecursive2(next, current);
		}
		return current;
	}
	
	//1->2->3->4->5
	//I've lost reference to original tail of the list
	static ListNode reverseLinkedListRecursive(ListNode current) {
		if(current.next != null) {
			ListNode next = current.next;
			current.next = null;
			ListNode previous = reverseLinkedListRecursive(next);
			previous.next = current;
		}
		return current;
	}
	
	static ListNode reverseLinkedListIterative(ListNode head) {
		ListNode current = head;
		
		ListNode previous = null;
		while(current != null) {
			ListNode next = current.next;
			current.next = previous;
			previous = current;
			current = next;
		}
		return previous;
	}
	
	static ListNode reverseLinkedListIterativeStack(ListNode head) {
		if(head == null)
			return null;
		Stack<ListNode> s1 = new Stack<ListNode>();
		ListNode current = head, tail = null;
		while(current != null) {
			s1.push(current);
			current = current.next;
		}
		tail = s1.peek();			
		//1->2->3->4->5
		ListNode previous = null;
		while(s1.size() > 0) {
			current = s1.pop();
			if(previous != null) {
				previous.next = current;
			}
			previous = current;
		}
		previous.next = null;
		return tail;
	}
}
