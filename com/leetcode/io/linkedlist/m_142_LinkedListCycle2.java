package io.linkedlist;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class m_142_LinkedListCycle2 {
	
	//Using O(n) memory
    public ListNode detectCycle2(ListNode head) {
        ListNode ref = head;
        Set<ListNode> visited = new HashSet<ListNode>();
        while(ref != null && visited.add(ref)) 
            ref = ref.next;
        return ref == null? null : ref;
    }
    
    
    public ListNode detectCycle(ListNode head) {
        
        if(head == null)
            return null;
        
        ListNode slow = head;
        ListNode fast = head.next;
        
        while(fast != null && fast.next != null && fast != slow) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        if(slow == fast) {  
            //There exists a cycle
        	//check each node for potential start
            ListNode potentialStart = head;
            while(potentialStart != fast) {
                /** Set current to fast.next, 
                        check for current != fast for a cycle
                        check for current != potentialStart for start of cycle
                */
                ListNode current = fast.next;
                while(current != potentialStart && current != fast) {
                    current = current.next;
                }
                if(current == potentialStart)
                    return potentialStart;
                //check for next potential start cycle
                potentialStart = potentialStart.next;
            }
            //the potential can be start of the cycle
            if(potentialStart == fast)
                return fast;
        }
        return null;
    }
    
    @Test
    public void Test1() {
		ListNode head = new ListNode(3);
		ListNode tmp = head;
		tmp.next = new ListNode(2);
		ListNode startCycle = tmp.next;
		tmp = tmp.next;
		tmp.next = new ListNode(0);
		tmp = tmp.next;
		tmp.next = new ListNode(-4); 
		tmp.next.next = startCycle;
		detectCycle(head);
    }
    
    //@Test
    public void TestOneNodeCycle() {
		ListNode head = new ListNode(-1);
		ListNode tmp = head;
		tmp.next = new ListNode(-7);
		tmp = tmp.next;
		tmp.next = new ListNode(7);
		tmp = tmp.next;
		tmp.next = new ListNode(-4);
		tmp = tmp.next;
		tmp.next = new ListNode(19);   
		tmp = tmp.next;
		tmp.next = new ListNode(6);
		tmp = tmp.next;
		tmp.next = new ListNode(-9);
		tmp = tmp.next;
		tmp.next = new ListNode(5);
		tmp = tmp.next;
		tmp.next = new ListNode(-2);
		tmp = tmp.next;
		tmp.next = new ListNode(-5);
		tmp.next.next = tmp.next;
		
		detectCycle(head);
    }
}
