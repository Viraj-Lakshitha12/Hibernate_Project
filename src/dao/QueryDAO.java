package dao;

import dto.CustomDTO;

import java.util.ArrayList;
import java.util.List;

public interface QueryDAO {
    List<Object[]> getAllPendingKeyMoney() throws Exception;
    List findDetails(String id);
}
