package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.Audi;
import jm.task.core.jdbc.model.Bmw;
import jm.task.core.jdbc.model.Car;
import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cache.ehcache.internal.EhcacheRegionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String PASSWORD = "Qazpwsl123";
    private static final String USERNAME = "root";
    private static final String URL = "jdbc:mysql://localhost:3306/new";

    static SessionFactory sessionFactory = null;

    private Util() {}

    public static Connection open() {
        try {
            return DriverManager.getConnection(
                URL, USERNAME, PASSWORD
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/new");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "example");
                //settings.put(Environment.GENERATE_STATISTICS, true);
                settings.put(Environment.USE_QUERY_CACHE, true);
                EhcacheRegionFactory er = new EhcacheRegionFactory();
                settings.put(Environment.CACHE_REGION_FACTORY, er);
               // settings.put(Environment.USE_SECOND_LEVEL_CACHE, true);
                settings.put(Environment.HBM2DDL_AUTO, "create");

                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                configuration.setProperties(settings);

                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Car.class);
                configuration.addAnnotatedClass(Audi.class);
                configuration.addAnnotatedClass(Bmw.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

}
