/*
 * Name: Ngoc Thinh Nguyen
 * ID: V00817304
 * Class: csc115
 * Assignment 5
 * Date: April 1, 2015
 */


/* I want to find if the binary tree is balance, i will working on this idea
 * when i finish my finals
 * thanks 
 */
public class BalanceExtra extends BSTRefBased{
	
	//BSTRefBased root1;

	/* 
     * write the method to count how many node, then i 
     * can keep track the tree look like
     */
    protected int countNodes(TreeNode root)
    {
    	int count =0;
	 	if( root != null){
	 		count = 1 + countNodes(root.left) + countNodes(root.right);
	 	}
	 	return count;
	 }
	 /*since the countNodes is protected, you will need to public method called 
	  * the protected method and return the result
	  */
	  public int countNodes(){
	  	return countNodes(getRoot());
	  }
	  
	  
		
	  /*  The height of the tree is 0 if the tree is empty, Then it is 1 plus the height
	   * of the left subtree or the right subtree, whichever is greater. 
	   */
	   protected int getHeight( TreeNode root){
	   		int heigh = 0;
	   		// if the tree is empty, then height is 0
	   		if(root != null){
	   			// get the height of the leftSubtre
	   			int leftHeigh = getHeight(root.left);
	   			//System.out.println(leftHeigh);
	   			 // get the heigh of the rightChild
	   			int rightHeigh = getHeight(root.right);
	   			//System.out.println(rightHeigh);
	   			
	   			// now we have the heigh of the left and right subtree
	   			// we can compare them, if the height of left subtree is greater
	   			// than the height of rightSubtree, then the height of tree is 1 + 
	   			// height of left subtree, and otherwise
	   			if(leftHeigh >= rightHeigh){
	   				heigh = 1 + leftHeigh;
	   			}else{
	   				heigh = 1 +  rightHeigh;
	   			}
	   		}
	   		return heigh;
	   }
	   
	   // we still nee the public method for getHeight()
	   public int getHeight(){
	   		return getHeight(getRoot());
	   }
}