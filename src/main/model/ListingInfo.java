package main.model;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import objects.Listing;
import objects.Vehicle;

public class ListingInfo {
	
	private final SimpleStringProperty vin;
	private final SimpleStringProperty make;
	private final SimpleStringProperty model;
	private final SimpleIntegerProperty year;
	private final SimpleIntegerProperty mileage;
	private final SimpleFloatProperty price;
	private final SimpleStringProperty status;
	private final long id;
	
	/**
	 * Class constructor specifying the listing
	 * @param l - The listing to get info from
	 * @see Listing
	 */
	public ListingInfo(Listing l) {
		Vehicle v = l.getVehicle();
		
		this.vin = new SimpleStringProperty(v.getVin());
		this.make = new SimpleStringProperty(v.getMake());
		this.model = new SimpleStringProperty(v.getModel());
		this.year = new SimpleIntegerProperty(v.getYear());
		this.mileage = new SimpleIntegerProperty(v.getMileage());
		this.price = new SimpleFloatProperty(l.getPrice());
		
		if (l.getSoldToId() == 0 && l.isPublished()) {
			this.status = new SimpleStringProperty("Listed");
		} else if (l.getSoldToId() != 0) {
			this.status = new SimpleStringProperty("Sold");
		} else if (!l.isPublished()) {
			this.status = new SimpleStringProperty("Pending Approval");
		} else {
			this.status = new SimpleStringProperty("Denied");
		}
		
		this.id = l.getId();
	}
	
	/**
	 * Gets the VIN of the listing 
	 * @return String
	 */
	public String getVin() {
		return vin.get();
	}
	
	/**
	 * Gets the make of the listing 
	 * @return String
	 */
	public String getMake() {
		return make.get();
	}
	
	/**
	 * Gets the model of the listing
	 * @return String
	 */
	public String getModel() {
		return model.get();
	}
	
	/**
	 * Gets the year of the listing
	 * @return int
	 */
	public int getYear() {
		return year.get();
	}
	
	/**
	 * Gets the mileage of the listing
	 * @return int
	 */
	public int getMileage() {
		return mileage.get();
	}
	
	/**
	 * Gets the price of the listing
	 * @return float
	 */
	public float getPrice() {
		return price.get();
	}
	
	/**
	 * Gets the id of the listing
	 * @return long
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * Gets the status of the listing
	 * @return String
	 */
	public String getStatus() {
		return status.get();
	}
}

