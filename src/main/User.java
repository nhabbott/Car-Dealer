package main;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
public class User {
	
	@Id @GeneratedValue
	@Column(name = "id")
	private long id;
	
	@Column(name = "firstName")
	private String firstName;
	
	@Column(name = "lastName")
	private String lastName;
	
	@Column(name = "isAdmin")
	private boolean isAdmin;
	
	@Column(name = "userName")
	private String userName;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "salt")
	private String salt;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "wishlist")
	private List<Long> wishlist;
	
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
		this.wishlist = new ArrayList<Long>();
	}
	
	public User(String firstName, String lastName, boolean isAdmin, String userName, String password, String email, List<Long> wishlist) {
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

	public List<Long> getWishlist() {
		return wishlist;
	}

	public void setWishlist(List<Long> wishlist) {
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
	
	
}
