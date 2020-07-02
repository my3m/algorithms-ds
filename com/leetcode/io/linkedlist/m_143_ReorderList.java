package io.linkedlist;

import java.util.Stack;

import org.junit.Test;

public class m_143_ReorderList {
	
    public void reorderList(ListNode head) {
        
        if(head == null || head.next == null)
            return;
        
        /*
            l0, ln, l1, ln-1, l2, ln-2
            
            1->2->3->4
            
            @4, 1->4->2, left=2
            @3, 1->4->2->3
            
            Intuiton= using a stack, we can simulate moving back in a linked list.
            			using the start node, maniuplate pointers to achive result
            				only for stack.size()/2 nodes. Set the last node next to null
        */
        
	    Stack<ListNode> s1 = new Stack<ListNode>();
	    ListNode ref = head;
	    ListNode left = head;
	    while(ref != null) {
	        s1.push(ref);
	        ref = ref.next;
	    }

        int count = s1.size();
	    while(s1.size() != count/2) {
	        ListNode current = s1.pop();
            
	        ListNode left_ref = left.next;
	        left.next = current;
	        left.next.next = left_ref;
	        left = left.next.next;
	    }
        left.next = null;
    }
	
    public ListNode recurse(ListNode start, ListNode current) {
        ListNode left = start;
        if(current.next != null)
            left = recurse(start, current.next);
        
        System.out.println(left.toString());
        if(left.next == current) {
            left.next.next = null;
        }
        
        ListNode ref = left.next;
        left.next = current;
        left.next.next = ref;
        //1->4->2
        
        return left.next.next;
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
		
		System.out.println(5/2);
		reorderList(head);
		System.out.println(head.toString());
	}
}
