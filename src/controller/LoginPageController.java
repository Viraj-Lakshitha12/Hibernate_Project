package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dto.UserDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.custom.UserService;
import service.custom.impl.UserServiceImpl;
import util.Navigation;
import util.Routes;

import javax.jws.soap.SOAPBinding;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class LoginPageController {
    public JFXTextField txtUserName;
    public JFXPasswordField txtPassword;
    public AnchorPane pane;

    private final UserService userService = new UserServiceImpl();
    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {

        try {
            UserDTO userDTO = userService.searchUsers(txtUserName.getText());
            if (userDTO==null){
                new Alert(Alert.AlertType.ERROR, " Password").show();
                return;
            }

            if  (txtUserName.getText()!=null&& txtPassword.getText()!=null) {

                if (txtUserName.getText().equals(userDTO.getUserId()) &&  txtPassword.getText().equals(userDTO.getPassword())){
                    Stage stage1 = (Stage) pane.getScene().getWindow();
                    stage1.close();
                    URL resource = getClass().getResource("/view/DashBoard.fxml");
                    Parent load = FXMLLoader.load(resource);
                    Stage stage2 = new Stage();
                    Scene scene = new Scene(load);
                    stage2.setScene(scene);
                    stage2.show();

                }else {
                    new Alert(Alert.AlertType.WARNING,"Wrong UserName OR Password, Try Again!").show();
                }

            }
            } catch(SQLException | ClassNotFoundException e){
                throw new RuntimeException(e);
            }
    }
}
