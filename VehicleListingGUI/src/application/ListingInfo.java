package application;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ListingInfo {

	private final SimpleStringProperty vin;
	private final SimpleStringProperty make;
	private final SimpleStringProperty model;
	private final SimpleIntegerProperty year;
	private final SimpleIntegerProperty mileage;
	private final SimpleFloatProperty price;
	
	
	ListingInfo(Listing l) {
		this.vin = new SimpleStringProperty(l.getVehicle().getVin());
		this.make = new SimpleStringProperty(l.getVehicle().getMake());
		this.model = new SimpleStringProperty(l.getVehicle().getModel());
		this.year = new SimpleIntegerProperty(l.getVehicle().getYear());
		this.mileage = new SimpleIntegerProperty(l.getVehicle().getMileage());
		this.price = new SimpleFloatProperty(l.getPrice());
		
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
	
	
}

