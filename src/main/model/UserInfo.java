package main.model;

import javafx.beans.property.SimpleStringProperty;
import objects.User;

public class UserInfo {
	private final SimpleStringProperty username;
	private final SimpleStringProperty firstName;
	private final SimpleStringProperty lastName;
	private final SimpleStringProperty email;
	private final SimpleStringProperty isAdmin;
	
	/**
	 * 
	 * @param u
	 */
	public UserInfo(User u) {
		this.username = new SimpleStringProperty(u.getUserName());
		this.firstName = new SimpleStringProperty(u.getFirstName());
		this.lastName = new SimpleStringProperty(u.getLastName());
		this.email = new SimpleStringProperty(u.getEmail());
		
		// Convert bool to text
		if (u.isAdmin()) {
			this.isAdmin = new SimpleStringProperty("True");
		} else {
			this.isAdmin = new SimpleStringProperty("False");
		}
	}

	/**
	 * Gets the username of the user
	 * @return String
	 */
	public String getUsername() {
		return username.get();
	}

	/**
	 * Gets the first name of the user
	 * @return String
	 */
	public String getFirstName() {
		return firstName.get();
	}

	/**
	 * Gets the last name of the user
	 * @return String
	 */
	public String getLastName() {
		return lastName.get();
	}

	/**
	 * Gets the email of the user 
	 * @return String
	 */
	public String getEmail() {
		return email.get();
	}

	/**
	 * Gets whether or not the user is admin
	 * @return String
	 */
	public String getIsAdmin() {
		return isAdmin.get();
	}
}
