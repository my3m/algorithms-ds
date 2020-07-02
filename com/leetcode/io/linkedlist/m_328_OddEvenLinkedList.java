package io.linkedlist;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class m_328_OddEvenLinkedList {
    public ListNode oddEvenList(ListNode head) {
        if(head == null)
            return null;
        //1->2->3
        
        //1->2
        //1->null
        //current = 2
        //break
        //1->2->3->4->5
        ListNode odd = head, even = head.next, evenStart = head.next;
        
        //WE WANT TO END ON A ODD PTR, SO CHECK CONDITION EVEN != NULL && EVEN.NEXT != NULL
        while(odd != null && odd.next != null && even != null && even.next !=null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        // if(even != null)
        //     even.next = null;
        // System.out.println(head.next.val);
        odd.next = evenStart;
        return head;
    }
    
    @Test
    public void EvenSizeList() {
    	ListNode l1 = new ListNode(new int[] {1, 2, 3, 4 });
    	ListNode exp = new ListNode(new int[] { 1, 3, 2, 4});
    	
    	assertEquals(exp.toString(), oddEvenList(l1).toString());
    }
    
    @Test
    public void OddSizeList() {
    	ListNode l1 = new ListNode(new int[] {1, 2, 3, 4, 5 });
    	ListNode exp = new ListNode(new int[] { 1, 3, 5, 2, 4});
    	
    	assertEquals(exp.toString(), oddEvenList(l1).toString());
    }
}
