package dto;

public class RoomsDTO {
    String roomId;
    String roomType;
    double key_money;
    int qty;

    public RoomsDTO() {
    }

    public RoomsDTO(String roomId, String roomType, double key_money, int qty) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.key_money = key_money;
        this.qty = qty;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
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
        return "RoomsDTO{" +
                "roomId='" + roomId + '\'' +
                ", roomType='" + roomType + '\'' +
                ", key_money=" + key_money +
                ", qty=" + qty +
                '}';
    }
}
