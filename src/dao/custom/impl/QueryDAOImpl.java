package dao.custom.impl;

import dao.QueryDAO;
import dto.CustomDTO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public List<Object[]> getAllPendingKeyMoney() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("SELECT r.res_id,s.id,s.name,ro.roomId,ro.room_type,ro.key_money,r.status,r.date FROM Reservation r JOIN Student s ON r.student = s.id JOIN Rooms ro ON r.room =ro.roomId WHERE r.status= : y");


        List<Object[]> list = query.setParameter("y", "Paid Later").list();

        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public List findDetails(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("SELECT r.res_id,s.id,s.name,ro.roomId,ro.room_type,ro.key_money,r.status,r.date FROM Reservation r JOIN Student s ON r.student = s.id JOIN Rooms ro ON r.room =ro.roomId WHERE r.res_id= : y");
        List list = query.setParameter("y", id).list();

        transaction.commit();
        session.close();
        return list;
    }
}
