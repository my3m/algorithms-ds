package io.linkedlist;

import java.util.HashMap;
import java.util.Map;

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

/*
 * Cannot create more than 1 copy for each node in the list.
 * Deep Copy
 */
public class m_138_CopyListWithRandomPointer {
    public Node copyRandomList(Node head) {
        if(head == null)
            return null;
        
        return copyRecursive(head, new HashMap<Node, Node>());
    }
    
    public Node copyRandom1Space(Node head) {
        
        Node current = head;
        while(current != null) {
            //Keep store of reference of next ptr
            Node next = current.next;
            //create a clone of current
            Node clone = new Node(current.val);
            current.next = clone;
            clone.next = next;
            current = next;
        }
        
        current = head;
        while(current != null) {
            Node currentRandom = current.random;
            Node clonedNode = current.next;
            if(currentRandom != null)
                clonedNode.random = currentRandom.next;
            current = clonedNode.next;
        }
        
        Node clonedHead = head.next;
        //unweave the nodes
        current = head;
        while(current != null) {
            Node clonedNode = current.next;
            Node next = clonedNode.next;
            clonedNode.next = next != null ? next.next : null;
            current.next = next;
            current = next;
        }
        
        return clonedHead;
    }
    
    public Node copyRandomListIterative(Node head) {
        if(head == null)
            return null;
        
        Map<Node, Node> map = new HashMap<Node, Node>();        
        for(Node curr = head; curr != null; curr = curr.next) {
            map.putIfAbsent(curr, new Node(curr.val));
        }
        
        Node ref = head;
        Node current = map.get(head);
        while(ref != null) {
            current.next = map.get(ref.next);
            current.random = map.get(ref.random);
            ref = ref.next;
            current = current.next;
        }
        return map.get(head);
    }    
    
    public Node copyRecursive(Node ref, Map<Node, Node> map) {
        if(ref == null)
            return null;
        if(map.containsKey(ref)) {
            return map.get(ref);
        }
        Node current = new Node(ref.val);
        map.put(ref, current);
        current.random = copyRecursive(ref.random, map);
        current.next = copyRecursive(ref.next, map);
        return current;
    }
}
