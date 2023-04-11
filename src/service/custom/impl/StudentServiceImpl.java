package service.custom.impl;

import dao.custom.StudentDAO;
import dao.custom.impl.StudentDAOImpl;
import dto.StudentDTO;
import service.custom.StudentService;
public class StudentServiceImpl implements StudentService {

    private final StudentDAO studentDAO = new StudentDAOImpl();
    @Override
    public boolean saveStudent(StudentDTO studentDTO) {
        return false;
    }

    @Override
    public boolean updateStudent(StudentDTO studentDTO) {
        return false;
    }

    @Override
    public boolean deleteStudent(String id) {
        return false;
    }

    @Override
    public StudentDTO searchStudent(String id) {
        return null;
    }
}
