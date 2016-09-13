/*******************************************************************************
 * For Assignment #5    
 * Name: Ngoc Thinh Nguyen
 *Program name: GameOfPig.java
 *Program objects: Focus on If/Else,While loops, and Methods 
 *Program Desciption: drawing the 4 slugs, they move with constant velocity
 *                    
 * program input: the file, array...
 * program output: 4 slugs,
 * Due: Friday November 7, 8 pm
 ********************************************************************************/



import java.awt.*;
import java.io.*;
import java.util.*;

public class Slugs{

  public static int boxSize;
  public static int d;

   public static void main(String[] agrs){
   	   
   	 // Read the file name call Slug_details.txt 
   	   
        Scanner input = null;
        try{
        	input = new Scanner(new File("slug_details.txt"));
        }
        catch (java.io.FileNotFoundException e) {
        	System.out.println("Sorry, file not found!");
        	System.exit(-1); 
   	}
   	String filename = input.next();
    //System.out.println(filename); 
   	boxSize = input.nextInt(); 
    //System.out.println(boxSize);
   	d = input.nextInt();
    //System.out.println(d);
   	Point [] Slug = new Point [4];
   	Slug[0]= new Point(0,0);
    //System.out.println(Slug[0].x);
   	Slug[1]= new Point(0,boxSize);
   	Slug[2]= new Point(boxSize,boxSize);
   	Slug[3]= new Point(boxSize,0);
    //System.out.println(Slug[2].x);
        
   	SlugMove(Slug, d);
}

   public static void SlugMove(Point[]Slug, int d){

          BufferedWriter output = null;
          try {
            output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("out.txt"),"utf-8"));
            output.write(boxSize + " " + boxSize);
            output.newLine();

            // calcuate the distance between two slugs
            double distance = Math.sqrt((Math.pow((Slug[(0+1)%4].x - Slug[0].x), 2))+ (Math.pow((Slug[(0+1)%4].y-Slug[0].y), 2)));
            while(distance > d){
            for (int i = 0; i < Slug.length; i++){
              distance = Math.sqrt((Math.pow((Slug[(i+1)%4].x - Slug[i].x), 2))+ (Math.pow((Slug[(i+1)%4].y-Slug[i].y), 2)));
              //System.out.println(d);

              
                   double ratio = d/distance;
                   //System.out.println("ratio " + ratio);
                   //System.out.println("check" + (int)((Slug[(i+1)%4].x - Slug[i].x)*ratio));
                   int movex = Slug[i].x +  (int)((Slug[(i+1)%4].x - Slug[i].x)*ratio);
                   //System.out.println("movex " + movex);
                   int movey= Slug[i].y + (int)((Slug[(i+1)%4].y - Slug[i].y)*ratio);
                   output.write(Slug[i].x + " " + Slug[i].y + " " + movex + " " + movey);
                   output.newLine();
                   System.out.println(movex + " " + movey);
                   Slug[i] = new Point(movex,movey);
                   System.out.println("After move " + Slug[i].x + " " + Slug[i].y);
                   //break;

              }
            }
          }
          catch (IOException ex) {
            // report
          } finally {
              try {output.close();} catch (Exception ex) {/*ignore*/}
          }
    }
}
// the code is not completed. I still don't understand.  