package io.linkedlist;

import java.util.Stack;
class Node2 {
    public int val;
    public Node2 prev;
    public Node2 next;
    public Node2 child;
};
public class m_430_FlattenAMultiLevelDoublyLinkedList {
	   public Node2 flatten(Node2 head) {
	        return flatternHelper(head, new Stack<Node2>());
	    }
	   
	    public Node2 flatternHelperIteratively(Node2 head) {
	        Stack<Node2> s1 = new Stack<Node2>();
	        Node2 current = head;
	        Node2 previous = null;
	        while(current != null || s1.size() > 0) {
	            
	            if(current == null && s1.size() > 0) {
	                current = s1.pop();
	                previous.next = current;
	                current.prev = previous;
	            }

	            if(current.child != null) {
	                if(current.next != null)
	                    s1.push(current.next);
	                
	                current.next = current.child;
	                current.child.prev = current;
	                current.child = null;
	                current = current.next;
	            }
	            else {
	                previous = current;
	                current = current.next;
	            }
	        }
	        return head;
	    }	   
	    
	    //3->7->8->11->12->9->10
	    public Node2 flatternHelper(Node2 current, Stack<Node2> next) {
	        if(current == null)
	            return null;
	        //System.out.printf("current: %d\r\n", current.val);
	        if(current.child != null) {
	        	//dont add if the next is null
	        	//basically if it has a child node and the next node is pointing to null
	        	if(current.next != null)
	        		next.push(current.next);
	            Node2 next_result = flatternHelper(current.child, next);
	            current.next = next_result;
	            next_result.prev = current;
	            current.child = null;
	        }
	        else if(current.next != null) {
	            current.next = flatternHelper(current.next, next);
	        }
	        else {
	            if(next.size() > 0) {
	                Node2 next_result = flatternHelper(next.pop(), next);
	                current.next = next_result;
	                //why is null check is needed here?
	                if(next_result != null)
	                    next_result.prev = current;
	            }            
	        }
	        return current;
	    }
}
