package dto;

public class ReservationDTO {
    String reservation_id;
    String date;
    String student_id;
    String room_type;
    String status;


    public ReservationDTO() {
    }

    public ReservationDTO(String reservation_id, String date, String student_id, String room_type, String status) {
        this.reservation_id = reservation_id;
        this.date = date;
        this.student_id = student_id;
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

}
