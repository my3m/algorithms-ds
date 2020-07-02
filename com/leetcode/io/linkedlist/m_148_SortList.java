package io.linkedlist;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class m_148_SortList {
	public ListNode sortList(ListNode head) {
		// base case
		if (head == null || head.next == null)
			return head;

		ListNode prev = null, slow = head, fast = head;
		while (fast != null && fast.next != null) {
			prev = slow;
			fast = fast.next.next;
			slow = slow.next;
		}

		// System.out.println(slow.val);
		// System.out.println(prev.val);
		// System.out.println();
		// 5.next = null;
		prev.next = null;

		ListNode l1 = sortList(head);
		ListNode l2 = sortList(slow);

		return merge(l1, l2);
		// 8->9,6->7
		// 6->7->8->9
		// 4-5>, 1, 2->3
		// 1->2->3->4->5
		// 6->7->8->9, 1->2->3->4->5
	}

	public ListNode merge(ListNode l1, ListNode l2) {

		if (l1 == null)
			return l2;
		else if (l2 == null)
			return l1;

		ListNode dummy = new ListNode(0), merged = dummy;
		// 2->5->7->12
		// 16->4->6->9->14

		while (l1 != null && l2 != null) {
			if (l1.val <= l2.val) {
				ListNode next = l1.next;
				merged.next = l1;
				l1 = l1.next;
			} else {
				merged.next = l2;
				l2 = l2.next;
			}
			merged = merged.next;
		}
		while (l1 != null) {
			merged.next = l1;
			l1 = l1.next;
			merged = merged.next;
		}
		while (l2 != null) {
			merged.next = l2;
			l2 = l2.next;
			merged = merged.next;
		}
		return dummy.next;
	}
	
	@Test
	public void Test1() {
		ListNode head = new ListNode(new int[] { 9,8,7,6,5,4,3,2,1});
		ListNode expected = new ListNode(new int[] {1,2,3,4,5,6,7,8,9});
		
		assertEquals(expected.toString(), sortList(head).toString());
	}
	
	@Test
	public void Test2() {
		int[] arr = new int[] { 4,3,1,3,8,5,2,8,3,8,33,5,7,2};
		ListNode head = new ListNode(arr);
		Arrays.sort(arr);
		ListNode expected = new ListNode(arr);
		
		assertEquals(expected.toString(), sortList(head).toString());
	}
}
