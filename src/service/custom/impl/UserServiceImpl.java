package service.custom.impl;

import dao.custom.UserDAO;
import dao.custom.impl.UserDAOImpl;
import dto.StudentDTO;
import dto.UserDTO;
import entity.User;
import service.custom.UserService;
import service.custom.util.Convertor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private final UserDAO userDAO = new UserDAOImpl();
    private final Convertor convertor = new Convertor();
    @Override
    public boolean saveUsers(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        return userDAO.save(convertor.toUsers(userDTO));
    }

    @Override
    public boolean updateUsers(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        return userDAO.update(convertor.toUsers(userDTO));
    }

    @Override
    public boolean deleteUsers(String id) throws SQLException, ClassNotFoundException {
        return userDAO.deleteByPk(id);
    }

    @Override
    public UserDTO searchUsers(String id) throws SQLException, ClassNotFoundException {
        User byPk = userDAO.findByPk(id);
        return convertor.fromUsers(byPk);
    }

    @Override
    public ArrayList<UserDTO> getAllUsers() throws SQLException, ClassNotFoundException {
        return (ArrayList<UserDTO>) userDAO.getAll().stream().map(user -> convertor.fromUsers(user)).collect(Collectors.toList());

    }
}
