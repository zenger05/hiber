package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


public class Main2 {
    private static final SessionFactory sessionFactory = Util.getSessionFactory();
    static Long id = 1L;
    static Long id2 = 1L;
    static Long id3 = 2L;
    static User user1;
    static User user2;
    static User user3;
    static User user4;
    static Query<User> query = null;

    public static void main(String[] args) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                query = session.createQuery("FROM User WHERE id = " + id, User.class).setCacheable(true);
                System.out.println(session.contains(user1));
                user1 = query.getSingleResult();
                System.out.println(session.contains(user1));
                user2 = query.getSingleResult();
//                Object user3 = session.get(User.class, id3);
                transaction.commit();

            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
            }
        }
        try (Session session2 = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session2.beginTransaction();
                Query<User> query2 = session2.createQuery("FROM User WHERE id = " + id, User.class).setCacheable(true);
                user3 = query2.getSingleResult();
                user4 = query2.getSingleResult();
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
            }
        }
    }
}
