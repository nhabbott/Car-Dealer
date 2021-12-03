package objects;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="listing", uniqueConstraints={
		@UniqueConstraint(columnNames="listingId")
})
public class Listing {
	
	@Id
	@Column(name="listingId", unique=true, nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;	
	
	@Column(name="userId", unique=false, nullable=false)
	private long userId;
	
	@Column(name="soldToId", unique=false, nullable=true)
	private Long soldToId;
	
	@OneToOne(cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Vehicle vehicle;
	
	@ManyToOne
	@JoinColumn(name="wishlistId")
	private Wishlist wishlist;
	
	@Column(name="price", unique=false, nullable=false)
	private float price;
	
	@Column(name="datePosted", unique=false, nullable=false)
	private String datePosted;
	
	@Column(name="publishListing", unique=false, nullable=false)
	private boolean publishListing;
	
	public Listing() {}
	
	/**
	 * Creates new listing object. Defaults the listing to not be published
	 * 
	 * @param v Vehicle Object
	 * @param price The desired price of the listing
	 */
	public Listing(Vehicle v, float price, long userId) {
		// Get current date and format it as a string
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
		Date d = new Date();
		
		// Set listing properties
		this.vehicle = v;
		this.price = price;
		this.userId = userId;
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
	public Listing(Vehicle v, float price, long userId, boolean publishListing) {
		// Get current date and format it as a string
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
		Date d = new Date();
		
		// Set listing properties
		this.vehicle = v;
		this.price = price;
		this.userId = userId;
		this.datePosted = dateFormat.format(d);
		this.publishListing = publishListing;
	}
	

	/**
	 * Gets the MySQL id of the listing
	 * 
	 * @return id - The id given to the object by MySQL
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * Gets the id of the user who created the listing
	 * 
	 * @return id - The user id
	 */
	public long getUserId() {
		return userId;
	}
	
	public Long getSoldToId() {
		return soldToId;
	}
	
	/**
	 * Gets vehicle of the listing
	 * 
	 * @return Vehicle - The vehicle of the listing
	 */
	public Vehicle getVehicle() {
		return vehicle;
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
