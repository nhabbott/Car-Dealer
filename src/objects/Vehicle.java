package objects;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="vehicle", uniqueConstraints={
		@UniqueConstraint(columnNames="vin")
})
public class Vehicle {
	public enum vehicleType {
		coupe("coupe"),
		crossover("crossover"),
		truck("truck"),
		sedan("sedan"),
		sportscar("sportscar"),
		hatchback("hatchback");

		private String choice;
		
		vehicleType(String choice) {
			this.choice = choice;
		}
		
		public String getChoice() {
			return choice;
		}
	}
	
	public enum vehicleSize {
		compact("compact"),
		mid("mid"),
		full("full");
		
		private String choice;
		
		vehicleSize(String choice) {
			this.choice = choice;
		}
		
		public String getChoice() {
			return choice;
		}
	}
	
	public enum vehicleTrans {
		automatic("automatic"),
		manual("manual");
		
		private String choice;
		
		vehicleTrans(String choice) {
			this.choice = choice;
		}
		
		public String getChoice() {
			return choice;
		}
	}
	
	public enum numOfCylinders {
		four("four"),
		six("six"),
		eight("eight"),
		twelve("twelve");
		
		private String choice;
		
		numOfCylinders(String choice) {
			this.choice = choice;
		}
		
		public String getChoice() {
			return choice;
		}
	}
	
	public enum fuelType {
		gasoline("gasoline"),
		diesel("diesel"),
		electric("electric"),
		hybrid("hybrid");
		
		private String choice;
		
		fuelType(String choice) {
			this.choice = choice;
		}
		
		public String getChoice() {
			return choice;
		}
	}
	
	//TODO Figure out implementation of optional features, color, fuel economy, history, and location
	@Id
	@Column(name="vid", unique=true, nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;	
	
	@Column(name="vin", unique=true, nullable=false)
	private String vin;
	
	@Column(name="type", unique=false, nullable=false)
	@Enumerated(EnumType.STRING)
	private vehicleType type;
	
	@Column(name="size", unique=false, nullable=false)
	@Enumerated(EnumType.STRING)
	private vehicleSize size;
	
	@Column(name="year", unique=false, nullable=false)
	private int year;
	
	@Column(name="make", unique=false, nullable=false)
	private String make;
	
	@Column(name="model", unique=false, nullable=false)
	private String model;
	
	@Column(name="cylinders", unique=false, nullable=false)
	@Enumerated(EnumType.STRING)
	private numOfCylinders cylinders;
	
	@Column(name="trans", unique=false, nullable=false)
	@Enumerated(EnumType.STRING)
	private vehicleTrans trans;
	
	@Column(name="fuel", unique=false, nullable=false)
	@Enumerated(EnumType.STRING)
	private fuelType fuel;
	
	@Column(name="countryOfProd", unique=false, nullable=false)
	private String countryOfProd;
	
	@Column(name="mileage", unique=false, nullable=false)
	private int mileage;
	
	@Column(name="age", unique=false, nullable=false)
	private int age;
	
	@OneToOne(mappedBy="vehicle", cascade=CascadeType.ALL)
	private Listing listing;
	
	/**
	 * Class constructor
	 */
	public Vehicle() {}
	
	/**
	 * Class constructor specifying Vehicle details
	 * @param vin
	 * @param type
	 * @param size
	 * @param year
	 * @param make
	 * @param model
	 * @param cylinders
	 * @param trans
	 * @param fuel
	 * @param countryOfProd
	 * @param mileage
	 */
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
	 * Gets the id of the vehicle
	 * @return long
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * Sets the id of the vehicle
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * Gets the VIN of the vehicle
	 * @return String
	 */
	public String getVin() {
		return vin;
	}
	
	/**
	 * Sets the VIN of the listing
	 * @param vin
	 */
	public void setVin(String vin) {
		this.vin = vin;
	}
	
	/**
	 * Gets the type of the vehicle
	 * @return enum - vehicleType
	 */
	@Enumerated(EnumType.STRING)
	public vehicleType getType() {
		return type;
	}
	
	/**
	 * Sets the type of the vehicle
	 * @param enum - vehicleType
	 */
	public void setType(vehicleType type) {
		this.type = type;
	}
	
	/**
	 * Gets the make of the vehicle
	 * @return String
	 */
	public String getMake() {
		return make;
	}
	
	/**
	 * Sets the make of the vehicle
	 * @param make
	 */
	public void setMake(String make) {
		this.make = make;
	}
	
	/**
	 * Gets the model of the vehicle
	 * @return String
	 */
	public String getModel() {
		return model;
	}
	
	/**
	 * Sets the model of the vehicle
	 * @param model
	 */
	public void setModel(String model) {
		this.model = model;
	}
	
	/**
	 * Gets the country of production of the vehicle
	 * @return String
	 */
	public String getCountryOfProd() {
		return countryOfProd;
	}
	
	/**
	 * Sets the country of production of the vehicle
	 * @param countryOfProd
	 */
	public void setCountryOfProd(String countryOfProd) {
		this.countryOfProd = countryOfProd;
	}
	
	/**
	 * Gets the year of the vehicle
	 * @return int
	 */
	public int getYear() {
		return year;
	}
	
	/**
	 * Sets the year of the vehicle
	 * @param year
	 */
	public void setYear(int year) {
		this.year = year;
	}
	
	/**
	 * Gets the mileage of the vehicle
	 * @return int
	 */
	public int getMileage() {
		return mileage;
	}
	
	/**
	 * Sets the mileage of the vehicle
	 * @param mileage
	 */
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	/**
	 * Gets the size of the vehicle
	 * @return enum - vehicleSize
	 */
	@Enumerated(EnumType.STRING)
	public vehicleSize getSize() {
		return size;
	}

	/**
	 * Sets the size of the vehicle
	 * @param size
	 */
	public void setSize(vehicleSize size) {
		this.size = size;
	}
	
	/**
	 * Gets the number of cylinders of the vehicle
	 * @return enum - numOfCylinders
	 */
	//@Enumerated(EnumType.STRING)
	public numOfCylinders getCylinders() {
		return cylinders;
	}

	/**
	 * Sets the number of cylinders of the vehicle
	 * @param cylinders
	 */
	public void setCylinders(numOfCylinders cylinders) {
		this.cylinders = cylinders;
	}

	/**
	 * Gets the transmission type of the vehicle
	 * @return enum - vehicleTrans
	 */
	@Enumerated(EnumType.STRING)
	public vehicleTrans getTrans() {
		return trans;
	}

	/**
	 * Sets the transmission type of the vehicle
	 * @param trans
	 */
	public void setTrans(vehicleTrans trans) {
		this.trans = trans;
	}

	/**
	 * Gets the fuel type of the vehicle
	 * @return enum - fuelType
	 */
	@Enumerated(EnumType.STRING)
	public fuelType getFuel() {
		return fuel;
	}

	/**
	 * Sets the fuel type of the vehicle
	 * @param fuel
	 */
	public void setFuel(fuelType fuel) {
		this.fuel = fuel;
	}
	
	/**
	 * Gets the age of the vehicle
	 * @return int
	 */
	public int getAge() {
		return age;
	}
	
	/**
	 * Sets the age of the vehicle
	 * @param age
	 */
	public void setAge(int age) {
		this.age = age;
	}
}
