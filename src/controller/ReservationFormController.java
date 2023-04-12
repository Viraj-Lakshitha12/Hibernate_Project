package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import util.Navigation;
import util.Routes;

import java.io.IOException;

public class ReservationFormController {
    public AnchorPane pane;
    public ComboBox cmbStudentIfd;
    public TextField txtStatus;
    public Label lblRoomId;
    public Label lblReservationId;
    public Label lblAvailableRoom;
    public ComboBox cmbRoomType;
    public Label lblTime;
    public TableView tblView;
    public TableColumn colReservationId;
    public TableColumn colRoomId;
    public TableColumn colRoomType;
    public TableColumn colStudentId;
    public TableColumn colDate;
    public TableColumn colStatus;
    public ComboBox cmbRoomID;
    public Label lblRoomType;

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.MAIN_FORM,pane);
    }

    public void cmbRoomTypeOnAction(ActionEvent actionEvent) {
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
    }

    public void cmbRoomIdOnAction(ActionEvent actionEvent) {
    }
}