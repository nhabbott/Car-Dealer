package main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

import static cache.Caching.cache;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

import auth.Authentication;

import java.net.URL;

public class LoginController implements Initializable {

	// For scene changes
	protected Main m = new Main();
	
	// For login
	protected UserManager um = new UserManager();
	
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
        
        // Init UserManager
        um.setup();
    }

    /**
     * EventHandler for loginButton
     * @param event
     * @throws IOException
     */
    public void loginButtonOnAction(ActionEvent event) throws IOException {
        if (usernameTextField.getText().isBlank() || enterPasswordField.getText().isBlank()) {
        	loginMessageLabel.setText("Please enter username and password");
        } else {
        	validateLogin(usernameTextField.getText(), enterPasswordField.getText());
        }
    }

    /**
     * EventHandler for cancelButton
     * @param event
     * @throws IOException
     */
    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    /**
     * EventHandler for registerButton
     * @param event
     * @throws IOException
     */
    public void registerButtonOnAction(ActionEvent event) throws IOException {
		m.changeScene("register.fxml");
    }
    
    /**
     * EventHandler for forgotPasswordButton
     * @param event
     * @throws IOException
     */
    public void forgotPasswordButtonOnAction(ActionEvent event) throws IOException {
    	//
    }

    /**
     * Validates user credentials
     * @param user - Username
     * @param pass - Password
     * @throws IOException
     */
    public void validateLogin(String user, String pass) {
    	// Find user in DB with matching username
    	try {
			User u = um.getByUsername(user);
			
			// Verify password
			boolean authed = Authentication.authenticate(pass, Authentication.toByteArray(u.getSalt()), Authentication.toByteArray(u.getPassword()));
			
			if (authed) {
				// Cache user
				cache.add("user", u);
				
				// Change scene
		    	if (u.isAdmin()) {
		    		m.changeScene("admin.fxml");
		    	} else {
		    		m.changeScene("listing.fxml");
		    	}
			} else {
				loginMessageLabel.setText("Incorrect username or password");
			}
		} catch (Exception e) {
			e.printStackTrace();
			loginMessageLabel.setText("Incorrect username or password");
		} finally {
			um.exit();
		}
    }

}
