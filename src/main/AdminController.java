package main;

import main.model.PreviousSale;
import main.model.Request;
import main.model.UserInfo;
import managers.ListingManager;
import managers.UserManager;
import objects.Listing;
import objects.RequestButton;
import objects.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import static cache.Caching.cache;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import exceptions.DatabaseErrorException;

public class AdminController implements Initializable {

	// For scene changes
	private Main m = new Main();
	
	// For DB
	private ListingManager lm = new ListingManager();
	private UserManager um = new UserManager();
	
    @FXML
    private TableView<Request> requestTableView;
    @FXML
    private TableView<PreviousSale> sellsTableView;
    @FXML
    private TableColumn<Request, Button> acceptTableColumnR;
    @FXML
    private TableColumn<Request, Button> declineTableColumnR;
    @FXML
    private TableColumn<Request, String> makeTableColumnR;
    @FXML
    private TableColumn<PreviousSale, String> makeTableColumnS;
    @FXML
    private TableColumn<Request, String> mileageTableColumnR;
    @FXML
    private TableColumn<PreviousSale, String> mileageTableColumnS;
    @FXML
    private TableColumn<Request, String> modelTableColumnR;
    @FXML
    private TableColumn<PreviousSale, String> modelTableColumnS;
    @FXML
    private TableColumn<Request, String> nameTableColumnR;
    @FXML
    private TableColumn<PreviousSale, String> nameTableColumnS;
    @FXML
    private TableColumn<Request, String> priceTableColumnR;
    @FXML
    private TableColumn<PreviousSale, String> priceTableColumnS;
    @FXML
    private TableColumn<Request, String> vinTableColumnR;
    @FXML
    private TableColumn<PreviousSale, String> vinTableColumnS;
    @FXML
    private TableColumn<Request, String> yearTableColumnR;
    @FXML
    private TableColumn<PreviousSale, String> yearTableColumnS;
    @FXML
    private TableView<UserInfo> userListTable;
    @FXML
    private TableColumn<User, String> usernameColumn;
    @FXML
    private TableColumn<User, String> firstNameColumn;
    @FXML
    private TableColumn<User, String> lastNameColumn;
    @FXML
    private TableColumn<User, String> emailColumn;
    @FXML
    private TableColumn<User, String> isAdminColumn;
    @FXML
    private TextField usernameSearchBar;
    @FXML
    private TextField makeAdminTextField;
    @FXML
    private Button makeAdminButton;
    @FXML
    private Button toListingsButton;
    @FXML
    private Button exitButton;
    @FXML
    private Button refreshButton;
    @FXML
    private Label adminNameLabel;
    
    private ObservableList<Request> requestData = FXCollections.observableArrayList();
    private ObservableList<PreviousSale> soldData = FXCollections.observableArrayList();
    private ObservableList<UserInfo> userData = FXCollections.observableArrayList();

    /**
     * 
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Get user
    	User u = (User) cache.get("user");
    	
    	// Set user label
    	adminNameLabel.setText(u.getFirstName() + " " + u.getLastName());
    	
    	// Init ListingManager & UserManager
    	lm.setup();
    	um.setup();
    	
    	// Fill table
    	initTable();
    }

    /**
     * Initializes the table with information from the database
     */
    private void initTable() {
    	
    	// Setup request table
    	initColumnsR();
    	if (!cache.contains("requests")) { 
    		loadDataR();
    	}
    	
    	requestTableView.setItems(requestData);
    	
    	// Setup sold table
    	initColumnsS();
    	if (!cache.contains("sold")) {
    		loadDataS();
    	}
    	
    	sellsTableView.setItems(soldData);
    	
    	// Setup users table
    	initColumnsU();
    	if (!cache.contains("users")) {
    		loadDataU();
    	}
    	
    	userListTable.setItems(userData);
    	
    	// Close ListingManager
    	lm.exit();
    	um.exit();
    }

    /**
     * Initializes the requests column headers
     */
    private void initColumnsR() {
        //name, vin, make, model, year, mileage, price;
        nameTableColumnR.setCellValueFactory(new PropertyValueFactory<>("name"));
        vinTableColumnR.setCellValueFactory(new PropertyValueFactory<>("vin"));
        makeTableColumnR.setCellValueFactory(new PropertyValueFactory<>("make"));
        modelTableColumnR.setCellValueFactory(new PropertyValueFactory<>("model"));
        yearTableColumnR.setCellValueFactory(new PropertyValueFactory<>("year"));
        mileageTableColumnR.setCellValueFactory(new PropertyValueFactory<>("mileage"));
        priceTableColumnR.setCellValueFactory(new PropertyValueFactory<>("price"));
        acceptTableColumnR.setCellValueFactory(new PropertyValueFactory<>("accept"));
        declineTableColumnR.setCellValueFactory(new PropertyValueFactory<>("decline"));

        // Buttons
        addTableButtons();
    }

    /**
     * Initializes the sells column headers
     */
    private void initColumnsS() {
        //name, vin, make, model, year, mileage, price;
        nameTableColumnS.setCellValueFactory(new PropertyValueFactory<>("name"));
        vinTableColumnS.setCellValueFactory(new PropertyValueFactory<>("vin"));
        makeTableColumnS.setCellValueFactory(new PropertyValueFactory<>("make"));
        modelTableColumnS.setCellValueFactory(new PropertyValueFactory<>("model"));
        yearTableColumnS.setCellValueFactory(new PropertyValueFactory<>("year"));
        mileageTableColumnS.setCellValueFactory(new PropertyValueFactory<>("mileage"));
        priceTableColumnS.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
    
    /**
     * Initializes the users column headers
     */
    private void initColumnsU() {
        //name, vin, make, model, year, mileage, price;
    	usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        isAdminColumn.setCellValueFactory(new PropertyValueFactory<>("isAdmin"));
    }

    private void addTableButtons() {
    	
    }
    
    public void refresh(ActionEvent e) {
    	requestTableView.refresh();
    }
    
    
    public void searchUsername(ActionEvent e) {
    	
    	String username = usernameSearchBar.getText();
    	
    	// Search user list for given username
    }


    /**
	 * Scene changer
	 * @param e
	 * @throws IOException
	 */
	public void goToListings(ActionEvent e) throws IOException {
		m.changeScene("listing.fxml");
	}
    
    /**
     * Action for makeAdminButton
     * @param event - ActionEvent from button
     */
    public void makeAdminButtonOnAction(ActionEvent event) {
        //make the username an admin
    }

    /**
     * Event Handler for exitButton
     * @param event - ActionEvent
     * @throws IOException
     */
    public void exitButtonOnAction(ActionEvent event) throws IOException {
		m.changeScene("login.fxml");
    }

    /**
     * Retrieves all requests from the database
     * @see ListingManager
     */
    private void loadDataR() {
		List<Listing> listings = null;
		
		try {
			// Get all pending listings
			listings = lm.getAllRequests();
			
			// Cache all pending listings to memory for 30 min
			cache.add("requests", listings, TimeUnit.MINUTES.toMillis(30));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (listings != null) {
			listings.forEach((p) -> {
				// Add request
				Request r = new Request(p);
				
				// Setup buttons
				RequestButton a = new RequestButton(r);
				RequestButton d = new RequestButton(r);
				
				a.setText("Accept");
				d.setText("Deny");
				
				r.setAcceptButton(a);
				r.setDeclineButton(d);
				
				requestData.add(r);
			});
		}
    }

    /**
     * Retrieves all sells from the database
     * @see ListingManager
     */
     private void loadDataS() {
 		List<Listing> listings = null;
		
 		try {
 			// Get all pending listings
 			listings = lm.getAllSold();
 			
 			// Cache all sold listings to memory for 30 min
 			cache.add("sold", listings, TimeUnit.MINUTES.toMillis(30));
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
 		
 		if (listings != null) {
	 		listings.forEach((p) -> {
	 			// Add sale
	 			PreviousSale s = new PreviousSale(p);
	 			soldData.add(s);
	 		});
 		}
     }
    
     private void loadDataU() {
    	 List<User> users = null;
    	 
    	 try {
    		 users = um.getAll();
    	 } catch (DatabaseErrorException e) {
    		 e.printStackTrace();
    	 }
    	 
    	 if (users != null) {
    		 users.forEach((p) -> {
    			 UserInfo u = new UserInfo(p);
    			 userData.add(u);
    		 });
    	 }
     }
     
	/**
	 * EventHandler for the Listing accept buttons
	 * @see RequestButton
	 */
	EventHandler<ActionEvent> acceptButtonHandler = new EventHandler<ActionEvent>() {
		@SuppressWarnings("unchecked")
		@Override
		public void handle(ActionEvent e) {
			// Init ListingManager
			lm.setup();
			
			// Get needed info
			RequestButton b = (RequestButton) e.getSource();
			long listingId = (long) b.getRequest().getId();
			
			// Publish listing
			try {
				lm.setPublished(listingId, true);
			} catch (DatabaseErrorException e1) {
				e1.printStackTrace();
			} finally {
				lm.exit();
			}
			
			// Fix the cache
			if (cache.contains("requests")) {
				List<Listing> l = (List<Listing>) cache.get("requests");
				l.forEach(p -> {
					if (p.getId() == listingId) {
						l.remove(p);
					}
				});
				
				cache.remove("requests");
				cache.add("requests", l, TimeUnit.MINUTES.toMillis(30));
			}
			
			// Remove from table
			//requestTableView.
			
			// Update sold table
			//loadDataS();
			//sellsTableView.setItems(soldData);
			
			// End event
			e.consume();
		}
	};
	
	/**
	 * EventHandler for the Listing decline buttons
	 * @see RequestButton
	 */
	EventHandler<ActionEvent> declineButtonHandler = new EventHandler<ActionEvent>() {
		@SuppressWarnings("unchecked")
		@Override
		public void handle(ActionEvent e) {
			// Init ListingManager
			lm.setup();
			
			// Get needed info
			RequestButton b = (RequestButton) e.getSource();
			long listingId = (long) b.getRequest().getId();
			
			// Un-publish listing
			try {
				lm.setPublished(listingId, false);
			} catch (DatabaseErrorException e1) {
				e1.printStackTrace();
			} finally {
				lm.exit();
			}
			
			// Fix the cache
			if (cache.contains("requests")) {
				List<Listing> l = (List<Listing>) cache.get("requests");
				l.forEach(p -> {
					if (p.getId() == listingId) {
						l.remove(p);
					}
				});
				
				cache.remove("requests");
				cache.add("requests", l, TimeUnit.MINUTES.toMillis(30));
			}
			
			// Remove from table
			//sellsTableView.
			
			// Update requests table
			//loadDataR();
			//requestTableView.setItems(requestData);
			
			// End event
			e.consume();
		}
	};
}
