/***************************************************************************************
 * For Assignment #6   
 * Name: Ngoc Thinh Nguyen
 *Program name: ImageProcessor.java
 *Program objects: Focus on If/Else,While loops, methods, two-dimensional arrays
 *                 ,and how to design code that is tolerant of error cases.
 *Program Desciption: - Images are often represented inprograms as 2D arrays. If the image
 *                        a grayscale, then we encode the shades as integer values in 2D
 *                        array. 
 *                    - we use the back-while pixels, from 0 to 255
 *                        255 is while, 0 is black
 *                    - using the ASCII table to create the ASCII image
 *                    
 * program input: using the command line argument, which is we get the user input via 
 *                 the String[] args array. If the user input the invalid argument
 *                  then the program give error message
 * program output: the program will output one image trasformation (ASCII - art version
 *                  of image) and reflect horizontal, reflect vertical, and repeat image 
 *                  by number of times, and adjust the brightness of image
 * Due: Friday November 22, 8 pm
 ***************************************************************************************/

// Initial source-code for ImageProcessor.java
// Given as part of Assignment #6, CSC 110, UVic, Fall 2014
// Originally created by Mike Zastre, last edited by Melanie Tory, Nov. 2014

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class ImageProcessor {
  public static void main(String[] agrs) throws FileNotFoundException{

      
      // initialize the input file and output file from command line arguments
      String inFile ;
      String  outFile;
      
      // transfer the image to 2D-array
      int [][] Array = new int [2][2];
      
      // create the ASCII image from original image
      if( agrs[0].equals("-ascii")){
            inFile = agrs[1];
            outFile=agrs[2];
            Array = readGrayscaleImage(inFile);
            PrintStream output = new PrintStream(new File(outFile));
            TextFile(Array,output);
          
      }
      // reflect the image across vertical axis
      else if( agrs[0].equals("-reflectV")){
      	    inFile = agrs[1];
      	    outFile=agrs[2];
      	    Array = readGrayscaleImage(inFile);
            writeGrayscaleImage(outFile, ArrayForReflectVertical(Array));
      }
      // reflect the image across horizontal axis
      else if(agrs[0].equals("-reflectH")){
      	    inFile = agrs[1];
      	    outFile=agrs[2];
      	    Array = readGrayscaleImage(inFile);
            writeGrayscaleImage(outFile, ArrayForReflectHorizontial(Array));
      }
      // tile image
      else if(agrs[0].equals("-tile")){
      	    inFile = agrs[3];
      	    outFile=agrs[4];
      	    Array = readGrayscaleImage(inFile);
      	    int extendRow = Integer.parseInt(agrs[1]);
      	    int extendColumn = Integer.parseInt(agrs[2]);
      	    int [][] ExtendArray = tileArray(Array, extendRow,extendColumn);
            writeGrayscaleImage(outFile, ExtendArray);
      }
      // brightness/darken image
      else if( agrs[0].equals("-adjustBrightness")){
            inFile = agrs[2];
      	    outFile=agrs[3];
      	    Array = readGrayscaleImage(inFile);
      	    int amount = Integer.parseInt(agrs[1]);
      	    writeGrayscaleImage(outFile, adjustBrightness(Array, amount));
      }
      // print out error message if the argument is invalid
      else{
          System.out.println();
          System.out.print(" Error: the arguments given by the user don't match ");
          System.out.println("the expected format, please try again!!");
          System.out.println();
      }
      
      
}

// create method of adjust the brightness/draken of image
public static int[][] adjustBrightness(int[][] newArray, int amount){
        int row = newArray.length;
        int column = newArray[0].length;
        int[][] adjustArray = new int[row][column];
        for(int i=0 ; i< row ; i++){
            for(int j=0 ; j< column ; j++){
                 adjustArray[i][j] = amount + newArray[i][j];
                 // 255 is while
                 if(adjustArray[i][j] > 255){
                      adjustArray[i][j]=255;
                 }
                 // 0 is dark, interval [0,255] is from dark to while
                 else if(adjustArray[i][j] < 0){
                      adjustArray[i][j]=0;
                 }
              
                 
            }
        }
        return adjustArray;
}
// create the method to repeat number horizontal times and vertical times of the image
public static int[][] tileArray(int[][]newArray,int extendRow,int extendColumn){
        int row = newArray.length;
        int column = newArray[0].length;
        int [][] bigArray = new int[row * extendColumn][column * extendRow];
        
        for(int i =0 ; i<row*extendColumn; i++){
	    for(int j=0 ; j<column* extendRow;j++){
	        bigArray[i][j]=newArray[i%row][j%column];
	        
	    }
	    
	}
    return bigArray;
}

// create a new array that we can reflect the image horizontally
public static int [][] ArrayForReflectHorizontial(int [][] newArray){
     for(int i =0 ; i < newArray.length/2 ; i++){
         for( int j=0 ; j < (newArray[0].length) ; j++){
         int temp = newArray[i][j];
         newArray[i][j] = newArray[newArray.length -i-1][j];
         newArray[newArray[i].length -1-i][j] = temp ;

     }
  }
  return newArray;
}
// create a new array that we can reflect the image vertically
public static int [][] ArrayForReflectVertical(int [][] newArray){
     for(int i =0 ; i < newArray.length ; i++){
         for( int j=0 ; j < (newArray[i].length)/2 ; j++){
         int temp = newArray[i][j];
         newArray[i][j] = newArray[i][newArray[i].length -1-j];
         newArray[i][newArray[i].length -1-j] = temp ;

     }
  }
  return newArray;
}

// create a new array that we can convert the picture into ASCII image
public static void TextFile (int [][] twoDm, PrintStream output){
            for(int i =0 ; i < twoDm.length ; i++){
                for( int j = 0; j< twoDm[i].length ; j++){
                      // 0 to 25 : 'M'
                    if( twoDm[i][j]>=0 && twoDm[i][j]<=25){
                       output.print('M');
                    }
                      // 26 to 50 : '$'
                    else if( twoDm[i][j]>=26 && twoDm[i][j]<=50){
                        output.print('$');
                    }
                      // 51 to 76 : 'o'
                    else if( twoDm[i][j]>=51 && twoDm[i][j]<=76){
                        output.print('o');
                    }
                      // 77 to 102 : '|'
                    else if( twoDm[i][j]>=77 && twoDm[i][j]<=102){
                        output.print('|');
                    }
                      // 103 to 127 : '*'
                    else if( twoDm[i][j]>=103 && twoDm[i][j]<=127){
                        output.print('*');
                    }
                      // 128 to 152 : ':'
                    else if( twoDm[i][j]>=128 && twoDm[i][j]<=152){
                        output.print(':');
                    }
                      // 153 to 178 : '='
                    else if( twoDm[i][j]>=153 && twoDm[i][j]<=178){
                        output.print('=');
                    }
                      // 179 to 204 : " \'"
                    else if( twoDm[i][j]>=179 && twoDm[i][j]<=204){
                        output.print("\'");
                    }
                      // 205 to 230 : '.'
                    else if( twoDm[i][j]>=205 && twoDm[i][j]<=230){
                        output.print('.');
                    }
                      // 231 to 255 : ' ' while space
                    else if( twoDm[i][j]>=231 && twoDm[i][j]<=255){
                        output.print(' ');
                    }
                  
                }
                output.println();
            }
    
    } 

    // THIS METHOD MAY BE CALLED, BUT MUST NOT BE MODIFIED!
    // This method reads an image file.
    // expects one parameter: a filename of an image file to be read
    // returns a 2D array of ints representing grayscale values in the input image
    public static int[][] readGrayscaleImage(String filename) {
        int [][] result = null; //create the array
        try {
            File imageFile = new File(filename);    //create the file
            BufferedImage image = ImageIO.read(imageFile);
            int height = image.getHeight();
            int width  = image.getWidth();
            result = new int[height][width];        //read each pixel value
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int rgb = image.getRGB(x, y);
                    result[y][x] = rgb & 0xff;
                }
            }
        }
        catch (IOException ioe) {
            System.err.println("Problems reading file named " + filename);
            System.exit(-1);
        }
        return result;  //once we're done filling it, return the new array
    }


    // THIS METHOD MAY BE CALLED, BUT MUST NOT BE MODIFIED!
    // This method creates an output image based on an array of ints and writes it to a file.
    // expects two parameters: a filename for the image file that will be created
    //      and a 2D array of ints that will be converted into the image
    public static void writeGrayscaleImage(String filename, int[][] array) {
        int width = array[0].length;
        int height = array.length;

        try {
            BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);    //create the image
            
            //set all its pixel values based on values in the input array
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int rgb = array[y][x];
                    rgb |= rgb << 8;
                    rgb |= rgb << 16;
                    image.setRGB(x, y, rgb);
                }
            }
            
            //write the image to a file
            File imageFile = new File(filename);
            ImageIO.write(image, "jpg", imageFile);
        }
        catch (IOException ioe) {
            System.err.println("Problems writing file named " + filename);
            System.exit(-1);
        }
    }
}
