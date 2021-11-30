package main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import managers.UserManager;
import objects.User;
import javafx.event.ActionEvent;


import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

import static cache.Caching.cache;

import java.net.URL;

public class LoginController implements Initializable {

    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private ImageView logoImageView;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField enterPasswordField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File logoFile = new File("Images/Login.png" );
        Image logoImage = new Image(logoFile.toURI().toString());
        logoImageView.setImage(logoImage);
    }


    public void loginButtonOnAction(ActionEvent event) throws IOException {
        if (usernameTextField.getText().isBlank() || enterPasswordField.getText().isBlank()) {
        	loginMessageLabel.setText("Please enter username and password");
        } else {
        	validateLogin(event, usernameTextField.getText(), enterPasswordField.getText());
        }
    }

    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void registerButtonOnAction(ActionEvent event) throws IOException {
        Parent registerPageParent = FXMLLoader.load(getClass().getResource("register.fxml"));
        Scene registerPageScene = new Scene(registerPageParent);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(registerPageScene);
        appStage.show();

    }

    public void validateLogin(ActionEvent event, String user, String pass) throws IOException {
    	// Init user manager
    	UserManager um = new UserManager();
    	um.setup();
    	
    	// Create next view
    	Parent viewPageParent = FXMLLoader.load(getClass().getResource("sell.fxml"));
        Scene viewPageScene = new Scene(viewPageParent);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	
    	// Find user in DB with matching username
    	try {
			User u = um.getByUsername(user);
			
			// Verify password
			boolean authed = Authentication.authenticate(pass, Authentication.toByteArray(u.getSalt()), Authentication.toByteArray(u.getPassword()));
			
			if (authed) {
				// Go to home
		         appStage.setScene(viewPageScene);
		         appStage.show();
				
				// Cache user
				cache.add("user", u, 0);
			} else {
				loginMessageLabel.setText("Incorrect username or password");
			}
		} catch (Exception e) {
			loginMessageLabel.setText("Incorrect username or password");
		} finally {
			um.exit();
		}
    }

}
