package GUI;

import GUI.model.PreviousSales;
import GUI.model.Request;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    @FXML
    private TableView<?> requestTableView;

    @FXML
    private TableView<?> sellsTableView;

    @FXML
    private TableColumn<Request, Button> acceptTableColumnR;

    @FXML
    private TableColumn<Request, Button> declineTableColumnR;

    @FXML
    private TableColumn<Request, String> makeTableColumnR;

    @FXML
    private TableColumn<PreviousSales, String> makeTableColumnS;

    @FXML
    private TableColumn<Request, String> mileageTableColumnR;

    @FXML
    private TableColumn<PreviousSales, String> mileageTableColumnS;

    @FXML
    private TableColumn<Request, String> modelTableColumnR;

    @FXML
    private TableColumn<PreviousSales, String> modelTableColumnS;

    @FXML
    private TableColumn<Request, String> nameTableColumnR;

    @FXML
    private TableColumn<PreviousSales, String> nameTableColumnS;

    @FXML
    private TableColumn<Request, String> priceTableColumnR;

    @FXML
    private TableColumn<PreviousSales, String> priceTableColumnS;

    @FXML
    private TableColumn<Request, String> vinTableColumnR;

    @FXML
    private TableColumn<PreviousSales, String> vinTableColumnS;

    @FXML
    private TableColumn<Request, String> yearTableColumnR;

    @FXML
    private TableColumn<PreviousSales, String> yearTableColumnS;

    @FXML
    private TextField makeAdminTextField;

    @FXML
    private Button makeAdminButton;

    @FXML
    private Button exitButton;

    @FXML
    private Label adminNameLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTable();
    }

    private void initTable() {

    }

    private void initColumnsR() {
        //name, vin, make, model, year, mileage, price;
        nameTableColumnR.setCellValueFactory(new PropertyValueFactory<>("name"));
        vinTableColumnR.setCellValueFactory(new PropertyValueFactory<>("vin"));
        makeTableColumnR.setCellValueFactory(new PropertyValueFactory<>("make"));
        modelTableColumnR.setCellValueFactory(new PropertyValueFactory<>("model"));
        yearTableColumnR.setCellValueFactory(new PropertyValueFactory<>("year"));
        mileageTableColumnR.setCellValueFactory(new PropertyValueFactory<>("mileage"));
        priceTableColumnR.setCellValueFactory(new PropertyValueFactory<>("price"));
        acceptTableColumnR.setCellValueFactory(new PropertyValueFactory<>("accept"));
        declineTableColumnR.setCellValueFactory(new PropertyValueFactory<>("decline"));

        editableCols();
    }

    private void initColumnsS() {
        //name, vin, make, model, year, mileage, price;
        nameTableColumnS.setCellValueFactory(new PropertyValueFactory<>("name"));
        vinTableColumnS.setCellValueFactory(new PropertyValueFactory<>("vin"));
        makeTableColumnS.setCellValueFactory(new PropertyValueFactory<>("make"));
        modelTableColumnS.setCellValueFactory(new PropertyValueFactory<>("model"));
        yearTableColumnS.setCellValueFactory(new PropertyValueFactory<>("year"));
        mileageTableColumnS.setCellValueFactory(new PropertyValueFactory<>("mileage"));
        priceTableColumnS.setCellValueFactory(new PropertyValueFactory<>("price"));

        editableCols();
    }

    private void editableCols() {

        nameTableColumnR.setCellFactory(TextFieldTableCell.forTableColumn());

        nameTableColumnR.setOnEditCommit(e-> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setName(e.getNewValue());
        });

        vinTableColumnR.setCellFactory(TextFieldTableCell.forTableColumn());

        vinTableColumnR.setOnEditCommit(e-> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setVin(e.getNewValue());
        });

        makeTableColumnR.setCellFactory(TextFieldTableCell.forTableColumn());

        makeTableColumnR.setOnEditCommit(e-> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setMake(e.getNewValue());
        });

        modelTableColumnR.setCellFactory(TextFieldTableCell.forTableColumn());

        modelTableColumnR.setOnEditCommit(e-> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setModel(e.getNewValue());
        });

        yearTableColumnR.setCellFactory(TextFieldTableCell.forTableColumn());

        yearTableColumnR.setOnEditCommit(e-> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setYear(e.getNewValue());
        });

        mileageTableColumnR.setCellFactory(TextFieldTableCell.forTableColumn());

        mileageTableColumnR.setOnEditCommit(e-> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setMileage(e.getNewValue());
        });

        priceTableColumnR.setCellFactory(TextFieldTableCell.forTableColumn());

        priceTableColumnR.setOnEditCommit(e-> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setPrice(e.getNewValue());
        });

        requestTableView.setEditable(true);
        sellsTableView.setEditable(true);
    }


    public void makeAdminButtonOnAction(ActionEvent event) {
        //make the username an admin
    }

    public void exitButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    private void loadDataR() {
        ObservableList<Request> requestData = FXCollections.observableArrayList();
        // add data here for request dont forget to add accept and decline button
    }

    // private void loadDataS() {
    //    ObservableList<Request> requestData = FXCollections.observableArrayList();
    //
    // }
}
