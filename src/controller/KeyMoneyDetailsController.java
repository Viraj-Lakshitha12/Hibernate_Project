package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.CustomDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.custom.KeyMoneyService;
import service.custom.impl.KeyMoneyServiceImpl;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class KeyMoneyDetailsController {
    public int tblVIew;
    public TableColumn colStudentId;
    public TableColumn colName;
    public TableColumn colRoomTypeId;
    public TableColumn colType;
    public TableColumn colKeyMoney;
    public TableColumn colPaymentStatus;
    public TableColumn colDate;
    public JFXTextField txtStudentId;
    public JFXComboBox cmbPaymentStatus;
    public JFXButton btnUpdate;
    public JFXTextField txtReservationId;
    public JFXTextField txtDate;
    public JFXTextField txtName;
    public JFXTextField txtType;
    public JFXTextField txtRoomTypeId;
    public JFXTextField txtKeyMoney;
    public AnchorPane pane;
    public TableView tableView;
    public TableColumn colReservationId;

    private final KeyMoneyService keyMoneyService = new KeyMoneyServiceImpl();
    public void initialize(){
        loadAllKeyMoneyDetails();
    }

    private void loadAllKeyMoneyDetails() {
        colReservationId.setCellValueFactory(new PropertyValueFactory("res_id"));
        colStudentId.setCellValueFactory(new PropertyValueFactory("student_id"));
        colName.setCellValueFactory(new PropertyValueFactory("name"));
        colRoomTypeId.setCellValueFactory(new PropertyValueFactory("room_type_id"));
        colType.setCellValueFactory(new PropertyValueFactory("type"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory("key_money"));
        colPaymentStatus.setCellValueFactory(new PropertyValueFactory("status"));
        colDate.setCellValueFactory(new PropertyValueFactory("date"));

        try {
            ArrayList<CustomDTO> allPendingKeyMoney = keyMoneyService.getAllPendingKeyMoney();
            ObservableList observableList = FXCollections.observableArrayList();
            for (CustomDTO c:allPendingKeyMoney) {
                observableList.add(c);
                tableView.setItems(observableList);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage1 = (Stage) pane.getScene().getWindow();
        stage1.close();
        URL resource = getClass().getResource("/view/DashBoard.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage stage2 = new Stage();
        Scene scene = new Scene(load);
        stage2.setScene(scene);
        stage2.show();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        try {
            boolean b = keyMoneyService.updateReservationUsingId(txtReservationId.getText(), String.valueOf(cmbPaymentStatus.getValue()));
            if (b){
                new Alert(Alert.AlertType.CONFIRMATION,"Updated").show();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
