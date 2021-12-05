package main.model;

import static cache.Caching.cache;

import exceptions.DatabaseErrorException;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import managers.UserManager;
import objects.Listing;
import objects.User;

public class PreviousSale {
	private UserManager um = new UserManager();
	
	private final long id;
	
	private final SimpleStringProperty name;
	private final SimpleStringProperty soldTo;
	private final SimpleStringProperty vin;
	private final SimpleStringProperty make;
	private final SimpleStringProperty model;
	private final SimpleIntegerProperty year;
	private final SimpleIntegerProperty mileage;
	private final SimpleFloatProperty price;
	
	/**
	 * Class constructor specifying the listing
	 * @param l - The listing to get info from
	 */
	public PreviousSale(Listing l) {
		// Get user info
		User u = null;
		User s = null;
		um.setup();
		
		if (cache.contains(String.valueOf(l.getUserId())) && cache.contains(String.valueOf(l.getSoldToId()))) {
			u = (User) cache.get(String.valueOf(l.getUserId()));
			s = (User) cache.get(String.valueOf(l.getSoldToId()));
		} else {
			um.setup();
			
			try {
				u = (User) um.get(l.getUserId());
				cache.add(String.valueOf(l.getUserId()), u);
				s = (User) um.get(l.getSoldToId());
				cache.add(String.valueOf(l.getSoldToId()), s);
			} catch (DatabaseErrorException e) {
				e.printStackTrace();
			} finally {
				um.exit();
			}
		}
		
		this.id = l.getId();
		
		if (s == null) {
			this.soldTo = new SimpleStringProperty("Declined");
		} else {
			this.soldTo = new SimpleStringProperty(s.getLastName() + ", " + s.getFirstName());
		}
		
		this.name = new SimpleStringProperty(u.getLastName() + ", " + u.getFirstName());
		this.vin = new SimpleStringProperty(l.getVehicle().getVin());
		this.make = new SimpleStringProperty(l.getVehicle().getMake());
		this.model = new SimpleStringProperty(l.getVehicle().getModel());
		this.year = new SimpleIntegerProperty(l.getVehicle().getYear());
		this.mileage = new SimpleIntegerProperty(l.getVehicle().getMileage());
		this.price = new SimpleFloatProperty(l.getPrice());
	}
	
	/**
	 * Gets the id of the listing
	 * @return long
	 */
	public long getId() {
		return this.id;
	}
	
	/**
	 * Gets the username of the user who created the listing
	 * @return String
	 */
	public String getName() {
		return name.get();
	}
	
	public String getSoldTo() {
		return soldTo.get();
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
}