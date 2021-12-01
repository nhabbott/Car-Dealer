package main;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static cache.Caching.cache;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import main.model.ListingInfo;
import managers.ListingManager;
import objects.Listing;
import objects.User;

public class ListingController implements Initializable {
	
	// For scene changes
	protected Main m = new Main();
	
	// For retrieving listings
	protected ListingManager lm = new ListingManager();
	
	@FXML
	private Button sellVehicleButton;
	@FXML
	private Button logoutButton;
	@FXML
	private Button purchaseButton;
	@FXML 
	private Label username; 
	@FXML
	private TableView<ListingInfo> table;
	@FXML 
	private TableColumn<ListingInfo, String> VINColumn; 
	@FXML 
	private TableColumn<ListingInfo, String> makeColumn;
	@FXML 
	private TableColumn<ListingInfo, String> modelColumn;
	@FXML 
	private TableColumn<ListingInfo, Integer> yearColumn; 
	@FXML 
	private TableColumn<ListingInfo, Integer> mileageColumn; 
	@FXML 
	private TableColumn<ListingInfo, Integer> priceColumn;
		
	private ObservableList<ListingInfo> data = FXCollections.observableArrayList();
	
	public void goToSellVehicle(ActionEvent e) throws IOException {
		m.changeScene("sell.fxml");
	}
	
	public void goToPurchase(ActionEvent e) throws IOException {
		m.changeScene("purchase.fxml");
	}
	
	public void logOut(ActionEvent e) throws IOException {
		cache.clear();
		m.changeScene("login.fxml");
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Get user from cache
		User u = (User) cache.get("user");
		
		// Get user's name
		username.setText(u.getFirstName() + " " + u.getLastName());
		
		// Update Table
		//populate(retrieveData());
		//table.setItems(data);
		
		lm.setup();
	}

	
	// Obtain listings from database as a list
	private List<Listing> retrieveData() {
		// Return var
		List<Listing> listings = null;
		
		try {
			listings = lm.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lm.exit();
		}
		
		return listings;
	}
	
	// Create table info from listings
	private void populate(final List<Listing> listings) {
		listings.forEach(p -> data.add(new ListingInfo(p)));
	}
}

