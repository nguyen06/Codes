/*******************************************************************************
 * For Assignment #7   
 * Name: Ngoc Thinh Nguyen
 *Program name: Data Analysis
 *Program objects:      The program focus on how to use a class to instantiate 
 *                      objects,how to use instance method, sort and search for 
 *                      data in the array, create and use an array of object, 
 *                      and write ouput to the file.
 *Program Desciption: - This program name is CO2EmissionReport.java, which read
 *                      and use the information from data file that contains 
 *                      information on carbon dioxide emissions per country.
 *                    - implement two methods to  selection sorts the data in 
 *                      the one for total emission, and onther is for per-person 
 *                      road emissions.
 * program input:       asking the user to enter the data file name, and 
 *                      continue to ask user enter the data file name if the one 
 *                      entered is invalid.
 * program output:      Print the report to output file, which reports on the 
 *                      highest and lowest total CO2 emission producers, and 
 *                      highest and lowest per person CO2 emission producers and
 *                      rank of Canada in the list.
 * Due: Friday December 5, 8 pm
 ******************************************************************************/

import java.util.*; // use for Scanner
import java.io.*;  // use for file, FileNotFoundException
public class CO2EmissionReport{
   public static void main(String[] agrs){
   	String filename; 
   	Scanner console = new Scanner(System.in);
   	System.out.print("Enter the name of the file: ");
   	boolean loop = true;
   	
    // use the while loop to repeat asking user to enter the file name as the 
    // one enter is invalid. I use boolean since we need the condition to repeat
     while(loop){
     	     
     	// using the catching an exception
        try{
            filename = console.nextLine();
            Scanner input = new Scanner(new File(filename));
            
            // this use to print to the file, look at later
            PrintStream output = new PrintStream(new File("CarbonReport.txt"));
            
            // using the valid file name to read information in file
            CO2Data[] array = readData(filename);
            
            // use the array to sort the total emission 
            sortByTotalEmissions(array);
            
            // after sort for total Emission, can can print with contry is 
            // highest and lowest CO2 emission, and the rank of Canada.
            output.println("\n");
            output.print("The country with lowest total emissions is:    ");
            output.println(array[0].getCountry()+ " "+ array[0].getTotalCO2());
            output.print("The coutry with the highest total emossions is:   ");
            output.print(array[array.length-1].getCountry()+ "  ");
            output.println(array[array.length-1].getTotalCO2());
            String name = "Canada";
            for(int i=0 ;i< array.length ; i++){
       	       if(array[i].getCountry().equals (name)){
       	         output.print("Canada is ranked "+ (i+1));
       	         output.println(" out of 10 lowest for total emossions");
       	        }
       	    }
            output.println("\n\n");
            
            // now we also use the selection sort for CO2 per-person, by 
            // using the array, simce the array have change from the sort for
            // total emission, we need to ceart the new sort for CO2 per peron
            // we can not print from the fisrt array
            sortByCO2perperson(array);
            
            
            // print which contries has highest and lowest CO2 per-person 
            // ranl of Canada to output file
            output.print("The country with lowest per person emissions is ");
            output.println(array[0].getCountry()+" "+array[0].getCO2PerPerson());
            output.print("The country with highest per person emissions is ");
            output.print(array[array.length-1].getCountry()+ " ");
            output.println(array[array.length-1].getCO2PerPerson());
            for(int i=0 ;i< array.length ; i++){
       	       if(array[i].getCountry().equals (name)){
       	         output.print("Canada is ranked "+ (i+1));
       	         output.println(" out of 10 lowest for total emossions");
       	      }
            }
            break;
            
          }
          catch(java.io.FileNotFoundException ex){ 
            System.out.print(" The file name does not exist. ");
            System.out.print("Enter the new file name: ");
            loop = true;
        }
     }
}
  
   // use the file name to read the information in the file and put them into
   // the array, we don't use the first line, and the second line is a number of
   // how many contries will be sort.So , we can use the second line to create 
   // the size of array. 
   // by using the CO2Data.class, we can put all information of each coutry into
   // the column (object)
   // return the array with full information we need to sort, now back to main
   public static CO2Data[] readData( String filename){
    CO2Data [] array = null;
    try{
      Scanner input = new Scanner(new File(filename));
      String firstRow = input.nextLine();
      //System.out.println(firstRow);
      int size = input.nextInt();
      //System.out.println(size);
      
      array = new CO2Data[size];
      for( int i =0 ; i< size ; i++){
           array[i]= new CO2Data();
           
      }
      // using for loop to fill information to array
     for(int i=0; i< size; i++){
          array[i].setCountry(input.next()); // fill the contry
          array[i].setTotalCO2(input.nextDouble()); // fill the total CO2
          array[i].setRoadCO2(input.nextDouble()); // fill the Road CO2
          array[i].setCO2PerPerson(input.nextDouble());// fill the CO2 perperson
          array[i].setCarsPerPerson(input.nextInt()); // fill CarsPerPerson
          
             
          //System.out.println(i);
          }
         
          
      }catch(java.io.FileNotFoundException ex){
         System.out.println(" File not found ");
         System.exit(-1);
      }
      return array;
   }
   
   
   
   
   // this method use the array we created last step to sort for Total emission
   // we have to find index of mallest value first, then use swap method to swap
   // the value of the smallest to first index of array, keep process untill 
   // the array is sorted. Selection sort, After finishing this method go back 
   // main and we can print to file.
   public static void sortByTotalEmissions( CO2Data[]array){
       int index =0;
       for(int i=0 ; i< array.length ; i++){
           index = indexOfSmallest(array , i);
           Swap(array, index , i);
       }
       
     }
     // use the array, and position i of array to find the  index  of smallest
   // value of getTotalCO2 by using the CO2Data class. 
   //  return the index of the smallest value
   public static int indexOfSmallest( CO2Data[] array, int start){
         CO2Data Smallest = array[start];
         int index = start;
         for(int i = start +1 ; i< array.length ; i++){
              if(array[i].getTotalCO2() < Smallest.getTotalCO2()){
                  Smallest = array[i];
                  index = i;
              }
         }
        return index;
   }
   
   // now we know the index of mallest value, we can swap the recent value with 
   // the mallest value, 
   // the parameter should be a index or mallest value, and index of recent 
   // value, and the array, the array can be sort at the first position, and 
   // second, and until finish. 
   public static void Swap( CO2Data[] array, int x , int y){
         CO2Data temp = array[x];
         array[x] = array[y];
         array[y] = temp;
   }
   
       
   // similar to sortbyTotalCO2, can we do the same thing for sort by CO2 per 
   // person, input the array, find the index of smallest, and swap the smallest
   // value with the current value, the process keep going to finish sorting
   // then look go back to main , we can print 
   public static void sortByCO2perperson(CO2Data[]array){
       int index =0;
       for(int i=0 ; i< array.length ; i++){
           index = indexOfSmallest2(array , i);
           Swap(array, index , i); // use the same swap method 
       }
      
   }
   // use the array, and position i of array to find the smallest index value
   // of CO2 per person , and return the index of smallest value
    public static int indexOfSmallest2( CO2Data[] array, int start){
         CO2Data Smallest = array[start];
         int index = start;
         for(int i = start +1 ; i< array.length ; i++){
              if(array[i].getCO2PerPerson() < Smallest.getCO2PerPerson()){
                  Smallest = array[i];
                  index = i;
              }
         }
        return index;
   }
  
}