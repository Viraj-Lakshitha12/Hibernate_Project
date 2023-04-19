package dao.custom.impl;

import dao.custom.ReservationDAO;
import dto.ReservationDTO;
import entity.Reservation;
import entity.Rooms;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {
    @Override
    public boolean save(Reservation reservation) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(reservation);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean update(Reservation reservation) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(reservation);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean deleteByPk(String pk) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Reservation reservation = session.get(Reservation.class, pk);
            session.delete(reservation);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public Reservation findByPk(String pk) throws SQLException, ClassNotFoundException {
//        Session session = FactoryConfiguration.getInstance().getSession();
//        Transaction transaction = session.beginTransaction();
//
//        try {
//            ReservationDTO reservation = session.get(Reservation.class, pk);
//            transaction.commit();
//            return reservation;
//        } catch (Exception e) {
//            transaction.rollback();
//        } finally {
//            session.close();
//        }
        return null;
    }

    @Override
    public ArrayList<Reservation> getAll() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            String s="FROM Reservation ";
            Query query = session.createQuery(s);
            List<Reservation> list = query.list();

            transaction.commit();
            return new ArrayList<>(list);
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public String GenerateReservationId() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List resultList = session.createSQLQuery("SELECT * FROM Reservation ORDER BY res_id DESC LIMIT 1").addEntity(Reservation.class).getResultList();
        transaction.commit();
        session.close();

        return resultList.size()==0?"RS000":((Reservation)resultList.get(0)).getRes_id();
    }
}
