package io.playground;

import io.linkedlist.ListNode;

public class LinkedListDS {
	public static void main(String[] args) {
		System.out.println(3^3);
		ListNode l = new ListNode(new int[] { 1,2,3,4,5,6,7,8,9 });
		ListNode l2 = new ListNode(new int[] { 1,2,3,4,5,6,7,8,9,10 });
		//evenOdd(l);
		//findMiddle(l).print();
		//findMiddle(l2).print();
		//findMiddle(new ListNode(new int[] { 1, 2 })).print();
		partitionList(new ListNode(new int[] { 1, 2, 3, 4 }));
		//partitionList(l);
	}
	
	
	/*** Partion list, return 0 ... middle -1, middle .... null ***/
	public static ListNode partitionList(ListNode head) {
		//1->2->3->4->5->6->7->8->9->10
		//1->2->3->4->5
		//6->7->8->9->10
		
		//1->2
		
		if(head == null || head.next == null)
			return head;
		
		ListNode prev = findPreMiddle(head);
		ListNode left = head;
		ListNode right = prev.next;
		prev.next = null;
		left.print();
		right.print();
		partitionList(left);
		partitionList(right);
		return null;
	}
	
	public static ListNode findPreMiddle(ListNode head) {
		if(head == null || head.next == null)
			return head;
		ListNode slow = head, fast = head.next, prev = null;
		while(fast != null && slow != fast) {
			prev = slow;
			slow = slow.next;
			fast = fast.next != null ? fast.next.next : null;
		}
		return prev;
	}
	
	public static ListNode findMiddle(ListNode head) {
		if(head == null || head.next == null)
			return head;
		ListNode slow = head, fast = head.next;
		while(fast != null && slow != fast) {
			slow = slow.next;
			fast = fast.next != null ? fast.next.next : null;
		}
		return slow;
	}
	
	/** Ref odd-even LC question
	 */
	public static void evenOdd(ListNode head) {
		
		//*end on a odd node **/
		//1->2->3->4->5->6->7
		//odd=1,3,5,7, even=2,4,6,8
		//o=1->3,3->5
		//e=2->4,4->6
		
		//odd.next = even.next;
		//even.next = firstOdd
		
		//*end on a even node **/
		//1->2->3->4->5->6->7,8
		//odd=1,3,5,7, even=2,4,6,8
		//o=1->3,3->5,5->7
		//e=2->4,4->6,6->8
		
		//land on the last even node, and then connect it to first odd instance (head)
		//in both instances, if o.next = e.next, the answer will be correct
		//then point the tail of even to first odd (head)
		
		ListNode o = head;
		ListNode e = head.next;
		ListNode f = e;
		while(e != null && e.next != null && e.next.next != null) {
			o.next = e.next;
			o = o.next;
			e.next = o.next;
			e = e.next;
		}
		o.next = e.next;
		e.next = head;
		f.print();
	}
}
