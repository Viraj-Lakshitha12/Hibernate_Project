package util;

import entity.Reservation;
import entity.Rooms;
import entity.Student;
import entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {

    public static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    private FactoryConfiguration(){
        Configuration configuration = new Configuration().addAnnotatedClass(Student.class).addAnnotatedClass(Rooms.class)
                .addAnnotatedClass(Reservation.class).addAnnotatedClass(User.class);

        sessionFactory = configuration.buildSessionFactory();
    }
    public  static FactoryConfiguration getInstance(){
        return factoryConfiguration == null ? factoryConfiguration = new FactoryConfiguration() : factoryConfiguration;
    }
    public Session getSession(){
        return sessionFactory.openSession();
    }
}
