package service.custom.util;

import dto.RoomsDTO;
import entity.Rooms;

public class Convertor {
    public RoomsDTO fromRooms(Rooms rooms){
        return new RoomsDTO(rooms.getRoomId(),rooms.getRoom_type(),rooms.getKey_money(),rooms.getQty());
    }
    public Rooms toRooms(RoomsDTO roomsDTO){
        return new Rooms(roomsDTO.getRoomId(),roomsDTO.getRoomType(),roomsDTO.getKey_money(),roomsDTO.getQty());
    }
}
