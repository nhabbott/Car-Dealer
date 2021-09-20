package main;

import java.util.Calendar;

public class Vehicle {
	enum vehicleType {
		coupe,
		crossover,
		truck,
		sedan,
		sportscar,
		hatchback
	}
	
	enum vehicleSize {
		compact,
		mid,
		full
	}
	
	enum vehicleTrans {
		automatic,
		manual
	}
	
	enum numOfCylinders {
		four,
		six,
		eight,
		twelve
	}
	
	enum fuelType {
		gasoline,
		diesel,
		electric,
		hybrid
	}
	
	//TODO Figure out implementation of optional features, color, fuel economy, history, and location
	
	private int id;
	private String vin;
	private vehicleType type;
	private vehicleSize size;
	private int year;
	private String make;
	private String model;
	private numOfCylinders cylinders;
	private vehicleTrans trans;
	private fuelType fuel;
	private String countryOfProd;
	private int mileage;
	private final int age;
	
	
	public Vehicle(String vin, vehicleType type, vehicleSize size, int year, String make, String model, numOfCylinders cylinders, vehicleTrans trans, fuelType fuel, String countryOfProd, int mileage) {
		this.vin = vin;
		this.type = type;
		this.size = size;
		this.year = year;
		this.make = make;
		this.model = model;
		this.cylinders = cylinders;
		this.trans = trans;
		this.fuel = fuel;
		this.countryOfProd = countryOfProd;
		this.mileage = mileage;
		this.age = ((Calendar.getInstance().get(Calendar.YEAR)) - (year));	// Subtract current year from year vehicle was manufactured to get the age
	}

	/** 
	 * 
	 * Getters & Setters
	 * 
	 **/
	public String getVin() {
		return vin;
	}
	
	public void setVin(String vin) {
		this.vin = vin;
	}
	
	public vehicleType getType() {
		return type;
	}
	
	public void setType(vehicleType type) {
		this.type = type;
	}
	
	public String getMake() {
		return make;
	}
	
	public void setMake(String make) {
		this.make = make;
	}
	
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public String getCountryOfProd() {
		return countryOfProd;
	}
	
	public void setCountryOfProd(String countryOfProd) {
		this.countryOfProd = countryOfProd;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public int getMileage() {
		return mileage;
	}
	
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public vehicleSize getSize() {
		return size;
	}

	public void setSize(vehicleSize size) {
		this.size = size;
	}

	public numOfCylinders getCylinders() {
		return cylinders;
	}

	public void setCylinders(numOfCylinders cylinders) {
		this.cylinders = cylinders;
	}

	public vehicleTrans getTrans() {
		return trans;
	}

	public void setTrans(vehicleTrans trans) {
		this.trans = trans;
	}

	public fuelType getFuel() {
		return fuel;
	}

	public void setFuel(fuelType fuel) {
		this.fuel = fuel;
	}
	
	public int getAge() {
		return age;
	}
}
