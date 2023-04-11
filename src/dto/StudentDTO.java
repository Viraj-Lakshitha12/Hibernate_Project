package dto;

public class StudentDTO {
    String id;
    String name;
    String address;
    String contact_Number;
    String date_of_birthday;
    String gender;

    public StudentDTO() {
    }

    public StudentDTO(String id, String name, String address, String contact_Number, String date_of_birthday, String gender) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact_Number = contact_Number;
        this.date_of_birthday = date_of_birthday;
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact_Number() {
        return contact_Number;
    }

    public void setContact_Number(String contact_Number) {
        this.contact_Number = contact_Number;
    }

    public String getDate_of_birthday() {
        return date_of_birthday;
    }

    public void setDate_of_birthday(String date_of_birthday) {
        this.date_of_birthday = date_of_birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact_Number=" + contact_Number +
                ", date_of_birthday='" + date_of_birthday + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
