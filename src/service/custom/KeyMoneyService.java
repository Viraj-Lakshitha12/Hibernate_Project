package service.custom;

import dto.CustomDTO;

import java.util.ArrayList;
import java.util.List;

public interface KeyMoneyService {
    ArrayList<CustomDTO> getAllPendingKeyMoney() throws Exception;
    boolean updateReservationUsingId(String id, String status) throws Exception;
    List findDetails(String id);
}
