package entity;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class Student {
    @Id
    private String id;
    private String name;
    private String address;
    private String number;
    private LocalDate bod;
    private String gender;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    List<Reservation> reservationList = new ArrayList<>();

    public Student() {
    }
    public Student(String student_id, String student_name, String address, String contact) {

    }

    public Student(String id, String name, String address, String number, LocalDate bod, String gender) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.number = number;
        this.bod = bod;
        this.gender = gender;
    }


    public Student(String student_id) {
        this.id = id;
    }
}
