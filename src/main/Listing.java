package main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Listing {

	@SuppressWarnings("unused")
	private int id;
	protected Vehicle vehicle;
	private float price;
	private String datePosted;
	private boolean publishListing;
	
	/** 
	 * 
	 * Constructors
	 * 
	 **/
	public Listing(Vehicle v, float price) {
		// Get current date and format it as a string
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
		Date d = new Date();
		
		// Set listing properties
		this.vehicle = v;
		this.price = price;
		this.datePosted = dateFormat.format(d);
		this.publishListing = false;
	}
	
	public Listing(Vehicle v, float price, boolean publishListing) {
		// Get current date and format it as a string
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
		Date d = new Date();
		
		// Set listing properties
		this.vehicle = v;
		this.price = price;
		this.datePosted = dateFormat.format(d);
		this.publishListing = publishListing;
	}
	
	/** 
	 * 
	 * Getters & Setters
	 * 
	 **/
	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getDatePosted() {
		return datePosted;
	}

	public void setDatePosted(String datePosted) {
		this.datePosted = datePosted;
	}

	public boolean isPublished() {
		return publishListing;
	}

	public void setPublishListing(boolean publishListing) {
		this.publishListing = publishListing;
	}
}
