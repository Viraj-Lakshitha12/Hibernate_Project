package dto;

import entity.Rooms;
import entity.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class ReservationDTO {
    @Id
    String reservation_id;
    LocalDate date;
    String student_id;
    String room_id;
    String room_type;
    String status;


}
