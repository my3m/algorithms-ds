package com.interviewcake.trees;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import java.util.*;

import static org.junit.Assert.*;

public class BinarySearchTreeChecker {

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

    public static boolean isBinarySearchTree(BinaryTreeNode root) {

        // determine if the tree is a valid binary search tree
        //return isBinarySearchTree(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return isBinarySearchTreeIterative(root);

        //return false;
    }
    
    /*
      1    
       \
        2
       / \
          10
          / \
         3   30
      
    
    */
    
    public static boolean isBinarySearchTreeIterative(BinaryTreeNode root) {
        if(root == null)
            return true;
        LinkedList<Object[]> s = new LinkedList<>();
        s.push(new Object[] { root, null, null });
        while(s.size() > 0) {
            Object[] temp = s.pop();
            BinaryTreeNode node = (BinaryTreeNode)temp[0];
            Integer left = (Integer)temp[1], right = (Integer)temp[2];
            if(left != null && node.value <= left)
                return false;
            else if(right != null && node.value >= right)
                return false;
            if(node.right != null) {
                s.push(new Object[] { node.right, node.value, right });
            }
            if(node.left != null) {
                s.push(new Object[] { node.left, left, node.value });
            }            
        }
        return true;
    }
    
    public static boolean isBinarySearchTree(BinaryTreeNode root, int left, int right) {
        if(root == null)
            return true;
        if(root.value <= left || root.value >= right) {
            return false;
        }
        return isBinarySearchTree(root.left, left, root.value) &&
            isBinarySearchTree(root.right, root.value, right);
    }


















    // tests

    @Test
    public void validFullTreeTest() {
        final BinaryTreeNode root = new BinaryTreeNode(50);
        final BinaryTreeNode a = root.insertLeft(30);
        a.insertLeft(10);
        a.insertRight(40);
        final BinaryTreeNode b = root.insertRight(70);
        b.insertLeft(60);
        b.insertRight(80);
        final boolean result = isBinarySearchTree(root);
        assertTrue(result);
    }

    @Test
    public void bothSubtreesValidTest() {
        final BinaryTreeNode root = new BinaryTreeNode(50);
        final BinaryTreeNode a = root.insertLeft(30);
        a.insertLeft(20);
        a.insertRight(60);
        final BinaryTreeNode b = root.insertRight(80);
        b.insertLeft(70);
        b.insertRight(90);
        final boolean result = isBinarySearchTree(root);
        assertFalse(result);
    }

    @Test
    public void descendingLinkedListTest() {
        final BinaryTreeNode root = new BinaryTreeNode(50);
        root.insertLeft(40).insertLeft(30).insertLeft(20).insertLeft(10);
        final boolean result = isBinarySearchTree(root);
        assertTrue(result);
    }

    @Test
    public void outOfOrderLinkedListTest() {
        final BinaryTreeNode root = new BinaryTreeNode(50);
        root.insertRight(70).insertRight(60).insertRight(80);
        final boolean result = isBinarySearchTree(root);
        assertFalse(result);
    }

    @Test
    public void oneNodeTreeTest() {
        final BinaryTreeNode root = new BinaryTreeNode(50);
        final boolean result = isBinarySearchTree(root);
        assertTrue(result);
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(BinarySearchTreeChecker.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}