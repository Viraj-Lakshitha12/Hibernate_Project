package service.custom;

import dto.StudentDTO;

public interface StudentService {
    boolean saveStudent(StudentDTO studentDTO);
    boolean updateStudent(StudentDTO studentDTO);
    boolean deleteStudent(String id);
    StudentDTO searchStudent(String id);
}
