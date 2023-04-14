package dao.custom;

import dao.CrudDAO;
import entity.Rooms;
import service.custom.RoomsService;

public interface RoomsDAO extends CrudDAO<Rooms,String> {
    boolean reservationQtyUpdate(Rooms rooms);
}
