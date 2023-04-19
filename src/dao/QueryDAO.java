package dao;

import java.util.List;

public interface QueryDAO {
    List<Object[]> getAllPendingKeyMoney() throws Exception;

}
