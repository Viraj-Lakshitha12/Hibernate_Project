package service.custom.util;

import dto.ReservationDTO;
import dto.RoomsDTO;
import dto.StudentDTO;
import entity.Reservation;
import entity.Rooms;
import entity.Student;

public class Convertor {
    public RoomsDTO fromRooms(Rooms rooms){
        return new RoomsDTO(rooms.getRoomId(),rooms.getRoom_type(),rooms.getKey_money(),rooms.getQyt());
    }
    public Rooms toRooms(RoomsDTO roomsDTO){
        return new Rooms(roomsDTO.getRoomId(),roomsDTO.getRoomType(),roomsDTO.getKey_money(),roomsDTO.getQty());
    }

    public StudentDTO fromStudent(Student student){
        return new StudentDTO(student.getId(),student.getName(),student.getAddress(),student.getNumber(),student.getBod(),student.getGender());
    }
    public  Student toStudent(StudentDTO studentDTO){
        return new Student(studentDTO.getStudent_id(),studentDTO.getName(),studentDTO.getAddress(),studentDTO.getContact_no(),studentDTO.getDob(),studentDTO.getGender());
    }

    public ReservationDTO fromReservation(Reservation reservation){
        String id = reservation.getStudent().getId();
        String roomId = reservation.getRoom().getRoomId();
        String room_type = reservation.getRoom().getRoom_type();
        return new ReservationDTO(reservation.getRes_id(),reservation.getDate(),id,roomId,reservation.getStatus());
    }
}
