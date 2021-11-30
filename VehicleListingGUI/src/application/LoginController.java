package application;

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
import javafx.event.ActionEvent;


import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

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
        if (!usernameTextField.getText().isBlank() && !enterPasswordField.getText().isBlank()) {
        	
        	//validateLogin();
        	
        	Main m = new Main();
    		m.changeScene("listing.fxml");
    		
            
        } else {
            loginMessageLabel.setText("Please enter username and password");
        }
    }
    
    public void adminLoginButtonOnAction(ActionEvent event) throws IOException {
    	if (!usernameTextField.getText().isBlank() && !enterPasswordField.getText().isBlank()) {
        	
    		//validateLogin();
    		
        	Main m = new Main();
    		m.changeScene("admin.fxml");
    		
            
        } else {
            loginMessageLabel.setText("Please enter username and password");
        }
    }

    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void registerButtonOnAction(ActionEvent event) throws IOException {
        Main m = new Main();
		m.changeScene("register.fxml");

    }

    public void validateLogin() {
        //connect to database
   
    }

}
