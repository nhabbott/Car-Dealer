package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SellController {

        @FXML
        private Button cancelButton;

        @FXML
        private ComboBox<?> copComboBox;

        @FXML
        private ChoiceBox<?> cylinderChoiceBox;

        @FXML
        private ChoiceBox<?> fuelChoiceBox;

        @FXML
        private ComboBox<?> makeComboBox;

        @FXML
        private TextField mileageTextField;

        @FXML
        private ComboBox<?> modelComboBox;

        @FXML
        private Label nameLabel; //not sure how to make this the current users name

        @FXML
        private TextField priceTextField;

        @FXML
        private Button sellButton;

        @FXML
        private ChoiceBox<?> sizeChoiceBox;

        @FXML
        private ChoiceBox<?> transmissionChoiceBox;

        @FXML
        private ChoiceBox<?> typeChoiceBox;

        @FXML
        private TextField vinTextField;

        @FXML
        private TextField yearTextField;

        @FXML
        private Label sellMessageLabel;


    public void cancelButtonOnAction(ActionEvent event) throws IOException {
        Parent listingPageParent = FXMLLoader.load(getClass().getResource("listing.fxml"));
        Scene listingPageScene = new Scene(listingPageParent);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(listingPageScene);
        appStage.show();
    }

    public void sellButtonOnAction(ActionEvent event) {
        if (!vinTextField.getText().isBlank() && !yearTextField.getText().isBlank() &&
                !mileageTextField.getText().isBlank() && !priceTextField.getText().isBlank() &&
                !typeChoiceBox.getSelectionModel().isEmpty() && !transmissionChoiceBox.getSelectionModel().isEmpty()
                && !sizeChoiceBox.getSelectionModel().isEmpty() && !cylinderChoiceBox.getSelectionModel().isEmpty() &&
                !fuelChoiceBox.getSelectionModel().isEmpty() && !makeComboBox.getSelectionModel().isEmpty() &&
                !modelComboBox.getSelectionModel().isEmpty() && !copComboBox.getSelectionModel().isEmpty()) {
            //I'll assume saving the above fields into the datebase goes here
        } else {
            sellMessageLabel.setText("Please provide information for all fields.");
        }
    }


}
