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
	
	private final RequestButton accept;
    private final RequestButton decline;
	
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
	
	public long getId() {
		return this.id;
	}
	public String getName() {
		return name.get();
	}
	public String getVin() {
		return vin.get();
	}
	public String getMake() {
		return make.get();
	}
	public String getModel() {
		return model.get();
	}
	public int getYear() {
		return year.get();
	}
	public int getMileage() {
		return mileage.get();
	}
	public float getPrice() {
		return price.get();
	}
	public RequestButton getAcceptButton() {
		return accept;
	}
	public RequestButton getDeclineButton() {
		return decline;
	}
}