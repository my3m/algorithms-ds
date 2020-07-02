package io.linkedlist;

import org.junit.Test;

public class m_24_SwapNodesInPairs {
    public ListNode swapPairs(ListNode head) {
        //1->2->3->4->5
        //prev = null
        //2's next is 3
        //2->1
        //1->3
        
        //2->1->3->4->5
        //prev=1
        //next = 5
        //prev.next = 4
        //4->3
        //3->5
        //current = 5
        
        //2->1->3->4->5
        //when we finish with 1,
        //current = current.next.next;
        
        //1->2->3->4->5
        //2->1->3->4->5
        if(head == null)
            return null;
        
        ListNode b = head.next, a = head, prev = null;
        ListNode ans = b;
        while(b != null) {
            ListNode next_b = b.next;
            a.next = next_b;
            b.next = a;
            if(prev != null)
                prev.next = b;
            prev = a;
            a = a.next;
            b = a != null ? a.next : null;
        }
        return ans;
    }
    
    //EdgeCases: null node, 1 node
    @Test
    public void Test1() {
    	ListNode c =swapPairs(new ListNode(new int[] { 1, 2, 3, 4, 5 }));
    	System.out.println(c);
    }
}
