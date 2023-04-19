package util;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {
    private static AnchorPane pane;

    public static void navigate(Routes route, AnchorPane pane) throws IOException {
        Navigation.pane = pane;
        Navigation.pane.getChildren().clear();
        Stage window = (Stage)Navigation.pane.getScene().getWindow();

        switch (route) {
            case STUDENT_REGISTRATION:
                window.setTitle("Student Registration Form");
                initUI("StudentRegistrationForm.fxml");
                break;
            case MAIN_FORM:
                window.setTitle("Dashboard Form");
                initUI("DashBoard.fxml");
                break;
            case MANAGE_ROOMS:
                window.setTitle("Room Form");
                initUI("ManageRooms.fxml");
                break;
            case RESERVATION:
                window.setTitle("Room Reservation");
                initUI("ReservationForm.fxml");
                break;
            case MANAGE_USERS:
                window.setTitle("manage Users");
                initUI("Users.fxml");
                break;
            case LOGIN:
                window.setTitle("Login Form");
                initUI("LoginPage.fxml");
                break;
            case KEY_MONEY:
                window.setTitle("Key Money Form");
                initUI("KeyMoneyDetails.fxml");
                break;
            default:
                new Alert(Alert.AlertType.ERROR, "UI Not Found!").show();
        }
    }
    public static void initUI(String location) throws IOException {
        Navigation.pane.getChildren().add(FXMLLoader.load(Navigation.class.getResource("/view/" + location)));
    }
}
