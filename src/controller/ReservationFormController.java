package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.MAIN_FORM,pane);
    }

    public void cmbRoomTypeOnAction(ActionEvent actionEvent) {
    }
}
