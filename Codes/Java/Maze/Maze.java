/* Name : Ngoc Thinh Nguyen 		                          Student ID: V00817304
 * Assignment # 3: 				Recursion 					  course : csc 115
 *
 */



public class Maze
{
	private String [] textmaze ; 
	public int startRow;
	private int startCol;
	private int finishRow;
	private int finishCol;
	private char Wall = '*';
	MazeLocationList tailNodes = new MazeLocationListRefBased();
	public boolean [][] mazeBooleanArray;
		
	
	
	public Maze(String[] textmaze, int startRow, int startCol, int finishRow, int finishCol)
	{
		this.textmaze = textmaze;
		this.startRow = startRow;
		this. startCol= startCol;
		this.finishRow=finishRow;
		this.finishCol=finishCol;
		
		/* create a boolean 2D array, and set all elements of the array is false
		 * the reason of create this array: we need to model the maze as 2D array,
		 * the boolean type can be used later for preventing the maze can not go back 
		 * it path, we can set any square we have walked through is "true"
		 */
		mazeBooleanArray = new boolean [textmaze.length][textmaze[0].length()];
		
		}
	
	/* This method takes no parameter and return an instance of MazeLocationList when the
	 *  path is found. if there no path through the maze, then return null.
	 * there is  This method is called recursive method is that when we get to the base
	 * case, we return true to the square before the base case, and continue return true
	 * util we meet the started position. We can use insertTail() to insert the square 
	 * location to Maze linked list
	 */
	public MazeLocationList solve(){
	
	
		/* this is a base case if we have meet the square with its coordinate the same
		 * the same with started square then we known that we have found the path. However
		 * we also have the case there is no path, the start point is the same with the 
		 * finish point, then we return null in this case
		 * first create the MazePath linked list. Remove the tail from tailNodes linked
		 * we get from finding the maze path. Since the tailNodes linked list is convert
		 * order from the MazeLocation expected[] from MazeTester.java, we need to convert 
		 * order of maze path
		 */
		if(findPath(startRow, startCol, finishRow, finishCol)){
			MazeLocationList MazePath = new MazeLocationListRefBased();
			while (!tailNodes.isEmpty()) {
				MazePath.insertTail(tailNodes.removeTail());
			}
				//System.out.println(MazePath.toString());
			return MazePath;
			
			
		}
		return null;
		
		
	}
	
	
	/* the boolean method will return true of the path is found, and false if the path
	 * is not found, Since we are only allow to move up, down, left, and right one unit
	 * square each time. there for we can check the right, down, up, and left. use the 
	 * recursion to backtrast if there is not valid move available. when we back look
	 * at other direction except one direction of square we have already been, and the
	 * direction we just back from. I set condition for boolean is true if the square 
	 * already been, then we check condition, we know where we can to check
	 */
	private boolean findPath(int fromRow, int fromCol, int toRow, int toCol){
		
		mazeBooleanArray[fromRow][fromCol] = true;
		/* Let check if the started square and the finished are the same, if yes
		 * we insert the tail as the MazeLocation(fromRow, fromCol), and return true
		 * this is the base we need
		 */
		if(fromRow == toRow && fromCol == toCol){
		
			tailNodes.insertTail(new MazeLocation(fromRow, fromCol));
			//System.out.println(tailNodes.toString());
			/* use the for loop to print the boolean 2D array to see if the path is math
			 * with the linked list 
			 */
			 for(int i = 0 ; i< mazeBooleanArray.length ; i++){
					for(int j = 0 ; j< mazeBooleanArray[0].length ; j++){
						if(mazeBooleanArray[i][j] == true){
						System.out.print(mazeBooleanArray[i][j] +" |");
						}else{
							System.out.print(mazeBooleanArray[i][j] +"|");
						}
					}
					System.out.println();
				}
			
			return true;
		}
		
		
		
		
		/* check the east ( right) is valid, if the started column is greater than the 
		 * of the textmaze row or the right side square is blocked, we can not go, and 
		 * should check another direction; otherwise, we use recursion to find the 
		 * direction at the next right side square, insert the tail to tailNode linked 
		 * list, return true if the path is found
		 */
		if(fromCol < textmaze[fromRow].length() -1 && !mazeBooleanArray[fromRow][fromCol + 1] &&
							textmaze[fromRow].charAt(fromCol +1) != Wall){
			
			if(findPath(fromRow, fromCol +1 , toRow , toCol)){
				//System.out.println(tailNodes.toString());
				tailNodes.insertTail(new MazeLocation(fromRow, fromCol));
				return true;
				
			}
		}
		
		
		/* check if the south( down) is valid, if the started row is greater than the length 
		 * of the textmaze column or the  south square is blocked, we can not go, and 
		 * should check another direction; otherwise, we use recursion to find the 
		 * direction of the sound square, insert the tail to tailNode linked 
		 * list
		 * 
		 */
		 
		if(fromRow < textmaze.length -1 && textmaze[fromRow +1].charAt(fromCol) != Wall &&
				!mazeBooleanArray[fromRow + 1][fromCol] )
		{
			if(findPath(fromRow  +1, fromCol , toRow , toCol))
			{
				//System.out.println(tailNodes.toString());
				tailNodes.insertTail(new MazeLocation(fromRow, fromCol));
				return true;
				
			}
		}
		
		
		/* check if the north is valid, if the started row is not zero  
		 * or the up square is not blocked, we can walk to north side, and 
		 * we use recursion to find the whether can walk further 
		 *  insert the tail to tailNode linked list
		 * return true 
		 */
		if(fromRow != 0 && textmaze[fromRow -1].charAt(fromCol) != Wall &&
			!mazeBooleanArray[fromRow -1][fromCol])
		{
			if(findPath(fromRow  -1, fromCol , toRow , toCol))
			{
				//System.out.println(tailNodes.toString());
				tailNodes.insertTail(new MazeLocation(fromRow, fromCol));
				return true;
				
			}
		}
		
		
		/* check if the west is valid, if the started column is not zero  
		 * or the left square is not blocked, we can walk to north side, and 
		 * we use recursion to find the whether can walk further 
		 *  insert the tail to tailNode linked list
		 * return true 
		 */
		if(fromCol != 0 && textmaze[fromRow ].charAt(fromCol - 1) != Wall &&
			!mazeBooleanArray[fromRow][fromCol - 1])
		{
			if(findPath(fromRow, fromCol - 1 , toRow , toCol))
			{
				//System.out.println(tailNodes.toString());
				tailNodes.insertTail(new MazeLocation(fromRow, fromCol));
				return true;
				
			}
			
		}
		// return false if the path is not found
		return false;
		
	}
		
	
	// this method come from CSC 115 lab
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for( int i = 0; i < mazeBooleanArray.length ; i++){
			for ( int j =0 ; i< mazeBooleanArray[i].length; j++){
				
				sb.append(mazeBooleanArray[i][j]);
			}
			
			sb.append("\n");
		}
		return sb.toString();	
	} 
	
}
