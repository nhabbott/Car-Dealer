package main;

import main.model.PreviousSale;
import main.model.Request;
import managers.ListingManager;
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
	
    @FXML
    private TableView<Request> requestTableView;

    @FXML
    private TableView<?> sellsTableView;

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
    private TextField makeAdminTextField;

    @FXML
    private Button makeAdminButton;

    @FXML
    private Button exitButton;

    @FXML
    private Label adminNameLabel;
    
    private ObservableList<Request> requestData = FXCollections.observableArrayList();
    private ObservableList<PreviousSale> soldData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Get user
    	User u = (User) cache.get("user");
    	
    	// Set user label
    	adminNameLabel.setText(u.getFirstName() + " " + u.getLastName());
    	
    	// Init ListingManager
    	lm.setup();
    	
    	// Fill table
    	initTable();
    }

    private void initTable() {
    	
    	// Setup request table
    	initColumnsR();
    	if (!cache.contains("requests")) { 
    		loadDataR();
    	}
    	
    	requestTableView.setItems(requestData);
    	
    	// Setup sold table
    	initColumnsS();
    }

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

        //editableCols();
    }

    private void initColumnsS() {
        //name, vin, make, model, year, mileage, price;
        nameTableColumnS.setCellValueFactory(new PropertyValueFactory<>("name"));
        vinTableColumnS.setCellValueFactory(new PropertyValueFactory<>("vin"));
        makeTableColumnS.setCellValueFactory(new PropertyValueFactory<>("make"));
        modelTableColumnS.setCellValueFactory(new PropertyValueFactory<>("model"));
        yearTableColumnS.setCellValueFactory(new PropertyValueFactory<>("year"));
        mileageTableColumnS.setCellValueFactory(new PropertyValueFactory<>("mileage"));
        priceTableColumnS.setCellValueFactory(new PropertyValueFactory<>("price"));

        //editableCols();
    }

    /*private void editableCols() {

        nameTableColumnR.setCellFactory(TextFieldTableCell.forTableColumn());

        nameTableColumnR.setOnEditCommit(e-> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setName(e.getNewValue());
        });

        vinTableColumnR.setCellFactory(TextFieldTableCell.forTableColumn());

        vinTableColumnR.setOnEditCommit(e-> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setVin(e.getNewValue());
        });

        makeTableColumnR.setCellFactory(TextFieldTableCell.forTableColumn());

        makeTableColumnR.setOnEditCommit(e-> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setMake(e.getNewValue());
        });

        modelTableColumnR.setCellFactory(TextFieldTableCell.forTableColumn());

        modelTableColumnR.setOnEditCommit(e-> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setModel(e.getNewValue());
        });

        yearTableColumnR.setCellFactory(TextFieldTableCell.forTableColumn());

        yearTableColumnR.setOnEditCommit(e-> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setYear(e.getNewValue());
        });

        mileageTableColumnR.setCellFactory(TextFieldTableCell.forTableColumn());

        mileageTableColumnR.setOnEditCommit(e-> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setMileage(e.getNewValue());
        });

        priceTableColumnR.setCellFactory(TextFieldTableCell.forTableColumn());

        priceTableColumnR.setOnEditCommit(e-> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setPrice(e.getNewValue());
        });

        requestTableView.setEditable(true);
        sellsTableView.setEditable(true);
    }*/


    public void makeAdminButtonOnAction(ActionEvent event) {
        //make the username an admin
    }

    public void exitButtonOnAction(ActionEvent event) throws IOException {
		m.changeScene("login.fxml");
    }

    private void loadDataR() {
		List<Listing> listings = null;
		
		try {
			// Get all pending listings
			listings = lm.getAllRequests();
			
			// Cache all pending listings to memory for 30 min
			cache.add("requests", listings, TimeUnit.MINUTES.toMillis(30));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lm.exit();
		}
		
		if (listings != null) {
			listings.forEach((p) -> {
				// Add request
				Request r = new Request(p);
				
				// Setup buttons
				r.getAcceptButton().setOnAction(acceptButtonHandler);
				r.getDeclineButton().setOnAction(declineButtonHandler);
				
				requestData.add(r);
			});
		}
    }

     private void loadDataS() {
 		List<Listing> listings = null;
		
 		try {
 			// Get all pending listings
 			listings = lm.getAllSold();
 			
 			// Cache all sold listings to memory for 30 min
 			cache.add("sold", listings, TimeUnit.MINUTES.toMillis(30));
 		} catch (Exception e) {
 			e.printStackTrace();
 		} finally {
 			lm.exit();
 		}
 		
 		if (listings != null) {
	 		listings.forEach((p) -> {
	 			// Add sale
	 			PreviousSale r = new PreviousSale(p);
	 			soldData.add(r);
	 		});
 		}
     }
    
	// Buttons
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
				lm.setPublished(listingId);
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
	
	EventHandler<ActionEvent> declineButtonHandler = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent e) {
			e.consume();
		}
	};
}
