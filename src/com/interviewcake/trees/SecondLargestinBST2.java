package com.interviewcake.trees;

import org.junit.Test;

import static org.junit.Assert.*;

public class SecondLargestinBST2 {

    public static class BinaryTreeNode {

        public int value;
        public BinaryTreeNode left;
        public BinaryTreeNode right;

        public BinaryTreeNode(int value) {
            this.value = value;
        }

        public BinaryTreeNode insertLeft(int leftValue) {
            this.left = new BinaryTreeNode(leftValue);
            return this.left;
        }

        public BinaryTreeNode insertRight(int rightValue) {
            this.right = new BinaryTreeNode(rightValue);
            return this.right;
        }
    }

    public static int findSecondLargest(BinaryTreeNode rootNode) {

        // find the second largest item in the binary search tree
        /*
        
    case where parent has left child
    x.parent.left - continue traversing to the right
    
    	12
       /
      10
     /
    7
   /
  4
        
            6
           / \
          2   7
               \
                8
                 \
                 20
                /
               10
                \
                 12
                
    case where parent has no left child
            3
           / \
          2   4
               \
                5        
                
			    	12
			       /
			      10
			     /
			    7
			   /
			  4                        
                
        */
        return find(rootNode, null, true);
    }
    
    public static int find(BinaryTreeNode node, BinaryTreeNode parent, boolean isParentValue) {
        if(node.right != null) {
            return find(node.right, node, isParentValue);
        }
        else {
        	if(isParentValue) {        		
	            if(node.left != null) {
	                return find(node.left, node, false);
	            }
	            else {
	                return parent.value;
	            }
        	}
        	return node.value;
        }
    }

//    @Test
//    public void TestHappyCase() {
//    	BinaryTreeNode root = new BinaryTreeNode(5);
//    	root.insertRight(6).insertRight(7).insertRight(8).insertRight(10).insertRight(12);
//    	assertEquals(10, findSecondLargest(root));
//    }
    
    /*
            6
           / \
          2   7
               \
                8
                 \
                 20
                /
               10
                \
                 12
     */
    
//    @Test
//    public void Test2() {
//    	BinaryTreeNode root = new BinaryTreeNode(5);
//    	root.insertLeft(2);
//    	BinaryTreeNode a = root.insertRight(6).insertRight(7).insertRight(8);
//    	BinaryTreeNode b = a.insertRight(20);
//    	b.insertLeft(10).insertRight(12);
//    	assertEquals(12, findSecondLargest(root));
//    }    
    
    /*
			    	12
			       /
			      10
			     /
			    7
			   /
			  4
     */
    
    @Test
    public void Test3() {
    	BinaryTreeNode root = new BinaryTreeNode(12);
    	root.insertLeft(10).insertLeft(7).insertLeft(4);
    	assertEquals(10, findSecondLargest(root));
    }
    
//    @Test
//    public void descendingLinkedListTest() {
//        final BinaryTreeNode root = new BinaryTreeNode(50);
//        root.insertLeft(40).insertLeft(30).insertLeft(20);
//        final int actual = findSecondLargest(root);
//        final int expected = 40;
//        assertEquals(expected, actual);
//    }
}


