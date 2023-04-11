package service.custom.impl;

import dao.custom.StudentDAO;
import dao.custom.impl.StudentDAOImpl;
import dto.StudentDTO;
import entity.Student;
import service.custom.StudentService;
import service.custom.util.Convertor;

import java.sql.SQLException;

public class StudentServiceImpl implements StudentService {

    private final StudentDAO studentDAO = new StudentDAOImpl();
    private  final Convertor convertor = new Convertor();
    @Override
    public boolean saveStudent(StudentDTO studentDTO) throws SQLException, ClassNotFoundException {
        return studentDAO.save(convertor.toStudent(studentDTO));
    }

    @Override
    public boolean updateStudent(StudentDTO studentDTO) throws SQLException, ClassNotFoundException {
        return studentDAO.update(convertor.toStudent(studentDTO));
    }

    @Override
    public boolean deleteStudent(String id) throws SQLException, ClassNotFoundException {
        return studentDAO.deleteByPk(id);
    }

    @Override
    public StudentDTO searchStudent(String id) throws SQLException, ClassNotFoundException {
        Student student = studentDAO.findByPk(id);
        StudentDTO studentDTO = new StudentDTO();
        if (student!=null) {
             studentDTO = convertor.fromStudent(student);
        }
        return studentDTO;
    }
}
