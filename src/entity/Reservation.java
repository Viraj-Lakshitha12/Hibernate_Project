package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Reservation {
    @Id
    String reservation_id;
    String date;
    String student_id;
    String room_id;
    String room_type;
    String status;

    public Reservation() {
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public Reservation(String reservation_id, String date, String student_id, String room_id, String room_type, String status) {
        this.reservation_id = reservation_id;
        this.date = date;
        this.student_id = student_id;
        this.room_id = room_id;
        this.room_type = room_type;
        this.status = status;
    }

    public String getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(String reservation_id) {
        this.reservation_id = reservation_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getRoom_type() {
        return room_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservation_id='" + reservation_id + '\'' +
                ", date='" + date + '\'' +
                ", student_id='" + student_id + '\'' +
                ", room_id='" + room_id + '\'' +
                ", room_type='" + room_type + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
