package io.linkedlist;

public class p_FindStartOfCycle {
	public static void main(String[] args) {
		
	}
	
	static ListNode findStartCycle(ListNode head) {
		
		if(head == null)
			return null;
		
		ListNode slow = head;
		ListNode fast = head.next;
		
		while(fast != null && fast.next != null && fast != slow) {
			slow = slow.next;
			fast = fast.next.next;
		}
		
		if(slow == fast) {
			ListNode startCycleNode = head;		
			//start exists in between head.......fast
			while(startCycleNode != fast) {
				ListNode current = fast.next;
				while(current != startCycleNode && current != fast) {
					current = current.next;
				}
				if(startCycleNode == current)
					return startCycleNode;
				startCycleNode = startCycleNode.next;
			}
			return fast;
		}
		return null;
	}
}
