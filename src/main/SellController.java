package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import managers.ListingManager;
import objects.User;
import objects.Vehicle;
import objects.Vehicle.fuelType;
import objects.Vehicle.numOfCylinders;
import objects.Vehicle.vehicleSize;
import objects.Vehicle.vehicleTrans;
import objects.Vehicle.vehicleType;

import static cache.Caching.cache;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import exceptions.DatabaseErrorException;

public class SellController implements Initializable{

		// For scene changes
		private Main m = new Main();
		
		// For DB ops
		private ListingManager lm = new ListingManager();
		
		// Get user from cache
    	User u = (User) cache.get("user");
	
        @FXML
        private Button cancelButton;

        @FXML
        private TextField copTextField;

        @FXML
        private ChoiceBox<String> cylinderChoiceBox;

        @FXML
        private ChoiceBox<String> fuelChoiceBox;

        @FXML
        private TextField makeTextField;

        @FXML
        private TextField mileageTextField;

        @FXML
        private TextField modelTextField;

        @FXML
        private Label nameLabel;

        @FXML
        private TextField priceTextField;

        @FXML
        private Button sellButton;

        @FXML
        private ChoiceBox<String> sizeChoiceBox;

        @FXML
        private ChoiceBox<String> transmissionChoiceBox;

        @FXML
        private ChoiceBox<String> typeChoiceBox;

        @FXML
        private TextField vinTextField;

        @FXML
        private TextField yearTextField;

        @FXML
        private Label sellMessageLabel;
        
        
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	// Get user's name
    	nameLabel.setText(u.getFirstName() + " " + u.getLastName());
    	
    	// Init choice boxes
    	cylinderChoiceBox.getItems().addAll("Four", "Six", "Eight", "Twelve");
    	fuelChoiceBox.getItems().addAll("Gasoline", "Diesel", "Electric", "Hybrid");
    	sizeChoiceBox.getItems().addAll("Compact", "Mid", "Full");
    	transmissionChoiceBox.getItems().addAll("Automatic", "Manual");
    	typeChoiceBox.getItems().addAll("Coupe", "Crossover", "Truck", "Sedan", "Sportscar", "Hatchback");
    	
    	// Init DB managers
    	lm.setup();
		
	}

    public void cancelButtonOnAction(ActionEvent event) throws IOException {
		m.changeScene("listing.fxml");
    }

    public void sellButtonOnAction(ActionEvent event) throws IOException {
    	// Get text
    	String vin = vinTextField.getText();
    	String year = yearTextField.getText();
    	String mileage = mileageTextField.getText();
    	String price = priceTextField.getText();
    	String type = typeChoiceBox.getValue().toLowerCase();
    	String trans = transmissionChoiceBox.getValue().toLowerCase();
    	String size = sizeChoiceBox.getValue().toLowerCase();
    	String cylinders = cylinderChoiceBox.getValue().toLowerCase();
    	String fuel = fuelChoiceBox.getValue().toLowerCase();
    	String make = makeTextField.getText();
    	String model = modelTextField.getText();
    	String cop = copTextField.getText();
    	
    	if (vin.isBlank() || year.isBlank() || mileage.isBlank() || price.isBlank() || type.isBlank() || trans.isBlank() || size.isBlank() || cylinders.isBlank() || fuel.isBlank() || make.isBlank() || model.isBlank() || cop.isBlank()) { 
    		sellMessageLabel.setText("Please provide information for all fields.");
        } else {
        	// Create vehicle
        	Vehicle v = new Vehicle(vin, vehicleType.valueOf(type), vehicleSize.valueOf(size), Integer.parseInt(year), make, model, numOfCylinders.valueOf(cylinders), vehicleTrans.valueOf(trans), fuelType.valueOf(fuel), cop, Integer.parseInt(mileage));
        	
        	// Create new listing
        	try {
				lm.create(v, Integer.parseInt(price), u.getId());
			} catch (NumberFormatException | DatabaseErrorException e) {
				e.printStackTrace();
			} finally {
				lm.exit();
			}
        	
        	// Return to listings once database has been updated.
    		m.changeScene("listing.fxml");
        }
    }
}
