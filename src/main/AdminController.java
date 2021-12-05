package main;

import main.model.PreviousSale;
import main.model.Request;
import main.model.UserInfo;
import managers.ListingManager;
import managers.UserManager;
import objects.Listing;
import objects.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

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
    private TableColumn<Request, String> acceptTableColumnR;
    @FXML
    private TableColumn<Request, String> declineTableColumnR;
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
    private TableColumn<PreviousSale, String> soldToTableColumnS;
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
    private TableColumn<UserInfo, String> usernameColumn;
    @FXML
    private TableColumn<UserInfo, String> firstNameColumn;
    @FXML
    private TableColumn<UserInfo, String> lastNameColumn;
    @FXML
    private TableColumn<UserInfo, String> emailColumn;
    @FXML
    private TableColumn<UserInfo, String> isAdminColumn;
    @FXML
    private TableColumn<UserInfo, String> makeAdminColumn;
    @FXML
    private TextField usernameSearchBar;
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
    private FilteredList<UserInfo> users;

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
    @SuppressWarnings("unchecked")
	private void initTable() {
    	
    	// Setup request table
    	initColumnsR();
    	if (!cache.contains("requests")) { 
    		loadDataR();
    	} else {
    		List<Listing> listings = (List<Listing>) cache.get("requests");
    		
			listings.forEach((p) -> {
				// Add request
				Request r = new Request(p);			
				requestData.add(r);
			});
    	}
    	
    	acceptTableColumnR.setCellFactory(acceptBtnCellFactory);
    	declineTableColumnR.setCellFactory(declineBtnCellFactory);
    	requestTableView.setItems(requestData);
    	
    	// Setup sold table
    	initColumnsS();
    	if (!cache.contains("sold")) {
    		loadDataS();
    	} else {
    		List<Listing> listings = (List<Listing>) cache.get("sold");
    		
			listings.forEach((p) -> {
				// Add request
				PreviousSale r = new PreviousSale(p);			
				soldData.add(r);
			});
    	}
    	
    	sellsTableView.setItems(soldData);
    	
    	// Setup users table
    	initColumnsU();
    	if (!cache.contains("users")) {
    		loadDataU();
    	} else {
    		List<User> users = (List<User>) cache.get("users");
    		
			users.forEach((p) -> {
				// Add request
				UserInfo r = new UserInfo(p);			
				userData.add(r);
			});
    	}
    	
    	makeAdminColumn.setCellFactory(makeAdminBtnCellFactory);
    	users = new FilteredList<UserInfo>(userData, u -> true);
    	userListTable.setItems(users);
    	
    	initUserSearch();
    	
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
        acceptTableColumnR.setCellValueFactory(new PropertyValueFactory<>(""));
        declineTableColumnR.setCellValueFactory(new PropertyValueFactory<>(""));
    }

    /**
     * Initializes the sells column headers
     */
    private void initColumnsS() {
        //name, vin, make, model, year, mileage, price;
        nameTableColumnS.setCellValueFactory(new PropertyValueFactory<>("name"));
        soldToTableColumnS.setCellValueFactory(new PropertyValueFactory<>("soldTo"));
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
        makeAdminColumn.setCellValueFactory(new PropertyValueFactory<>(""));
    }
    
    private void initUserSearch() {
    	usernameSearchBar.textProperty().addListener((observable, oldValue, newValue) -> {
    		users.setPredicate(u -> {
    			if (newValue == null || newValue.isEmpty()) {
    				return true;
    			}
    			
    			String name = u.getUsername();
    			String f = newValue.toLowerCase();
    			
    			if (name.toLowerCase().contains(f)) {
    				return true;
    			} else {
    				return false;
    			}
    		});
    	});
    }

    /**
     * Callback for accept button
     */
    @SuppressWarnings("rawtypes")
    Callback<TableColumn<Request, String>, TableCell<Request, String>> acceptBtnCellFactory = new Callback<TableColumn<Request, String>, TableCell<Request, String>>() {
		@Override
		public TableCell call(TableColumn<Request, String> p) {
			final TableCell<Request, String> cell = new TableCell<Request, String>() {

                final Button btn = new Button("Accept");

                @SuppressWarnings({ "unchecked" })
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
                			Request b = (Request) getTableView().getItems().get(getIndex());
                			long listingId = (long) b.getId();
                			
                			// Publish listing
                			try {
                				lm.setPublished(listingId, true);
                			} catch (DatabaseErrorException e1) {
                				e1.printStackTrace();
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
                			requestTableView.getItems().remove(getIndex());
                			
                			// Update sold table
                			loadDataS();
                			sellsTableView.setItems(soldData);
                			sellsTableView.refresh();
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
     * Callback for decline button
     */
    @SuppressWarnings("rawtypes")
    Callback<TableColumn<Request, String>, TableCell<Request, String>> declineBtnCellFactory = new Callback<TableColumn<Request, String>, TableCell<Request, String>>() {
		@Override
		public TableCell call(TableColumn<Request, String> p) {
			final TableCell<Request, String> cell = new TableCell<Request, String>() {

                final Button btn = new Button("Decline");

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
                			Request b = (Request) getTableView().getItems().get(getIndex());
                			long listingId = (long) b.getId();
                			
                			// Un-publish listing
                			try {
                				lm.setPublished(listingId, false);
                				lm.setSold(listingId, -1);
                			} catch (DatabaseErrorException e1) {
                				e1.printStackTrace();
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
                			sellsTableView.getItems().remove(getIndex());
                			
                			// Update requests table
                			loadDataR();
                			requestTableView.setItems(requestData);
                			requestTableView.refresh();
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
     * Callback for make admin button
     */
    @SuppressWarnings("rawtypes")
    Callback<TableColumn<UserInfo, String>, TableCell<UserInfo, String>> makeAdminBtnCellFactory = new Callback<TableColumn<UserInfo, String>, TableCell<UserInfo, String>>() {
		@Override
		public TableCell call(TableColumn<UserInfo, String> p) {
			final TableCell<UserInfo, String> cell = new TableCell<UserInfo, String>() {
				final Button btn = new Button();

                @SuppressWarnings("unchecked")
				@Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                    	UserInfo u = (UserInfo) getTableView().getItems().get(getIndex());
                    	boolean admin;
                    	
            			// Check current permissions
                        if (u.isAdmin()) {
                        	btn.setText("Make User");
                        	admin = false;
                        } else {
                        	btn.setText("Make Admin");
                        	admin = true;
                        }
                    	
                        btn.setOnAction(event -> {
                			// Init ListingManager
                			um.setup();
                			
                			// Un-publish listing
                			try {
                    			// Get needed info
                				User uu = (User) um.getByEmail(u.getEmail());
                				cache.add("tempUser", uu);
                    			
                				um.setAdmin(uu.getId(), admin);
                			} catch (DatabaseErrorException e1) {
                				e1.printStackTrace();
                			}
                			
                			// Fix the cache
                			if (cache.contains("users")) {
                				List<User> l = (List<User>) cache.get("users");
                				l.forEach(p -> {
                					if (p.getId() == ((User) cache.get("tempUser")).getId()) {
                						l.remove(p);
                					}
                				});
                				
                				cache.remove("tempUser");
                				cache.remove("users");
                				cache.add("users", l, TimeUnit.MINUTES.toMillis(30));
                			}
                			
                			// Update users table
                			userData.clear();
                			loadDataU();
                			users = new FilteredList<UserInfo>(userData, var -> true);
                			userListTable.setItems(users);
                			userListTable.refresh();
                        });
                        setGraphic(btn);
                        setText(null);
                    }
                }
            };
            return cell;
		}
    };
    
    public void refresh(ActionEvent e) {
    	// Clear current data
    	requestTableView.getItems().clear();
    	requestTableView.refresh();
    	
    	// Clear cache
    	if (cache.contains("requests")) {
    		cache.remove("requests");
    	}
    	
    	// Get new data
    	lm.setup();
    	loadDataR();
    	lm.exit();
    	
    	// Refresh table
    	acceptTableColumnR.setCellFactory(acceptBtnCellFactory);
    	declineTableColumnR.setCellFactory(declineBtnCellFactory);
    	requestTableView.setItems(requestData);
    	requestTableView.refresh();
    }
    
    public void refreshSells(ActionEvent e) {
    	// Clear current data
    	sellsTableView.getItems().clear();
    	sellsTableView.refresh();
    	
    	// Clear cache
    	if (cache.contains("sold")) {
    		cache.remove("sold");
    	}
    	
    	// Get new data
    	lm.setup();
    	loadDataS();
    	lm.exit();
    	
    	// Refresh table
    	sellsTableView.setItems(soldData);
    	sellsTableView.refresh();
    }

    /**
	 * Scene changer
	 * @param e
	 * @throws IOException
	 */
	public void goToListings(ActionEvent e) throws IOException {
		lm.exit();
		m.changeScene("listing.fxml");
	}

    /**
     * Event Handler for exitButton
     * @param event - ActionEvent
     * @throws IOException
     */
    public void exitButtonOnAction(ActionEvent event) throws IOException {
    	lm.exit();
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
    
     /**
      * Retrieves all users from the database
      */
     private void loadDataU() {
    	 List<User> users = null;
    	 
    	 try {
    		 // Get all users
    		 users = um.getAll();
    		 
    		 // Cache all users to memory for 30 min
    		 cache.add("users", users, TimeUnit.MINUTES.toMillis(30));
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
}