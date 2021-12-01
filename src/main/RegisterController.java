package main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import managers.UserManager;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static cache.Caching.cache;

import exceptions.DatabaseErrorException;

public class RegisterController implements Initializable {

	// For scene changes
	protected Main m = new Main();
	
	// For registration
	protected UserManager um = new UserManager();
	
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		um.setup();
	}
    
     public void submitButtonOnAction(ActionEvent event) throws IOException {
    	 if (usernameTextField.getText().isBlank() || passwordTextField.getText().isBlank() || firstnameTextField.getText().isBlank() || lastnameTextField.getText().isBlank() || emailTextField.getText().isBlank()) {
         	registerMessageLabel.setText("Please fill in all of the boxes.");
         } else {
         	// Create new user
         	try {
 				long newUserId = um.create(firstnameTextField.getText(), lastnameTextField.getText(), false, usernameTextField.getText(), passwordTextField.getText(), emailTextField.getText());
 				
 				// Cache new user
 				cache.add("user", um.get(newUserId));
 				
 				// Change scene
 		        m.changeScene("listing.fxml");
 			} catch (DatabaseErrorException e) {
 				registerMessageLabel.setText("There was an error, please try again.");
 			} finally {
 				um.exit();
 			}
         }
    }

    public void backButtonOnAction(ActionEvent event) throws IOException {
		m.changeScene("login.fxml");
    }
}