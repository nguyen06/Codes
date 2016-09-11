//package csc115a;

/*
 * Name: Ngoc Thinh Nguyen
 * ID: V00817304
 * Class: csc115
 * Assignment 5
 * Date: April 1, 2015
 */

import java.util.LinkedList;

/**
 * @author Mike Zastre, UVic
 *
 * Part of CSC 115, Spring 2015, Assignment #5
 */

public class BSTIterator implements java.util.Iterator<WordRefs> {
    private BSTRefBased t;
    private WordRefs currentItem;
    private LinkedList<WordRefs> list;

    public BSTIterator(BSTRefBased t) {
        this.t = t;
        currentItem = null;
        list = new LinkedList<WordRefs>();
        setInorder();
    }
	// check if the list is emptly, return true if not empty
    public boolean hasNext() {
    	 return !list.isEmpty();
    }
	
	// set the currentItem after removing 
    public WordRefs next() throws java.util.NoSuchElementException {
    	currentItem = list.remove();
        return currentItem;
    }

    public void remove() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    public void setPreorder() {
    	list.clear();
        preorder(t.getRoot());
    }

    public void setInorder() {
    	list.clear();
        inorder(t.getRoot());
    }

    public void setPostorder() {
    	list.clear();
        postorder(t.getRoot());
    }
	/*
	 * add the rood first, then go to left, then add the left
	 * keep the process util no left rood, then add the node from right
	 * recursive prcoess, and back the root.
	 */
    private void preorder(TreeNode node) {
    	if (node != null) {
            list.add(node.item);
            preorder(node.left);
            preorder(node.right);
        }
    }
	
	/*
	 * first move to the left, util the left can not move 
	 * then add the node list, recursive, back to the root, 
	 */
    private void inorder(TreeNode node) {
    	if (node != null) {
            inorder(node.left);
            list.add(node.item);
            inorder(node.right);
        }
    }
	/*
	 * first go to the left, util get the null, then go to right util get null
	 * add to the list, recursive back to root
	 */
    private void postorder(TreeNode node) {
    	if (node != null) {
            postorder(node.left);
            postorder(node.right);
            list.add(node.item);
        }
    }
}
