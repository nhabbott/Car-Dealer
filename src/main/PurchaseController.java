package main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PurchaseController implements Initializable {

	
	@FXML
	private Button cancelButton;
	@FXML
	private Button purchaseButton;
	@FXML
	private Label username;
	@FXML
	private Label errorMessage;
	@FXML
	private TextField vinTextField;
	@FXML
	private Label typeLabel;
	@FXML
	private Label sizeLabel;
	@FXML
	private Label yearLabel;
	@FXML
	private Label makeLabel;
	@FXML
	private Label modelLabel;
	@FXML
	private Label cylinderLabel;
	@FXML
	private Label transmissionLabel;
	@FXML
	private Label fuelLabel;
	@FXML
	private Label copLabel;
	@FXML
	private Label mileageLabel;
	@FXML
	private Label priceLabel;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//Get Username to display
		username.setText("Placeholder");	
	}
	
	
	public void getListingInfo(ActionEvent event) throws IOException {
		// Obtain VIN
		String vin = vinTextField.getText();
		
		if(/*Check if VIN exists in DataBase*/ false) {
			errorMessage.setText("Invalid VIN");
		}
		
		else {
			// Get data for Listing based on VIN
			typeLabel.setText("Type");
			sizeLabel.setText("Size");
			yearLabel.setText("Year");
			makeLabel.setText("Make");
			modelLabel.setText("Model");
			cylinderLabel.setText("Cylinder");
			transmissionLabel.setText("Transmission");
			fuelLabel.setText("Fuel");
			copLabel.setText("Country");
			mileageLabel.setText("Mileage");
			priceLabel.setText("Price");
		}
		
		
	}
	
	public void cancelButtonOnAction(ActionEvent event) throws IOException {
    	Main m = new Main();
		m.changeScene("listing.fxml");
    }
	
	public void purchaseButtonOnAction(ActionEvent event) throws IOException {
		
		if(vinTextField.getText().isBlank()) {
			errorMessage.setText("Please enter a VIN");
		}
		
		else if(/*Check if VIN exists in DataBase*/ false) {
			errorMessage.setText("Invalid VIN");
		}
		
		else {
			// Update Listing information
			
	    	Main m = new Main();
			m.changeScene("listing.fxml");
		}
    }

	
	
	
	
	
	
}
