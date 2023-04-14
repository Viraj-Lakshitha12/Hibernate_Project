package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Reservation {
    @Id
    private String res_id;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name="id")
    private Student student;

    @ManyToOne
    @JoinColumn(name="roomId")
    private Rooms room;

    private String status;




//    public Reservation(String reservation_id, LocalDate date, String status, String room_id, String student_id) {
//        this.res_id = reservation_id;
//        this.date = date;
//        this.student = student;
//        this.room = room;
//        this.status = status;
//    }
}
