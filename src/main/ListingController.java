package main;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import exceptions.DatabaseErrorException;

import static cache.Caching.cache;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.model.ListingInfo;
import managers.ListingManager;
import objects.Listing;
import objects.User;

public class ListingController implements Initializable {
	
	// For scene changes
	private Main m = new Main();
	
	// For retrieving listings
	private ListingManager lm = new ListingManager();
	
	// Get User
	User u = (User) cache.get("user");
	
	@FXML
	private Button sellVehicleButton;
	@FXML
	private Button toAdminButton;
	@FXML
	private Button logoutButton;
	@FXML
	private Button purchaseButton;
	@FXML 
	private Label username; 
	@FXML
	private Tab allListingsTab;
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
	@FXML
	private TableColumn<ListingInfo, Button> purchaseColumn;
	@FXML
	private Tab myListingsTab;
	@FXML
	private TableView<ListingInfo> myTable;
	@FXML 
	private TableColumn<ListingInfo, String> myVINColumn; 
	@FXML 
	private TableColumn<ListingInfo, String> myMakeColumn;
	@FXML 
	private TableColumn<ListingInfo, String> myModelColumn;
	@FXML 
	private TableColumn<ListingInfo, Integer> myYearColumn; 
	@FXML 
	private TableColumn<ListingInfo, Integer> myMileageColumn; 
	@FXML 
	private TableColumn<ListingInfo, Integer> myPriceColumn;
	
	private ObservableList<ListingInfo> listingData = FXCollections.observableArrayList();
	private ObservableList<ListingInfo> userListingData = FXCollections.observableArrayList();
	
	/**
	 * Scene changer
	 * @param e
	 * @throws IOException
	 */
	public void goToSellVehicle(ActionEvent e) throws IOException {
		m.changeScene("sell.fxml");
	}
	
	/**
	 * Scene changer
	 * @param e
	 * @throws IOException
	 */
	public void goToAdmin(ActionEvent e) throws IOException {
		m.changeScene("admin.fxml");
	}
	
	/**
	 * Scene changer
	 * @param e
	 * @throws IOException
	 */
	public void goToPurchase(ActionEvent e) throws IOException {
		m.changeScene("purchase.fxml");
	}
	
	/**
	 * Logout button handler
	 * @param e
	 * @throws IOException
	 */
	public void logOut(ActionEvent e) throws IOException {
		cache.clear();
		m.changeScene("login.fxml");
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Check admin button
		if (u.isAdmin()) {
			toAdminButton.setDisable(false);
		} else {
			toAdminButton.setDisable(true);
		}
		
		// Get user's name
		username.setText(u.getFirstName() + " " + u.getLastName());
		
		// Init table columns
		initColumnsL();
		initColumnsLU();
		
		// Update listing table
		populateListings(retrieveListingData());
		table.setItems(listingData);
		
		// Update user's listing table
		populateUserListings(retrieveUserListingData());
		myTable.setItems(userListingData);
	}

    /**
     * Initializes the listing column headers
     */
    private void initColumnsL() {
    	VINColumn.setCellValueFactory(new PropertyValueFactory<>("vin"));
        makeColumn.setCellValueFactory(new PropertyValueFactory<>("make"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        mileageColumn.setCellValueFactory(new PropertyValueFactory<>("mileage"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        purchaseColumn.setCellValueFactory(new PropertyValueFactory<>("purchase"));
    }

    /**
     * Initializes the user listing column headers
     */
    private void initColumnsLU() {
    	myVINColumn.setCellValueFactory(new PropertyValueFactory<>("vin"));
        myMakeColumn.setCellValueFactory(new PropertyValueFactory<>("make"));
        myModelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        myYearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        myMileageColumn.setCellValueFactory(new PropertyValueFactory<>("mileage"));
        myPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
	
	/**
	 * Get listing info from DB
	 * @return List<Listing>
	 */
	private List<Listing> retrieveListingData() {
		lm.setup();
		
		// Return var
		List<Listing> listings = null;
		
		try {
			listings = lm.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listings;
	}
	
	/**
	 * Get user's active listings from DB
	 * @return List<Listing>
	 */
	private List<Listing> retrieveUserListingData() {
		// Return var
		List<Listing> listings = null;
		
		try {
			listings = lm.getUsersListings(u.getId());
		} catch (DatabaseErrorException e) {
			e.printStackTrace();
		}
		
		return listings;
	}
	
	/**
	 * Create table info from listings
	 * @param List<Listing>
	 */
	private void populateUserListings(final List<Listing> listings) {
		listings.forEach(p -> userListingData.add(new ListingInfo(p)));
	}
	
	/**
	 * Create table info from listings
	 * @param List<Listing>
	 */
	private void populateListings(final List<Listing> listings) {
		listings.forEach(p -> listingData.add(new ListingInfo(p)));
	}
}

