package main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LISTING")
public class Listing {
	
	@Id @GeneratedValue
	@Column(name = "id")
	private long id;	
	
	@Column(name = "vehicleId")
	private long vehicleId;
	
	@Column(name = "price")
	private float price;
	
	@Column(name = "datePosted")
	private String datePosted;
	
	@Column(name = "publishListing")
	private boolean publishListing;
	
	/**
	 * Creates new listing object. Defaults the listing to not be published
	 * 
	 * @param v Vehicle Object
	 * @param price The desired price of the listing
	 */
	public Listing(Vehicle v, float price) {
		// Get current date and format it as a string
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
		Date d = new Date();
		
		// Set listing properties
		this.vehicleId = v.getId();
		this.price = price;
		this.datePosted = dateFormat.format(d);
		this.publishListing = false;
	}
	
	/**
	 * Creates new listing object
	 * 
	 * @param v Vehicle Object
	 * @param price The desired price of the listing
	 * @param publishListing Should the listing be shown to customers
	 */
	public Listing(Vehicle v, float price, boolean publishListing) {
		// Get current date and format it as a string
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
		Date d = new Date();
		
		// Set listing properties
		this.vehicleId = v.getId();
		this.price = price;
		this.datePosted = dateFormat.format(d);
		this.publishListing = publishListing;
	}
	

	/**
	 * Gets the MySQL id of the listing
	 * 
	 * @return id - The id given to the object by MySQL
	 */
	@Id
	public long getId() {
		return id;
	}
	
	/**
	 * Gets the current price of the listing
	 * 
	 * @return price - The price of the listing
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * Sets the price of the listing
	 * 
	 * @param price The price of the listing
	 */
	public void setPrice(float price) {
		this.price = price;
	}

	/**
	 * Gets the date for when the listing was listed
	 * 
	 * @return datePosted - The date the listing was listed
	 */
	public String getDatePosted() {
		return datePosted;
	}

	 /**
	  * Sets the date for when the listing was listed
	  * 
	  * @param datePosted The date the listing was listed
	  */
	public void setDatePosted(String datePosted) {
		this.datePosted = datePosted;
	}

	/**
	 * Checks if the listing is currently listed
	 * 
	 * @return publishListing - Whether or not the customer can see the listing
	 */
	public boolean isPublished() {
		return publishListing;
	}

	/**
	 * Sets whether or not the listing is listed
	 * 
	 * @param publishListing Whether or not to list the listing
	 */
	public void setPublishListing(boolean publishListing) {
		this.publishListing = publishListing;
	}
}
