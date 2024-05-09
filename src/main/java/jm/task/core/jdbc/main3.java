package jm.task.core.jdbc;

import jm.task.core.jdbc.model.Audi;
import jm.task.core.jdbc.model.Bmw;
import jm.task.core.jdbc.model.Car;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class main3 {
    public static void main(String[] args) {
//        Car car = new Audi("BMW", "V5");
//        Car car1 = new Bmw("Gigi","V8");
        Audi car = new Audi("BMW", "V5");
        Bmw car1 = new Bmw("Gigi","V8");
        SessionFactory sessionFactory = Util.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(car);
        session.save(car1);
        transaction.commit();
        session.close();
    }
}
