package io.design;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class m_706_DesignHashMap {
	class Node {
	    public Node next;
	    public int key;
	    public int value;
	    public int hashCode;
	    
	    public Node(int hashCode, int key, int value) {
	        this.hashCode = hashCode;
	        this.key = key;
	        this.value = value;
	    }
	    
	    @Override
	    public String toString() {
			StringBuilder sb = new StringBuilder();
			Node ref = this;
			
			if(ref != null) {
				sb.append("[" + ref.key + ":" + ref.value + "]");
				ref = ref.next;
				while(ref != null) {
					sb.append("->[" + ref.key + ":" + ref.value + "]");
					ref = ref.next;
				}
			}
			return sb.toString();
	    }
	}

	class MyHashMap {
	    
	    //put= O(1)
	    //set= O(1)
	    
	    //map the hashcode to an index in the array
	    //hashcode % arr.length
	    
	    Node[] dictionary = null;
	    int size = 0;
	    int capacity = 16;
	    float loadFactor = 0.75f;

	    /** Initialize your data structure here. */
	    public MyHashMap() {
	        this.dictionary = new Node[capacity];
	    }
	    
	    /**
	        Initializes or doubles table size.
	    **/
	    Node[] resize() {
	        if(this.size > (this.loadFactor*this.capacity)) {
	        	/*
	            int nsize = this.dictionary.length * 2;
	            Node[] newDictionary = new Node[nsize];
	            for(Node e : dictionary) {
	                if(e != null) {
	                    //Node p = e;
	                    int idx = e.hashCode % nsize;
	                    // while (p != null) {
	                    //     //p.hashCode = getHashCode(p.key);
	                    //     p = p.next;
	                    // }
	                    newDictionary[idx] = e;
	                }
	            }
	            this.dictionary = newDictionary;
	            this.capacity = nsize;
	        }
	        return this.dictionary;
	        */
	        	Node[] tmp = dictionary;
	        	
	        	this.capacity = tmp.length * 2;
	        	this.dictionary = new Node[this.capacity];
	        	for(Node head : tmp) {
	        		for(Node x = head; x != null; x = x.next) {
	        			put(x.key, x.value);
	        		}
	        	}
	        }
	        return this.dictionary;
	    }
	    
	    public int getHashCode(int key) {
	        return key % 2069;
	    }
	    
	    /** value will always be non-negative. */
	    //cases. new key, if it already exists
	    public void put(int key, int value) {
	        int hashCode = getHashCode(key);
	        int index = hashCode % this.dictionary.length;
	        if(dictionary[index] == null) {
	            dictionary[index] = new Node(hashCode, key, value);
	        }
	        else {
	            Node p = dictionary[index];
	            //1->2->3->4
	            Node prev = null;
	            //incase of collision, we will compare with keys
	            while(p != null && p.key != key) {
	            	prev = p;
	            	p = p.next;
	            }
	            if(p == null)  {
	            	prev.next = new Node(hashCode, key, value);
	            }
	            else {
	            	p.value = value;
	            }
	        }
	        size++;
	        resize();
	    }
	    
	    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
	    public int get(int key) {
	        int hashCode = getHashCode(key);
	        int index = hashCode % this.dictionary.length;        
	        Node current = dictionary[index];
	        if(current == null)
	            return - 1;
	        else {
	            //1->2->3
	            while(current != null && current.key != key)
	                current = current.next;
	            if(current != null)
	                return current.value;
	        }
	        return -1;
	    }
	    
	    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
	    public void remove(int key) {
	        int hashCode = getHashCode(key);
	        int index = hashCode % dictionary.length;      
	        Node current = dictionary[index];
	        if(current != null) {
	            if(current.key == key) {
	                dictionary[index] = current.next; 
	            }
	            else 
	            {
	                //1->2->3->4->5
	                Node prev = current;
	                current = current.next;
	                while(current != null && current.key != key) {
	                    prev = current;
	                    current = current.next;
	                }
	                if(current != null) {
	                    prev.next = current.next;
	                }
	            }
	        }
	    }
	}
	
	@Test
	public void TestCase1() {
		MyHashMap hashMap = new MyHashMap();
		hashMap.put(1, 1);     
		hashMap.put(2070, 20);  
		hashMap.put(6208, 22);  
		hashMap.put(2, 2);         
		assertEquals(1, hashMap.get(1));            		// returns 1
		assertEquals(20, hashMap.get(2070));  
		assertEquals(22, hashMap.get(6208));  
		assertEquals(-1, hashMap.get(3));           	 // returns -1 (not found)
		hashMap.put(2, 1);          					// update the existing value
		assertEquals(1,hashMap.get(2));           	 // returns 1 
		hashMap.remove(2);          				// remove the mapping for 2
		assertEquals(-1,hashMap.get(2));            // returns -1 (not found) 
		hashMap.remove(2070);
		assertEquals(-1, hashMap.get(2070));  
		assertEquals(22, hashMap.get(6208)); 
		assertEquals(1, hashMap.get(1)); 
		hashMap.remove(1);
		assertEquals(22, hashMap.get(6208));
		
	}
	
	@Test
	public void TestCase2() {
		MyHashMap hashMap = new MyHashMap();
		for(int i = 0; i < 100; i++) {
			hashMap.put(i, i);
		}
		for(int i = 0; i < 100; i++) {
			assertEquals(i, hashMap.get(i));
		}		
	}
	
}
