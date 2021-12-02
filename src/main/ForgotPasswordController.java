package main;

import static cache.Caching.cache;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import objects.User;

public class ForgotPasswordController implements Initializable{

	// For scene changes
	private Main m = new Main();
	
	@FXML
	private VBox step1VBox;
	@FXML
	private VBox step2VBox;
	@FXML
	private VBox step3VBox;
	@FXML
	private Button backButton;
	@FXML
	private Button emailSubmitButton;
	@FXML
	private Button recoveryCodeSubmitButton;
	@FXML
	private Button newPasswordSubmitButton;
	@FXML
	private TextField emailField;
	@FXML
	private PasswordField recoveryCodeField;
	@FXML
	private PasswordField newPasswordField;
	@FXML
	private Label messageLabel;
	
	//Fade Transitions for each step
	FadeTransition fadeOut = new FadeTransition();
	FadeTransition fadeIn = new FadeTransition();
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Hide other information
		step2VBox.setVisible(false);
		step3VBox.setVisible(false);
		
		//Set up fade animations
		fadeOut.setDuration(Duration.millis(1000));     
		fadeOut.setFromValue(1);  
		fadeOut.setToValue(0);    
		fadeOut.setCycleCount(1);  
		fadeOut.setNode(step1VBox); 
        
        fadeIn.setDuration(Duration.millis(1000));     
        fadeIn.setFromValue(0);  
        fadeIn.setToValue(1);    
        fadeIn.setCycleCount(1);  
        fadeIn.setAutoReverse(true);
        fadeIn.setNode(step2VBox);
	}
	
	// Return to login screen
	public void backButtonOnAction(ActionEvent e) throws IOException {
		m.changeScene("login.fxml");
	}
	
	
	public void sendRecoveryEmail(ActionEvent e) {
		String email = emailField.getText();
		
		// Send recovery code to email
		//TODO
		
		messageLabel.setText("Email has been sent with a recovery code.");
		
		// Update GUI
		fadeOut.play();
		step2VBox.setVisible(true);
		fadeIn.play();
	}
	
	public void checkCode(ActionEvent e) {
		String code = recoveryCodeField.getText();
		
		// check code to make sure it is correct
		//TODO
		
		messageLabel.setText("Code accepted: Please enter new password.");
		
		// Update GUI
		fadeOut.setNode(step2VBox);
		fadeIn.setNode(step3VBox);
		fadeOut.play();
		step3VBox.setVisible(true);
		fadeIn.play();
	}
	
	public void setNewPassword(ActionEvent e) {
		String password = newPasswordField.getText();
		
		// Update password in database
		//TODO
		
		messageLabel.setText("Password Updated. Press back to return to login screen.");
		newPasswordField.clear();
		
	}
}
