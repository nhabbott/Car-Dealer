package objects;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.UpdateTimestamp;

import auth.Authentication;

@Entity
@Table(name="user", uniqueConstraints={
		@UniqueConstraint(columnNames="userId"),
		@UniqueConstraint(columnNames="email")
})
public class User {
	@Id
	@Column(name="userId", unique=true, nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="firstName", unique=false, nullable=false)
	private String firstName;
	
	@Column(name="lastName", unique=false, nullable=false)
	private String lastName;
	
	@Column(name="isAdmin", unique=false, nullable=false)
	private boolean isAdmin;
	
	@Column(name="userName", unique=false, nullable=false)
	private String userName;
	
	@Column(name="password", unique=false, nullable=false)
	private String password;
	
	@Column(name="salt", unique=false, nullable=false)
	private String salt;
	
	@Column(name="email", unique=true, nullable=false)
	private String email;
	
	@OneToOne(cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Wishlist wishlist;
	
	public User() {}

	public User(String firstName, String lastName, boolean isAdmin, String userName, String password, String email) {
		String finalHash = null;
		byte[] bSalt = null;
		
		try {
			bSalt = Authentication.salt();
			finalHash = Authentication.convertToString(Authentication.hash(password, bSalt));
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.isAdmin = isAdmin;
		this.userName = userName;
		this.password = finalHash;
		this.salt = Authentication.convertToString(bSalt);
		this.email = email;
		this.wishlist = new Wishlist();
	}
	
	public User(String firstName, String lastName, boolean isAdmin, String userName, String password, String email, Wishlist wishlist) {
		String finalHash = null;
		byte[] bSalt = null;
		
		try {
			bSalt = Authentication.salt();
			finalHash = Authentication.convertToString(Authentication.hash(password, bSalt));
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.isAdmin = isAdmin;
		this.userName = userName;
		this.password = finalHash;
		this.salt = Authentication.convertToString(bSalt);
		this.email = email;
		this.wishlist = wishlist;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Wishlist getWishlist() {
		return wishlist;
	}

	public void setWishlist(Wishlist wishlist) {
		this.wishlist = wishlist;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSalt() {
		return salt;
	}
}
