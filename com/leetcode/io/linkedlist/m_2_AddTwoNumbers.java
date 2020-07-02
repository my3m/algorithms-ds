package io.linkedlist;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;

import org.junit.Test;



/*

What if the the digits in the linked list are stored in non-reversed order? For example:

(3 -> 4 -> 2) + (4 -> 6 -> 5) = 8 -> 0 -> 7
(3→4→2)+(4→6→5)=8→0→7
	
 */
public class m_2_AddTwoNumbers {
    //2,4,2
    //4,6,5
    //7,0,7
	
    //342
    // 46
    
    //2-4-3
    //6-4-0
    
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
       
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;
        
        int carry = 0;
        
        //If there are two unequal lengths, this will be traversal
        //n + n-1 + n-2
        while(l1 != null || l2!=null) {
            int a = l1 != null ? l1.val : 0;
            int b = l2 != null ? l2.val : 0;
            
            int sum = carry + a + b;
            
            current.next = new ListNode(sum % 10);
            carry = sum / 10;

            current=current.next;
            
            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }
        
        //157 + 97
        
        //7,5,1
        //7,9,0
        //4,5,2
        
        if(carry > 0) {
            current.next = new ListNode(carry);
        }

        return dummyHead.next;
        //7,7,1
    }	
    
    public ListNode addTwoNumbersFirstAttempt(ListNode l1, ListNode l2) {
        LinkedList<Integer> s1 = new LinkedList<Integer>();
        LinkedList<Integer> s2 = new LinkedList<Integer>();
        while(l1 != null) 
        {
            s1.add(l1.val);
            l1 = l1.next;
        }
        while(l2 != null) 
        {
            s2.add(l2.val);
            l2 = l2.next;
        }  

        
        
        
        //7,2,0
        //0,5,1
        //7,7,1
        if(s1.size() > s2.size()) {
            while(s1.size() > s2.size()) {
                s2.add(0);
            }
        } else if(s2.size() > s1.size()) {
            while(s2.size() > s1.size()) {
                s1.add(0);
            }
        }
        
        //1,5,0
        //0,2,7
        
        //0,5,1
        //7,2,0
        
        //0,5,1
        //7,2,0
        
        //7,7,1
        
        
        
        int[] addResult = addTwoNumbers(s1.poll(), s2.poll(), 0);
        ListNode head = new ListNode(addResult[0]);
        ListNode ref = head;
        int carry = addResult[1];
        
        int length = s1.size();
        
        while(length > 0) {
            addResult = addTwoNumbers(s1.poll(), s2.poll(), carry);
            ref.next = new ListNode(addResult[0]);
            carry = addResult[1];
            ref = ref.next;
            length--;
        }
        if(carry > 0) {
            ref.next = new ListNode(carry);
        }
        return head;
        //7,7,1
    }
    
    
    //s1=9, s2=9, val=18, val/10=1, val%10=> 8
    //val/10 == 1, val%10=
    //139
    //119
    //008
    int[] addTwoNumbers(int a, int b, int carry) {
        int val = a + b + carry;
        if (val / 10 == 1) {
            return new int[] { val % 10, 1 };
        }
        else {
            return new int[] { val, 0 };
        }
    }
    
    @Test
    public void TestCarryBit() {
    	ListNode l1 = new ListNode(5);
    	ListNode l2 = new ListNode(5);
    	
    	ListNode expected = new ListNode(0);
    	expected.next = new ListNode(1);
    	
    	assertEquals(expected.toString(), addTwoNumbers(l1, l2).toString());
    }
    
    @Test
    public void TestUnequalSize() {
    	ListNode tmp = new ListNode(0);
    	ListNode l1 = tmp;
    	tmp.next = new ListNode(5);
    	tmp = tmp.next;
    	tmp.next = new ListNode(1);
    	tmp = tmp.next;
    	
    	ListNode tmp2 = new ListNode(7);
    	ListNode l2 = tmp2;
    	tmp2.next = new ListNode(2);
    	
    	ListNode ref = new ListNode(7);
    	ListNode expected = ref;
    	ref.next = new ListNode(7);
    	ref = ref.next;
    	ref.next = new ListNode(1);
    	
    	assertEquals(expected.toString(), addTwoNumbers(l1, l2).toString());
    }
    
    @Test
    public void TestAmbigiousExample() {
    	ListNode tmp = new ListNode(2);
    	ListNode l1 = tmp;
    	tmp.next = new ListNode(4);
    	tmp = tmp.next;
    	tmp.next = new ListNode(3);
    	tmp = tmp.next;
    	
    	ListNode tmp2 = new ListNode(5);
    	ListNode l2 = tmp2;
    	tmp2.next = new ListNode(6);
    	tmp2 = tmp2.next;
    	tmp2.next = new ListNode(4);
    	tmp2 = tmp2.next;
    	
    	ListNode ref = new ListNode(7);
    	ListNode expected = ref;
    	ref.next = new ListNode(0);
    	ref = ref.next;
    	ref.next = new ListNode(8);
    	
    	assertEquals(expected.toString(), addTwoNumbers(l1, l2).toString());
    }
}
