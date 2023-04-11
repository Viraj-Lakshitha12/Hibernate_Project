package entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Rooms {
    @Id
    String roomId;
    String room_type;
    double key_money;
    int qty;

    public Rooms(String roomId, String room_type, double key_money, int qty) {
        this.roomId = roomId;
        this.room_type = room_type;
        this.key_money = key_money;
        this.qty = qty;
    }

    public Rooms() {
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoom_type() {
        return room_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public double getKey_money() {
        return key_money;
    }

    public void setKey_money(double key_money) {
        this.key_money = key_money;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "Rooms{" +
                "roomId='" + roomId + '\'' +
                ", room_type='" + room_type + '\'' +
                ", key_money=" + key_money +
                ", qty=" + qty +
                '}';
    }
}
