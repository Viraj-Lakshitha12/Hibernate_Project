package controller;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.base.IFXLabelFloatControl;
import dto.ReservationDTO;
import dto.RoomsDTO;
import dto.StudentDTO;
import entity.CartTM;
import entity.Reservation;
import entity.Rooms;
import entity.Student;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;
import service.custom.ReservationService;
import service.custom.RoomsService;
import service.custom.StudentService;
import service.custom.impl.ReservationServiceImpl;
import service.custom.impl.RoomsServiceImpl;
import service.custom.impl.StudentServiceImpl;
import service.custom.util.Convertor;
import util.FactoryConfiguration;
import util.Navigation;
import util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ReservationFormController {

    public JFXTextField txtReservationID;
    public JFXTextField txtStatus;
    public TableView tblView;

    public Label lblRoomId;
    public ComboBox cmbStudentId;
    public AnchorPane pane;

    private final RoomsService roomsService = new RoomsServiceImpl();
    private final StudentService studentService = new StudentServiceImpl();
    private final ReservationService reservationService = new ReservationServiceImpl();

    private final Convertor convertor = new Convertor();
    private final ObservableList observableList = FXCollections.observableArrayList();

    public ComboBox cmbRoomType;
    public Label lblRoomType;
    public ComboBox cmbRoomId;
    public Label lblReservationId
            ;
    public Label lblDate;

    public TableColumn colDate;
    public JFXTextField txtReservationId;
    public JFXTextField txtAddress;
    public JFXTextField txtContactNo;
    public JFXTextField txtName;
    public JFXTextField txtType;
    public JFXTextField txtKeyMoney;
    public JFXTextField txtQty;

    public ComboBox paymentStatus;
    public TableColumn colRoomTypeId;
    public TableColumn colType;
    public TableColumn colKeyMoney;
    public TableColumn colPaymentStatus;
    public TableColumn colStudentID;
    public TableColumn colRoomId;
    public TableColumn colReservationId;

    public void initialize(){
        loadRoomTypes();
        loadStudentId();
        generateReservationId();
        loadLocalDate();
        loadPaymentStatus();
        //loadAllReservationDetails();
    }

    private void loadPaymentStatus() {
        ObservableList observableList1 = FXCollections.observableArrayList();
        observableList1.add("Paid ");
        observableList1.add("Paid Later");
        paymentStatus.setItems(observableList1);

    }

    private void loadAllReservationDetails() {

        colReservationId.setCellValueFactory(new PropertyValueFactory("reservation_id"));
        colType.setCellValueFactory(new PropertyValueFactory("room_type"));
        colPaymentStatus.setCellValueFactory(new PropertyValueFactory("status"));
        colDate.setCellValueFactory(new PropertyValueFactory("date"));
        colStudentID.setCellValueFactory(new PropertyValueFactory("student_id"));
        colRoomId.setCellValueFactory(new PropertyValueFactory("room_id"));

        try {
            ArrayList<ReservationDTO> allReservation = reservationService.getAllReservation();
            ObservableList observableList1 = FXCollections.observableArrayList();
            for (ReservationDTO a :allReservation) {
                observableList1.add(a);
                tblView.setItems(observableList1);
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
        String s = reservationService.GenerateReservationId();
        lblReservationId.setText(s);
    }

    private void loadStudentId() {
        try {
            ArrayList<StudentDTO> allRooms = studentService.getAllRooms();
            ObservableList observableList = FXCollections.observableArrayList();
            for (StudentDTO s:allRooms) {
                observableList.add(s.getStudent_id());
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
        Stage stage1 = (Stage) pane.getScene().getWindow();
        stage1.close();
        URL resource = getClass().getResource("/view/DashBoard.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage stage2 = new Stage();
        Scene scene = new Scene(load);
        stage2.setScene(scene);
        stage2.show();
    }


    public void btnAddOnAction(ActionEvent actionEvent) {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();


        String reservation_id = lblReservationId.getText();
        LocalDate date = LocalDate.parse(lblDate.getText());


        String student_id = cmbStudentId.getSelectionModel().getSelectedItem().toString();


        String room_id = cmbRoomId.getSelectionModel().getSelectedItem().toString();
        String status = paymentStatus.getSelectionModel().getSelectedItem().toString();

        try {
            RoomsDTO roomsDTO = roomsService.searchRooms(room_id);
            Rooms rooms = convertor.toRooms(roomsDTO);
            rooms.setQyt(roomsDTO.getQty()-1);

            StudentDTO studentDTO = studentService.searchStudent(student_id);
            Student student = convertor.toStudent(studentDTO);

            Reservation reservation = new Reservation();
            reservation.setRes_id(reservation_id);
            reservation.setDate(date);
            reservation.setStatus(status);
            reservation.setRoom(rooms);
            reservation.setStudent(student);

            session.save(reservation);
            session.update(rooms);
            transaction.commit();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        txtReservationIDOnAction(actionEvent);
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
//        String reservation_id = lblReservationId.getText();
//        LocalDate date = LocalDate.parse(lblDate.getText());
//
//
//        String student_id = cmbStudentId.getSelectionModel().getSelectedItem().toString();
//        String student_name = txtName.getText();
//        String address = txtAddress.getText();
//        String contact = txtContactNo.getText();
//
//
//        String room_id = cmbRoomId.getSelectionModel().getSelectedItem().toString();
//        String room_type = txtType.getText();
//        String status = paymentStatus.getSelectionModel().getSelectedItem().toString();
//
//
//        ReservationDTO reservationDTO = new ReservationDTO(reservation_id,date,student_id,room_id,room_type, status);
//        try {
//            boolean b = reservationService.updateReservation(reservationDTO);
//            if (b){
//                new Alert(Alert.AlertType.CONFIRMATION, "Successfully Update !").show();
//                Navigation.navigate(Routes.RESERVATION,pane);
//            }else {
//                new Alert(Alert.AlertType.ERROR, "Added Fail!").show();
//
//            }
//        } catch (SQLException | ClassNotFoundException | IOException e) {
//            throw new RuntimeException(e);
//        }
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
        String s = cmbStudentId.getSelectionModel().getSelectedItem().toString();
        try {
            StudentDTO studentDTO = studentService.searchStudent(s);
            txtName.setText(studentDTO.getName());
            txtAddress.setText(studentDTO.getAddress());
            txtContactNo.setText(studentDTO.getContact_no());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void cmbRoomIdOnAction(ActionEvent actionEvent) {
        String room_id=cmbRoomId.getSelectionModel().getSelectedItem().toString();
        try {
            RoomsDTO roomsDTO = roomsService.searchRooms(room_id);
            if (roomsDTO!=null) {
                txtType.setText(roomsDTO.getRoomType());
                txtKeyMoney.setText(String.valueOf(roomsDTO.getKey_money()));
                txtQty.setText(String.valueOf(roomsDTO.getQty()));
            }else{
                new Alert(Alert.AlertType.ERROR,"Not Found").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }



    public void txtReservationIDOnAction(ActionEvent actionEvent) {
//        String reservation_id = txtReservationId.getText();
//        try {
//            ReservationDTO reservationDTO = reservationService.searchReservation(reservation_id);
//            if (reservationDTO!=null){
//                lblRoomType.setText(reservationDTO.getRoom_type());
//                txtStatus.setText(reservationDTO.getStatus());
//                cmbStudentId.setAccessibleRoleDescription(reservationDTO.getStudent_id());
//            }else{
//                new Alert(Alert.AlertType.ERROR,"Not Found").show();
//            }
//
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
    }
}