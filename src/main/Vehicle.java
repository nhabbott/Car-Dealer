package main;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "VEHICLE")
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
	@Id @GeneratedValue
	@Column(name = "id")
	private long id;	
	
	@Column(name = "vin")
	private String vin;
	
	@Column(name = "type")
	@Enumerated(EnumType.STRING)
	private vehicleType type;
	
	@Column(name = "size")
	@Enumerated(EnumType.STRING)
	private vehicleSize size;
	
	@Column(name = "year")
	private int year;
	
	@Column(name = "make")
	private String make;
	
	@Column(name = "model")
	private String model;
	
	@Column(name = "cylinders")
	@Enumerated(EnumType.STRING)
	private numOfCylinders cylinders;
	
	@Column(name = "trans")
	@Enumerated(EnumType.STRING)
	private vehicleTrans trans;
	
	@Column(name = "fuel")
	@Enumerated(EnumType.STRING)
	private fuelType fuel;
	
	@Column(name = "countryOfProd")
	private String countryOfProd;
	
	@Column(name = "mileage")
	private int mileage;
	
	@Column(name = "age")
	private int age;
	
	/** 
	 * 
	 * Constructors
	 * 
	 **/
	public Vehicle() {}
	
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
	@Id
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getVin() {
		return vin;
	}
	
	public void setVin(String vin) {
		this.vin = vin;
	}
	
	@Enumerated(EnumType.STRING)
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

	@Enumerated(EnumType.STRING)
	public vehicleSize getSize() {
		return size;
	}

	public void setSize(vehicleSize size) {
		this.size = size;
	}

	//@Enumerated(EnumType.STRING)
	public numOfCylinders getCylinders() {
		return cylinders;
	}

	public void setCylinders(numOfCylinders cylinders) {
		this.cylinders = cylinders;
	}

	@Enumerated(EnumType.STRING)
	public vehicleTrans getTrans() {
		return trans;
	}

	public void setTrans(vehicleTrans trans) {
		this.trans = trans;
	}

	@Enumerated(EnumType.STRING)
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
