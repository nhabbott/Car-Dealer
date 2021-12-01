package objects;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="wishlist", uniqueConstraints={
		@UniqueConstraint(columnNames="wishlistId")
})
public class Wishlist {
	@Id
	@Column(name="wishlistId", unique=true, nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@OneToMany(mappedBy="wishlist")
	private List<Listing> list;
	
	@OneToOne(mappedBy="wishlist", cascade=CascadeType.ALL)
	private User user;
	
	/**
	 * Class constructor
	 */
	public Wishlist() {}

	/**
	 * Class constructor specifying the list of listings
	 * @param List<Listing>
	 */
	public Wishlist(List<Listing> list) {
		this.list = list;
	}
	
	/**
	 * Gets the list of listings
	 * @return List<Listing>
	 */
	public List<Listing> getList() {
		return list;
	}

	/**
	 * Sets the list of listings
	 * @param List<Listing>
	 */
	public void setList(List<Listing> list) {
		this.list = list;
	}

	/**
	 * Gets the id of the wishlist
	 * @return long
	 */
	public long getId() {
		return id;
	}
}
