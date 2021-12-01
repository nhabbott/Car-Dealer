package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import objects.User;

import static cache.Caching.cache;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SellController implements Initializable{

		protected Main m = new Main();
	
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
		// Get user from cache
    	User u = (User) cache.get("user");
    	
    	// Get user's name
    	nameLabel.setText(u.getFirstName() + " " + u.getLastName());
    	
    	// Init choice boxes
    	cylinderChoiceBox.getItems().addAll("Four", "Six", "Eight", "Twelve");
    	fuelChoiceBox.getItems().addAll("Gasoline", "Diesel", "Electric", "Hybrid");
    	sizeChoiceBox.getItems().addAll("Compact", "Mid", "Full");
    	transmissionChoiceBox.getItems().addAll("Automatic", "Manual");
    	typeChoiceBox.getItems().addAll("Coupe", "Crossover", "Truck", "Sedan", "Sportscar", "Hatchback");
		
	}

    public void cancelButtonOnAction(ActionEvent event) throws IOException {
		m.changeScene("listing.fxml");
    }

    public void sellButtonOnAction(ActionEvent event) throws IOException {
    	if (vinTextField.getText().isBlank() || yearTextField.getText().isBlank() || mileageTextField.getText().isBlank() || priceTextField.getText().isBlank() || typeChoiceBox.getSelectionModel().isEmpty() || transmissionChoiceBox.getSelectionModel().isEmpty() || sizeChoiceBox.getSelectionModel().isEmpty() || cylinderChoiceBox.getSelectionModel().isEmpty() || fuelChoiceBox.getSelectionModel().isEmpty() || makeTextField.getText().isBlank() || modelTextField.getText().isBlank() || copTextField.getText().isBlank()) { 
    		sellMessageLabel.setText("Please provide information for all fields.");
        } else {
        	//I'll assume saving the above fields into the datebase goes here
        	
        	// Return to listings once database has been updated.
    		m.changeScene("listing.fxml");
        }
    }
}
