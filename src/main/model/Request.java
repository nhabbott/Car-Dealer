package main.model;

import exceptions.DatabaseErrorException;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import managers.UserManager;
import objects.Listing;
import objects.RequestButton;
import objects.User;

public class Request {
	private UserManager um = new UserManager();
	
	private final long id;
	
	private final SimpleStringProperty name;
	private final SimpleStringProperty vin;
	private final SimpleStringProperty make;
	private final SimpleStringProperty model;
	private final SimpleIntegerProperty year;
	private final SimpleIntegerProperty mileage;
	private final SimpleFloatProperty price;
	
	private RequestButton accept;
    private RequestButton decline;
	
    /**
     * Class constructor specifying the listing
     * @param l - Listing to get info from
     * @see Listing
     */
	public Request(Listing l) {
		// Get user info
		User u = null;
		um.setup();
		
		try {
			u = (User) um.get(l.getUserId());
		} catch (DatabaseErrorException e) {
			e.printStackTrace();
		} finally {
			um.exit();
		}
		
		// Setup buttons
		this.accept = new RequestButton(this);
		this.decline = new RequestButton(this);
		accept.setText("Approve");
		decline.setText("Deny");
		
		this.id = l.getId();
		
		this.name = new SimpleStringProperty(u.getUserName());
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
	 * Sets the accept button for the listing
	 * @param RequestButton
	 * @see RequestButton
	 */
	public void setAcceptButton(RequestButton a) {
		this.accept = a;
	}
	
	/**
	 * Sets the accept button for the listing
	 * @param RequestButton
	 * @see RequestButton
	 */
	public void setDeclineButton(RequestButton d) {
		this.decline = d;
	}
}