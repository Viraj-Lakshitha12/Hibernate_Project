package controller;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import util.Navigation;
import util.Routes;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DashBoardController {
    public AnchorPane root;
    public ImageView imgEmployee;
    public ImageView imgMealPlans;
    public ImageView imgRooms;
    public Label lbl1;
    public Label lblTime;
    public Label lblDate;
    public Label lblDatee;
    public ImageView imgStudents;
    public ImageView imgReservation;
    public ImageView imgLogOut;

    public void initialize() {
        setTime();
        setDate();
    }

    private void setDate() {
        LocalDate currentDate = LocalDate.now();
        lblDatee.setText(currentDate.toString());
    }

    private void setTime() {
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        String formattedTime = currentTime.format(formatter);
       lblTime.setText(formattedTime);

    }


    public void playMouseEnterAnimation(MouseEvent event) {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();

            switch (icon.getId()) {
                case "imgEmployee":
                    lbl1.setText("Manage Users");
                    break;
                case "imgRooms":
                    lbl1.setText("Manage Rooms");
                    break;
                case "imgStudents":
                    lbl1.setText("Manage Students");
                    break;
                case "imgReservation":
                    lbl1.setText("Reservations");
                    break;
                case "imgLogOut":
                    lbl1.setText("LOGOUT");
                    break;
            }

            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play();

            DropShadow glow = new DropShadow();
            glow.setColor(Color.CORNFLOWERBLUE);
            glow.setWidth(20);
            glow.setHeight(20);
            glow.setRadius(20);
            icon.setEffect(glow);
        }
    }

    public void playMouseExitAnimation(MouseEvent event) {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();

            icon.setEffect(null);
//            lblMenu.setText("Welcome");
//            lblDescription.setText("Please select one of above main operations to proceed");
        }
    }


    public void StudentRegistration(MouseEvent event) throws IOException {
        Navigation.navigate(Routes.STUDENT_REGISTRATION,root);
    }

    public void ManageRooms(MouseEvent event) throws IOException {
        Navigation.navigate(Routes.MANAGE_ROOMS,root);
    }

    public void ReservationOnClicked(MouseEvent event) throws IOException {
        Navigation.navigate(Routes.RESERVATION,root);
    }

    public void UsersNavigation(MouseEvent event) throws IOException {
        Navigation.navigate(Routes.MANAGE_USERS,root);
    }

    public void Logout(MouseEvent event) throws IOException {
        Stage stage1 = (Stage) root.getScene().getWindow();
        stage1.close();
        URL resource = getClass().getResource("/view/LoginPage.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage stage2 = new Stage();
        Scene scene = new Scene(load);
        stage2.setScene(scene);
        stage2.show();
    }
}
