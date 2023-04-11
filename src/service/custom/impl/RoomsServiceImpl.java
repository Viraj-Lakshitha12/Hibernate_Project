package service.custom.impl;

import dao.custom.RoomsDAO;
import dao.custom.impl.RoomsDAOImpl;
import dto.RoomsDTO;
import entity.Rooms;
import service.custom.RoomsService;
import service.custom.util.Convertor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class RoomsServiceImpl implements RoomsService {
    RoomsDAO roomsDAO = new RoomsDAOImpl();
    Convertor convertor= new Convertor();

    @Override
    public boolean saveRooms(RoomsDTO roomsDTO) throws SQLException, ClassNotFoundException {
        return roomsDAO.save(convertor.toRooms(roomsDTO));
    }

    @Override
    public boolean updateRooms(RoomsDTO roomsDTO) throws SQLException, ClassNotFoundException {
        return roomsDAO.update(convertor.toRooms(roomsDTO));
    }

    @Override
    public boolean deleteRooms(String id) throws SQLException, ClassNotFoundException {
        return roomsDAO.deleteByPk(id);
    }

    @Override
    public RoomsDTO searchRooms(String id) throws SQLException, ClassNotFoundException {
        Rooms byPk = roomsDAO.findByPk(id);
        return convertor.fromRooms(byPk);
    }

    @Override
    public ArrayList<RoomsDTO> getAllRooms() throws SQLException, ClassNotFoundException {
        return (ArrayList<RoomsDTO>) roomsDAO.getAll().stream().map(customer -> convertor.fromRooms(customer)).collect(Collectors.toList());
    }
}
