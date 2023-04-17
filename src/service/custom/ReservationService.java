package service.custom;

import dto.ReservationDTO;
import dto.RoomsDTO;
import entity.Reservation;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ReservationService{
    boolean saveReservation(ReservationDTO reservationDTO) throws SQLException, ClassNotFoundException;
    boolean updateReservation(ReservationDTO reservationDTO) throws SQLException, ClassNotFoundException;
    boolean deleteReservation(String id) throws SQLException, ClassNotFoundException;
    ReservationDTO searchReservation(String id) throws SQLException, ClassNotFoundException;
    ArrayList<ReservationDTO> getAllReservation() throws SQLException, ClassNotFoundException;

    String GenerateReservationId();
}
