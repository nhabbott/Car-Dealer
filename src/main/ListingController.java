package main;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
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
	private TableColumn<ListingInfo, String> purchaseColumn;
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
	@FXML
	private TableColumn<ListingInfo, String> myStatusColumn;
	
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
	
	@SuppressWarnings("unchecked")
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
		if (!cache.contains("listings")) {
			// Update listing table
			retrieveListingData();
		} else {
    		List<Listing> l = (List<Listing>) cache.get("listings");
    		
			l.forEach((p) -> {
				// Add request
				ListingInfo r = new ListingInfo(p);			
				listingData.add(r);
			});
		}
		
		// Setup purchase button
		purchaseColumn.setCellFactory(purchaseBtnCellFactory);
		table.setItems(listingData);
		
		initColumnsLU();
		if (!cache.contains("userListings")) {
			// Update user's listing table
			retrieveUserListingData();
		} else {
    		List<Listing> lu = (List<Listing>) cache.get("userListings");
    		
    		lu.forEach((p) -> {
				// Add request
				ListingInfo r = new ListingInfo(p);			
				userListingData.add(r);
			});
		}
		
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
        purchaseColumn.setCellValueFactory(new PropertyValueFactory<>(""));
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
        myStatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
    }
    
    /**
     * Callback for purchase button
     */
    @SuppressWarnings("rawtypes")
    Callback<TableColumn<ListingInfo, String>, TableCell<ListingInfo, String>> purchaseBtnCellFactory = new Callback<TableColumn<ListingInfo, String>, TableCell<ListingInfo, String>>() {
		@Override
		public TableCell call(TableColumn<ListingInfo, String> p) {
			final TableCell<ListingInfo, String> cell = new TableCell<ListingInfo, String>() {

                final Button btn = new Button("Purchase");

                @SuppressWarnings("unchecked")
				@Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        btn.setOnAction(event -> {
                        	// Init ListingManager
                			lm.setup();
                			
                			// Get needed info
                			ListingInfo b = (ListingInfo) getTableView().getItems().get(getIndex());
                			long listingId = (long) b.getId();
                			
                			// Purchase
                			try {
                				lm.setSold(listingId, u.getId());
                			} catch (DatabaseErrorException e1) {
                				e1.printStackTrace();
                			}
                			
                			// Fix the cache
                			if (cache.contains("listings")) {
                				List<Listing> l = (List<Listing>) cache.get("listings");
                				Iterator<Listing> i = l.iterator();
                				
                				while (i.hasNext()) {
                					Listing li = i.next();
                					
                					if (li.getId() == listingId) {
                						i.remove();
                					}
                				}
                				
                				cache.remove("listings");
                				cache.add("listings", l, TimeUnit.MINUTES.toMillis(30));
                			}
                			
                			// Remove from table
                			table.getItems().remove(getIndex());
                        });
                        setGraphic(btn);
                        setText(null);
                    }
                }
            };
            return cell;
		}
    };
	
	/**
	 * Get listing info from DB
	 */
	private void retrieveListingData() {
		lm.setup();
		
		// Return var
		List<Listing> listings = null;
		
		try {
			// Get all listings
			listings = lm.getAll();
			
			// Cache all listings to memory for 30 min
			cache.add("listings", listings, TimeUnit.MINUTES.toMillis(30));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (listings != null) {
			listings.forEach(p -> listingData.add(new ListingInfo(p)));
		}
	}
	
	/**
	 * Get user's active listings from DB
	 */
	private void retrieveUserListingData() {
		// Return var
		List<Listing> lu = null;
		
		try {
			// Get a user's listings
			lu = lm.getUsersListings(u.getId());
			
			// Cache all user's listings
			cache.add("userListings", lu, TimeUnit.MINUTES.toMillis(30));
		} catch (DatabaseErrorException e) {
			e.printStackTrace();
		}
		
		if (lu != null) {
			lu.forEach(p -> userListingData.add(new ListingInfo(p)));
		}
	}
}

