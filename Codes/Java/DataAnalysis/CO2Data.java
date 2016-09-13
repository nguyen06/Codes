// creating the class use for CO2EmissionReport.java
// we set all the countries to setCountry, all the value
// of total CO2 to setTotalCO2, and all CO2 per person to SetCO2PerPerson, 
// because all of value of total CO2 of all country is 
// locate the same object, we can comapare them.
// every element of the array contain the same number of objects, 

public class CO2Data {
   
	private String country;
	private double totalCO2;
	private double roadCO2;
	private double CO2PerPerson;
	private int carsPerPerson;
	
	public CO2Data() {
		country = "";
		totalCO2 = 0;
		roadCO2 = 0;
		CO2PerPerson = 0;
		carsPerPerson = 0;
	}
	
	public String getCountry() {
		return country;
	}
	// array[i].setCountry(input.next());
	// the name of country in the third line goes to 
	// setCountry  
	public void setCountry(String country) {
		this.country = country;
	}
	
	public double getTotalCO2() {
		return totalCO2;
	}
	// array[i].setTotalCO2(input.nextDouble());
	// set the value of total CO2 to object setTotalCO2,
	public void setTotalCO2(double totalCO2) {
		this.totalCO2 = totalCO2;
	}
	
	public double getRoadCO2() {
		return roadCO2;
	}
	// set all value of road CO2 to the same object
	public void setRoadCO2(double roadCO2) {
		this.roadCO2 = roadCO2;
	}
	
	public double getCO2PerPerson() {
		return CO2PerPerson;
	}
	
	// set all the value of CO2 per person to the setCO2PerPerson
	//array[i].setCO2PerPerson(input.nextDouble());
	public void setCO2PerPerson(double cO2PerPerson) {
		this.CO2PerPerson = cO2PerPerson;
	}
	
	public int getCarsPerPerson() {
		return carsPerPerson;
	}
	// set all value read from file in column of Cars per 100 people to
	// the object setCarsPerPerson
	public void setCarsPerPerson(int carsPerPerson) {
		this.carsPerPerson = carsPerPerson;
	}
}
