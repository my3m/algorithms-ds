package io.linkedlist;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class m_21_MergeTwoSorrtedLists {
	
	
	/**
			Intuiton: Similar to merge two sorted arrays with usage of two ptr's
						Create a new node, and keep adding the next lowest value
							& keep updating the two ptr's
							
							Can you do this recursively?
	 */
	
	public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        
        while(l1 != null || l2 != null) {
            if(l1 != null && l2 != null) {
                if(l1.val <= l2.val) {
                    current.next = l1;
                    l1 = l1.next;
                }
                else {
                    current.next = l2;
                    l2 = l2.next;
                }
            }
            else if(l1 != null) {
                current.next = l1;
                l1 = l1.next;
            }
            else if(l2 != null) {
                current.next =l2;
                l2 = l2.next;
            }
            current = current.next;
        }
        
        return dummy.next;
    }
	
    //l1 = 3->5->9
    //l2 = 2->4->8
    //0->1->1->2->3->4->4
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode previous = dummy;
        
        while(l1 != null && l2 != null) {
        	if(l1.val >= l2.val) {
            	ListNode tmp = l2.next;
            	previous.next = l2;
            	l2.next = l1;
            	l2 = tmp;
            } else if (l1.val < l2.val) {
            	previous = l1;
            	l1 = l1.next;
            }
        }
        
        if(l1 == null)
        	previous.next = l2;
        else if(l2 == null)
        	previous.next = l1;
        
        return dummy.next;
    }
    
    //l1 = 12->14->18
    //l2 = 3->5->9
    //0->1->1->2->3->4->4
    public ListNode mergeTwoLists3(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode previous = dummy;
        
        while(l1 != null || l2 != null) {
        	
        	if(l1 == null) {
        		previous.next = l2;
        		break;
        	}
        	
        	if(l2 == null) {
        		previous = l1;
        		l1 = l1.next;
        	}
        		     	
        	if(l1 != null && l2 != null) {
	        	if(l1.val >= l2.val) {
	            	ListNode tmp = l2.next;
	            	previous.next = l2;
	            	l2.next = l1;
	            	l2 = tmp;
	            } else if (l1.val < l2.val) {
	            	previous = l1;
	            	l1 = l1.next;
	            }
        	}
        }
        
        return dummy.next;
    }    
    
    //1->5->6
    //7->14->18
    //recursive approach can result in a stack-overflow
    public ListNode mergeTwoListsR(ListNode l1 , ListNode l2) {
    	if(l1 == null)
    		return l2;
    	else if(l2 == null)
    		return l1;
		//2->3->4->7
    	if(l1.val < l2.val) {
    		l1.next = mergeTwoListsR(l1.next, l2);
    		return l1;
    	} else {
    		l2.next = mergeTwoListsR(l1, l2.next);
    		return l2;
    	}
    }
    
    //2, 
    @Test
    public void Test1() {
    	ListNode l1 = new ListNode(new int[] { 2, 7, 10 , 900});
    	ListNode l2 = new ListNode(new int[] { 3, 4, 8, 12, 18 });
    	
    	ListNode l3 = mergeTwoListsR(l1, l2);
    	System.out.println(l3);
    }
}
