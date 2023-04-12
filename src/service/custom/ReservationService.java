package service.custom;

import dto.ReservationDTO;
import dto.RoomsDTO;
import entity.Reservation;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ReservationService{
    boolean saveRooms(ReservationDTO reservationDTO) throws SQLException, ClassNotFoundException;
    boolean updateRooms(ReservationDTO reservationDTO) throws SQLException, ClassNotFoundException;
    boolean deleteRooms(String id) throws SQLException, ClassNotFoundException;
    RoomsDTO searchRooms(String id) throws SQLException, ClassNotFoundException;
    ArrayList<ReservationDTO> getAllRooms() throws SQLException, ClassNotFoundException;
}
