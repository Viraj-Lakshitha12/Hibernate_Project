package service.custom.util;

import dto.ReservationDTO;
import dto.RoomsDTO;
import dto.StudentDTO;
import dto.UserDTO;
import entity.Reservation;
import entity.Rooms;
import entity.Student;
import entity.User;

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

    public Reservation toReservation(ReservationDTO reservationDTO){
        return new Reservation(reservationDTO.getReservation_id(),reservationDTO.getDate(),new Student(reservationDTO.getStudent_id()),new Rooms(reservationDTO.getRoom_id()),reservationDTO.getStatus());
    }

    public UserDTO fromUsers(User user){
        return new UserDTO(user.getUserId(),user.getName(), user.getTelNo(), user.getEmail(), user.getUserName(), user.getPassword());
    }

    public User toUsers(UserDTO userDTO){
        return new User(userDTO.getUserId(),userDTO.getName(), userDTO.getTelNo(), userDTO.getEmail(), userDTO.getUserName(), userDTO.getPassword());
    }
}
