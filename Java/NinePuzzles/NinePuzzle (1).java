/* NinePuzzle.java
 CSC 225 - Fall 2015
 Assignment 5 - Template for the 9-puzzle
 
 This template includes some testing code to help verify the implementation.
 Input boards can be provided with standard input or read from a file.
 
 To provide test inputs with standard input, run the program with
	java NinePuzzle
 To terminate the input, use Ctrl-D (which signals EOF).
 
 To read test inputs from a file (e.g. boards.txt), run the program with
 java NinePuzzle boards.txt
 
 The input format for both input methods is the same. Input consists
 of a series of 9-puzzle boards, with the '0' character representing the
 empty square. For example, a sample board with the middle square empty is
 
 1 2 3
 4 0 5
 6 7 8
 
 And a solved board is
 
 1 2 3
 4 5 6
 7 8 0
 
 An input file can contain an unlimited number of boards; each will be
 processed separately.
 
 B. Bird    - 07/11/2014
 M. Simpson - 11/07/2015
 */

import java.util.*;
import java.io.File;

class NinePuzzle{
    
    //The total number of possible boards is 9! = 1*2*3*4*5*6*7*8*9 = 362880
    public static final int NUM_BOARDS = 362880;
    public static HashSet<Integer> hs = new HashSet<Integer>();
    public static HashMap<Integer,Integer> hm = new HashMap<Integer,Integer>();
    
    // create a upBoard
    public static boolean upBoard(int[][] B,int [][]newBoard){
        int i,j,x=0,y=0;
        for(i=0;i<3;i++)
        {
            for(j=0;j<3;j++)
            {
                newBoard[i][j]=B[i][j];
                if(B[i][j]==0)
                {
                    x=i;y=j;
                }
            }
        }
        if(x-1>=0)
        {
            newBoard[x][y]=newBoard[x-1][y];
            newBoard[x-1][y]=0;
            return true;
        }
        else
            return false;
    }
    
    // create a downBoard
    public static boolean downBoard(int[][] B,int [][]newBoard){
        int i,j,x=0,y=0;
        for(i=0;i<3;i++)
        {
            for(j=0;j<3;j++)
            {
                newBoard[i][j]=B[i][j];
                if(B[i][j]==0)
                {
                    x=i;y=j;
                }
            }
        }
        if(x+1<3)
        {
            newBoard[x][y]=newBoard[x+1][y];
            newBoard[x+1][y]=0;
            return true;
        }
        else
            return false;
    }
    
     // create a leftBoard
    public static boolean leftBoard(int[][] B,int [][]newBoard){
        int i,j,x=0,y=0;
        for(i=0;i<3;i++)
        {
            for(j=0;j<3;j++)
            {
                newBoard[i][j]=B[i][j];
                if(B[i][j]==0)
                {
                    x=i;y=j;
                }
            }
        }
        if(y-1>=0)
        {
            newBoard[x][y]=newBoard[x][y-1];
            newBoard[x][y-1]=0;
            return true;
        }
        else
            return false;
    }
    
    // create a rightBoard
    public static boolean rightBoard(int[][] B,int [][]newBoard){
        int i,j,x=0,y=0;
        for(i=0;i<3;i++)
        {
            for(j=0;j<3;j++)
            {
                newBoard[i][j]=B[i][j];
                if(B[i][j]==0)
                {
                    x=i;y=j;
                }
            }
        }
        if(y+1<3)
        {
            newBoard[x][y]=newBoard[x][y+1];
            newBoard[x][y+1]=0;
            return true;
        }
        else
            return false;
    }
    
    /*  SolveNinePuzzle(B)
     Given a valid 9-puzzle board (with the empty space represented by the
     value 0),return true if the board is solvable and false otherwise.
     If the board is solvable, a sequence of moves which solves the board
     will be printed, using the printBoard function below.
     */
    public static boolean SolveNinePuzzle(int[][] B){
        int index = getIndexFromBoard(B),i=0,j=0,val=1,point;
        Queue<Integer> queue = new LinkedList<Integer>();
        int newBoard[][]=new int[3][3];
        hs.add(index);
        queue.add(index);
        hm.put(index,-1);
        while(!queue.isEmpty())
        {
            index=queue.remove();
            B=getBoardFromIndex(index);
            val=1;
            for(i=0;i<3;i++)
            {
                for(j=0;j<3;j++)
                {
                    if(i==2 && j==2)
                    {
                        if(B[i][j]!=0)
                            break;
                    }
                    else
                    {
                        if(B[i][j]==val)
                            val++;
                        else
                            break;
                    }
                }
                if(j<3)
                    break;
            }
            if(i==3 && j==3)
            {
                Stack<Integer> st =new Stack<Integer>();
                st.push(index);
                index=hm.get(index);
                while(index!=-1)
                {
                    st.push(index);
                    index=hm.get(index);
                }
                while(!st.empty())
                {
                    printBoard(getBoardFromIndex(st.peek()));
                    st.pop();
                }
                return true;
            }
            if(downBoard(B,newBoard))
            {
                point = getIndexFromBoard(newBoard);
                if(!hs.contains(point))
                {
                    queue.add(point);
                    hs.add(point);
                    hm.put(point,index);
                }
            }
            if(upBoard(B,newBoard))
            {
                point = getIndexFromBoard(newBoard);
                if(!hs.contains(point))
                {
                    queue.add(point);
                    hs.add(point);
                    hm.put(point,index);
                }
            }
            if(rightBoard(B,newBoard))
            {
                point = getIndexFromBoard(newBoard);
                if(!hs.contains(point))
                {
                    queue.add(point);
                    hs.add(point);
                    hm.put(point,index);
                }
            }
            if(leftBoard(B,newBoard))
            {
                point = getIndexFromBoard(newBoard);
                if(!hs.contains(point))
                {
                    queue.add(point);
                    hs.add(point);
                    hm.put(point,index);
                }
            }
        }
        return false;
    }
    
    /*  printBoard(B)
     Print the given 9-puzzle board. The SolveNinePuzzle method above should
     use this method when printing the sequence of moves which solves the input
     board. If any other method is used (e.g. printing the board manually), the
     submission may lose marks.
     */
    public static void printBoard(int[][] B){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++)
                System.out.printf("%d ",B[i][j]);
            System.out.println();
        }
        System.out.println();
    }
    
    
    /* Board/Index conversion functions
     These should be treated as black boxes (i.e. don't modify them, don't worry about
     understanding them). The conversion scheme used here is adapted from
     W. Myrvold and F. Ruskey, Ranking and Unranking Permutations in Linear Time,
     Information Processing Letters, 79 (2001) 281-284.
     */
    public static int getIndexFromBoard(int[][] B){
        int i,j,tmp,s,n;
        int[] P = new int[9];
        int[] PI = new int[9];
        for (i = 0; i < 9; i++){
            P[i] = B[i/3][i%3];
            PI[P[i]] = i;
        }
        int id = 0;
        int multiplier = 1;
        for(n = 9; n > 1; n--){
            s = P[n-1];
            P[n-1] = P[PI[n-1]];
            P[PI[n-1]] = s;
            
            tmp = PI[s];
            PI[s] = PI[n-1];
            PI[n-1] = tmp;
            id += multiplier*s;
            multiplier *= n;
        }
        return id;
    }
    
    public static int[][] getBoardFromIndex(int id){
        int[] P = new int[9];
        int i,n,tmp;
        for (i = 0; i < 9; i++)
            P[i] = i;
        for (n = 9; n > 0; n--){
            tmp = P[n-1];
            P[n-1] = P[id%n];
            P[id%n] = tmp;
            id /= n;
        }
        int[][] B = new int[3][3];
        for(i = 0; i < 9; i++)
            B[i/3][i%3] = P[i];
        return B;
    }
    
    
    public static void main(String[] args){
        /* Code to test your implementation */
        /* You may modify this, but nothing in this function will be marked */
        
        
        Scanner s;
        
        if (args.length > 0){
            //If a file argument was provided on the command line, read from the file
            try{
                s = new Scanner(new File(args[0]));
            } catch(java.io.FileNotFoundException e){
                System.out.printf("Unable to open %s\n",args[0]);
                return;
            }
            System.out.printf("Reading input values from %s.\n",args[0]);
        }else{
            //Otherwise, read from standard input
            s = new Scanner(System.in);
            System.out.printf("Reading input values from stdin.\n");
        }
        
        int graphNum = 0;
        double totalTimeSeconds = 0;
        
        //Read boards until EOF is encountered (or an error occurs)
        while(true){
            graphNum++;
            if(graphNum != 1 && !s.hasNextInt())
                break;
            System.out.printf("Reading board %d\n",graphNum);
            int[][] B = new int[3][3];
            int valuesRead = 0;
            for (int i = 0; i < 3 && s.hasNextInt(); i++){
                for (int j = 0; j < 3 && s.hasNextInt(); j++){
                    B[i][j] = s.nextInt();
                    System.out.print(B[i][j] + " ");
                    valuesRead++;
                }
                System.out.println();
            }
            if (valuesRead < 9){
                System.out.printf("Board %d contains too few values.\n",graphNum);
                break;
            }
            System.out.printf("Attempting to solve board %d...\n",graphNum);
            long startTime = System.currentTimeMillis();
            hs.clear();
            hm.clear();
            boolean isSolvable = SolveNinePuzzle(B);
            long endTime = System.currentTimeMillis();
            totalTimeSeconds += (endTime-startTime)/1000.0;
            
            if (isSolvable)
                System.out.printf("Board %d: Solvable.\n",graphNum);
            else
                System.out.printf("Board %d: Not solvable.\n",graphNum);
        }
        graphNum--;
        System.out.printf("Processed %d board%s.\n Average Time (seconds): %.2f\n",graphNum,(graphNum != 1)?"s":"",(graphNum>1)?totalTimeSeconds/graphNum:0);
        
    }
    
}
