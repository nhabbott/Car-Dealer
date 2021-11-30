package main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import managers.UserManager;
import javafx.event.ActionEvent;

import java.io.IOException;

import static cache.Caching.cache;
import exceptions.DatabaseErrorException;

public class RegisterController {

    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private TextField firstnameTextField;
    @FXML
    private TextField lastnameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private Label registerMessageLabel;

     public void submitButtonOnAction(ActionEvent event) throws IOException {
        if (usernameTextField.getText().isBlank() || passwordTextField.getText().isBlank() || firstnameTextField.getText().isBlank() || lastnameTextField.getText().isBlank() || emailTextField.getText().isBlank()) {
        	registerMessageLabel.setText("Please fill in all of the boxes.");
        } else {
        	// Init user manager
        	UserManager um = new UserManager();
        	um.setup();
        	
        	// Create new user
        	try {
				long newUserId = um.create(firstnameTextField.getText(), lastnameTextField.getText(), false, usernameTextField.getText(), passwordTextField.getText(), emailTextField.getText());
				
				// Cache new user
				if (cache.contains("user")) {
					// Remove old user from cache
					cache.remove("user");
					
					// Cache new user
					cache.add("user", um.get(newUserId));
				} else {
					// Cache new user
					cache.add("user", um.get(newUserId));
				}
				
				// Create next view
		    	Parent viewPageParent = FXMLLoader.load(getClass().getResource("sell.fxml"));
		        Scene viewPageScene = new Scene(viewPageParent);
		        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		        
		        // Send user to home screen
		        appStage.setScene(viewPageScene);
		        appStage.show();
			} catch (DatabaseErrorException e) {
				registerMessageLabel.setText("There was an error, please try again.");
			}
        }
    }

    public void backButtonOnAction(ActionEvent event) throws IOException {
        Parent loginPageParent = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene loginPageScene = new Scene(loginPageParent);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(loginPageScene);
        appStage.show();
    }

}