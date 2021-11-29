package GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;

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

     public void submitButtonOnAction(ActionEvent event) {
        if (!usernameTextField.getText().isBlank() && !passwordTextField.getText().isBlank() && !firstnameTextField.getText().isBlank() && !lastnameTextField.getText().isBlank() && !emailTextField.getText().isBlank()) {
        //I'll assume saving the above fields into the datebase goes here
        } else {
            registerMessageLabel.setText("Please fill in all of the boxes.");
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