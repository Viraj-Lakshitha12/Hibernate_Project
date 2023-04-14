package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.Navigation;
import util.Routes;

import java.io.IOException;
import java.net.URL;

public class LoginPageController {
    public JFXTextField txtUserName;
    public JFXPasswordField txtPassword;
    public AnchorPane pane;

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage1= (Stage)pane.getScene().getWindow();
        stage1.close();
        URL resource = getClass().getResource("/view/DashBoard.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage stage2= new Stage();
        Scene scene = new Scene(load);
        stage2.setScene(scene);
        stage2.show();

    }
}
