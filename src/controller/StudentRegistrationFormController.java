package controller;

import com.jfoenix.controls.JFXTextField;
import dto.StudentDTO;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import service.custom.StudentService;
import service.custom.impl.StudentServiceImpl;
import util.Navigation;
import util.Routes;

import java.io.IOException;
import java.sql.SQLException;

public class StudentRegistrationFormController {
    public AnchorPane pane;
    public JFXTextField txtContact;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtStudentId;
    public JFXTextField txtBirthday;
    public JFXTextField txtGender;
    public TableView tblView;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colBirthday;
    public TableColumn colGender;

    private final StudentService studentService = new StudentServiceImpl();
    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.MAIN_FORM,pane);
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
        String student_id=txtStudentId.getText();
        try {
            StudentDTO studentDTO = studentService.searchStudent(student_id);
            if (studentDTO!=null) {
                txtStudentId.setText(studentDTO.getId());
                txtName.setText(studentDTO.getName());
                txtAddress.setText(studentDTO.getAddress());
                txtContact.setText(studentDTO.getContact_Number());
                txtBirthday.setText(studentDTO.getDate_of_birthday());
                txtGender.setText(studentDTO.getGender());
            }else {
                new Alert(Alert.AlertType.ERROR, "Not Found Student!..Try Again").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Not Found Student!").show();
        }
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        String student_id = txtStudentId.getText();
        String name=txtName.getText();
        String address=txtAddress.getText();
        String contact = txtContact.getText();
        String dob = txtBirthday.getText();
        String gender=txtGender.getText();

        try {
            boolean b = studentService.saveStudent(new StudentDTO(student_id, name, address, contact, dob, gender));
            new Alert(Alert.AlertType.CONFIRMATION, "Successfully Added Student !").show();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        txtSearchOnAction(actionEvent);
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String student_id = txtStudentId.getText();
        String name=txtName.getText();
        String address=txtAddress.getText();
        String contact = txtContact.getText();
        String dob = txtBirthday.getText();
        String gender=txtGender.getText();

        try {
            boolean b = studentService.updateStudent(new StudentDTO(student_id, name, address, contact, dob, gender));
            new Alert(Alert.AlertType.CONFIRMATION, "Successfully Updated Student !").show();
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Unsuccessfully Updated Student !").show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String student_id = txtStudentId.getText();

        try {
            boolean b = studentService.deleteStudent(student_id);
            new Alert(Alert.AlertType.CONFIRMATION, "Successfully Delete Student !").show();
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Delete Fail Student !").show();
        }
    }
}
