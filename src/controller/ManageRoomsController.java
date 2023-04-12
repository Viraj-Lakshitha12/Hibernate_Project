package controller;

import com.jfoenix.controls.JFXTextField;
import dto.RoomsDTO;
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
import service.custom.RoomsService;
import service.custom.impl.RoomsServiceImpl;
import util.Navigation;
import util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

public class ManageRoomsController {
    public AnchorPane pane;
    public JFXTextField txtRoomId;
    public JFXTextField txtKeyMoney;
    public JFXTextField txtRoomType;
    public JFXTextField txtQty;
    public TableView tblView;
    public TableColumn colRoomId;
    public TableColumn colRoomType;
    public TableColumn colKeyMoney;
    public TableColumn ColAvailableQty;

    private final RoomsService roomsService=new RoomsServiceImpl();
    private final ObservableList observableList = FXCollections.observableArrayList();
    public TableColumn colQty;

    public void initialize(){
        loadAllRoomDetails();
    }

    private void loadAllRoomDetails() {
        colRoomId.setCellValueFactory(new PropertyValueFactory("roomId"));
        colRoomType.setCellValueFactory(new PropertyValueFactory("room_type"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory("key_money"));
        colQty.setCellValueFactory(new PropertyValueFactory("qty"));

        try {
            ArrayList<RoomsDTO> allRooms = roomsService.getAllRooms();
            for (RoomsDTO c:allRooms) {
                observableList.add(c);
                tblView.setItems(observableList);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.MAIN_FORM,pane);
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        String room_id = txtRoomId.getText();
        String room_type= txtRoomType.getText();
        double key_money = Double.parseDouble(txtKeyMoney.getText());
        int qty = Integer.parseInt(txtQty.getText());

        try {
            boolean b = roomsService.saveRooms(new RoomsDTO(room_id, room_type, key_money, qty));
            if (b){
                new Alert(Alert.AlertType.CONFIRMATION, "Successfully Added !").show();
            }else{
                new Alert(Alert.AlertType.CONFIRMATION, "Added  Fail!").show();

            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        String room_id = txtRoomId.getText();

        try {
            RoomsDTO roomsDTO = roomsService.searchRooms(room_id);
            if (roomsDTO!=null) {
                txtRoomType.setText(roomsDTO.getRoomType());
                txtKeyMoney.setText(String.valueOf(roomsDTO.getKey_money()));
                txtQty.setText(String.valueOf(roomsDTO.getQty()));
            }else {
                new Alert(Alert.AlertType.ERROR, "Not Found Room!..Try Again").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String room_id = txtRoomId.getText();
        String room_type= txtRoomType.getText();
        double key_money = Double.parseDouble(txtKeyMoney.getText());
        int qty = Integer.parseInt(txtQty.getText());

        try {
            boolean b = roomsService.updateRooms(new RoomsDTO(room_id, room_type, key_money, qty));
            if (b){
                new Alert(Alert.AlertType.CONFIRMATION, "Successfully Update !").show();
            }else{
                new Alert(Alert.AlertType.CONFIRMATION, "update  Fail!").show();

            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String room_id = txtRoomId.getText();
        try {
            boolean b = roomsService.deleteRooms(room_id);
            if (b){
                new Alert(Alert.AlertType.CONFIRMATION, "Successfully Delete !").show();
            }else{
                new Alert(Alert.AlertType.CONFIRMATION, "Delete  Fail!").show();

            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void txtSearchRooms(ActionEvent actionEvent) {
        btnSearchOnAction(actionEvent);
    }
}
