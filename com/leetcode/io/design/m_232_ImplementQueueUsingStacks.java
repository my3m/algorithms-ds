package io.design;

import java.util.Stack;

import org.junit.Test;

class MyQueue {

    Stack<Integer> s1 = new Stack<Integer>();
    Stack<Integer> s2 = new Stack<Integer>();
    Integer front;
    
    /** Initialize your data structure here. */
    public MyQueue() {
        //
    }
    

    /**
		[1,2,3] =>
					[1]
					[2]
					[3]
                    
        3,2,1
        1,2,3
     */
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        if(s1.size() == 0)
            front = x;
        s1.push(x);
    }
    
    /** Removes the element from in front of queue and returns that element. */
    //s2=3,2
    //s1=6,7
    public int pop() {
    	if(s2.size() == 0)
            while(s1.size() > 0)
                s2.push(s1.pop());
        return s2.pop();
    }
    
    /** Get the front element. */
    public int peek() {
        if(s2.size() > 0)
    	    return s2.peek();
        return front;
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return s1.size() == 0 && s2.size() == 0;
    }
}

public class m_232_ImplementQueueUsingStacks {

	@Test
	public void Test1() {
		
	}
}
