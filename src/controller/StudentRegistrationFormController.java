package controller;

import com.jfoenix.controls.JFXTextField;
import dto.RoomsDTO;
import dto.StudentDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import service.custom.StudentService;
import service.custom.impl.StudentServiceImpl;
import util.Navigation;
import util.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

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
    private final ObservableList observableList = FXCollections.observableArrayList();
    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.MAIN_FORM,pane);
    }

    public void initialize(){
        loadAllStudentDetails();
    }

    private void loadAllStudentDetails() {
        colId.setCellValueFactory(new PropertyValueFactory("student_id"));
        colName.setCellValueFactory(new PropertyValueFactory("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory("address"));
        colContact.setCellValueFactory(new PropertyValueFactory("contact_no"));
        colBirthday.setCellValueFactory(new PropertyValueFactory("dob"));
        colGender.setCellValueFactory(new PropertyValueFactory("gender"));

        try {
            ArrayList<StudentDTO> allStudent = studentService.getAllRooms();
            for (StudentDTO c:allStudent) {
                observableList.add(c);
                tblView.setItems(observableList);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
        String student_id=txtStudentId.getText();
        try {
            StudentDTO studentDTO = studentService.searchStudent(student_id);
            if (studentDTO!=null) {
                txtStudentId.setText(studentDTO.getStudent_id());
                txtName.setText(studentDTO.getName());
                txtAddress.setText(studentDTO.getAddress());
                txtContact.setText(studentDTO.getContact_no());
                txtBirthday.setText(String.valueOf(studentDTO.getDob()));
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
        LocalDate dob = LocalDate.parse(txtBirthday.getText());
        String gender=txtGender.getText();

        try {
            boolean b = studentService.saveStudent(new StudentDTO(student_id, name, address, contact, dob, gender));
            if (b){
                new Alert(Alert.AlertType.CONFIRMATION, "Successfully Added Student !").show();
                Navigation.navigate(Routes.STUDENT_REGISTRATION,pane);
            }else{
                new Alert(Alert.AlertType.ERROR, " Added Fail !").show();
            }
        } catch (SQLException | ClassNotFoundException | IOException e) {
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
        LocalDate dob = LocalDate.parse(txtBirthday.getText());
        String gender=txtGender.getText();

        try {
            boolean b = studentService.updateStudent(new StudentDTO(student_id, name, address, contact, dob, gender));
            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "Successfully Updated Student !").show();
                Navigation.navigate(Routes.STUDENT_REGISTRATION,pane);
            }else{
                new Alert(Alert.AlertType.ERROR, "Unsuccessfully Updated Student !").show();
            }
            } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Unsuccessfully Updated Student !").show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String student_id = txtStudentId.getText();

        try {
            boolean b = studentService.deleteStudent(student_id);
            if(b){
                new Alert(Alert.AlertType.CONFIRMATION, "Successfully Delete Student !").show();
                Navigation.navigate(Routes.STUDENT_REGISTRATION,pane);
            }else{
                new Alert(Alert.AlertType.ERROR, "Delete Fail Student !").show();
                Navigation.navigate(Routes.STUDENT_REGISTRATION,pane);
            }
        } catch (SQLException | ClassNotFoundException | IOException e) {
            new Alert(Alert.AlertType.ERROR, "Delete Fail Student !").show();
        }
    }
}
