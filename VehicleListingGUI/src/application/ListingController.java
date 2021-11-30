package application;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import application.Vehicle.fuelType;
import application.Vehicle.numOfCylinders;
import application.Vehicle.vehicleSize;
import application.Vehicle.vehicleTrans;
import application.Vehicle.vehicleType;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class ListingController implements Initializable {
	
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
		Main m = new Main();
		m.changeScene("sell.fxml");
	}
	
	public void goToPurchase(ActionEvent e) throws IOException {
		Main m = new Main();
		m.changeScene("purchase.fxml");
	}
	
	public void logOut(ActionEvent e) throws IOException {
		Main m = new Main();
		m.changeScene("login.fxml");
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		// Get username from database
		username.setText("Placeholder");
		
		// Update Table
		populate(retrieveData());
		table.setItems(data);
		
		
	}

	// Obtain listings from database as a list
	private List<Listing> retrieveData() {
		return Arrays.asList(
				new Listing(new Vehicle("00110", vehicleType.coupe, vehicleSize.compact, 2018, "Chevy", "Equinox", numOfCylinders.eight, vehicleTrans.automatic, fuelType.diesel,"USA", 20000), 20000, 1),
				new Listing(new Vehicle("00111", vehicleType.crossover, vehicleSize.full,2016, "Toyota", "Camrey",  numOfCylinders.six, vehicleTrans.automatic, fuelType.gasoline,"Japan", 40000), 50000, 2));
	}
	
	// Create table info from listings
	private void populate(final List<Listing> listings) {
		listings.forEach(p -> data.add(new ListingInfo(p)));
	}
}

