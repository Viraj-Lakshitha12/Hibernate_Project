package dao.custom;

import dao.CrudDAO;
import dto.ReservationDTO;
import entity.Reservation;

public interface ReservationDAO extends CrudDAO<Reservation,String> {
    String GenerateReservationId();
}
