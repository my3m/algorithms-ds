package io.design;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

class Node {
	int key;
	Node next;
	
	public Node(int key) {
		this.key = key;
	}
}

class MyHashSet {

    float loadFactor = 0.75f;
    int capacity = 16;
    int size = 0;
    Node[] values = new Node[16];
    
    /** Initialize your data structure here. */
    public MyHashSet() {
        
    }
    
    void resize() {
        if(size > (loadFactor*capacity)) {
        	Node[] tmp = values;
            this.capacity = tmp.length * 2;
            this.values = new Node[this.capacity];
            for(Node head : tmp) {
               for(Node x = head; x != null; x = x.next) {
            	   add(x.key);
               }
            }
        }
    }
           
    int getHashCode(int value) {
        return value % 2069;
    }
    
    public void add(int key) {
        int hashCode = getHashCode(key);
        int idx = hashCode % capacity;
        
        if(values[idx] == null) {
        	values[idx] = new Node(key);
            size++;
            resize();        	
        }
        else {
        	//1->2->3
        	//1->
        	Node current = values[idx];
        	while(current.next != null && current.key != key) {
        		current = current.next;
        	}
        	if(current.key != key)
        		current.next = new Node(key);
        }
    }
    
    public void remove(int key) {
        int hashCode = getHashCode(key);
        int idx = hashCode % capacity;
        Node current = values[idx];
        if (current != null) {
        	//1->2->3
            
            if(current.key == key) {
                 values[idx] =  values[idx].next;
            }
            else {
                Node prev = current;
                current = current.next;
                while(current != null && current.key != key) {
                    prev = current;
                    current = current.next;
                }                
                
                //1->-2->3
                if(current != null)
                    prev.next = current.next;
            }
            
        	// Node prev = null;
        	// while(current != null && current.key != key) {
        	// 	prev = current;
        	// 	current = current.next;
        	// }
        	// if(prev == null)
        	// 	values[idx] = values[idx].next;
        	// else {
        	// if(current != null)
        	// 	    prev.next = current.next;
        	// }
        }
        
    }
    
    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int hashCode = getHashCode(key);
        int idx = hashCode % capacity;
        Node current = values[idx];
    	//1->2->3
    	while(current != null && current.key != key) {
    		current = current.next;
    	}
        return current != null && current.key == key;
    }
}

public class m_705_DesignHashSet {
	@Test
	public void Test1() {
		MyHashSet set = new MyHashSet();
		assertFalse(set.contains(72));
		set.remove(91);;
		set.add(48);
		set.add(41);
		assertFalse(set.contains(96));
	}
	
	@Test
	public void Test2() {
		MyHashSet set = new MyHashSet();
		assertFalse(set.contains(72));
		set.add(1);
		set.add(2);
		assertTrue(set.contains(1));
		assertFalse(set.contains(3));
		set.add(2);
		assertTrue(set.contains(2));
		set.remove(2);;
		assertFalse(set.contains(2));
	}
}
