package service.custom.impl;

import dao.custom.ReservationDAO;
import dao.custom.impl.ReservationDAOImpl;
import dto.ReservationDTO;
import dto.RoomsDTO;
import entity.Reservation;
import service.custom.ReservationService;
import service.custom.util.Convertor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ReservationServiceImpl implements ReservationService {

    private final ReservationDAO reservationDAO = new ReservationDAOImpl();
    private final Convertor convertor = new Convertor();

    @Override
    public boolean saveRooms(ReservationDTO reservationDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateRooms(ReservationDTO reservationDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteRooms(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public RoomsDTO searchRooms(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<ReservationDTO> getAllRooms() throws SQLException, ClassNotFoundException {
        return (ArrayList<ReservationDTO>) reservationDAO.getAll().stream().map(reservation -> convertor.fromReservation(reservation)).collect(Collectors.toList());

    }

}
