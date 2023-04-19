package service.custom.impl;

import dao.QueryDAO;
import dao.custom.impl.QueryDAOImpl;
import dto.CustomDTO;
import service.custom.KeyMoneyService;
import service.custom.ReservationService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class KeyMoneyServiceImpl implements KeyMoneyService {
    private final ReservationService reservationService = new ReservationServiceImpl();
    private final QueryDAO queryDAO = new QueryDAOImpl();

    @Override
    public ArrayList<CustomDTO> getAllPendingKeyMoney() throws Exception {
        List<Object[]> objects = queryDAO.getAllPendingKeyMoney();
        ArrayList<CustomDTO> arrayList = new ArrayList<>();

        for (Object[] o:objects) {
            arrayList.add(new CustomDTO((String) o[0],(String) o[1],(String) o[2],(String) o[3],(String) o[4],(double) o[5],(String) o[6],(LocalDate) o[7]));
        }

        return arrayList;
    }

    @Override
    public boolean updateReservationUsingId(String id, String status) throws Exception {
        return reservationService.updateUsingId(id,status);
    }
}
