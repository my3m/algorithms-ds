package com.interviewcake.trees;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

import java.util.LinkedList;

public class BalancedBinaryTree {

    public static class BinaryTreeNode {

        public int value;
        public BinaryTreeNode left;
        public BinaryTreeNode right;
        public int d;

        public BinaryTreeNode(int value) {
            this.value = value;
        }
        
        public int getDepth() {
        	return this.d;
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

    public static boolean isBalanced(BinaryTreeNode treeRoot) {
        if(treeRoot == null)
            return true;
        // determine if the tree is superbalanced
//        Integer[] r = isBalancedHelperRec(treeRoot);
//        return !(r[0] == -1 && r[1] == -1);
        return isBalancedHelper(treeRoot);
    }
    
    /*
        2 
       /
      3
     / \
    4   8
   /
  5

    
    */
    
    
    /*
    
    [2.3], [-1,4]
    
        [2, 4], [3,5]
    
        1
       / \
      2   7
     / \   \
    3   4   8
   / \       \
  5   6       9
               \
                10
                
                
        6
       / \
      1   0
           \
            7
            
    /*
    
        1
       / \
      2   4
     / \   \
    3   7   5
         \   \
          8   6
               \
                9
	    1
	   / \
	  5   9
	     / \
	    8   5
	   /
	  7  
  
    */
    
    public static boolean isBalancedHelper(BinaryTreeNode root) {
    	if(root == null)
    		return true;
    	LinkedList<BinaryTreeNode> s = new LinkedList<>();
    	root.d = 0;
    	s.push(root);
    	int min = 0, max = 0;
    	while(s.size() > 0) {
    		BinaryTreeNode node = s.pop();
    		if(node.left == null && node.right == null) {
    			int depth = node.getDepth();
    			if(min == 0) {
    				min = depth;
    			}
    			else if(depth < min){
    				min = depth;
    			}    			
    			max = Math.max(max, depth);
    			if(Math.abs(min - depth) > 1 || Math.abs(max - depth) > 1)
    				return false;
    		}
    		if(node.right != null) {
    			node.right.d = node.getDepth() + 1;
    			s.push(node.right);
    		}
    		if(node.left != null) {
    			node.left.d = node.getDepth() + 1;
    			s.push(node.left);
    		}
    	}
    	return true;
    }
    
    public static Integer[] isBalancedHelperRec(BinaryTreeNode root) {
        if(root.left == null && root.right == null) {
            return new Integer[] { 1, 1 };
        }
        Integer[] left = null, right = null;
        if(root.left != null)
            left = isBalancedHelperRec(root.left);
        if(root.right != null)
            right = isBalancedHelperRec(root.right);
        if(left != null && left[0] == -1 || right != null && right[0] == -1)
            return new Integer[] { -1, -1 };
        if(left != null && right != null) {
            if(Math.abs(left[0] - right[1]) > 1 || Math.abs(right[0]-left[1]) > 1) {
                return new Integer[] {-1, -1};
            }
            else {
                return new Integer[] { Math.min(left[0], right[0]) + 1,
                                        Math.max(left[1], right[1]) + 1 };
            }
        }
        else {
            if(left != null) {
                return new Integer[] { left[0] + 1, left[1] + 1 };
            }
            else {
                return new Integer[] { right[0] + 1, right[1] + 1 };
            }
        }
    }



    // tests

    @Test
    public void fullTreeTest() {
        final BinaryTreeNode root = new BinaryTreeNode(5);
        final BinaryTreeNode a = root.insertLeft(8);
        final BinaryTreeNode b = root.insertRight(6);
        a.insertLeft(1);
        a.insertRight(2);
        b.insertLeft(3);
        b.insertRight(4);
        final boolean result = isBalanced(root);
        assertTrue(result);
    }

    /*
		
		3
	   / \
	  4   2
	 /     \
	1       9
		
		
     */
    
    @Test
    public void bothLeavesAtTheSameDepthTest() {
        final BinaryTreeNode root = new BinaryTreeNode(3);
        root.insertLeft(4).insertLeft(1);
        root.insertRight(2).insertRight(9);
        final boolean result = isBalanced(root);
        assertTrue(result);
    }

    @Test
    public void leafHeightsDifferByOneTest() {
        final BinaryTreeNode root = new BinaryTreeNode(6);
        root.insertLeft(1);
        root.insertRight(0).insertRight(7);
        final boolean result = isBalanced(root);
        assertTrue(result);
    }

    @Test
    public void leafHeightsDifferByTwoTest() {
        final BinaryTreeNode root = new BinaryTreeNode(6);
        root.insertLeft(1);
        root.insertRight(0).insertRight(7).insertRight(8);
        final boolean result = isBalanced(root);
        assertFalse(result);
    }

    @Test
    public void bothSubTreesSuperbalancedTest() {
        final BinaryTreeNode root = new BinaryTreeNode(1);
        root.insertLeft(5);
        final BinaryTreeNode b = root.insertRight(9);
        b.insertLeft(8).insertLeft(7);
        b.insertRight(5);
        final boolean result = isBalanced(root);
        assertFalse(result);
    }

 
    /*
    
        1
       / \
      2   7
     / \   \
    3   4   8
   / \       \
  5   6       9 
               \
                10
    
    */
    

    @Test
    public void threeLeavesAtDifferentLevelsTest() {
        final BinaryTreeNode root = new BinaryTreeNode(1);
        final BinaryTreeNode a = root.insertLeft(2);
        final BinaryTreeNode b = a.insertLeft(3);
        a.insertRight(4);
        b.insertLeft(5);
        b.insertRight(6);
        root.insertRight(7).insertRight(8).insertRight(9).insertRight(10);
        final boolean result = isBalanced(root);
        assertFalse(result);          
    }

    @Test
    public void onlyOneNodeTest() {
        final BinaryTreeNode root = new BinaryTreeNode(1);
        final boolean result = isBalanced(root);
        assertTrue(result);
    }

    /*
    
//        1
//       /
//      2
//     /
//    3
//   /
//  4
//    
//    */
//
//    @Test
//    public void treeIsLinkedListTest() {
//        final BinaryTreeNode root = new BinaryTreeNode(1);
//        root.insertRight(2).insertRight(3).insertRight(4);
//        final boolean result = isBalanced(root);
//        assertTrue(result);
//    }

//    public static void main(String[] args) {
//        Result result = JUnitCore.runClasses(BalancedBinaryTree.class);
//        for (Failure failure : result.getFailures()) {
//            System.out.println(failure.toString());
//        }
//        if (result.wasSuccessful()) {
//            System.out.println("All tests passed.");
//        }
//    }
}
