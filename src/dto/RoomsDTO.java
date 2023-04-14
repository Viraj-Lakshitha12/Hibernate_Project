package dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RoomsDTO {
    String roomId;
    String roomType;
    double key_money;
    int qty;


}
