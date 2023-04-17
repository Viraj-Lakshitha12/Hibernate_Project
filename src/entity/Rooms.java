package entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class Rooms {
    @Id
    String roomId;
    String room_type;
    double key_money;
    private int qyt;
    @OneToMany(mappedBy = "room", targetEntity = Reservation.class)
    List<Reservation> reservationList = new ArrayList<>();

    public Rooms(String roomId, String room_type, double key_money, int qyt) {
        this.roomId = roomId;
        this.room_type = room_type;
        this.key_money = key_money;
        this.qyt = qyt;
    }


    public Rooms(String room_id) {
        this.roomId = room_id;
    }
}
