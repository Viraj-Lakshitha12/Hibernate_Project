package service.custom;

import dto.StudentDTO;

import java.sql.SQLException;

public interface StudentService {
    boolean saveStudent(StudentDTO studentDTO) throws SQLException, ClassNotFoundException;
    boolean updateStudent(StudentDTO studentDTO) throws SQLException, ClassNotFoundException;
    boolean deleteStudent(String id) throws SQLException, ClassNotFoundException;
    StudentDTO searchStudent(String id) throws SQLException, ClassNotFoundException;
}
