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
    public ArrayList<ReservationDTO> getAllReservation() throws SQLException, ClassNotFoundException {
        return (ArrayList<ReservationDTO>) reservationDAO.getAll().stream().map(reservation -> convertor.fromReservation(reservation)).collect(Collectors.toList());
    }
    @Override
    public boolean saveReservation(ReservationDTO reservationDTO) throws SQLException, ClassNotFoundException {
        return true; //reservationDAO.save(convertor.toReservation(reservationDTO));
    }
    @Override
    public boolean updateReservation(ReservationDTO reservationDTO) throws SQLException, ClassNotFoundException {
        return true;// reservationDAO.update(convertor.toReservation(reservationDTO));
    }
    @Override
    public boolean deleteReservation(String id) throws SQLException, ClassNotFoundException {
        return reservationDAO.deleteByPk(id);
    }
    @Override
    public ReservationDTO searchReservation(String id) throws SQLException, ClassNotFoundException {
        Reservation reservation = reservationDAO.findByPk(id);
        return convertor.fromReservation(reservation);
    }
}
