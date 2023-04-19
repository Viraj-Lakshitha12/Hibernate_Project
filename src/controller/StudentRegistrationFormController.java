package controller;

import com.jfoenix.controls.JFXTextField;
import dto.RoomsDTO;
import dto.StudentDTO;
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
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import service.custom.StudentService;
import service.custom.impl.StudentServiceImpl;
import util.Navigation;
import util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Pattern;

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

    private Pattern id;
    private Pattern name;
    private Pattern address;
    private Pattern contactNo;
    private Pattern dob;

    private final StudentService studentService = new StudentServiceImpl();
    private final ObservableList observableList = FXCollections.observableArrayList();
    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage1 = (Stage) pane.getScene().getWindow();
        stage1.close();
        URL resource = getClass().getResource("/view/DashBoard.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage stage2 = new Stage();
        Scene scene = new Scene(load);
        stage2.setScene(scene);
        stage2.show();
    }

    public void initialize(){
        loadAllStudentDetails();
        pattern();
    }

    private void pattern() {
        id = Pattern.compile("^[S][0-9]{3,}$");
        name = Pattern.compile("^[A-Za-z0-9]{3,}$");
        address =  Pattern.compile("^[A-Za-z0-9]{3,}$");
        contactNo = Pattern.compile("^[0-9]{10}$");
        dob=Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$\n");
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
        boolean isStudentIdMatched = id.matcher(txtStudentId.getText()).matches();
        boolean isStudentName = name.matcher(txtName.getText()).matches();
        boolean isStudentAddress = address.matcher(txtAddress.getText()).matches();
        boolean isStudentContact = contactNo.matcher(txtContact.getText()).matches();
        boolean  isStudentDob= contactNo.matcher(txtBirthday.getText()).matches();

        if (isStudentIdMatched){
            if (isStudentName){
                if (isStudentAddress){
                    if (isStudentContact){
                        if (isStudentDob){

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

                        }else{
                            txtBirthday.setFocusColor(Paint.valueOf("Red"));
                            txtBirthday.requestFocus();
                        }
                    }else{
                        txtContact.setFocusColor(Paint.valueOf("Red"));
                        txtContact.requestFocus();
                    }
                }else{
                    txtAddress.setFocusColor(Paint.valueOf("Red"));
                    txtAddress.requestFocus();
                }
            }else{
                txtName.setFocusColor(Paint.valueOf("Red"));
                txtName.requestFocus();
            }
        }else{
            txtStudentId.setFocusColor(Paint.valueOf("Red"));
            txtStudentId.requestFocus();
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
