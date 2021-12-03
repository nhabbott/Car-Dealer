package main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import exceptions.DatabaseErrorException;
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

import managers.UserManager;

public class ForgotPasswordController implements Initializable{

	// For scene changes
	private Main m = new Main();
	
	// For password reset
	UserManager um = new UserManager();
	
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
		// Init UserManager
		um.setup();
		
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
		User u = null;
		boolean worked = false;
		
		try {
			// Get user
			u = (User) um.getByEmail(email);
			
			// Check if user exists
			if (u == null) {
				return;
			}
			
			// Send email
			worked = um.forgotPassowrdEmail(u.getEmail());
		} catch (DatabaseErrorException e1) {
			e1.printStackTrace();
			messageLabel.setText("There was an error sending your reset email.");
		}
		
		if (worked) {
			messageLabel.setText("If a user with this email exists, a recovery code has been sent.");
			
			// Update GUI
			fadeOut.play();
			step2VBox.setVisible(true);
			fadeIn.play();
		} else {
			messageLabel.setText("There was an error sending your reset email.");
		}
	}
	
	public void checkCode(ActionEvent e) {
		String code = recoveryCodeField.getText();
		boolean same = false;
		
		try {
			same = um.checkResetToken(code);
		} catch (DatabaseErrorException e1) {
			e1.printStackTrace();
		}
		
		if (same) {
			messageLabel.setText("Code accepted: Please enter new password.");
			
			// Update GUI
			fadeOut.setNode(step2VBox);
			fadeIn.setNode(step3VBox);
			fadeOut.play();
			step3VBox.setVisible(true);
			fadeIn.play();
		} else {
			messageLabel.setText("Code does not match");
		}
	}
	
	public void setNewPassword(ActionEvent e) throws IOException {
		String password = newPasswordField.getText();
		String code = recoveryCodeField.getText();
		boolean worked = false;
		
		try {
			worked = um.resetPassword(code, password);
		} catch (DatabaseErrorException e1) {
			e1.printStackTrace();
		}
		
		if (worked) {
			messageLabel.setText("Password Updated. Press back to return to login screen.");
			newPasswordField.clear();
			
			// Wait for user to read message
			try {
				java.util.concurrent.TimeUnit.SECONDS.sleep(4);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			} finally {
				um.exit();
			}
			
			// Switch scene
			m.changeScene("login.fxml");
		} else {
			messageLabel.setText("There was an error, please try again later.");
		}
		
	}
}
