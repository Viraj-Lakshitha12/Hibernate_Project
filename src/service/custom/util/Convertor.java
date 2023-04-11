package service.custom.util;

import dto.RoomsDTO;
import dto.StudentDTO;
import entity.Rooms;
import entity.Student;

public class Convertor {
    public RoomsDTO fromRooms(Rooms rooms){
        return new RoomsDTO(rooms.getRoomId(),rooms.getRoom_type(),rooms.getKey_money(),rooms.getQty());
    }
    public Rooms toRooms(RoomsDTO roomsDTO){
        return new Rooms(roomsDTO.getRoomId(),roomsDTO.getRoomType(),roomsDTO.getKey_money(),roomsDTO.getQty());
    }

    public StudentDTO fromStudent(Student student){
        return new StudentDTO(student.getId(),student.getName(),student.getAddress(),student.getContact_Number(),student.getDate_of_birthday(),student.getGender());
    }
    public  Student toStudent(StudentDTO studentDTO){
        return new Student(studentDTO.getId(),studentDTO.getName(),studentDTO.getAddress(),studentDTO.getContact_Number(),studentDTO.getDate_of_birthday(),studentDTO.getGender());
    }
}
