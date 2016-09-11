/***************************************************************************************
 * For Assignment #3    ID: V00817304
 * Name: Ngoc Thinh Nguyen
 *Program name: CarbonCalc.java
 *Program objects: Static Method with: Parameter passing & Return Values
 *Program Desciption: - This propram request user input a values from keyboard, 
 *                      and use them to calculate the CO2 emissions for 
 *                       transportation, electricity, and food. Then print out
 *                      the total carbon footprint in metric tons per years.
 *                    - there are something to known before starting
 *                       + Regular gasoline in north american produces 2.3 kg/litre
 *                       +The average CO2 emission for electricity produce in Canada
 *                          is 0.257 kg/kWh
 *                    - Kilowatt hours per month from you electric bill
 *                    - We need to create a Scanner in main method to pass inputs
 *                      into other methods " Scanner console" 
 *                    - In each method enters the input by using the scanner console
 *                    - Using the Tester.java to test the correctness 
 * program input: the program ask user for 8 inputs: 
 *                                Kilometres of your car travel per day
 *                                fuel Efficiency
 *                                kilowatt hour per month
 *                                number of people in your house
 *                                Enter percent meat and fish eaten: 
 *                                Enter percent Dairy eaten
 *                                Enter percent fruit and vegetable eaten
 *                                Enter percent Carbs eaten
 *                                
 * program output: the total CO2 in tons per year, and the percentage of CO2 for Car, 
 *                 Electricity, and Food
 * Due: Friday October 3, 8 pm
 ***************************************************************************************/




import java.util.*; // can get in put from keyboard
    public class CarbonCalc{
        public static void main(String[] args){
            
            Scanner console = new Scanner(System.in);
                   // get input from Scanner console
            double trans = determineTransportationEmission( console);
            
            double elec = determineElectricityEmission( console);
            
            double food = determineFoodEmission( console);
            
            double CarbonFootprint = calculateTotalEmission( trans, elec, food);
            
            printReport(trans, elec, food, CarbonFootprint);
        
        }
                // use the Scanner console to enter the values, then calculate 
                // and return the kg CO2 for determineTransportationEmission
        public static double determineTransportationEmission( Scanner console){
            System.out.println();
            System.out.print(" Enter Kilometres of your car travel per day:   ");
            double kmPerday = console.nextDouble();
            System.out.println();
            System.out.print(" Enter the fuel Efficiency:                     ");
            double fuelEfficiency = console.nextDouble();
            // calculate the KG CO2 
            // Regular gasoline in north american produces 2.3 kg/litre
            double regularGasOnline = 2.3;  
            double litresUsedPerYear = 365 * ( kmPerday / fuelEfficiency);
            double KgCO2ForTrans = regularGasOnline * litresUsedPerYear ;
            return KgCO2ForTrans;
        }
               // use the console to enter values, then calculate and return the 
               // value of CO2 to the  determineElectricityEmission
        public static double determineElectricityEmission( Scanner console){
            System.out.println();
            System.out.print(" Enter kilowatt hour per month:                 ");
            double kwhPerMonth = console.nextDouble();
            System.out.println();
            System.out.print(" Enter number of people in your house:          ");
            double numPeopleInHome = console.nextDouble();
            System.out.println();
            
            // average CO2 emission for electricity produce in Canada is 0.257 kg/kWh
            double averageCO2EmissionInCA = 0.257;
            double averageCO2EmissionPerHourse = kwhPerMonth * 12 *averageCO2EmissionInCA;
            double KgCO2ForElectricity = averageCO2EmissionPerHourse / numPeopleInHome;
            return KgCO2ForElectricity;
       }
            // use the console to enter values, then calculate and return the 
               // value of CO2 to the  determineFoodEmission
       public static double determineFoodEmission( Scanner console){
            System.out.print(" Enter percent meat and fish eaten:             ");
            double percentMeatFishEaten = console.nextDouble();
            System.out.println();
            System.out.print(" Enter percent Dairy eaten:                     ");
            double percentDairyEaten = console.nextDouble();
            System.out.println();
            System.out.print(" Enter percent fruit and vegetable eaten:       ");
            double percentFruitVegEaten = console.nextDouble();
            System.out.println();
            System.out.print(" Enter percent Carbs eaten:                     ");
            double percentCarbEaten = console.nextDouble();
            System.out.println();     
                 
            // calculation
            double yearKgCO2ForMeat = percentMeatFishEaten * 53.1;
            double yearKgCO2ForDairy = percentDairyEaten * 13.8;
            double yearKgCO2ForFruitVeg = percentFruitVegEaten * 7.6;
            double yearKgCO2ForCarbs = percentCarbEaten * 3.1;
            double kgCO2ForFood = yearKgCO2ForMeat + yearKgCO2ForDairy + 
                                  yearKgCO2ForFruitVeg + yearKgCO2ForCarbs ;
            return kgCO2ForFood;
       }
       
             // calculate the total Kg CO2 for one year per person by ton
       public static double calculateTotalEmission( double trans, double elec, 
                                                    double food){
            
            double totalEmission = trans + elec + food ;
            double totalEmissionByTon = totalEmission / 1000;
            return totalEmissionByTon;
       }
            // Calculate the percentage of CO2 in Transportation, electricity, and food
            // in total a year. Then print out the result
       public static void printReport( double trans, double elec, double food, 
                                       double CarbonFootprint){
           
            System.out.print(" You produce an annual total of " + CarbonFootprint +
                            " metric tons of CO2 per year");
            System.out.println(" The breakdown is as follows:");
            double percentTrans = trans/ (CarbonFootprint * 10);
            System.out.println("    Car               " + percentTrans + " % " );
            double percentElec = elec/ (CarbonFootprint * 10);
            System.out.println("    Electricity       " + percentElec + " % " );
            double percentFood = food/ (CarbonFootprint * 10);
            System.out.println("    Food              " + percentFood + " % " );
            System.out.println();
            
       }
     
 } // end program