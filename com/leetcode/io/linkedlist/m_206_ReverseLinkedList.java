package io.linkedlist;

import java.util.Stack;

public class m_206_ReverseLinkedList {
	
    public ListNode reverseList(ListNode head) {
        if(head == null)
            return null;
        //return reverseListIterative(head);
        return reverseListRecursive(head, null);
    }
    
    public ListNode reverseListRecursive(ListNode current, ListNode prev) {
        ListNode next = current.next;
        current.next = prev;
        if(next != null)
            current = reverseListRecursive(next, current);
        return current;
    }  	
	
    public ListNode reverseListIterative(ListNode head) {
        ListNode ref = head;
        ListNode prev = null;
        
        //Make sure 1 is pointing to null
        //if(prev != null) prevented that, and 1 is still pointing to 2, and 2 is pointing to 1
        while(ref != null) {
            ListNode tmp = ref.next;
            ref.next = prev;
            prev = ref;
            ref = tmp;
        }
        
        return prev;
    }
	
	/**
		You didn't have to create a stack. Simply reverse linked list
		by changing pointer to previous node.
		Make sure tail (old head) is pointing to null
	 */
    public ListNode reverseListFirstAttempt(ListNode head) {
	    Stack<Integer> s1 = new Stack<Integer>();
	    ListNode current = head;
	    while(current != null) {
	        s1.push(current.val);
	        current = current.next;
	    }
	    
	    ListNode dummyHead = new ListNode(0);
	    ListNode ref = dummyHead;
	    while(s1.size() > 0) {
	        ref.next = new ListNode(s1.pop());
	        ref = ref.next;
	    }
	    return dummyHead.next;   
    }
}
