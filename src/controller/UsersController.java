package controller;

import com.jfoenix.controls.JFXTextField;
import dto.UserDTO;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import service.custom.UserService;
import service.custom.impl.UserServiceImpl;
import util.Navigation;
import util.Routes;

import java.io.IOException;
import java.sql.SQLException;

public class UsersController {

    public AnchorPane pane;
    public JFXTextField txtUserId;
    public JFXTextField txtName;
    public TableView tblUser;
    public TableColumn colUserId;
    public TableColumn colName;
    public TableColumn colTelNo;
    public TableColumn colEmail;
    public TableColumn colUserName;
    public TableColumn colPassword;
    public JFXTextField txtTelNo;
    public JFXTextField txtEmail;
    public JFXTextField txtUserName;
    public JFXTextField txtPassword;

    private final UserService userService = new UserServiceImpl();

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.MAIN_FORM,pane);
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        String id = txtUserId.getText();
        String name = txtName.getText();
        String email = txtEmail.getText();
        String tel = txtTelNo.getText();
        String userName = txtUserName.getText();
        String password = txtPassword.getText();
        UserDTO userDTO = new UserDTO(id,name,email,tel,userName,password);

        try {
            boolean b = userService.saveUsers(userDTO);
            if (b){
                new Alert(Alert.AlertType.CONFIRMATION, "Successfully Added Users !").show();
                Navigation.navigate(Routes.MANAGE_USERS,pane);
            }else{
                new Alert(Alert.AlertType.ERROR, " Added Fail !").show();
            }
        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        String userIdText = txtUserId.getText();
        try {
            UserDTO userDTO = userService.searchUsers(userIdText);
            if (userDTO!=null){
               txtName.setText(userDTO.getName());
               txtEmail.setText(userDTO.getEmail());
               txtTelNo.setText(userDTO.getTelNo());
               txtUserName.setText(userDTO.getUserName());
               txtPassword.setText(userDTO.getPassword());
            }else{
                new Alert(Alert.AlertType.ERROR, "Not Found !").show();

            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String id = txtUserId.getText();
        String name = txtName.getText();
        String email = txtEmail.getText();
        String tel = txtTelNo.getText();
        String userName = txtUserName.getText();
        String password = txtPassword.getText();
        UserDTO userDTO = new UserDTO(id,name,email,tel,userName,password);

        try {
            boolean b = userService.updateUsers(userDTO);
            if (b){
                new Alert(Alert.AlertType.CONFIRMATION, "Successfully Update Users !").show();
                Navigation.navigate(Routes.MANAGE_USERS,pane);
            }else{
                new Alert(Alert.AlertType.ERROR, " Update Fail !").show();
            }
        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String txtUserIdText = txtUserId.getText();
        try {
            boolean b = userService.deleteUsers(txtUserIdText);
            if (b){
                new Alert(Alert.AlertType.CONFIRMATION, "Successfully Delete Users !").show();
                Navigation.navigate(Routes.MANAGE_USERS,pane);
            }else{
                new Alert(Alert.AlertType.ERROR, " Delete Fail !").show();
            }
        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void textFields_Key_Released(KeyEvent keyEvent) {
    }
}
