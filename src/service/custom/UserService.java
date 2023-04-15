package service.custom;

import dto.StudentDTO;
import dto.UserDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserService {
    boolean saveUsers(UserDTO userDTO) throws SQLException, ClassNotFoundException;
    boolean updateUsers(UserDTO userDTO) throws SQLException, ClassNotFoundException;
    boolean deleteUsers(String id) throws SQLException, ClassNotFoundException;
    UserDTO  searchUsers(String id) throws SQLException, ClassNotFoundException;
    ArrayList<UserDTO> getAllUsers() throws SQLException, ClassNotFoundException;
}
