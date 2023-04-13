package controller;

import com.jfoenix.controls.JFXTextField;
import dto.ReservationDTO;
import dto.RoomsDTO;
import dto.StudentDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import service.custom.ReservationService;
import service.custom.RoomsService;
import service.custom.StudentService;
import service.custom.impl.ReservationServiceImpl;
import service.custom.impl.RoomsServiceImpl;
import service.custom.impl.StudentServiceImpl;
import util.Navigation;
import util.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ReservationFormController {

    public JFXTextField txtReservationID;
    public JFXTextField txtStatus;
    public TableView tblView;
    public TableColumn colId;
    public TableColumn colRoomType;
    public TableColumn colRoomId;
    public TableColumn colStudentId;
    public TableColumn colStatus;
    public Label lblRoomId;
    public ComboBox cmbStudentId;
    public AnchorPane pane;

    private final RoomsService roomsService = new RoomsServiceImpl();
    private final StudentService studentService = new StudentServiceImpl();
    private final ReservationService reservationService = new ReservationServiceImpl();

    private final ObservableList observableList = FXCollections.observableArrayList();

    public ComboBox cmbRoomType;
    public Label lblRoomType;
    public ComboBox cmbRoomId;
    public Label lblReservationId
            ;
    public Label lblDate;

    public TableColumn colDate;
    public JFXTextField txtReservationId;

    public void initialize(){
        loadRoomTypes();
        loadStudentId();
        generateReservationId();
        loadLocalDate();
        loadAllReservationDetails();
    }

    private void loadAllReservationDetails() {
        colId.setCellValueFactory(new PropertyValueFactory("reservation_id"));
        colRoomType.setCellValueFactory(new PropertyValueFactory("room_type"));
        colRoomId.setCellValueFactory(new PropertyValueFactory("room_id"));
        colStudentId.setCellValueFactory(new PropertyValueFactory("student_id"));
        colStatus.setCellValueFactory(new PropertyValueFactory("status"));
        colDate.setCellValueFactory(new PropertyValueFactory("date"));

        try {
            ArrayList<ReservationDTO> allReservation = reservationService.getAllReservation();
            for (ReservationDTO a:allReservation) {
                observableList.add(a);
                tblView.setItems(observableList);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    private void loadLocalDate() {
        LocalDate localDate = LocalDate.now();
        lblDate.setText(localDate.toString());
    }

    private void generateReservationId() {
        try {
            ArrayList<ReservationDTO> allReservation = reservationService.getAllReservation();
            if (allReservation.size()!=0){
                lblReservationId.setText(String.valueOf(allReservation.size()+1));
            }else {
                lblReservationId.setText("1");
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadStudentId() {
        try {
            ArrayList<StudentDTO> allRooms = studentService.getAllRooms();
            ObservableList observableList = FXCollections.observableArrayList();
            for (StudentDTO s:allRooms) {
                observableList.add(s.getId());
                cmbStudentId.setItems(observableList);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadRoomTypes() {
        try {
            ArrayList<RoomsDTO> allRooms = roomsService.getAllRooms();
            ObservableList observableList1 = FXCollections.observableArrayList();
            for (RoomsDTO r:allRooms) {
                observableList1.add(r.getRoomId());
                cmbRoomId.setItems(observableList1);

            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.MAIN_FORM,pane);
    }


    public void btnAddOnAction(ActionEvent actionEvent) {
        String reservation_id = lblReservationId.getText();
        String date = lblDate.getText();
        String student_id = cmbStudentId.getSelectionModel().getSelectedItem().toString();
        System.out.println(student_id);
        String room_id = cmbRoomId.getSelectionModel().getSelectedItem().toString();
        String room_type = lblRoomType.getText();
        String status = txtStatus.getText();

        ReservationDTO reservationDTO = new ReservationDTO(reservation_id,date,student_id, room_id, room_type, status);
        try {
            boolean b = reservationService.saveReservation(reservationDTO);
            if (b){
                new Alert(Alert.AlertType.CONFIRMATION, "Successfully Added !").show();
                Navigation.navigate(Routes.RESERVATION,pane);
            }else {
                new Alert(Alert.AlertType.ERROR, "Added Fail!").show();
            }
        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        txtReservationIDOnAction(actionEvent);
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String reservation_id = lblReservationId.getText();
        String date = lblDate.getText();
        String student_id = cmbStudentId.getSelectionModel().getSelectedItem().toString();
        System.out.println(student_id);
        String room_id = cmbRoomId.getSelectionModel().getSelectedItem().toString();
        String room_type = lblRoomType.getText();
        String status = txtStatus.getText();

        ReservationDTO reservationDTO = new ReservationDTO(reservation_id,date,student_id, room_id, room_type, status);
        try {
            boolean b = reservationService.updateReservation(reservationDTO);
            if (b){
                new Alert(Alert.AlertType.CONFIRMATION, "Successfully Update !").show();
                Navigation.navigate(Routes.RESERVATION,pane);
            }else {
                new Alert(Alert.AlertType.ERROR, "Added Fail!").show();

            }
        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String reservation_id = txtReservationId.getText();
        try {
            boolean b = reservationService.deleteReservation(reservation_id);
            if (b){
                new Alert(Alert.AlertType.CONFIRMATION, "Successfully Delete !").show();
                Navigation.navigate(Routes.RESERVATION,pane);
           }else {
                new Alert(Alert.AlertType.ERROR, "Delete Fail!").show();
            }
        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void cmbStudentIdOnAction(ActionEvent actionEvent) {

    }

    public void cmbRoomIdOnAction(ActionEvent actionEvent) {
        String room_id=cmbRoomId.getSelectionModel().getSelectedItem().toString();
        try {
            RoomsDTO roomsDTO = roomsService.searchRooms(room_id);
            if (roomsDTO!=null) {
                lblRoomType.setText(roomsDTO.getRoomType());
            }else{
                new Alert(Alert.AlertType.ERROR,"Not Found").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }



    public void txtReservationIDOnAction(ActionEvent actionEvent) {
        String reservation_id = txtReservationId.getText();
        try {
            ReservationDTO reservationDTO = reservationService.searchReservation(reservation_id);
            if (reservationDTO!=null){
                lblRoomType.setText(reservationDTO.getRoom_type());
                txtStatus.setText(reservationDTO.getStatus());
                cmbStudentId.setAccessibleRoleDescription(reservationDTO.getStudent_id());
            }else{
                new Alert(Alert.AlertType.ERROR,"Not Found").show();
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}