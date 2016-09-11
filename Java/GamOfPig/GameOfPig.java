/***************************************************************************************
 * For Assignment #4    ID: V00817304
 * Name: Ngoc Thinh Nguyen
 *Program name: GameOfPig.java
 *Program objects: Focus on If/Else,While loops, and Methods 
 *Program Desciption: - This propram request user play the game against the computer 
 *                      using one dice. Using the Random method to generate the simulate
 *                      a 6 side die. 
 *                    - The player play first, they can choose to end their turn 
 *                       or her or his is ended when they roll a 1. the user must make a 
 *                       agreement whenever they would like to roll again. if they roll  
 *                       a 1, they loose all point in the round they are playing.
 *                    - The computer can end its turn when it rolls a 1 or it has already 
 *                      done four rolls in a round. Like the player, the computer 
 *                      is looses all its points on the playing round if it rolls a 1
 *                    
 * program input: Use a single random object to simulate the roll of dice. That means 
 *                 we can have a random number between 1 to 6.               
 *                 The user need to answer the question "Do you want to roll again?"
 *                 whenever they want to roll again
 * program output: The number of rolled each time the player and computer roll. Total  
 *                 earn and end of each turn. the cumulative score at the end of each 
 *                  turn, and whether the player or computer won or lost the game.
 *                  once a score of at least 100 is reached
 * Due: Friday October 24, 8 pm
 ***************************************************************************************/
import java.io.*;   // so can use the Random object
import java.util.*;  // can get input from keyboard 

public class GameOfPig{

    public static Random r; // global variable for rolling a dice
    public static Scanner console;   
    
    public static void main(String[] agrs){
        
     	int count = 0; int TotalHumanScore=0; int TotalComputerScore=0;
     	
     	r = new Random();
     	console = new Scanner(System.in);
     	
     	// calculate  the number of points after each round
     	while (TotalHumanScore<100 && TotalComputerScore <100){
     		
            count ++;     // calculate the number of rounds
            System.out.println();
            System.out.println(" Round: " + count);
            
     	    int humanScore = HumanPlay();
     	    TotalHumanScore += humanScore;
     	    System.out.println();
     	    
     	    int computerScore=ComputerRollTurn();
     	    TotalComputerScore += computerScore;
     	    System.out.println("\n\n");
     	    
     	    System.out.println(" ______________________________");
     	    System.out.println(" The Score after " + count + " rounds is: ");
            System.out.print("   Computer:   " +TotalComputerScore + "   ");
            System.out.println(" you:   " + TotalHumanScore);
            System.out.println(" ______________________________");
            System.out.println("\n\n");
            
               // the decision that whether the Player or Computer win
               // once a score at at least 100 is reached
            if (TotalHumanScore>=100 && TotalComputerScore >=100){
            	System.out.println("*****************************************");
                System.out.println(" No decision can be made, play again!");
                System.out.println("*****************************************");
                break;
            }else if(TotalComputerScore >=100){
            	System.out.println(" *****************************");
                System.out.println(" Congratulations! Computer win");
                System.out.println(" *****************************");
                break;
            }else if(TotalHumanScore>=100){
            	System.out.println(" *************************"); 
                System.out.println(" Congratulations! you win");
                System.out.println(" *************************");
                break;
            }
        }
        System.out.println();
   }
      
   public static int ComputerRollTurn(){
   	    System.out.println();
   	    System.out.println(" The computer roll");
   	    System.out.println(" -----------------");
     	int computerTotalScore=0;
     	for( int i=1 ; i<=4 ; i++){
            Random rand = new Random();
            int roll = r.nextInt(6)+1;
            computerTotalScore +=roll;
            System.out.print(" The Computer rolled  " + roll + ".  " );
            if(roll == 1){
               System.out.print(" The Computer's turn is over ");
               System.out.println("and the computer");
               System.out.print("\t\t\t\tlooses all its points ");
               System.out.println("from this round.");
               computerTotalScore=0;
               break;
           }
           System.out.print(" The Computer's Total so far: " + " ");
           System.out.println(computerTotalScore);
        }
        System.out.print(" The Computer's Total this round is:   ");
        System.out.println(computerTotalScore);
        return computerTotalScore;
    }
     
     // player roll the dice and decide whenever stop roll
     // if they roll a 1, they loose all his points in this round
     // they also lose stop roll, then the computer's turn
   public static int HumanPlay(){
    	System.out.println();
    	System.out.println(" You roll");
   	    System.out.println(" --------");
     	System.out.println();
     	
        int HumanScore =0;
        int points =HumanRollTurn();   // the value of roll each time
        HumanScore += points;
        System.out.println(" your total score so far is: " + HumanScore);
        System.out.println(" Do you want to roll again? ( y or n)" );
        String answer = console.next();
        
        // Asking the user whether they want to roll again
        while ( answer . equalsIgnoreCase("y")){
             points =HumanRollTurn();
             HumanScore += points;
             if ( points == 1){
            	System.out.print(" your turn is over and you loose all ");
            	System.out.println("your points from this round.");
            	HumanScore=0;
            	break; // stop the while loop
            }
            System.out.println(" your total score so far is "+ HumanScore);
            System.out.println(" Do you want to roll again?( y or n)");
            answer = console.next();
        }
        System.out.println(" Your total this round is: "+HumanScore);
        return HumanScore;
  }
    // rolling a dice and take the number each time
   public static int HumanRollTurn(){
        Random rand = new Random();
        int roll = r.nextInt(6)+1;
        System.out.print(" you rolled " + roll + ". " );
        return roll;
     }
}
          
          