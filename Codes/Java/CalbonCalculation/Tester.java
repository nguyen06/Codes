import java.util.Scanner;
/**
 * Tester.java
 * Created for CSC110 Assignment 3.
 * Students are welcome to use and modify this program to
 * help test the CarbonCalc.java methods.
 */

public class Tester {

public static final double EPSILON = 0.00000001;

/*
	Each of the following methods tests a specific method
	in the CarbonCalc assignment.
	It is recommended that you do not change any methods except
	to comment out the calls in main.
*/

	public static void transportTest() {
		System.out.println("Testing determineTransportationEmission");
		// the input variable will simulate the console.
		String input = "30 22.22";
		Scanner console = new Scanner(input);
		double answer = CarbonCalc.determineTransportationEmission(console);
		if (Math.abs(answer - 1133.438343834) < EPSILON) {
			System.out.println("Calculation correct");
		} else {
			System.out.println("Calculation incorrect");
		}
	}

	public static void elecTest() {
		System.out.println("Testing determineElectricityEmission");
		String input =  "975 4";
		Scanner console = new Scanner(input);
		double answer = CarbonCalc.determineElectricityEmission(console);
		if (Math.abs(answer-751.725) < EPSILON) {
			System.out.println("Calculation correct");
		} else {
			System.out.println("Calculation incorrect");
		}
	}

	public static void foodTest() {
		System.out.println("Testing determineFoodEmission");
		String input = "10 15 45 30"; 
		Scanner console = new Scanner(input);
		double answer = CarbonCalc.determineFoodEmission(console);
		if (Math.abs(answer-1173) < EPSILON) {
			System.out.println("Calculation correct");
		} else {
			System.out.println("Calculation incorrect");
		}
	}

	/*
	Comment out these statements as needed for testing.
	*/

	public static void main(String[] args) {
		transportTest();
		elecTest();
		foodTest();
	}
}
		

		
