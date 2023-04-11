package service.custom;

import dto.RoomsDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RoomsService{
    boolean saveRooms(RoomsDTO roomsDTO) throws SQLException, ClassNotFoundException;
    boolean updateRooms(RoomsDTO roomsDTO) throws SQLException, ClassNotFoundException;
    boolean deleteRooms(String id) throws SQLException, ClassNotFoundException;
    RoomsDTO searchRooms(String id) throws SQLException, ClassNotFoundException;
    ArrayList<RoomsDTO> getAllRooms() throws SQLException, ClassNotFoundException;
}
