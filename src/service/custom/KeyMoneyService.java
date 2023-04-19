package service.custom;

import dto.CustomDTO;

import java.util.ArrayList;

public interface KeyMoneyService {
    ArrayList<CustomDTO> getAllPendingKeyMoney() throws Exception;
    boolean updateReservationUsingId(String id, String status) throws Exception;

}
