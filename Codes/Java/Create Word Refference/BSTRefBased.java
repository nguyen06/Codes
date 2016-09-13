//package csc115a;
/*
 * Name: Ngoc Thinh Nguyen
 * Class: csc115
 * Assignment 5
 * Date: April 1, 2015
 */

import java.util.Iterator;

public class BSTRefBased extends AbstractBinaryTree 
    implements Iterable<WordRefs>
{
    private TreeNode root;


    public BSTRefBased() {
        root = null;
    }


    public BSTRefBased(WordRefs item,
        AbstractBinaryTree left,
        AbstractBinaryTree right)
    {
        root = new TreeNode(item, null, null);
        if (left != null) {
            attachLeftSubtree(left);
        }

        if (right != null) {
            attachRightSubtree(right);
        }
    }


    public boolean isEmpty() {
        return (root == null);
    }


    public void makeEmpty() {
        root = null;
    }


    protected TreeNode getRoot() {
        return root;
    }


    protected void setRoot(TreeNode r) {
        this.root = r;
    }


    public WordRefs getRootItem() throws TreeException {
        if (root == null) {
            throw new TreeException("getRootItem() on empty tree");
        }

        return root.item;
    }


    public void setRootItem(WordRefs item) {
        if (root == null) {
            root = new TreeNode(item);
        } else {
            root.item = item;
        }
    }


    public void attachLeft(WordRefs item) throws TreeException {
        if (isEmpty()) {
            throw new TreeException("attachLeft to empty tree");
        }

        if (!isEmpty() && root.left != null) {
            throw new TreeException("attachLeft to occupied left child");
        }

        root.left = new TreeNode(item, null, null);

        return;
    } 


    public void attachLeftSubtree(AbstractBinaryTree left) {
        if (isEmpty()) {
            throw new TreeException("attachLeftSubtree to empty tree");
        }

        if (!isEmpty() && root.left != null) {
            throw new 
                TreeException("attachLeftSubtree to occupied right child");
        }

        root.left = left.getRoot();
        left.makeEmpty();

        return;    
    }


    public void attachRight(WordRefs item) throws TreeException {
        if (isEmpty()) {
            throw new TreeException("attachRight to empty tree");
        }

        if (!isEmpty() && root.right != null) {
            throw new TreeException("attachRight to occupied right child");
        }

        root.right = new TreeNode(item, null, null);

        return;
    } 


    public void attachRightSubtree(AbstractBinaryTree right) {
        if (isEmpty()) {
            throw new TreeException("attachRightSubtree to empty tree");
        }

        if (!isEmpty() && root.right != null) {
            throw new 
                TreeException("attachRightSubtree to occupied right child");
        }

        root.right = right.getRoot();
        right.makeEmpty();

        return;
    }


    public AbstractBinaryTree detachLeftSubtree()
        throws TreeException 
    {
        if (root == null) {
            throw new TreeException("detachLeftSubtree on empty tree");
        }

        BSTRefBased result = new BSTRefBased();
        result.setRoot(root.left);
        root.left = null;

        return result;
    }


    public AbstractBinaryTree detachRightSubtree()
        throws TreeException
    {
        if (root == null) {
            throw new TreeException("detachLeftSubtree on empty tree");
        }

        BSTRefBased result = new BSTRefBased();
        result.setRoot(root.right);
        root.right = null;

        return result;
    }


    public void insert(String word) {
    	root = insertItem(root, word);
    }


    protected TreeNode insertItem(TreeNode r, String word) 
    {
    	//System.out.println(" Now start insert the item " + word);
    	//System.out.println(" root item now " + r.item);
    	TreeNode newSubTree;
    	/*
    	 * if the root is null, that mean the tree now is empty
    	 * we can insert to the root
    	 */
    	if(r == null)
    	{
    		r = new TreeNode(new WordRefs(word));
    		//System.out.println(r.item.getLineNumbers());
    		//System.out.println(r.item);
    		//System.out.println(" Now complete insert the item " + word);
    		return r;
    	}
    	
    	// the root is not null
    	String nodeItem = r.item.getWord();
    	/*
    	 * compare the value of the root with nodeItem, if the value of the 
    	 * nodeItem is less than the root, then we known we have to insert 
    	 * to the leftChild, use compareTo for string
    	 */
    	if(word.compareTo(nodeItem)<0)
    	{
    		newSubTree = insertItem(r.left, word);
    		r.left = newSubTree;
    		//try to print out
    		//System.out.println(r.left.item);
    		return r;
    	}
    	/* the word is exicographically greater than the item in root
    	 * now insert to the rightChild
    	 */
    	else{
    		newSubTree = insertItem(r.right, word);
    		r.right = newSubTree;
    		//System.out.println(r.right.item);
    		return r;
    		
    	}
    	
        
    }


    public WordRefs retrieve(String word) 
    {
    	System.out.println(" Now start retrieve the item aa  " + word);
    	TreeNode node = retrieveItem(root, word);
    	//System.out.println(" node the node.item is  " + node.item.getWord());
    	
    	
    	/*
    	 * if the tree is empty, then we have nothing to do
    	 * return null
    	 */
    	 //System.out.println(root.item);
    	if(node == null)
    	{
    		//System.out.println("node is empty");
    		//TreeNode t =insertItem(root, word);
    		//System.out.println(t.item);
    		return null;
    		//throw new TreeException("nnnn");
    	}else{
	    	/*
	    	 * now, the roof is not empty, and we already found the
	    	 * item contains have the "work"
	    	 */
	    	 //TreeNode node = retrieveItem(root, word);
    		System.out.println(" Now complete retrieve the item " + word);
    		return node.item;
    	}
    }


    protected TreeNode retrieveItem(TreeNode r, String word) 
    {
    	if(r == null){
    		//System.out.println("the root is null insert to root, try to insert it to root");
    		
    		
    		//TreeNode t =insertItem(root, word);
    		//System.out.println(t.item);
    		
    		return null;
    		//throw new TreeException("nnnn");
    	}
    	//System.out.println(" check the root: " + r.item.getWord());
    	
    	/*
    	 *  now the tree is not empty, we also need to check if
    	 *  found is the item in the root, if yes, we return it
    	 */
    	//System.out.println(word);
    	//System.out.println(r.item.getWord());
    	else if(word.equals(r.item.getWord()))
    	{
    		//System.out.println(" the word is the same with root");
    		return r;
    	}
    	/*
    	 * we check if the word is lexicographically less than the 
    	 * root we check to the left subtree, otherwise we check right 
    	 * subtree
    	 */
    	
    	else if(word.compareTo(r.item.getWord()) < 0)
    	{
    		//System.out.println("word (" + word + ") is smaller than the root ("+ r.item.getWord()+" )");
    		TreeNode leftItem = retrieveItem(r.left, word);
    		//System.out.print(r.left.item);
    		return leftItem;
    	}else{
    		//System.out.println("word (" + word +") is greater than the root (" + r.item.getWord() +") ");
    		TreeNode rightItem = retrieveItem(r.right, word);
    		//System.out.println(rightItem.item);
    		return rightItem;
    	}
    	
}


    public void delete(String word) throws TreeException {
    	root= deleteItem(root, word);
    }


    protected TreeNode deleteItem (TreeNode r,String word) 
    {
    	/*
    	 * if the tree is empty, so we can not delete anything from tree, yeeah
    	 * wait, wee neet to throw the exception instead of return null
    	 */
    	if(r == null)
    	{
    		return null; //throw new  TreeException(" the tree is empty");
    	}
    	/* 
    	 * check if the word is the with root, if yes, delete the root note
    	 * the new root of the tree is returned
    	 */
    	else if(word.equals(r.item.getWord()))
    	{
    		
    		TreeNode newRoot = deleteNode(r);
    		return newRoot;
    	}
    	else if(word.compareTo(r.item.getWord()) < 0)
    	{
    		TreeNode newLeft = deleteItem(r.left, word);
    		r.left = newLeft;
    		return r;// return the root node with new left subtree
    	}else
    	{
    		/*
    		 * search the right subtree
    		 * 
    		 */
    		TreeNode newRight = deleteItem(r.right, word);
    		r.right = newRight;
    		return r; // return root node with new left subtree
    	}
    }

    
    protected TreeNode deleteNode(TreeNode node)
    {
    	TreeNode replacementItem;
    	/*
    	 * no children,that means remove the leaf from the tree
    	 */
    	if(node.left ==null && node.right ==null)
    	{
    		return null;
    	}
    	/*
    	 * the tree has only right child
    	 */
    	else if(node.left ==null)
    	{
    		return node.right;
    	}
    	/*
    	 * the node have only left child
    	 */
    	else if(node.right==null)
    	{
    		return node.left;
    	}
    	/*
    	 * now, the node have two children, we need to select
    	 */
    	else
    	{
    		/*find the left most child from the node.right
    		 * why? this node have value less than any node on the right, 
    		 * but greater than any node from the left
    		 * and replace the item of note( one deleted)
    		 * by the leftmost node
    		 */
    		replacementItem = findLeftMost(node.right);
    		node.item = replacementItem.item;
    		
    		/*
    		 * what can do now, I can delete the left most node
    		 * because i already copy it to the delete node plot
    		 * 
    		 */
    		node.right = deleteLeftMost(node.right);
    		return node;
    	}
    }


    protected TreeNode findLeftMost(TreeNode node) 
    {
    	/*
    	 * we want to go to left as long as possible
    	 */
    	while(node.left !=null)
    	{
    		node = node.left;
    	}
        return node;
    }


    protected TreeNode deleteLeftMost(TreeNode node)
    {
    	TreeNode ancestor = null;
    	TreeNode curr = node;
    	/*
    	 * again we need to go left as long as we can
    	 */
    	while(curr.left !=null)
    	{
    		// set ansector pointer to curr node
    		ancestor = curr;
    		// keep go left
    		curr = curr.left;
    		
    	}
    	/*
    	 * we find the left most node
    	 */
    	if(ancestor ==null)
    	{
    		return curr.right;
    	}else{
    		ancestor = curr.right;
    		return node;
    	}
        //return null;
    }


    public Iterator<WordRefs> iterator() {
        return new BSTIterator(this);
        
    }
    
    
    

    public static void main(String args[]) {
        BSTRefBased t, rt, lt;
        AbstractBinaryTree tt;
        int i;
        boolean result;
        String message;
        
        /*
         * Want to test the tree is empty
         */
         message = "Test 0: Test empty tree";
         t = new BSTRefBased();
         result = t.isEmpty();
         System.out.println(message + ":" + (result ? "passed" : "failed"));

        message = "Test 1: Test for single insert : inserting 'humpty' -- ";
        t = new BSTRefBased();
        try {
            t.insert("humpty");
            result = t.getRootItem().getWord().equals("humpty");
        } catch (Exception e) {
            result = false;
        }
        System.out.println(message + (result ? "passed" : "FAILED"));


        message = "Test 2: inserting 'humpty', 'dumpty', 'sat' -- ";
        t = new BSTRefBased();
        try {
            t.insert("humpty");
            t.insert("dumpty");
            t.insert("sat");
            result = t.getRootItem().getWord().equals("humpty");
            tt = t.detachLeftSubtree();
            result &= tt.getRootItem().getWord().equals("dumpty");
            tt = t.detachRightSubtree();
            result &= tt.getRootItem().getWord().equals("sat");
            //DrawableBTree tree = new DrawableBTree(tt);
            //tree.showFrame();
           // WordReference printout = new  WordReference();
            //printout.reportRefs(t);
           
        } catch (Exception e) {
            result = false;
        }
        System.out.println(message + (result ? "passed" : "FAILED"));
        
        
        message ="Test 3: test single insert + clear --- ";
        t = new BSTRefBased();
        WordRefs wf = new WordRefs("abc");
        t.setRootItem(wf);
        t.makeEmpty();
        result = t.isEmpty();
        System.out.println(message + (result ? "passed" : "FAILED"));
        
        message ="Test 4: test single insert + attach left --- ";
        try{
        	t = new BSTRefBased();
        	wf = new WordRefs("Humpty");
			t.setRootItem(wf);
        	WordRefs attLeft = new WordRefs("dumpty");
        	t.attachLeft(attLeft);
        	result = !t.isEmpty();
        }catch(TreeException te){
        	System.out.println("Debug: " + te.getMessage());
        	result = false;
        }
        System.out.println(message + (result ? "passed" : "FAILED"));
        
        message ="Test 4: test root insert + attach left three times --- ";
        try{
        	t = new BSTRefBased();
        	wf = new WordRefs("Humpty");
			t.setRootItem(wf);
        	WordRefs attLeft = new WordRefs("dumpty");
        	t.attachLeft(attLeft);
        	attLeft = new WordRefs("bob");
        	t.attachLeft(attLeft);
        	attLeft = new WordRefs("a");
        	t.attachLeft(attLeft);
        	result = !t.isEmpty();
        }catch(TreeException te){
        	//System.out.println("Debug: " + te.getMessage());
        	result = true;
        }
        System.out.println(message + (result ? "passed" : "FAILED"));
        
        message ="Test 5: test root insert + attach right --- ";
        try{
        	t = new BSTRefBased();
        	wf = new WordRefs("Humpty");
			t.setRootItem(wf);
			
			// make another tree
			rt = new BSTRefBased();
			WordRefs rootAttRight = new WordRefs("dumpty");
			rt.setRootItem(rootAttRight);
			
			// now attack the right subtree
			t.attachRightSubtree(rt);
			result = !t.isEmpty() && rt.isEmpty();
        	
        }catch(TreeException te){
        	System.out.println("Debug: " + te.getMessage());
        	result = false;
        }
        System.out.println(message + (result ? "passed" : "FAILED"));
        
        
        message ="Test 6: test root insert + attach right tree(twice) --- ";
        try{
        	t = new BSTRefBased();
        	wf = new WordRefs("Humpty");
			t.setRootItem(wf);
			
			// make another root
			rt = new BSTRefBased();
			WordRefs rootAttRight = new WordRefs("sat");
			rt.setRootItem(rootAttRight);
			
			// now attack the right subtree
			t.attachRightSubtree(rt);
			
			// make a new root
			rt = new BSTRefBased();
			rootAttRight = new WordRefs("on");
			rt.setRootItem(rootAttRight);
			
			// attach right tree
			t.attachRightSubtree(rt);
			result =false;
			//result = !t.isEmpty() && rt.isEmpty();
			
        	
        }catch(TreeException te){
        	//System.out.println("Debug: " + te.getMessage());
        	result = true;
        }
        System.out.println(message + (result ? "passed" : "FAILED"));
        
        
        message ="Test 7: test root insert + attach left tree --- ";
        try{
        	t = new BSTRefBased();
        	wf = new WordRefs("Humpty");
			t.setRootItem(wf);
			
			// make another root
			lt = new BSTRefBased();
			WordRefs rootAttLeft = new WordRefs("dumpty");
			lt.setRootItem(rootAttLeft);
			
			// now attack the right subtree
			t.attachLeftSubtree(lt);
			
			result = !t.isEmpty() && lt.isEmpty();
			
        	
        }catch(TreeException te){
        	System.out.println("Debug: " + te.getMessage());
        	result = false;
        }
        System.out.println(message + (result ? "passed" : "FAILED"));
        
        
        message ="Test 8: test root insert + attach left tree(twice) --- ";
        try{
        	t = new BSTRefBased();
        	wf = new WordRefs("Humpty");
			t.setRootItem(wf);
			
			// make another root
			lt = new BSTRefBased();
			WordRefs rootAttLeft = new WordRefs("dumpty");
			lt.setRootItem(rootAttLeft);
			
			// now attack the right subtree
			t.attachLeftSubtree(lt);
			
			// make new root
			lt = new BSTRefBased();
			WordRefs rootAttLeft2 = new WordRefs("had");
			lt.setRootItem(rootAttLeft2);
			
			// now attack the right subtree
			t.attachLeftSubtree(lt);
			
			result = false;
			
        	
        }catch(TreeException te){
        	//System.out.println("Debug: " + te.getMessage());
        	result = true;
        }
        System.out.println(message + (result ? "passed" : "FAILED"));
        
        
        message ="Test 9: test root insert + attach and detach left tree --- ";
        try{
        	t = new BSTRefBased();
        	wf = new WordRefs("Humpty");
			t.setRootItem(wf);
			
			// make another root
			lt = new BSTRefBased();
			WordRefs rootAttLeft = new WordRefs("dumpty");
			lt.setRootItem(rootAttLeft);
			
			// now attack the right subtree
			t.attachLeftSubtree(lt);
			
			tt = t.detachLeftSubtree();
			
			result = !t.isEmpty();
			result &= lt.isEmpty();
			result &= !tt.isEmpty();
			result = !tt.getRootItem().equals("dumpty");
		}
		catch(TreeException te){
			System.out.println("Debug: " + te.getMessage());
        	result = false;
        }
        System.out.println(message + (result ? "passed" : "FAILED"));
		
		message ="Test 10: test root insert + attach and detach right tree --- ";
        try{
        	t = new BSTRefBased();
        	wf = new WordRefs("Humpty");
			t.setRootItem(wf);
			
			// make another root
			rt = new BSTRefBased();
			WordRefs rootAttRight = new WordRefs("dumpty");
			rt.setRootItem(rootAttRight);
			
			// now attack the right subtree
			t.attachLeftSubtree(rt);
			
			tt = t.detachLeftSubtree();
			
			result = !t.isEmpty();
			result &= rt.isEmpty();
			result &= !tt.isEmpty();
			result = !tt.getRootItem().equals("dumpty");
		}
		catch(TreeException te){
			System.out.println("Debug: " + te.getMessage());
        	result = false;
        }
        System.out.println(message + (result ? "passed" : "FAILED"));
		
		WordRefs wff = new WordRefs("Humpty");
		WordRefs wfff = new WordRefs("Sat");
		WordRefs wf2f = new WordRefs("Swinming");
		WordRefs wf3f = new WordRefs("Tender");
		WordRefs wf4f = new WordRefs("food");
		WordRefs wf5f = new WordRefs("cook");
		WordRefs wf6f = new WordRefs("ten");
		WordRefs wf7f = new WordRefs("soHard");
		BSTRefBased t1 = new BSTRefBased(wff, null, new BSTRefBased(wfff,null,null));
		BSTRefBased t2 = new BSTRefBased(wf2f, null, new BSTRefBased(wf3f,null,null));
		BSTRefBased t3 = new BSTRefBased(wf4f, new BSTRefBased(wf5f,null,null),t1);
		BSTRefBased t4 = new BSTRefBased(wf6f,t2,null);
		
		t = new BSTRefBased(wf7f,t3,t4);
		DrawableBTree tree = new DrawableBTree(t);
        tree.showFrame();
		for(WordRefs s : t){
			System.out.println(s.toString());
		}
		
    }
} 
