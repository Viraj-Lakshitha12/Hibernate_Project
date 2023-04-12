package controller;

import com.jfoenix.controls.JFXTextField;
import dto.RoomsDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import service.custom.RoomsService;
import service.custom.impl.RoomsServiceImpl;
import util.Navigation;
import util.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReservationFormController {

    public JFXTextField txtReservationID;
    public JFXTextField txtStatus;
    public TableView tblView;
    public TableColumn colId;
    public TableColumn colRoomType;
    public TableColumn colRoomId;
    public TableColumn colStudentId;
    public TableColumn colStatus;
    public Label lblRoomId;
    public ComboBox cmbStudentId;
    public AnchorPane pane;

    private final RoomsService roomsService = new RoomsServiceImpl();
    public ComboBox cmbRoomType;
    public Label lblRoomType;
    public ComboBox cmbRoomId;

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.MAIN_FORM,pane);
    }


    public void btnAddOnAction(ActionEvent actionEvent) {
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }

    public void cmbStudentIdOnAction(ActionEvent actionEvent) {

    }

    public void cmbRoomIdOnAction(ActionEvent actionEvent) {
        try {
            ArrayList<RoomsDTO> allRooms = roomsService.getAllRooms();
            ObservableList observableList = FXCollections.observableArrayList();
            for (RoomsDTO r:allRooms) {
                observableList.add(r.getRoomId());
                cmbRoomId.setItems(observableList);

            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}