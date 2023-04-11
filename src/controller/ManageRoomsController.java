package controller;

import com.jfoenix.controls.JFXTextField;
import dto.RoomsDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.custom.RoomsService;
import service.custom.impl.RoomsServiceImpl;
import util.Navigation;
import util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

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

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.MAIN_FORM,pane);
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        String room_id = txtRoomId.getText();
        String room_type= txtRoomType.getText();
        double key_money = Double.parseDouble(txtKeyMoney.getText());
        int qty = Integer.parseInt(txtQty.getText());

        try {
            roomsService.saveRooms(new RoomsDTO(room_id,room_type,key_money,qty));
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        String room_id = txtRoomId.getText();

        try {
            RoomsDTO roomsDTO = roomsService.searchRooms(room_id);
            txtRoomType.setText(roomsDTO.getRoomType());

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
            roomsService.updateRooms(new RoomsDTO(room_id,room_type,key_money,qty));
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }

    public void txtSearchRooms(ActionEvent actionEvent) {
    }
}
